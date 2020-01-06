/**
 * 
 */
package com.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author vijpande
->The protected resources (as resource server) are published under /api/ path, 
-> while authentication path (as resource owner/authorization server) is mapped to /oauth/token, following proposed default.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ResourceServerConfiguration.class);
	private static final String RESOURCE_ID = "api";
	private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
	private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
	private static final String SECURED_PATTERN = "/secured/**"; 

	@Autowired
	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	private final TokenStore tokenStore;

    public ResourceServerConfiguration(final TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }
    
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
        resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*
		 * http.requestMatchers()
		 * .antMatchers(SECURED_PATTERN).and().authorizeRequests()
		 * .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
		 * .anyRequest().access(SECURED_READ_SCOPE);
		 */
		//http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
		
		http.
        	anonymous().disable()
        	.authorizeRequests()
        	.antMatchers("/api/employees/**").access("hasRole('ADMIN')")
        	.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
		logger.debug(http.toString());

		/* http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .anonymous().disable()
         .authorizeRequests()
         .antMatchers("/api/**").authenticated()
       //  .antMatchers("/api/oauth/token").permitAll()
         .antMatchers("/service1/api/**").hasAuthority("ADMIN")
         //.antMatchers("/api/service1/employees/**").access("hasRole('ADMIN')")
         //.antMatchers("/api/**").authenticated()
         .antMatchers("/service1/oauth/token").permitAll()
         //.anyRequest().authenticated()
         .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new OAuth2AccessDeniedHandler());
*/
		 
	}
}