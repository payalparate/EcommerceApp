package com.training.exception;

/**
 * @author payal.parate
 *
 */
public class UserNotFoundException extends Exception{
	String str;

	public UserNotFoundException(String str) {
		super();
		this.str = str;
	}
}
