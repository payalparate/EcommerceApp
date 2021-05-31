package com.training.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.UserDTO;
import com.training.exception.UserNotFoundException;
import com.training.exception.handler.MessageResponse;
import com.training.service.UserService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping()
	public ResponseEntity<?> loginUser(@Valid @RequestParam String userName, @Valid @RequestParam String password) throws UserNotFoundException
	{
		UserDTO userDTO = userService.loginUser(userName, password);
		if(ObjectUtils.isNotEmpty(userDTO))
		{
			logger.info("User logged in with proper credentials");
			return ResponseEntity.ok(new MessageResponse("User logged in successfully !!!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid userName & password"));
	}

}
