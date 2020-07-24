package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Stock;
import com.example.entity.StockProducts;
import com.example.repo.ProductRepository;
import com.example.repo.StockRepository;
import com.sun.jersey.core.header.MediaTypes;

//@SpringBootTest
class OrderServiceApplicationTests extends AbstractTest {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	StockRepository stockRepository;
	
    @Override
    @BeforeEach
	public void setup() {
    	super.setup();
    }
	
	@Test
     void getProductList() throws Exception{
		String uri="http://localhost:8000/product-api/stock/103/product/";
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.get(uri).
				accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String json=mvcResult.getResponse().getContentAsString();
		StockProducts[] stock=super.mapFromJson(json, StockProducts[].class);
		assertTrue(stock.length>0);
		
	}

	@Transactional
	@Test
    void createProduct() throws Exception{
		String uri="http://localhost:8000/product-api/stock/103/product/";
		StockProducts s= new StockProducts();
	    s.setCreatedDate("24/07/2020");
	    s.setCreatedUser("saiman");
	    s.setDiscount(200);
	    s.setListPrice(1000);
	    s.setProductName("Bags");
		
	    
	   // Optional<Stock> stock=stockRepository.findById((long) 103);
	    
	    //s.setStock(stock.get());
	
		
		String inputJson=super.mapToJson(s);
		
		
		
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.post(uri).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		assertEquals(200, status);
//		String json=mvcResult.getResponse().getContentAsString();
//		assertEquals(inputJson, json);
		
	}
	
//	@Test
//    public void updateStock() throws Exception{
//		String uri="http://localhost:8000/product-api/stock/103/product/2";
//		StockProducts s= new StockProducts();
//	    s.setCreatedDate("24/07/2020");
//	    s.setCreatedUser("saiman");
//	    s.setDiscount(200);
//	    s.setListPrice(1000);
//	    s.setProductName("Bags");
//		
//		String inputJson=super.mapToJson(s);
//		
//		
//		
//		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.put(uri).
//				contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//		
//		int status=mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String json=mvcResult.getResponse().getContentAsString();
//	    assertEquals(inputJson, json);
//		
//	}
//	
	@Test
    void deleteStock() throws Exception{
		String uri="http://localhost:8000/product-api/stock/103/product/19/";
		
		MvcResult mvcResult= mock.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status=mvcResult.getResponse().getStatus();
		System.out.println("status is :"+ status);
		assertEquals(200, status);
	
	}
	
	
	
}
