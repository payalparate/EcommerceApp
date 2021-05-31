package com.training.service;

import java.util.List;

import org.springframework.beans.BeansException;

import com.training.dto.OrderRequestDTO;
import com.training.dto.OrderResponseDTO;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.InsufficientQuantityException;
import com.training.exception.ProductNotFoundException;
import com.training.exception.TransactionFailedException;
import com.training.exception.UserNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface OrderService {
	
	/**
	 * @param orderRequestDTO
	 * @return
	 * @throws ProductNotFoundException
	 * @throws BeansException
	 * @throws UserNotFoundException
	 * @throws InsufficientQuantityException
	 * @throws TransactionFailedException
	 * @throws AccountNotFoundException
	 * @throws InsufficientBalanceException 
	 */
	OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO) throws ProductNotFoundException, BeansException, UserNotFoundException, InsufficientQuantityException, TransactionFailedException, AccountNotFoundException, InsufficientBalanceException;

	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	List<OrderResponseDTO> getOrderByUserId(int userId) throws UserNotFoundException;
}
