package com.training.service;

import com.training.dto.UserDTO;
import com.training.exception.UserNotFoundException;


/**
 * @author payal.parate
 *
 */
public interface UserService {
	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public UserDTO loginUser(String username, String password) throws UserNotFoundException;
	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	public UserDTO getUserByUserId(int userId) throws UserNotFoundException;
}
