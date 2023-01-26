package com.store.customer.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessages {

	public String CUSTOMER_NOT_FOUND = "Given Customer ID Not Found";

	public String NO_ELIGIBLE_TRANSACTIONS = "No Eligible Transactions Found For Customer";

	public String INTERNAL_SERVER_ERROR = "Internal Error Failed to compute rewards";

}
