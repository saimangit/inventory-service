package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.demo.StockProductClient;
import com.example.entity.Stock;
import com.example.entity.StockProducts;
import com.example.exception.ProductNotFoundException;
import com.example.exception.StockNotFoundException;
import com.example.repo.ProductRepository;
import com.example.repo.StockRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product-api")
public class StockProductController {

	
	private static final String EXCEPTION_MESSAGE = "product not found for the pid : ";
	
	@Autowired
    StockRepository stockRepository;
	
	@Autowired
	StockProductClient stockProductClient;
    
    @Autowired
    ProductRepository productRepository;
    
    @ApiOperation(value = "view all products available")
    @GetMapping(path = "/stock/{sid}/product/",produces = "application/json")
	public Iterable<StockProducts> getNewAllProducts(@PathVariable("sid") String sid) { 
    	  
    	return productRepository.findBySid(sid);
    }
	

   
    @Transactional
    @ApiOperation(value = "adding product")
    @PostMapping(path="/stock/{sid}/product/",produces = "application/json")
  	public StockProducts addNewProduct( @PathVariable("sid") String sid,@Valid @RequestBody StockProducts product ) throws StockNotFoundException { 
      	   
    	 return stockRepository.findById((long) Integer.parseInt(sid)).
    	 map(stock -> {
    		 product.setStock(stock);
    		 return productRepository.save(product);
    	 }).orElseThrow(()->new StockNotFoundException("stock is not avvailable for the sid "+ sid));     
     }

    
    
    @ApiOperation(value = "updating the product")
    @PutMapping(path = "/stock/{sid}/product/{pid}/",produces ="application/json")
	public StockProducts getNewProduct(@PathVariable("sid") String sid,
			@PathVariable("pid") String pid,
			@Valid @RequestBody StockProducts product)  { 
               
    	if(! productRepository.existsById((long) Integer.parseInt(sid))) {
    		throw new ProductNotFoundException(EXCEPTION_MESSAGE + pid);
    	}
    	
      
        
        return productRepository.findById((long) Integer.parseInt(pid))
                .map(p -> {p.setCreatedDate(product.getCreatedDate());
                p.setCreatedUser(product.getCreatedUser());
                p.setDiscount(product.getDiscount());
                p.setListPrice(product.getListPrice());
                p.setCreatedDate(product.getCreatedDate());
                p.setProductName(product.getProductName());
                
                 return productRepository.save(p); }).orElseThrow(()-> new ProductNotFoundException(EXCEPTION_MESSAGE+pid));
        
       
           		
    }	

    
    @ApiOperation(value = "view a particular product by suppling pid")
    @DeleteMapping(value = "/stock/{sid}/product/{pid}/",produces ="application/json")
	public ResponseEntity<?> deleteNewProduct(@PathVariable("sid") String sid,
			@PathVariable("pid") String pid,
			@Valid @RequestBody StockProducts product)  { 
               

        
       return  productRepository.findById((long) Integer.parseInt(pid))
                .map(p -> {
                  productRepository.delete(p);
                  return ResponseEntity.ok().build();}).orElseThrow(()-> new ProductNotFoundException("product not found for the pid: "+pid));
        
       
           		
    }	
    
   
    @GetMapping(value="/stock/{sid}/qty/{qty}",produces = "application/json")
  	public ResponseEntity<String> updateNewStockQuantity(@PathVariable("sid") String sid, 
  			@PathVariable("qty") String qty) throws StockNotFoundException { 
      	
    	
    	int orderedQuantity=Integer.parseInt(qty);
  	
    	Optional<Stock> p= stockRepository.findById((long) Integer.parseInt(sid));
      	  
      	  if(p.isPresent()) {
 
      	     Stock stock=p.get();
      	  

           	  
      	  if(orderedQuantity < stock.getQty()) {
      	  
      		int currentQuantity= stock.getQty();
      		
      		p.get().setQty(currentQuantity-orderedQuantity);
      		
      	    stockRepository.save(p.get());
      	    
      	  return new ResponseEntity<>("stock of quantity "+qty+" has been ordered", HttpStatus.ACCEPTED);
      	  }
      	  else {
      		  return new ResponseEntity<>("Ordered quantity not available in the inventory", HttpStatus.NOT_ACCEPTABLE);
      	  }
      	  
      	  }
      	  else {
       		throw new StockNotFoundException("Stock not found for the sid:"+sid);
      	  }
      	  } 
    

    
        @GetMapping(value = "/stock-admin",produces ="application/json")
        public  List<com.example.dto.Stock> getStockDetails() {
			return stockProductClient.getNewAllStock();
        	
        }
        
        
}
