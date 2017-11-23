package com.hsp.domain;

/**
 * Areaconfig entity. @author MyEclipse Persistence Tools
 */

public class Areaconfig implements java.io.Serializable {

	// Fields

	private Long id;
	private String groupkey;
	private String section;

	// Constructors

	/** default constructor */
	public Areaconfig() {
	}

	/** full constructor */
	public Areaconfig(String groupkey, String section) {
		this.groupkey = groupkey;
		this.section = section;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupkey() {
		return this.groupkey;
	}

	public void setGroupkey(String groupkey) {
		this.groupkey = groupkey;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

}