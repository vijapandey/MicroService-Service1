package com.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.service.app.service.UserDetailsServiceImpl;

/**
 * @author vijpande
 * 
 * OAuth2 Roles
	-> Resource Owner : (the User) – an entity capable of granting access to a protected resource (for example end-user).
	->Resource Server : (the API server) – the server hosting the protected resources, capable of accepting responding to protected resource requests using access tokens.
	->Client Application : an application making protected resource requests on behalf of the resource owner and with its authorization.
    ->Authorization Server :The server issuing access tokens to the client after successfully authenticating the resource owner and obtaining authorization.
 *
 */
@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
@EnableGlobalMethodSecurity( prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
    @Autowired
    private ClientDetailsService clientDetailsService;
	  
    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    
    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    
    private PasswordEncoder passwordEncoder;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("configure security is going on !!!!!!!!!!!");
		//http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	/*	http.authorizeRequests().antMatchers("/home").access("hasRole('USER')")
				.antMatchers("/employees/**", "/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").and().formLogin().loginPage("/login")
				.successHandler(successHandler).usernameParameter("admin").passwordParameter("password").and()
				.csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
		
        http.exceptionHandling().accessDeniedPage("/403");*/
		
		  http
	          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	          .and()
	          .authorizeRequests()
	          .antMatchers("/oauth/token**", "/oauth/authorize**", "/login**", "/error**").permitAll()
	          .antMatchers("/api/**").hasAnyAuthority("ADMIN", "ROLE_USER")
	          .antMatchers("/api/employees/**").hasAuthority("ADMIN")
	          .antMatchers("/api/**").authenticated()
	          .anyRequest().authenticated()
	          .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler());

		/*http.csrf().disable().anonymous().disable().authorizeRequests()
		 .antMatchers("/oauth/token**", "/oauth/authorize**", "/login**", "/error**").permitAll();*/
	}

	/***
	 * Prior to Spring Security 5.0 the default PasswordEncoder was
	 * NoOpPasswordEncoder which required plain text passwords. In Spring Security
	 * 5, the default is DelegatingPasswordEncoder, which required Password Storage
	 * Format. Solution 1 – Add password storage format, for plain text, add {noop}
	 * {bcrypt} {pbkdf2} {scrypt} {sha256}
	 * 
	 * Ex. {id}encodedPassword id is an identifier used to look up which
	 * PasswordEncoder should be used and encodedPassword is the original encoded
	 * password for the selected PasswordEncoder
	 * 
	 */
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("configureGlobal security is going on !!!!!!!!!");
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
	}*/

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder( bCryptPasswordEncoder());//bCryptPasswordEncoder()  use hendi as user name OR passwordEncoder() use clientId as user name 
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}*/

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            logger.info("passwordEncoder : " + passwordEncoder.toString());
        }
        return passwordEncoder;
    }

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	/***
	 * 
	 * Solution 2 – User.withDefaultPasswordEncoder() for UserDetailsService
	 */
	/*
	 * @Bean public UserDetailsService userDetailsService() { User.UserBuilder users
	 * = User.withDefaultPasswordEncoder(); InMemoryUserDetailsManager manager = new
	 * InMemoryUserDetailsManager();
	 * manager.createUser(users.username("user").password("password").roles("USER").
	 * build());
	 * manager.createUser(users.username("admin").password("password").roles("USER",
	 * "ADMIN").build()); return manager; }
	 */

}
