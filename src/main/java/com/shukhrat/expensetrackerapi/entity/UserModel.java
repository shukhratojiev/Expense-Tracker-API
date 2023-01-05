package com.shukhrat.expensetrackerapi.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {

	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotNull(message = "Email should not be empty")
	@Email(message = "Enter valid email")
	private String email;
	
	@NotNull(message = "")
	@Size(min = 5, message = "Password should be 5 characters")
	private String password;
	
	private Long age = 0L;
}
