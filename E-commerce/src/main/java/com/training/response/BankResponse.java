package com.training.response;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class BankResponse {
	@NotEmpty(message = "enter your account number")
	private String accountNumber;
	private double amount;
	private int userId;
}
