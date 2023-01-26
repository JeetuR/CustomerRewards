package com.store.customer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.customer.config.RewardsConfig;
import com.store.customer.dao.CustomerDAO;
import com.store.customer.dao.CustomerTransactionDAO;
import com.store.customer.entity.CustomerMasterTbl;
import com.store.customer.entity.TransactionsTbl;
import com.store.customer.exception.ErrorMessages;
import com.store.customer.exception.RewardsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RewardsService {

	@Autowired
	private RewardsConfig rewardsConfig;

	@Autowired
	private CustomerTransactionDAO custTransDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	ErrorMessages errors;

	private SimpleDateFormat monthYearFmt = new SimpleDateFormat("yyyy_MMM");

	public Double computeRewards(Integer customerID) throws RewardsException {

		Optional<CustomerMasterTbl> customerOptional = customerDAO.findById(customerID);

		if (customerOptional.isEmpty()) {
			log.error(errors.CUSTOMER_NOT_FOUND + " : " + customerID);
			throw new RewardsException(errors.CUSTOMER_NOT_FOUND);
		}

		CustomerMasterTbl customer = new CustomerMasterTbl();
		customer.setId(customerID);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1 * rewardsConfig.TRANSACTIONS_PERIOD);

		List<TransactionsTbl> customerTransactions = custTransDAO
				.findAllByCustomerIdAndCreatedOnGreaterThanEqual(customer, calendar.getTime());
		if (customerTransactions == null || customerTransactions.size() == 0) {
			log.error(errors.NO_ELIGIBLE_TRANSACTIONS + "  for customer " + customerID);
			throw new RewardsException(errors.NO_ELIGIBLE_TRANSACTIONS);
		}

		Map<String, Collection<TransactionsTbl>> transByMonth = customerTransactions.stream()
				.collect(Collectors.groupingBy(tran -> monthYearFmt.format(tran.getCreatedOn()),
						Collectors.toCollection(ArrayList::new)));
		double rewardPoints = 0.0;

		for (Map.Entry<String, Collection<TransactionsTbl>> currentEntry : transByMonth
				.entrySet()) {
			Collection<TransactionsTbl> trans4YrMon = currentEntry.getValue();
			Optional<Double> totalTransAmtByYrMon = trans4YrMon.stream()
					.map(TransactionsTbl::getAmount).reduce(Double::sum);

			if (totalTransAmtByYrMon.isPresent()) {
				double amount100 = totalTransAmtByYrMon.get() - rewardsConfig.AMOUNT_100;
				amount100 = Math.max(0, amount100);
				rewardPoints += amount100 * rewardsConfig.POINTS_100_ABOVE;

				double amount50 = totalTransAmtByYrMon.get() - amount100 - rewardsConfig.AMOUNT_50;
				amount50 = Math.max(0, amount50);
				rewardPoints += amount50 * rewardsConfig.POINTS_50_TO_100;
			}
		}

		return rewardPoints;
	}

}
