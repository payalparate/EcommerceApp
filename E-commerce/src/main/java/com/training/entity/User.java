package com.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/**
 * @author payal.parate
 *
 */
@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String userName;
	private String password;
	private String email;
	private String phoneNo;
	private String address;

}
