package com.training.exception;

/**
 * @author payal.parate
 *
 */
public class ProductNotFoundException extends Exception {
	String str;

	public ProductNotFoundException(String str) {
		super();
		this.str = str;
	}
}
