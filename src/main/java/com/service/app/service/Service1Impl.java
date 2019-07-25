package com.service.app.service;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.service.app.dao.EmployeeDAO;
import com.service.app.model.Employee;
import com.service.app.model.EmployeeCompany;
import com.service.app.model.EmployeeSkills;

@Service
public class Service1Impl implements Service1 {

	private static Logger log = LoggerFactory.getLogger(Service1Impl.class);

	@Autowired
	private EmployeeDAO employeeDao;

	@Override
	@Cacheable("Employee")
	@RolesAllowed("ROLE_ADMIN")
	public List<Employee> getAllEmployees() throws Exception {
		log.info("Get All Employee method is calling ....");
		//Thread.sleep(10000000);
		return employeeDao.getAllEmployees();
	}

	@Override
	@Cacheable("Employee")
	@RolesAllowed("ROLE_ADMIN")
	public Employee getEmployeesById(int empId) {
		return employeeDao.getEmployeesById(empId);
	}

	@Override
	@Cacheable(value="deleteFalg", key="#Employee.empId")
	public Boolean deleteEmployeesById(int empId) {
		return employeeDao.deleteEmployeesById(empId);
	}

	@Override
	public ResponseEntity<Object> addEmployee(Employee employee) {
		Integer id = employeeDao.getAllEmployees().size() + 1;
		employee.setId(id);
		employeeDao.addEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@Override
	public List<EmployeeCompany> getEmployeeCompany() {
		log.info("Get All Employee Company method is calling ....");
		return employeeDao.getEmployeeCompany();
	}

	@Override
	@Cacheable(value="EmployeeCompany", condition="#Employee.empId.length < 50")
	public EmployeeCompany getEmployeeCompanyById(int empId) {
		return employeeDao.getEmployeeCompanyById(empId);
	}

	@Override
	public List<EmployeeSkills> getEmployeeSkills() {
		return employeeDao.getEmployeeSkills();
	}

}
