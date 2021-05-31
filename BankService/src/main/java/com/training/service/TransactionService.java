package com.training.service;

import java.util.List;

import com.training.dto.TransactionDTO;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.UserNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface TransactionService {
	
	/**
	 * @param transactionDTO
	 * @return
	 * @throws AccountNotFoundException
	 * @throws InsufficientBalanceException
	 */
	TransactionDTO saveTransaction(TransactionDTO transactionDTO) throws AccountNotFoundException, InsufficientBalanceException;
	
	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	List<TransactionDTO> getTransactionHistory(int userId) throws UserNotFoundException;

}
