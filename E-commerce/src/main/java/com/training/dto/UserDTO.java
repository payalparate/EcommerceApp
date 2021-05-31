package com.training.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class UserDTO {

	@NotEmpty(message="userName is mandatory")
	private String userName;
	@NotEmpty(message="password is mandatory")
	private String password;
	@Email
	private String email;
	@Size(min=10,max=10)
	private String phoneNo;
	private String address;
}
