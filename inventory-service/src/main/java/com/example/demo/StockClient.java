package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.configuration.FeignConfiguration;
import com.example.model.RawStock;

import feign.hystrix.FallbackFactory;

//url = "http://localhost:8010"
//url = "http://stockservicerds-env.eba-dwrqj2wp.us-east-1.elasticbeanstalk.com/"
@FeignClient(value = "stock-service",url = "http://stockservicerds-env.eba-dwrqj2wp.us-east-1.elasticbeanstalk.com/"
,configuration = FeignConfiguration.class,
fallback =  StockClientFallBack.class )
public interface StockClient {

	@GetMapping("/stock-api/stock")
    public List<RawStock> getAllStock();	
}

 @Component
 class StockClientFallBack implements StockClient {

	@Override
	public List<RawStock> getAllStock() {
		
		return new ArrayList<>();
	}

}