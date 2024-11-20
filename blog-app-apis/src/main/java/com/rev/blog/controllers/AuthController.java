package com.rev.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rev.blog.payloads.JwtAuthRequest;
import com.rev.blog.payloads.JwtAuthResponse;
import com.rev.blog.security.JwtHelper;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
		
//		System.out.println(jwtAuthRequest.getUsername());
//		System.out.println(jwtAuthRequest.getPassword());
		
		this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		
		
		
		UserDetails userByUsername = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		
		String token = this.jwtHelper.generateToken(userByUsername);
		
		JwtAuthResponse response = new JwtAuthResponse();
		
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}
		catch (BadCredentialsException e) {
			System.out.println("Invalid Details");
			throw new Exception("Invalid Username or Password");
			}
		
	}

}
