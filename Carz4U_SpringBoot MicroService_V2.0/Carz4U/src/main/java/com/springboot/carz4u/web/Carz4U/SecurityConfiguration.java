package com.springboot.carz4u.web.Carz4U;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**This class is  used for Authentication and Authorization.*/

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserDetailsService userDetailsService;
		
		Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
	
		/**This method is used for Authentication. Here we have used  UserDetailsService based
		 * authentication by implementing MyUserDetailsService. loadUserByUsername() in MyUserDetailsService class
		 * is used for implementing logic to fetch give username (Provided in Spring Security Login form) from DB. */
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			log.info("Start {} Class, configure  AuthenticationManagerBuilder method.",log.getName());
			auth.userDetailsService(userDetailsService);
			log.info("End {} Class, configure AuthenticationManagerBuilder method.",log.getName());
		}
		
		/**This method is used for Authorization. Primarily there are two roles
		 * ROLE_USER and ROLE_ADMIN and here we are restricting URL access based on user roles.*/	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.info("Start {} Class, configure  HttpSecurity method.",log.getName());
			http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/carDetails").hasRole("USER")
			.antMatchers("/*").hasAnyRole("USER","ADMIN")
			.and().formLogin();
			log.info("Start {} Class, configure  HttpSecurity method.",log.getName());
		}
		
		/**Here passwords are encoded as plain texts. This is not good and you need to implement
		 * other types of encoders like BCryptPasswordEncoder. */
		@Bean
		public PasswordEncoder getPasswordEncoder(){
			return NoOpPasswordEncoder.getInstance();
			
		}
}
