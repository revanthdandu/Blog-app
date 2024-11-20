package com.rev.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty(message = "Username should not be empty!")
	private String name;
	
	@Email(message = "Email address is not valid!")
	private String email;
	
	@NotEmpty(message = "Password should not be empty!")
	private String password;
	
	@NotEmpty(message = "About should not be empty!")
	private String about;
}
