package com.store.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.store.customer.exception.ErrorMessages;
import com.store.customer.exception.RewardsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@Autowired
	ErrorMessages errors;

	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUncaughtExceptions(Exception exp) {
		log.error("Unhandled exception : " + exp.getMessage(), exp);
		return ResponseEntity.badRequest().body(errors.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RewardsException.class)
	public ResponseEntity<?> handleRewardsExceptions(RewardsException rewardsExp) {
		log.error("Rewards exception : " + rewardsExp.getErrorMessage(), rewardsExp);
		return ResponseEntity.badRequest().body(rewardsExp.getErrorMessage());
	}

}
