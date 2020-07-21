package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StockClient;
import com.example.exception.ProductNotFoundException;
import com.example.model.Product;
import com.example.model.RawStock;
import com.example.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/ims-api")
@Validated
@Api(value = "inventory-service",description = "operations on the inventory service")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	StockClient stockClient;
	
    @ApiOperation(value = "view the list of products available at inventory",response = Iterable.class)
    @RequestMapping(value = "/product",method = RequestMethod.GET,produces = "application/json")
	public Iterable<Product> getAllProducts() { 
    	  return productService.getAll();
    }
	

    @ApiOperation(value = "view a particular product by suppling pid")
    @RequestMapping(value = "/product/{pid}",method = RequestMethod.GET,produces ="application/json")
	public ResponseEntity<Optional<Product>> getProduct(@PathVariable("pid") String pid) throws ProductNotFoundException { 
               
    	Optional<Product> raw= productService.getByPid(pid);
        if(!raw.isPresent()) {
     	   throw new ProductNotFoundException("product not found for the sid:"+pid);
        }
        
        return ResponseEntity.ok().body(raw);
           		
    }	
  

    @ApiOperation(value = "add a product")
    @RequestMapping(value="/aproduct",method = RequestMethod.POST,produces = "application/json")
  	public ResponseEntity<String> addProduct( @Valid @RequestBody Product product ) { 
      	   productService.add(product);
      	   return new ResponseEntity<String>("Products got created", HttpStatus.CREATED);
     }
     
    @ApiOperation(value = "update a product")
    @RequestMapping(value="/aproduct/{pid}",method = RequestMethod.PUT,produces = "application/json")
  	public ResponseEntity<Product> updateProduct(@PathVariable("pid") String pid, @Valid @RequestBody Product product ) { 
      	  Optional<Product> p= productService.getByPid(pid);
      	 
      	 if(!p.isPresent()) {
       		throw new ProductNotFoundException("Product not found for the pid:"+pid);
       	  }
      	
      	  System.out.println("value of p"+ p.isPresent());
      	 
      		Product prod=p.get();
      		prod.setPid(product.getPid());
      		prod.setPname(product.getPname());
      		prod.setPrice(product.getPrice());
      		prod.setVendor(product.getVendor());
      	    productService.add(prod); 
      	    
      	  return ResponseEntity.ok().body(prod);
      	 
      	 }
   
    @ApiOperation(value = "delete a product")
    @RequestMapping(value="/aproduct/{pid}",method = RequestMethod.DELETE,produces = "application/json")
  	public Map<String,Boolean> deleteProduct(@PathVariable("pid") int pid) throws ProductNotFoundException { 
      	 
    	Optional<Product> p= productService.getById(pid);
     	 
     	 if(!p.isPresent()) {
      		throw new ProductNotFoundException("Product not found for the pid:"+pid);
      	  }
    	
    	productService.delete(p.get().getId());
  	    Map<String,Boolean> map= new HashMap<String, Boolean>();
	    map.put("deleted Stock", Boolean.TRUE);
	    return map;
      }
    @PostMapping("/product/stock-product")
  	public void addStock() { 
      	
    	List<RawStock> list= stockClient.getAllStock();
    	
     List<RawStock> flist= new ArrayList<>();
     
    	for(RawStock l :list) {
        	 if(l.getValid().equals("yes")) {
        		 flist.add(l);
        	 }
         }
    	
    	
    	System.out.println("list of products"+ flist.size());
    	
    	productService.addProducts(flist);
    	
    }
    
    @GetMapping ("/product/vendor/{vendor}")
    public List<Product> getProductsByVendor(@PathVariable("vendor") String vendor){
	   return productService.getProducts(vendor);	
    }
}
