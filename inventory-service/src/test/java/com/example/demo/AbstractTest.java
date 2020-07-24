package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.controller.StockProductController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=StockServiceApplication.class)
@ContextConfiguration(classes = StockProductController.class)
@WebAppConfiguration
class AbstractTest {

	protected MockMvc mock;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	
	@Test
	void test() {
		
	}
	
	protected void setup() {
		mock=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	protected String mapToJson(Object obj) throws JsonProcessingException {
	
		ObjectMapper map= new ObjectMapper();
		return map.writeValueAsString(obj);
	}
	
	protected <T> T mapFromJson(String json,Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper map= new ObjectMapper();
		return map.readValue(json, clazz);
		
	}
}
