
/** Description */

Configured Spring security in Carz4U Spring Boot project with User details from MySql and JWT authorization.
i.e. Spring Boot + Spring Security + MySql + JWT

Able to pass JWT token to browser from "/authenticate", also able to send it back to "/welcome" (i.e. Server)
as part of Request Header. But still receiving 403 error on "/welcome" in browser.

"/welcome" is working in POSTMAN but not in browser (i.e. JWT authorization is working from POSTMAN but not in browser).

TODO: Need to fix 403 error accessing "/welcome" in browser.

Testing JWT authorization from POSTMAN
	i. Start the Carz4U application (you can remove Netflix Eureka Client configuration to avoid EurekaServer
	   also we do not need CarDetailsService, RtoServices projects to be up as in "/welcome" we are not 
	   fetching any data) in local machine.
	ii. Send POST request to "http://localhost:8080/authenticate" URL in POSTMAN with below JSON "Request body"
		{"userName":"vijay","password":"vijay"}
	iii. Copy JWT token from "Response Header"
	iv. Now send POST request to "http://localhost:8080/welcome" URL in POSTMAN with below Key-Value "Request Header"
		"Authorization" - "Bearer {JWT Token}"
		e.g. "Authorization" - "Bearer eyJhbGciOiJIUzI1NiJ9
					.eyJzdWIiOiJ2aWpheSIsImV4cCI6MTU5MDgwMzM0OSwiaWF0IjoxNTkwNzY3MzQ5fQ
					.RHfLE6mntKf-28E5-_schAXFjeIgG0HIPEqMULk9NMQ"
		(This is sample JWT token only, used for reference.)
	



     	
