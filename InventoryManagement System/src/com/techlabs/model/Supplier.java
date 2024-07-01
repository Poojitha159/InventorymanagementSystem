package com.techlabs.model;

import java.io.Serializable;

public class Supplier implements Serializable {
	
	private static final long serialVersionUID=1L;
	private String supplierid;
	private String name;
	private String contactInfo;
	public Supplier(String supplierid, String name, String contactInfo) {
		super();
		this.supplierid = supplierid;
		this.name = name;
		this.contactInfo = contactInfo;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "Supplier [supplierid=" + supplierid + ", name=" + name + ", contactInfo=" + contactInfo + "]";
	}
	
	
	
	

}
