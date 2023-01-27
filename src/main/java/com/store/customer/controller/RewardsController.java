package com.store.customer.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.customer.exception.ErrorMessages;
import com.store.customer.service.RewardsInfo;
import com.store.customer.service.RewardsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/store")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@Autowired
	ErrorMessages errors;

	@RequestMapping(value = "/customer/getRewardsPoints/{customerID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> computeRewards(
			@PathVariable(name = "customerID", required = true) String customerID) {

		Integer custID = null;

		try {
			custID = Integer.parseInt(customerID);
		} catch (Exception invalidCustID) {
			log.error("Invalid customer ID in request : " + customerID, invalidCustID);
			return ResponseEntity.badRequest().body(errors.CUSTOMER_NOT_FOUND);
		}

		Double custRewardPoints = rewardsService.computeRewards(custID);
		if (custRewardPoints == null) {
			return ResponseEntity.badRequest().body(errors.NO_ELIGIBLE_TRANSACTIONS);
		}

		String formattedPointsValue = new DecimalFormat("###.00").format(custRewardPoints);

		RewardsInfo response = RewardsInfo.builder().customerRewards(formattedPointsValue).build();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
