/**
 * 
 */
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

/**
 * @author jroddam
 *
 */
@Slf4j
@Service
public class RewardsService {

	@Autowired
	private RewardsConfig rewardsConfig;
	
	@Autowired
	private CustomerTransactionDAO custTransDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	ErrorMessages errors = new ErrorMessages();

	
	private static SimpleDateFormat monthYearFmt = new SimpleDateFormat("yyyy_MMM");
	
	public Double computeRewardsFor(Integer customerID) throws RewardsException {
		log.debug("Computing rewards for : " + customerID);
		// look up customer 
		log.info("Checking if customer present in the system..." );
		Optional<CustomerMasterTbl> customerOptional = customerDAO.findById(customerID);
		
		if (customerOptional.isEmpty()) {
			throw new RewardsException(errors.CUSTOMER_NOT_FOUND);		
		}
		
		CustomerMasterTbl customer = new CustomerMasterTbl();
		customer.setId(customerID);

		// limit transaction period
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1 * rewardsConfig.TRANSACTIONS_PERIOD);
		
		// fetch all transactions for the given customer over the defined period.
		log.info("Fetching transactions for customer " + customerID + " within the transaction period...");
		List<TransactionsTbl> customerTransactions = custTransDAO.findAllByCustomerIdAndCreatedOnGreaterThanEqual(customer, calendar.getTime());
		if(customerTransactions == null || customerTransactions.size() == 0) {
			throw new RewardsException(errors.NO_ELIGIBLE_TRANSACTIONS);
		}
		log.info("Total transactions for customer " + customerID + " within the transaction period are : " +  customerTransactions.size());
		
		// group transactions by year_month
		log.debug("Grouping transaction  by year and month....");
		Map<String, Collection<TransactionsTbl>> transByMonth = customerTransactions.stream().collect(Collectors.groupingBy(tran -> monthYearFmt.format(tran.getCreatedOn()), Collectors.toCollection(ArrayList::new) ));
		log.info("Transactions grouped by month year " + transByMonth.size());
		double rewardPoints = 0.0;
		
		// loop thru the transactions for every year month and compute the reward points
		for(Map.Entry<String, Collection<TransactionsTbl>> currentEntry : transByMonth.entrySet()) {
			Collection<TransactionsTbl> trans4YrMon = currentEntry.getValue();
			// sum the amount for all transactions in year month 
			log.debug("Sum up amount of all transactions for year month : " + currentEntry.getKey());
			Optional<Double> totalTransAmtByYrMon = trans4YrMon.stream().map(TransactionsTbl::getAmount).reduce(Double::sum);
			
			if (totalTransAmtByYrMon.isPresent()) {
				log.debug("Apply points for " + rewardsConfig.AMOUNT_100);
				// for amount > 100
				double amount100 = totalTransAmtByYrMon.get() - rewardsConfig.AMOUNT_100;
				amount100 = Math.max(0, amount100);
				rewardPoints += amount100 * rewardsConfig.POINTS_100_ABOVE; 
				log.debug("Points after applying for " + rewardsConfig.AMOUNT_100 + "  : " + rewardPoints);
				
				log.debug("Apply points for " + rewardsConfig.AMOUNT_50);
				// for amount 50 to 100
				double amount50 = totalTransAmtByYrMon.get() - amount100 - rewardsConfig.AMOUNT_50;
				amount50 = Math.max(0, amount50);
				rewardPoints += amount50 * rewardsConfig.POINTS_50_TO_100;
				log.debug("Points after applying for " + rewardsConfig.AMOUNT_50 + "  : " + rewardPoints);
			}
		}
		
		log.info("Reward points computed for customer ID " + customerID + "  are : " + rewardPoints );
		return rewardPoints;
	}
	
	
}
