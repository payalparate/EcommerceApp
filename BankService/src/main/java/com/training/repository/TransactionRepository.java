package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Transaction;

/**
 * @author payal.parate
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	/**
	 * @param userId
	 * @return
	 */
	List<Transaction> findByUserId(int userId);

}
