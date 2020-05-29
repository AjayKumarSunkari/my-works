package com.springboot.carz4u.web.jwt.model;

import java.io.Serializable;

/**This POJO/model class is used  for holding JWT token generated in 
 * "/authenticate" URI.*/
public class AuthenticationResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2408996763918969627L;
	
	private final String jwtToken;

	public AuthenticationResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	public String getJwtToken(){
		return jwtToken;
	}
	
}
