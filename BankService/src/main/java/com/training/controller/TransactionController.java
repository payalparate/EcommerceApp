package com.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.TransactionDTO;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.UserNotFoundException;
import com.training.exception.handler.MessageResponse;
import com.training.service.TransactionService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	static Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	/**
	 * @param transactionDTO
	 * @return
	 * @throws AccountNotFoundException
	 * @throws InsufficientBalanceException
	 */
	@PostMapping()
	public ResponseEntity<?> saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException, InsufficientBalanceException
	{
		TransactionDTO dto = new TransactionDTO();
		     dto=transactionService.saveTransaction(transactionDTO);			
		if(dto!=null)
		{
			logger.info("Details required for doing transaction is obtained");
			return ResponseEntity.ok(new MessageResponse("Your Order is placed successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
		
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?>  getTransactionHistory(@PathVariable int userId) throws UserNotFoundException
	{
		
		List<TransactionDTO> dto= transactionService.getTransactionHistory(userId);
		if(dto!=null)
		{
			logger.info("Fetching transactionHistory of particular user ");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
	}

}
