package com.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author vijpande
 *
 *         Regarding authorization, three main areas are identified:
 * 
 *         -> Web requests authorization ->Method level authorization ->Access
 *         to domain object instances authorization
 * 
 *         Grant Types: Authorization Code Password Client credentials Implicit
 *
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(SecurityProperties.class)
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(OAuthConfiguration.class);
	
	static final String CLIENT_ID = "vijay-client-id";
	static final String CLIENT_SECRET = "vijay-secret-info";	
	static final String[] GRANT_TYPE = { "authorization_code","implicit", "password","client_credentials","refresh_token"};
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserApprovalHandler userApprovalHandler;
	
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		logger.info("Client_Secret : " + passwordEncoder.encode(CLIENT_SECRET));
        configurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret( passwordEncoder.encode(CLIENT_SECRET) )
                .authorizedGrantTypes(GRANT_TYPE)
               // .authorities("READ_ONLY_CLIENT")
             //   .redirectUris("http://localhost:8081/login")
    			.resourceIds("api")
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        logger.info("Oauth configuration server : " + configurer.hashCode());
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
					.userApprovalHandler(userApprovalHandler)
						.authenticationManager(authenticationManager)
							.accessTokenConverter(accessTokenConverter())
								.userDetailsService(userDetailsService);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("HMACSHA256");
		return converter;
	}
}