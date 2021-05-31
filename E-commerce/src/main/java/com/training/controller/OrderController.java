package com.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.OrderRequestDTO;
import com.training.dto.OrderResponseDTO;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.InsufficientQuantityException;
import com.training.exception.ProductNotFoundException;
import com.training.exception.TransactionFailedException;
import com.training.exception.UserNotFoundException;
import com.training.exception.handler.MessageResponse;
import com.training.feignClient.BankClient;
import com.training.response.BankResponse;
import com.training.service.OrderService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService  orderService;
	
	static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	/**
	 * @param orderRequestDTO
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 * @throws BeansException
	 * @throws UserNotFoundException
	 * @throws InsufficientQuantityException
	 * @throws TransactionFailedException
	 * @throws AccountNotFoundException
	 * @throws InsufficientBalanceException 
	 */
	@PostMapping
	public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) throws ProductNotFoundException,MethodArgumentNotValidException, BeansException, UserNotFoundException, InsufficientQuantityException, TransactionFailedException,AccountNotFoundException, InsufficientBalanceException
	{
		OrderResponseDTO dto = new OrderResponseDTO();
		
			dto=orderService.saveOrder(orderRequestDTO);	
			//logger.error("Account not found exception occured");
	
		if (dto != null) {		
			logger.info("Details required for placing an order is fulfilled");
			return ResponseEntity.ok(new MessageResponse("Your Order is placed successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 * @throws MethodArgumentNotValidException
	 */
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getOrderByUserId(@PathVariable int userId) throws UserNotFoundException,MethodArgumentNotValidException
	{
		List<OrderResponseDTO> dto= orderService.getOrderByUserId(userId);
		if (dto != null) {
			logger.info("Trying to fetch order history for user with id : "+userId);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));

	}

}
