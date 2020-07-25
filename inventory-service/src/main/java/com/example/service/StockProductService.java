package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.StockProductClient;
import com.example.dto.StockProductsDTO;
import com.example.entity.Stock;
import com.example.entity.StockProducts;
import com.example.exception.ProductNotFoundException;
import com.example.exception.StockNotFoundException;
import com.example.repo.ProductRepository;
import com.example.repo.StockRepository;

@Service
public class StockProductService {

	private static final String EXCEPTION_MESSAGE = "product not found for the pid : ";

	@Autowired
	StockRepository stockRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StockProductClient stockProductClient;

	public Iterable<StockProducts> displayProduct(String sid) {
		return productRepository.findBySid( Integer.parseInt(sid));
	}

	public StockProducts addStock(String sid, StockProductsDTO product) throws StockNotFoundException {
		
		StockProducts sp = objectMapping(product);
	
		
		return stockRepository.findById((long) Integer.parseInt(sid)).map(stock -> {
			sp.setStock(stock);
			return productRepository.save(sp);
		}).orElseThrow(() -> new StockNotFoundException("stock is not available for the sid " + sid));
	}

	private StockProducts objectMapping(StockProductsDTO product) {
		StockProducts sp= new StockProducts();
		sp.setCreatedDate(product.getCreatedDate());
		sp.setCreatedUser(product.getCreatedUser());
		sp.setDiscount(product.getDiscount());
		sp.setListPrice(product.getListPrice());
		sp.setProductName(product.getProductName());
		return sp;
	}

	public StockProducts updateStock(String sid, String pid, StockProductsDTO product) throws StockNotFoundException  {
		if (!stockRepository.existsById((long) Integer.parseInt(sid))) {
			throw new StockNotFoundException(EXCEPTION_MESSAGE + pid);
		}

		StockProducts sp = objectMapping(product);
		
		
		return productRepository.findById((long) Integer.parseInt(pid)).map(p -> {
			
			p.setCreatedUser(sp.getCreatedUser());
			p.setDiscount(sp.getDiscount());
			p.setListPrice(sp.getListPrice());
			p.setCreatedDate(sp.getCreatedDate());
			p.setProductName(sp.getProductName());

			return productRepository.save(p);
		}).orElseThrow(() -> new ProductNotFoundException(EXCEPTION_MESSAGE + pid));
	}

	public ResponseEntity<Object> deleteStock(String pid) {
		return productRepository.findById((long) Integer.parseInt(pid)).map(p -> {
			productRepository.delete(p);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ProductNotFoundException("product not found for the pid: " + pid));
	}

	public ResponseEntity<String> updateQuantity(String sid, String qty) throws StockNotFoundException {

		int orderedQuantity = Integer.parseInt(qty);

		Optional<Stock> p = stockRepository.findById((long) Integer.parseInt(sid));

		if (p.isPresent()) {

			Stock stock = p.get();

			if (orderedQuantity < stock.getQty()) {

				int currentQuantity = stock.getQty();

				p.get().setQty(currentQuantity - orderedQuantity);

				stockRepository.save(p.get());

				return new ResponseEntity<>("stock of quantity " + qty + " has been ordered", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Ordered quantity not available in the inventory",
						HttpStatus.NOT_ACCEPTABLE);
			}

		} else {
			throw new StockNotFoundException("Stock not found for the sid:" + sid);
		}
	}

	public List<Stock> getAllStock() {
		return stockProductClient.getNewAllStock();
	}

	public Optional<StockProducts> getProduct(String sid, String pid) {
		
		int s=Integer.parseInt(sid);
		Long p=(long) Integer.parseInt(pid);		
		return productRepository.findOneProduct(s,p);
	}
}