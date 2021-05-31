package com.training.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.training.dto.AccountDTO;
import com.training.entity.Account;
import com.training.exception.AccountNotFoundException;
import com.training.repository.AccountRepository;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
	
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	AccountServiceImpl accountServiceImpl;
	
	static AccountDTO accountDTO;
	static Account account;
	
	@BeforeAll
	public static void setUp()
	{
		accountDTO = new AccountDTO();
		accountDTO.setAccountId(12);
		accountDTO.setAccountNumber("ABCD3456");
		accountDTO.setUserId(1);
		
		account = new Account();
		account.setAccountNumber("ABCD3456");
		account.setBalance(200.00);
	account.setUserId(12);
	}
	
	@Test
	@DisplayName("Get Function: Positive Scenario")
	public void testGetAccountDetailsByAccountNumber() throws AccountNotFoundException
	{
		
		when(accountRepository.findByAccountNumber("ABCD3456")).thenReturn(account);
		AccountDTO accountDTO2=accountServiceImpl.getAccountDetailsByAccountNumber("ABCD3456");
		assertEquals(account.getAccountNumber(), accountDTO2.getAccountNumber());
	}

	@Test
	@DisplayName("Get Function: Negative Scenario")
	public void testGetAccountDetailsByAccountNumber2() throws AccountNotFoundException
	{
		
		when(accountRepository.findByAccountNumber("ABCD3456")).thenReturn(null);
		assertThrows(AccountNotFoundException.class, () ->accountServiceImpl.getAccountDetailsByAccountNumber("ABCD3456"));
	}
	
	@Test
	@DisplayName("Update Function: Positive Scenario")
	public void testUpdateAccount() throws AccountNotFoundException
	{
		
		when(accountRepository.save(any(Account.class))).thenAnswer(i ->{
			Account acc=i.getArgument(0);
			acc.setAccountId(1);
			return acc;
		});
		AccountDTO dto=accountServiceImpl.updateAccountDetails(accountDTO);
		assertEquals(accountDTO.getAccountNumber(), dto.getAccountNumber());
	}

}
