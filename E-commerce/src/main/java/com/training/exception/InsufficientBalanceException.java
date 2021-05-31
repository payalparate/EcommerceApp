package com.training.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsufficientBalanceException extends Exception {
	
	String str;

}
