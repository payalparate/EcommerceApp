package com.training.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class AccountDTO {
	private int accountId;
	private String accountNumber;
	private int userId;
	private double balance;
}
