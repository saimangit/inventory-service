package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.model.Product;
import com.example.model.RawStock;
import com.example.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepository;
	
	public Product add(Product product) {
		return productRepository.save(product);
	}
	
	public void update(Product product) {
		productRepository.save(product);
	}
	
	public Optional<Product> getByPid(String id) {
	return productRepository.findByPid(id);
	}
	
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public void delete(Long p) {
		productRepository.deleteById(p);;
	}

	public void add(List<Product> product) {
		// TODO Auto-generated method stub
		productRepository.saveAll(product);
	}



	public void addProducts(List<RawStock> list) {
		// TODO Auto-generated method stub
		
List<RawStock> fine= new ArrayList<>();
    	
    	fine = list.stream().filter(s->s.getValid().equals("yes")).collect(Collectors.toList());
    	
    	List<Product> product=new ArrayList<>();
    	
    	for(RawStock s:fine) {
    		
    		Product p  =new Product();
    		
    		   p.setPid("PID-"+s.getSupplierId());
               p.setPname(s.getProductName());
               p.setPrice((double) (s.getListPrice()-(s.getDiscount())));
               p.setVendor(s.getSupplierName());
               p.setSupplierName(s.getSupplierName());
               p.setSupplierContact(s.getSupplierContact());
               
               
               product.add(p);
    	}
    	
    	productRepository.saveAll(product); 
    	
	}

	public List<Product> getProducts(String vendor){
		return productRepository.getProductsByVendor(vendor);
	}

	public Optional<Product> getById(int pid) {
		// TODO Auto-generated method stub
		return productRepository.findById((long) pid);
	}

	
}
