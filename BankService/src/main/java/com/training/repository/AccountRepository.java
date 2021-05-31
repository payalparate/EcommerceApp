package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Account;

/**
 * @author payal.parate
 *
 */
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	/**
	 * @param accountNumber
	 * @return
	 */
	Account findByAccountNumber(String accountNumber);

}
