/**
 * 
 */
package com.service.app.test.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.service.app.dao.EmployeeDAO;
import com.service.app.model.Employee;
import com.service.app.service.Service1Impl;

import junit.framework.Assert;

/**
 * @author vijpande
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class Service1ImplTest {
	
	@InjectMocks
	private Service1Impl service1;
	
	@Mock
	private EmployeeDAO employeeDao;

	@Test
	public void testGetEmployee() throws Exception {
		//EmployeeDAO employeeDao = Mockito.mock(EmployeeDAO.class);
		//service1 = new Service1Impl();
		List<Employee> empList = new ArrayList<Employee>();
		Mockito.when(employeeDao.getAllEmployees()).thenReturn(empList);
		List<Employee> emps = service1.getAllEmployees();
		Assert.assertEquals(0, emps.size());
	}
	
	 @Test
	public void createEmployeeTest() {
		Employee emp = new Employee(1, "Lokesh", "Gupta", "user@email.com");
		employeeDao.addEmployee(emp);
		verify(employeeDao, times(1)).addEmployee(emp);
	}
}
