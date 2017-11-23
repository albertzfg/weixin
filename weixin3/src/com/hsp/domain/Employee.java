package com.hsp.domain;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long empNr;
	private String empChName;
	private String empName;
	private String section;
	private String shift;
	private Boolean onDuty;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(Long empNr, String empChName, String empName, String section, String shift, Boolean onDuty) {
		this.empNr = empNr;
		this.empChName = empChName;
		this.empName = empName;
		this.section = section;
		this.shift = shift;
		this.onDuty = onDuty;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getEmpNr() {
		return this.empNr;
	}

	public void setEmpNr(Long empNr) {
		this.empNr = empNr;
	}

	public String getEmpChName() {
		return this.empChName;
	}

	public void setEmpChName(String empChName) {
		this.empChName = empChName;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getShift() {
		return this.shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Boolean getOnDuty() {
		return this.onDuty;
	}

	public void setOnDuty(Boolean onDuty) {
		this.onDuty = onDuty;
	}

}