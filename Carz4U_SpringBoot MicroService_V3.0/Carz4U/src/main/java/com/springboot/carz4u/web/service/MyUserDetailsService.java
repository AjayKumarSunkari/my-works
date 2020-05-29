package com.springboot.carz4u.web.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.carz4u.web.exception.RecordNotFoundException;
import com.springboot.carz4u.web.model.MyUserDetails;
import com.springboot.carz4u.web.model.User;
import com.springboot.carz4u.web.repository.UserRepository;

/**This is a Service class which implements UserDetailsService.
 * You can provide any userName and Password "pass" is 
 * configured in MyUserDetails.getPassword()*/
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired	
	UserRepository userRepository;
	
	Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	/**This method takes the (Spring Security default Login form) username as input from user 
	 * and checks if the user exist in DB. If user exist it will create new MyUserDetails 
	 * object (MyUserDetails holds the Principal User details). 
	 * Principal user is nothing but currently logged in user. */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inside {} Class, loadUserByUsername method.",log.getName());
		try{
			Optional<User> user = userRepository.findByUsername(username);
			user.orElseThrow(() -> new RecordNotFoundException());
			log.info("userName provided is {}, password provided is {}", user.get().getUsername(),user.get().getPassword());
			return new MyUserDetails(user.get());
		}catch(NullPointerException npe){
			log.error("NullPointerException occured in loadUserByUsername(), error is {}", npe.getStackTrace());
		}catch(Exception ex){
			log.error("Exception occured in loadUserByUsername(), error is {}", ex.getStackTrace());
		}
		return null;
	}

}
