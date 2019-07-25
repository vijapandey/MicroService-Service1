package com.service.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.service.app.model.Employee;
import com.service.app.model.EmployeeCompany;
import com.service.app.model.EmployeeSkills;

public interface Service1 {

	public List<Employee> getAllEmployees() throws Exception;

	public Employee getEmployeesById(int empId);

	public Boolean deleteEmployeesById(int empId);

	public ResponseEntity<Object> addEmployee(Employee employee);

	public List<EmployeeCompany> getEmployeeCompany();

	public EmployeeCompany getEmployeeCompanyById(int empId);

	public List<EmployeeSkills> getEmployeeSkills();

}
