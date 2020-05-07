
/** Description */
Configured Spring security in Carz4U Spring Boot project with User details from MySql 
i.e. Spring Boot + Spring Security + MySql.

Now the Carz4U app will ask for Sign-in or Login credentials at URL "/*",
as configured in SecurityConfiguration class which extends WebSecurityConfigurerAdapter. 

For Authentication we use WebSecurityConfigurerAdapter.configure(AuthenticationManagerBuilder auth)
and uses AuthenticationManagerBuilder.userDetailsService() to call upon UserDetailsService to retrive
custom Principal (i.e. Logged in user) details using UserDetails (UserDetails is like POJO).

For Authorization we use WebSecurityConfigurerAdapter.configure(HttpSecurity http).

In MyUserDetails class we are returning SimpleGrantedAuthority object with "ROLE_USER" (Do not rename as USER or something it will throw error).
We have only two roles "ROLE_USER", and "ROLE_ADMIN" in Spring Security.

We have created User entity(Hibernate class) and connceted to MySql schema ajaydb through UserRepository class.
Login credentials are extracted from user table from ajaydb schema.

	User Name(Password): vijay(vijay), ajay(ajay), user(pass)

