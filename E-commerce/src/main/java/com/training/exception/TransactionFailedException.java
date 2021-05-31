package com.training.exception;

/**
 * @author payal.parate
 *
 */
public class TransactionFailedException extends Exception {
	String str;

	public TransactionFailedException(String str) {
		super();
		this.str = str;
	}
}
