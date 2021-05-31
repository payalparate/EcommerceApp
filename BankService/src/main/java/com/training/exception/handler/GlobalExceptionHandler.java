package com.training.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.UserNotFoundException;

/**
 * @author payal.parate
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(ex.getLocalizedMessage()));
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleException(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not available for the requested data");
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<?> handleException(InsufficientBalanceException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insufficient balance to take a transaction " +InsufficientBalanceException.class.getName());
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> handleException(AccountNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account does not exist "+AccountNotFoundException.class.getName());
	}
	

}
