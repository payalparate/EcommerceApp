package com.training.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.dto.AccountDTO;
import com.training.dto.TransactionDTO;
import com.training.entity.Transaction;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.repository.TransactionRepository;
import com.training.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

	@Mock
	TransactionRepository transactionRepository;
	
	@Mock
	AccountService accountService;
	
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	
	static TransactionDTO transactionDTO;
	static Transaction transaction;
	static AccountDTO accountDTO;
	
	@BeforeAll
	public static void setUp()
	{
		transactionDTO = new TransactionDTO();
		transactionDTO.setAccountNumber("DFGB763");
		transactionDTO.setAmount(2000.0);
		transactionDTO.setUserId(1);
		
		transaction.setUserId(1);
		transaction.setAmount(2000.0);
		transaction.setAccountNumber("DFGB763");
		transaction.setTransactionStatus("SUCCESS");
		
		accountDTO.setAccountNumber("DFGB763");
		accountDTO.setBalance(2000.0);
		accountDTO.setUserId(1);
		
	}
	
	@Test
	@DisplayName("save method : positive scenerio")
	public void testSaveTransaction() throws AccountNotFoundException, InsufficientBalanceException
	{
		when(accountService.getAccountDetailsByAccountNumber("DFGB763")).thenReturn(accountDTO);
		TransactionDTO dto=transactionServiceImpl.saveTransaction(transactionDTO);
		assertEquals(transactionDTO.getAccountNumber(),dto.getAccountNumber());
	}
}
