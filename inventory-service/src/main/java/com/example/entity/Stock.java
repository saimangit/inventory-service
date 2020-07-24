package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;


import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="raw_stock")
public class Stock implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	  @Column(name="supplier_id")
	  private Long supplierId;
	  @NotBlank(message="supplier name is mandatory")
	  @Column(name="supplier_name",nullable = false)  
	  private String supplierName;
	  @NotBlank(message="supplier contact is mandatory")
	  @Column(name="supplier_contact",nullable=false)
	  private String supplierContact;
	  @Column(name="qty",nullable = false)  
	  private int qty;
	  @NotBlank(message="valid must not be null")
	  @Column(name="valid",nullable = false)  
	  private String valid;
      @OneToMany(mappedBy = "stock",cascade = CascadeType.ALL)
      @JsonIgnore
      //@OrderColumn(name="pid")
      private List<StockProducts> stockProducts = new ArrayList<>();
	    
	
	
	
      
      
	public Stock() {
		super();
	}
	public List<StockProducts> getStockProducts() {
		return stockProducts;
	}
	public void setStockProducts(List<StockProducts> stockProducts) {
		this.stockProducts = stockProducts;
		for(StockProducts s:stockProducts) {
			s.setStock(this);
		}
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	  
	  
}
