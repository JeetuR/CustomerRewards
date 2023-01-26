package com.store.customer.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.store.customer.config.RewardsConfig;
import com.store.customer.dao.CustomerDAO;
import com.store.customer.dao.CustomerTransactionDAO;
import com.store.customer.entity.CustomerMasterTbl;
import com.store.customer.entity.TransactionsTbl;
import com.store.customer.exception.ErrorMessages;
import com.store.customer.exception.RewardsException;
import com.store.customer.service.RewardsService;

@ExtendWith(MockitoExtension.class)
public class ServiceMockTests {

	@InjectMocks
	RewardsService mockRewardsService;

	@Mock
	CustomerDAO customerDAO;

	@Mock
	CustomerTransactionDAO custTransDAO;

	@Mock
	RewardsConfig rewardsConfig;

	@Mock
	ErrorMessages errorMessages;

	Optional<CustomerMasterTbl> custTbl;

	Integer custID = 301;

	List<TransactionsTbl> custTrans = new ArrayList<>();

	@Test
	public void getRewardsForValidCustomer() {

		when(customerDAO.findById(custID)).thenReturn(custTbl);
		when(custTransDAO.findAllByCustomerIdAndCreatedOnGreaterThanEqual(any(), any())).thenReturn(custTrans);

		Double rewards = mockRewardsService.computeRewards(custID);

		assertEquals(rewards, 273.98);

	}

	@Test
	public void lookupInvalidCustomer() {

		Integer invalidCustID = 999;

		lenient().when(customerDAO.findById(custID)).thenReturn(custTbl);

		RewardsException rexp = assertThrows(RewardsException.class,
				() -> mockRewardsService.computeRewards(invalidCustID), "Given Customer ID Not Found");

		assertTrue(rexp.getErrorMessage().contains("Given Customer ID Not Found"));

	}

	private Date parseDate(String dateStr) {
		DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return dateFmt.parse(dateStr);
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return null;
	}

	@BeforeEach
	public void loadData() {
		ReflectionTestUtils.setField(rewardsConfig, "AMOUNT_100", 100);
		ReflectionTestUtils.setField(rewardsConfig, "AMOUNT_50", 50);
		ReflectionTestUtils.setField(rewardsConfig, "POINTS_50_TO_100", 1);
		ReflectionTestUtils.setField(rewardsConfig, "POINTS_100_ABOVE", 2);
		ReflectionTestUtils.setField(rewardsConfig, "TRANSACTIONS_PERIOD", 3);

		ReflectionTestUtils.setField(errorMessages, "CUSTOMER_NOT_FOUND", "Given Customer ID Not Found");

		CustomerMasterTbl custRec = new CustomerMasterTbl();
		custRec.setId(custID);
		custRec.setLastName("LN3");
		custRec.setFirstName("FN3");

		custTbl = Optional.of(custRec);

		TransactionsTbl tran1 = new TransactionsTbl();
		tran1.setCreatedOn(parseDate("2022-11-30"));
		tran1.setAmount(52.22);

		custTrans.add(tran1);

		TransactionsTbl tran2 = new TransactionsTbl();
		tran2.setCreatedOn(parseDate("2023-01-05"));
		tran2.setAmount(165.73);

		custTrans.add(tran2);

		TransactionsTbl tran3 = new TransactionsTbl();
		tran3.setCreatedOn(parseDate("2023-01-05"));
		tran3.setAmount(45.15);

		custTrans.add(tran3);

	}

}
