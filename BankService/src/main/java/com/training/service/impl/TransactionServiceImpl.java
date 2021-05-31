package com.training.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.training.dto.AccountDTO;
import com.training.dto.TransactionDTO;
import com.training.entity.Transaction;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.UserNotFoundException;
import com.training.repository.TransactionRepository;
import com.training.service.AccountService;
import com.training.service.TransactionService;

/**
 * @author payal.parate
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService{
	
	private final static String MY_ACCOUNT_NUMBER = "PIGDKJ4552";
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountService accountService;
	
	static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	/**
	 *
	 */
	@Override
	public TransactionDTO saveTransaction(TransactionDTO transactionDTO) throws AccountNotFoundException, InsufficientBalanceException {
		Transaction transaction = new Transaction();
		AccountDTO accountDTO=new AccountDTO();
			   accountDTO=accountService.getAccountDetailsByAccountNumber(transactionDTO.getAccountNumber());
		    if(ObjectUtils.isEmpty(accountDTO))
		{
			logger.error("Account with account number : {} ",transactionDTO.getAccountNumber() ," does not exist ");
			//return null;
			throw new AccountNotFoundException("Unable to find account with account number : "+transactionDTO.getAccountNumber());
		}
		if(accountDTO.getBalance()<transactionDTO.getAmount())
		{
			logger.error("There is insufficient funds in your account");
			throw new InsufficientBalanceException("Insufficient funds in your account");
		}
		BeanUtils.copyProperties(transactionDTO, transaction);
		Date utildate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
		transaction.setDate(sqlDate);
		transaction.setTransactionStatus("DEBIT");
		transaction.setDescription("transfering funds");
		Transaction savedTransaction = transactionRepository.save(transaction);
		logger.info("Transaction details saved after debiting amount");
		
		Double balance=accountDTO.getBalance()-transaction.getAmount();
		accountDTO.setBalance(balance);
		accountService.updateAccountDetails(accountDTO);
		logger.info("Debited from account suceessfully");
		
		Transaction creditTransaction= new Transaction();
		creditTransaction.setAccountNumber(MY_ACCOUNT_NUMBER);
		creditTransaction.setUserId(1);
		creditTransaction.setAmount(transactionDTO.getAmount());
		creditTransaction.setDate(sqlDate);
		creditTransaction.setTransactionStatus("CREDIT");
		creditTransaction.setDescription("funds credited");
		transactionRepository.save(creditTransaction);
		logger.info("Transaction details saved with credited amount");
		
		AccountDTO accountDTO2 = accountService.getAccountDetailsByAccountNumber(MY_ACCOUNT_NUMBER);
		accountDTO2.setBalance(accountDTO2.getBalance()+creditTransaction.getAmount());
		accountService.updateAccountDetails(accountDTO2);
		logger.info("Credited to account suceessfully");
		
		BeanUtils.copyProperties(savedTransaction,transactionDTO);
		return transactionDTO;
	}

	/**
	 *
	 */
	@Override
	public List<TransactionDTO> getTransactionHistory(int userId) throws UserNotFoundException {
		List<Transaction> transactionList=transactionRepository.findByUserId(userId);
		if(ObjectUtils.isEmpty(transactionList))
		{
			logger.error("User not present for requested id");
			throw new UserNotFoundException("User not found for user id : "+userId);
		}
		List<TransactionDTO> transactionDTOList = new ArrayList<>();
		for (Transaction transaction : transactionList) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOList.add(transactionDTO);
		}
		logger.info("Retrieving transactionHistory of user");
		return transactionDTOList;
	}

}
