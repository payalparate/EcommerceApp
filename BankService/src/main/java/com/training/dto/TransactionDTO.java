package com.training.dto;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class TransactionDTO {
	
	@NotEmpty(message = "enter your account number")
	private String accountNumber;
	@FutureOrPresent
	private Date date;
	private double amount;
	private int userId;
}
