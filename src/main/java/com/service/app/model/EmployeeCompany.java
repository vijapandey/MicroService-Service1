/**
 * 
 */
package com.service.app.model;

import java.io.Serializable;

/**
 * @author vijpande
 *
 */
public class EmployeeCompany implements Serializable {

	private static final long serialVersionUID = -5121560393433494199L;
	private Integer id;
	private String name;
	private String houseNo;
	private String zipCode;

	public EmployeeCompany(Integer id, String name, String houseNo, String zipCode) {
		this.id = id;
		this.name = name;
		this.houseNo = houseNo;
		this.zipCode = zipCode;
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
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the houseNo
	 */
	public final String getHouseNo() {
		return houseNo;
	}

	/**
	 * @param houseNo the houseNo to set
	 */
	public final void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	/**
	 * @return the zipCode
	 */
	public final String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public final void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
