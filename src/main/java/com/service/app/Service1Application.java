package com.service.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.service.app"})
@EnableAsync
@EnableCaching
@ComponentScan(basePackages = {"com.service.app", "com.service.app.model", "com.service.app.controller", "com.service.app.service", "com.service.app.dao" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.service.app.dao")
@EntityScan("com.service.app.model")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EnableJpaAuditing
//@EnableAutoConfiguration
@Configuration
@EnableEurekaClient
public class Service1Application /**extends WebMvcConfigurerAdapter**/ extends SpringBootServletInitializer {
	
    private static final Logger logger = LoggerFactory.getLogger(Service1Application.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Service1Application.class, args);
		String applicationName = ctx.getEnvironment().getProperty("message.application.name");
		
		logger.info("Started application name : " + applicationName  +"..... using: mvn spring-boot:run");
	//	SpringApplication.run(Service1Application.class, args);
		
		//do not want command line properties to be added to the Environment
		//SpringApplication.setAddCommandLineProperties(false)
		
		//1) java -jar myproject.jar --spring.config.name=application.properties
		
		//2) java -jar demo.jar --spring.profile.active=dev

	}
	
	 /* @Override
      public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
			logger.info("configureAsyncSupport is runnig !!!!!!!!!!");
            configurer.setTaskExecutor(mvcTaskExecutor());
            configurer.setDefaultTimeout(30_000);
      }*/
 
      @Bean
      public ThreadPoolTaskExecutor mvcTaskExecutor() {
			logger.debug("mvcTaskExecutor is runnig !!!!!!!!!!");
            ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
            taskExecutor.setThreadNamePrefix("mvc-task-");
            return taskExecutor;
      }

}
