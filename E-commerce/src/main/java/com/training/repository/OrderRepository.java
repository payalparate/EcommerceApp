package com.training.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Orders;



/**
 * @author payal.parate
 *
 */
public interface OrderRepository extends JpaRepository<Orders, Integer>{

	/**
	 * @param userId
	 * @return
	 */
	List<Orders> findOrdersByUserId(int userId);
}
