package com.training.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.training.controller.TransactionController;
import com.training.dto.AccountDTO;
import com.training.entity.Account;
import com.training.exception.AccountNotFoundException;
import com.training.repository.AccountRepository;
import com.training.service.AccountService;

/**
 * @author payal.parate
 *
 */
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	/**
	 *
	 */
	@Override
	public AccountDTO getAccountDetailsByAccountNumber(String accountNumber) throws AccountNotFoundException {
		AccountDTO accountDTO = new AccountDTO();
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if(ObjectUtils.isEmpty(account))
		{
			logger.error("Account number : {} ",accountNumber, " does not exist ");	
			return null;
			//throw new AccountNotFoundException("Account not found for account number : "+accountNumber);
		}
		logger.info("Retrieving account info associated with account number : {} ",accountNumber);
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
	}
	/**
	 *
	 */
	@Override
	public AccountDTO updateAccountDetails(AccountDTO accountDTO) throws AccountNotFoundException {
		Account account= new Account();
		accountDTO.setBalance(accountDTO.getBalance());
		BeanUtils.copyProperties(accountDTO, account);
		logger.info("Updating account balance after a successfull transaction");
		accountRepository.save(account);
		return accountDTO;
	}

}
