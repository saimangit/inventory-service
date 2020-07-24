package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.StockProductClient;
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
		return productRepository.findBySid(sid);
	}

	public StockProducts addStock(String sid, StockProducts product) throws StockNotFoundException {
		return stockRepository.findById((long) Integer.parseInt(sid)).map(stock -> {
			product.setStock(stock);
			return productRepository.save(product);
		}).orElseThrow(() -> new StockNotFoundException("stock is not avvailable for the sid " + sid));
	}

	public StockProducts updateStock(String sid, String pid, StockProducts product) {
		if (!productRepository.existsById((long) Integer.parseInt(sid))) {
			throw new ProductNotFoundException(EXCEPTION_MESSAGE + pid);
		}

		return productRepository.findById((long) Integer.parseInt(pid)).map(p -> {
			p.setCreatedDate(product.getCreatedDate());
			p.setCreatedUser(product.getCreatedUser());
			p.setDiscount(product.getDiscount());
			p.setListPrice(product.getListPrice());
			p.setCreatedDate(product.getCreatedDate());
			p.setProductName(product.getProductName());

			return productRepository.save(p);
		}).orElseThrow(() -> new ProductNotFoundException(EXCEPTION_MESSAGE + pid));
	}

	public ResponseEntity<?> deleteStock(String pid) {
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

	public List<com.example.dto.Stock> getAllStock() {
		return stockProductClient.getNewAllStock();
	}
}