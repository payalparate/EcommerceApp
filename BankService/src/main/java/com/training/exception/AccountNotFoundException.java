package com.training.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author payal.parate
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class AccountNotFoundException extends Exception{
	String str;
	

}
