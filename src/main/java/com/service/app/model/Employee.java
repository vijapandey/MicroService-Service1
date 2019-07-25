package com.service.app.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

@XmlRootElement
@JsonRootName(value = "Employee")
@JsonPropertyOrder({ "id", "name", "lastName", "email" })
public class Employee implements Serializable {
	private static final long serialVersionUID = -5220749322955487417L;
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;

	public Employee(Integer id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// Getters and setters

	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	@JsonGetter("id")
	public Integer getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonSetter("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonSetter("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonGetter("email")
	public String getEmail() {
		return email;
	}

	@JsonSetter("email")
	public void setEmail(String email) {
		this.email = email;
	}
}