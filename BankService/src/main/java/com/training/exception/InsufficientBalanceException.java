package com.training.exception;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

/**
 * @author payal.parate
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class InsufficientBalanceException extends Exception {

	String str;
}
