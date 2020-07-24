package com.example.dto;


import java.util.ArrayList;

import java.util.List;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class StockDTO{


	  private Long supplierId;
	  @NotBlank(message="supplier name is mandatory")
	  private String supplierName;
	  @NotBlank(message="supplier contact is mandatory")
	  private String supplierContact;
	  private int qty;
	  @NotBlank(message="valid must not be null")
	  private String valid;
      @JsonIgnore
      //@OrderColumn(name="pid")
      private List<StockProductsDTO> stockProducts = new ArrayList<>();
	    
	
	
	
      
      
	public StockDTO() {
		super();
	}
	public List<StockProductsDTO> getStockProducts() {
		return stockProducts;
	}
	public void setStockProducts(List<StockProductsDTO> stockProducts) {
		this.stockProducts = stockProducts;
		for(StockProductsDTO s:stockProducts) {
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
