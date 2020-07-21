package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table(name="prod")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="pid is mandatory")	
	private String pid;
	@NotBlank(message="product name is mandatory")
	@Column(name="product_name",nullable = false) 
	private String pname;
	@NotBlank(message="vendor name is mandatory")
	@Column(name="vendor",nullable = false) 
	private String vendor;
	@Column(name="price",nullable = false) 
	private Double price;
	@NotBlank(message="supplier name is mandatory")
	@Column(name="supplier_name",nullable = false) 
	private String supplierName;
	@NotBlank(message="supplier contact is mandatory")
	@Column(name="supplier_contact",nullable = false) 
	private String supplierContact;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierContact() {
		return supplierContact;
	}
	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Product() {
		super();
	}
	
	
}
