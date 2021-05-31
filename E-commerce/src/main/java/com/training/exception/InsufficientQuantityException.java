package com.training.exception;

/**
 * @author payal.parate
 *
 */
public class InsufficientQuantityException extends Exception {
	String str;

	public InsufficientQuantityException(String str) {
		super();
		this.str = str;
	}
}
