package com.training.service;

import com.training.dto.AccountDTO;
import com.training.exception.AccountNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface AccountService {

	/**
	 * @param accountNumber
	 * @return
	 * @throws AccountNotFoundException
	 */
	AccountDTO getAccountDetailsByAccountNumber(String accountNumber) throws AccountNotFoundException;
	/**
	 * @param accountDTO
	 * @return
	 * @throws AccountNotFoundException
	 */
	AccountDTO updateAccountDetails(AccountDTO accountDTO) throws AccountNotFoundException;
}
