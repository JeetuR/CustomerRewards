package com.store.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.store.customer.exception.ErrorMessages;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@Autowired
	ErrorMessages errors;

	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUncaughtExceptions() {
		return ResponseEntity.badRequest().body(errors.INTERNAL_SERVER_ERROR);
	}
}
