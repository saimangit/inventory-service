package com.example.dto;

import java.util.ArrayList;
import java.util.List;

public class Stock {



	  private Long supplierId;
	
	  private String supplierName;

	  private String supplierContact;
	  
	  private int qty;
	 
	  private String valid;

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
