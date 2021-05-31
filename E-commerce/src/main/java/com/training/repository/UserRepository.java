package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.entity.User;


/**
 * @author payal.parate
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	 
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	User findByUserNameAndPassword(String userName, String password);
	/**
	 * @param userId
	 * @return
	 */
	User findByUserId(int userId);
}
