package com.springboot.carz4u.web.Carz4U;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		log.info("userName provided is ",username);
		try{
			User user = userRepository.findByUsername(username);
			if(!(user == null)){
				log.info("user.getUsername() is {} , user.getPassword() is {}", user.getUsername(), user.getPassword());
				return new MyUserDetails(user);
			}else{
				log.info("user is not available in DB.");
			}
		}catch(NullPointerException npe){
			log.error("NullPointerException occured in loadUserByUsername(), error is {}", npe.getStackTrace());
		}catch(Exception ex){
			log.error("Exception occured in loadUserByUsername(), error is {}", ex.getStackTrace());
		}
		return null;
	}

}
