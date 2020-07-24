package com.example.controller;

import java.util.List;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.dto.StockProductsDTO;
import com.example.entity.Stock;
import com.example.entity.StockProducts;

import com.example.exception.StockNotFoundException;


import com.example.service.StockProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product-api")
public class StockProductController {

	@Autowired
	StockProductService stockProductService;

	@ApiOperation(value = "view all products available")
	@GetMapping(path = "/stock/{sid}/product/", produces = "application/json")
	public Iterable<StockProducts> getNewAllProducts(@PathVariable("sid") String sid) {

		return stockProductService.displayProduct(sid);
	}

	@Transactional
	@ApiOperation(value = "adding product")
	@PostMapping(path = "/stock/{sid}/product/", produces = "application/json")
	public StockProducts addNewProduct(@PathVariable("sid") String sid, @Valid @RequestBody StockProductsDTO product)
			throws StockNotFoundException {

		return stockProductService.addStock(sid, product);
	}

	@ApiOperation(value = "updating the product")
	@PutMapping(path = "/stock/{sid}/product/{pid}/", produces = "application/json")
	public StockProducts getNewProduct(@PathVariable("sid") String sid, @PathVariable("pid") String pid,
			@Valid @RequestBody StockProductsDTO product) throws StockNotFoundException {

		return stockProductService.updateStock(sid, pid, product);

	}

	@ApiOperation(value = "view a particular product by suppling pid")
	@DeleteMapping(value = "/stock/{sid}/product/{pid}/", produces = "application/json")
	public ResponseEntity<?> deleteNewProduct(@PathVariable("sid") String sid, @PathVariable("pid") String pid) {

		return stockProductService.deleteStock(pid);

	}

	@GetMapping(value = "/stock/{sid}/qty/{qty}", produces = "application/json")
	public ResponseEntity<String> updateNewStockQuantity(@PathVariable("sid") String sid,
			@PathVariable("qty") String qty) throws StockNotFoundException {

		return stockProductService.updateQuantity(sid, qty);
	}

	@GetMapping(value = "/stock-admin", produces = "application/json")
	public List<Stock> getStockDetails() {

		return stockProductService.getAllStock();

	}

}
