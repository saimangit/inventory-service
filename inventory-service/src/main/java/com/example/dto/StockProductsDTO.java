package com.example.dto;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class StockProductsDTO {
      

	
	  private Long pid;
	  @NotBlank(message="product name must not be null")
	  
	  private String productName;
	  private int listPrice;
	  private int discount;
	  @NotBlank(message="createdUser must not be null")
	  private String createdUser;
	  @NotBlank(message="createdDate must not be null")
	 
	  private String createdDate;
	  
	 
	  @JsonIgnore
	  private StockDTO stock;
	  
	  
	  
	public StockProductsDTO() {
		super();
	}
	public StockDTO getStock() {
		return stock;
	}
	public void setStock(StockDTO stock) {
		this.stock = stock;
		stock.getStockProducts().add(this);
	}

	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getListPrice() {
		return listPrice;
	}
	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	  
	  
	  
	
}
