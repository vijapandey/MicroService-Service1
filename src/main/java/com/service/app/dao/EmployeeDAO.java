package com.service.app.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.service.app.model.Employee;
import com.service.app.model.EmployeeCompany;
import com.service.app.model.EmployeeSkills;
import com.service.app.model.Employees;

 
@Repository
public class EmployeeDAO {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    private static Employees list = new Employees();
  
    static
    {
        list.getEmployeeList().add(new Employee(1, "vijay", "pandey", "vijapandey@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "anand", "singh", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "sankar", "sharma", "ssic@gmail.com"));
        
        list.getEmployeeCompanyList().add(new EmployeeCompany(1, "HCL", "57", "1210"));
        list.getEmployeeCompanyList().add(new EmployeeCompany(2, "HCL", "999", "6888"));
        list.getEmployeeCompanyList().add(new EmployeeCompany(3, "Infinity", "211", "7577"));
        
        list.getEmployeeSkillsList().add(new EmployeeSkills(1, "Developer"));
        list.getEmployeeSkillsList().add(new EmployeeSkills(2, "Testing"));
        list.getEmployeeSkillsList().add(new EmployeeSkills(3, "Developer"));
    }
     
	
    /** find employee **/
	public Employee getEmployeesById(int empId) {		
	//	list.getEmployeeList().stream().anyMatch(obj -> obj.getId() == empId);
		List<Employee> sList = list.getEmployeeList().stream().filter(x -> x.getId() != empId)
        .collect(Collectors.toList());
		return sList.get(0);
	}
    
	public Boolean deleteEmployeesById(int empId) {
		Boolean flag = list.getEmployeeList().removeIf(obj -> obj.getId() == empId);
		logger.debug("After Delete : " + flag);
		return flag;
	}
	
    public List<Employee> getAllEmployees() {
        return list.getEmployeeList();
    }
     
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
    
    
	public List<EmployeeCompany> getEmployeeCompany() {
        return list.getEmployeeCompanyList();
	}

	
	public EmployeeCompany getEmployeeCompanyById(int empId) {
		List<EmployeeCompany> sList = list.getEmployeeCompanyList().stream().filter(x -> x.getId() != empId)
		        .collect(Collectors.toList());
				return sList.get(0);
	}

	
	public List<EmployeeSkills>  getEmployeeSkills() {
		return list.getEmployeeSkillsList();
	}
    
}
