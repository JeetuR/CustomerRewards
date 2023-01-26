package com.store.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RewardsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

}
