package com.springboot.carz4u.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.carz4u.web.jwt.filter.JwtRequestFilter;

/**This class is  used for Authentication and Authorization.*/

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserDetailsService userDetailsService;
		
		@Autowired
		JwtRequestFilter jwtRequestFilter;
		
		Logger log = LoggerFactory.getLogger(SecurityConfigurer.class);
	
		/**This method is used for Authentication. Here we have used  UserDetailsService based
		 * authentication by implementing MyUserDetailsService. */
		/*@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			log.info("Start {} Class, configure  AuthenticationManagerBuilder method.",log.getName());
			auth.userDetailsService(userDetailsService);
			log.info("End {} Class, configure AuthenticationManagerBuilder method.",log.getName());
		}*/
		
		/**This method is used for Authorization. Primarily there are two roles
		 * ROLE_USER and ROLE_ADMIN and here we are restricting URL access based on user roles.
		 * "/login" is used for accepting Username and Password from client so permitted all 
		 * using permitAll() method.
		 * "/authenticate" here we authenticate the User, generate JWT and send it back to browser
		 * by setting it in Request Header using ResponseEntity class.
		 * All the other URL's are accessible only if they have proper authorization (happening at
		 * JwtRequestFilter)*/	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.info("Start {} Class, configure  HttpSecurity method.",log.getName());
			http.csrf().disable()
			/**Permit all users (including Unauthorized users) to access below URL's, where authentication happens*/
				.authorizeRequests().antMatchers("/login", "/authenticate").permitAll()
				/** Permit all to access JS files, CSS files and Images*/
				.antMatchers("/WEB-INF/views/**","/js/**","/resources/images/**","/resources/styles/**").permitAll() 
				.anyRequest().authenticated()/**All URS's other than "/login", "/authenticate" are accessible only by authenticated users */
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			log.info("End {} Class, configure  HttpSecurity method.",log.getName());
		}
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
		
		/**Here passwords are encoded as plain texts. This is not good and you need to implement
		 * other types of encoders like BCryptPasswordEncoder. */
		@Bean
		public PasswordEncoder getPasswordEncoder(){
			return NoOpPasswordEncoder.getInstance();
			
		}
}
