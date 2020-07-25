package com.example.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.StockProducts;

public interface ProductRepository extends JpaRepository<StockProducts, Long> {

	@Query(value = "select * from product p where p.supplier_id=?1",nativeQuery = true)
	Iterable<StockProducts> findBySid(int sid);
	
	
	@Query(value = "select * from product p where p.supplier_id=?1 and p.pid=?2",nativeQuery = true)
	Optional<StockProducts> findOneProduct(int sid, Long p);
}
