package com.springboot.carz4u.web.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.carz4u.web.jwt.util.JwtUtil;
import com.springboot.carz4u.web.service.MyUserDetailsService;

/**For every Http Request, this filter class will be intercepted before UsernamePasswordAuthenticationFilter.class
 * (as configured in SecurityConfigurer class), verifies if the user is authorized (based on JWT token passed in
 * Request Header of each request).
 * If the user is not authorized, then 403 error will be thrown.  */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try{
		log.info("Inside {} class, doFilterInternal method.", log.getName());
		/**Extracts Request Header value with 
		 * Key="Authorization" and 
		 * Value="Bearer eyJhbGciOiJIUzI1NiJ9
		 * .eyJzdWIiOiJ2aWpheSIsImV4cCI6MTU4OTU5MzE1NCwiaWF0IjoxNTg5NTU3MTU0fQ
		 * .EXjpOx1qLpQjnYZPl63pMq7vRHlhFvtEt1hqpl19oKE"
		 * 
		 * This is sample JWT token value mentioned above.*/
		final String authorizationHeader = request.getHeader("Authorization");
		log.info("authorizationHeader: ", authorizationHeader);
		String userName = null;
		String jwt = null;
		
		/**If Request Header value(i.e. JWT token) is available then extract Username from JWT token */
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			jwt = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(jwt);
			log.info("{}, doFilterInternal() method, userName is {}",log.getName(),userName);
		}
		/**If the Username is not set as Principal or current logged in user then, 
		 * set it in SecurityContext. */
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null){
			log.info("{}, doFilterInternal() method, SecurityContextHolder Authentication is null",log.getName());
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt, userDetails)){
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		}catch(Exception e){
			log.info("Exception in {} class, doFilterInternal method is ",log.getName(),e.getStackTrace());
			log.info("Exception in {} class, doFilterInternal method is ",log.getName(),e.getLocalizedMessage());
		}
	  }

}
