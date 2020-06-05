
/** Description */
Configured Netflix Hystrix (Fallback method) in Carz4U Spring Boot Spring Security project with User details from MySql 
i.e. Spring Boot + Spring Security + MySql + Hystrix.

Added Hystrix Parameters/Properties (in addition to Fallbackmethod) to define request thread timeout and other parameters.

NOTE: JWT is not added as it is not working in browser.
      In Eclipse Console you will find option to add multiple consoles one for each project and terminate each one of the
      project (e.g. terminate CarDetailsService alone, terminate RtoServices alone).

Testing Hystrix (Fallback methods)
	i. Terminate CarDetailsService MS alone (in Eclipse Console you will find option to add multiple consoles
	   one for each project) and login Carz4U application. Now the "/carList" will return you 
	   "No cars found" as configured in Fallback method.
	ii.Terminate RtoServices MS alone and hit "/carDetails?carName={}", you will get Rto Charges 0 (Zero), as
	   as configured in Fallback method. 

Testing Hystrix Parameters/Properties
	i. Set "execution.isolation.thread.timeoutInMilliseconds" value to 50ms and you will always see Fallbackmethod being called.