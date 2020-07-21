package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class RawStock {


  private Long supplierId;
 
  private String supplierName;
 
  private int qty;

  private String valid;

  private String productName;
  
  private int listPrice;
   
  private int discount;
  
  
  private String supplierContact;
  
  private int version;
  
    private String createdUser;
   
  private String createdDate;
   

public String getSupplierContact() {
	return supplierContact;
}
public void setSupplierContact(String supplierContact) {
	this.supplierContact = supplierContact;
}
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
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
@Override
public String toString() {
	return "RawStock [supplierId=" + supplierId + ", supplierName=" + supplierName + ", qty=" + qty + ", valid=" + valid
			+ ", productName=" + productName + ", listPrice=" + listPrice + ", discount=" + discount + "]";
}

  
  
  
}
