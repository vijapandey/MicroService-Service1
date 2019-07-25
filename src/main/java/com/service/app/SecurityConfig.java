/**
 * 
 */
package com.service.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author vijpande
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("configure security is going on !!!!!!!!!");
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		/*http.authorizeRequests()
	    .antMatchers("/", "/home").access("hasRole('USER')")
	    .antMatchers("/admin/**").access("hasRole('ADMIN')")
	    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	    .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
	    .usernameParameter("ssoId").passwordParameter("password")
	    .and().csrf()
	    .and().exceptionHandling().accessDeniedPage("/Access_Denied");*/
	}

	/***
	 * Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder which required plain text passwords. 
	 * In Spring Security 5, the default is DelegatingPasswordEncoder, which required Password Storage Format.
	 * Solution 1 – Add password storage format, for plain text, add {noop}
	 *  {bcrypt}
	 *  {pbkdf2}
	 *  {scrypt}
	 *  {sha256}
	 * 
	 * Ex. {id}encodedPassword
	 * id is an identifier used to look up which PasswordEncoder 
	 * should be used and encodedPassword is the original encoded password for the selected PasswordEncoder
	 
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("configureGlobal security is going on !!!!!!!!!");
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
	}
	
	
	/***
	 * 
	 * Solution 2 – User.withDefaultPasswordEncoder() for UserDetailsService
	 */
	/*@Bean
	public UserDetailsService userDetailsService() {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(users.username("user").password("password").roles("USER").build());
		manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
		return manager;
	}*/
	
}
