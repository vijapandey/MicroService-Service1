package com.service.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@DataJpaTest
//@SpringApplicationConfiguration
//@SpringBootTest(classes = {Application.class, DataSourceConfiguration.class})
public class Service1ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
