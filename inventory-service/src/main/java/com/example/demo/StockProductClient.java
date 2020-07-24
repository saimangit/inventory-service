package com.example.demo;

import java.util.ArrayList;
import java.util.List;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.configuration.FeignConfiguration;
import com.example.entity.Stock;




@FeignClient(name = "stock-service",url = "http://localhost:8010",configuration = FeignConfiguration.class,
fallback =  StockProductClientFallBack.class)
public interface StockProductClient {

	 @GetMapping(value = "/stock-product-api/stock",produces = "application/json")
	  public List<Stock> getNewAllStock(); 
		
}


class StockProductClientFallBack implements StockProductClient{

	@Override
	public List<Stock> getNewAllStock() {
		return new ArrayList<>();
	}
}