/**
 * 
 */
package com.service.app.model;

import java.io.Serializable;

/**
 * @author vijpande
 *
 */
public class EmployeeSkills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id ;
	 
	private String skills;
	
	public EmployeeSkills(Integer id, String skills) {
		this.id = id;
		this.skills = skills;
	}

	/**
	 * @return the id
	 */
	public final Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the skills
	 */
	public final String getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public final void setSkills(String skills) {
		this.skills = skills;
	}
	
	
	
}
