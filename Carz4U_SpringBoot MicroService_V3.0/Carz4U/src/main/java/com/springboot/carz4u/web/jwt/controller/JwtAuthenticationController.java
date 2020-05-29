package com.springboot.carz4u.web.jwt.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.carz4u.web.jwt.model.AuthenticationRequest;
import com.springboot.carz4u.web.jwt.model.AuthenticationResponse;
import com.springboot.carz4u.web.jwt.util.JwtUtil;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	private static final Logger log = LoggerFactory.
			getLogger(JwtAuthenticationController.class);
	
	/**"/authenticate" URL authenticates the User, generates JWT token if Username 
	 * and password details are available in DB.
	 * UserDetails class holds the authenticated user or Principal user details.
	 * JWT is sent back to client/browser in Response Header using ResponseEntity class*/
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
																HttpServletResponse response) throws Exception{
		log.info("authenticationRequest.getUserName(): {}, authenticationRequest.getPassword(): {}",
				authenticationRequest.getUserName(), authenticationRequest.getPassword());
		try{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException e){
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		new AuthenticationResponse(jwt);
		String jwtToken = "Bearer " + jwt;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jwtToken);
		log.info("jwtToken: " + jwtToken);
		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
	 }
	
}
