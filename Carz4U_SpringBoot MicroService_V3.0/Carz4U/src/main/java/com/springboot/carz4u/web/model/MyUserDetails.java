package com.springboot.carz4u.web.model;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**This class is like POJO which holds Principal (Logged in user) details,
 *  by implementing UserDetails. 
 *  The role "ROLE_USER" is keyword and do not rename it.
 *  We have only two roles "ROLE_USER", and "ROLE_ADMIN" in Spring Security. 
 *  Password is hard coded to "pass" in getPassword().*/
public class MyUserDetails implements UserDetails {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(MyUserDetails.class);
	
	private User user;

	public MyUserDetails(User user){
		this.user = user;
	}
	
	public MyUserDetails(){
	}
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.info("Inside {} Class, getAuthorities method.",log.getName());
		try{
			log.info("Inside {} Class, try block.",log.getName());
			/** The ROLE_USER, ROLE_ADMIN are keyword's, 
			 * cannot be renamed and will cause problems if changed */
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")); 
		}catch(Exception ex){
			log.error("Exception in getAuthorities method is ", ex.getLocalizedMessage());
			return null;
		}
	}
    
	/** */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}
	/**If the user entered (Spring Security default Login form) Username details are available in DB,
	 * will be extracted from DB and the corresponding password (configured above in getPassword()) 
	 * will be matched with user provided password. If the password for the 
	 * username in DB doesn't match, will throw Bad credentials message.*/
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	/**These methods return values that are default set to true as of now. You can later configure 
	 * them based on user status in DB as we did in getPassword() and getUsername()*/
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
