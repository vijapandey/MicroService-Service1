package com.service.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


//@ConfigurationProperties, @EnableConfigurationProperties
//@RefreshScope
//@PropertySource("classpath:application-dev.properties")
@Configuration
@ConfigurationProperties(prefix = "message.default")
public class MessageProperties {
	
	@Value("${message.application.name}")
    private String welcome;
    
    private String goodbye;

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getGoodbye() {
		return goodbye;
	}

	public void setGoodbye(String goodbye) {
		this.goodbye = goodbye;
	}


}