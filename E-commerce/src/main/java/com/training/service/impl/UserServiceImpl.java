package com.training.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dto.UserDTO;
import com.training.entity.User;
import com.training.exception.UserNotFoundException;
import com.training.repository.UserRepository;
import com.training.service.UserService;


/**
 * @author payal.parate
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	@Override
	public UserDTO loginUser(String userName, String password) throws UserNotFoundException {
		User user=userRepository.findByUserNameAndPassword(userName, password);
		if(ObjectUtils.isEmpty(user))
		{
			throw new UserNotFoundException("Invalid credentials ");
		}
		logger.info("User logged in with userName : {} ",userName);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	/**
	 *
	 */
	@Override
	public UserDTO getUserByUserId(int userId) throws UserNotFoundException {
		UserDTO userDTO = new UserDTO();
		User user = userRepository.findByUserId(userId);
		if(org.springframework.util.ObjectUtils.isEmpty(user))
		{
			logger.error("User with id : {} ",userId," is not present in database ");
			throw new UserNotFoundException("Unable to locate user");
		}
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	
	}

}
