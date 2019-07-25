package com.service.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1154518704813260315L;

	private List<Employee> employeeList;

	private List<EmployeeCompany> employeeCompanyList;

	private List<EmployeeSkills> employeeSkillsList;

	/**
	 * @return the employeeCompanyList
	 */
	public final List<EmployeeCompany> getEmployeeCompanyList() {
		if (employeeCompanyList == null) {
			employeeCompanyList = new ArrayList<>();
		}
		return employeeCompanyList;
	}

	/**
	 * @param employeeCompanyList the employeeCompanyList to set
	 */
	public final void setEmployeeCompanyList(List<EmployeeCompany> employeeCompanyList) {
		this.employeeCompanyList = employeeCompanyList;
	}

	public List<Employee> getEmployeeList() {
		if (employeeList == null) {
			employeeList = new ArrayList<>();
		}
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * @return the employeeSkillsList
	 */
	public final List<EmployeeSkills> getEmployeeSkillsList() {
		if (employeeSkillsList == null) {
			employeeSkillsList = new ArrayList<>();
		}
		return employeeSkillsList;
	}

	/**
	 * @param employeeSkillsList the employeeSkillsList to set
	 */
	public final void setEmployeeSkillsList(List<EmployeeSkills> employeeSkillsList) {
		this.employeeSkillsList = employeeSkillsList;
	}

}