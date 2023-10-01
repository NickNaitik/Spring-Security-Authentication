package com.nick.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.api.model.JWTRequest;
import com.nick.api.model.JWTResponse;
import com.nick.api.util.JwtUtil;

@RestController
@RequestMapping("/api/v1")
public class authController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/auth")
	public ResponseEntity<JWTResponse> authenticate(@RequestBody JWTRequest request){
		
		
		try {
		this.authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch(BadCredentialsException bex) {
			throw new RuntimeException("Invalid Credentials !");
		}
		UserDetails userDetails =  userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);
		
		JWTResponse response = new JWTResponse(token, userDetails.getUsername());
		
		return ResponseEntity.ok(response); 	
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
    	return "Credentials Invalid!!";
    }
	
	

}
