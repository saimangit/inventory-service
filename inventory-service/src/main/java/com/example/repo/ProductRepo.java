package com.example.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true,value="select * from prod p where p.vendor=?1")
	public List<Product> getProductsByVendor(String vendor);
	@Query(nativeQuery = true,value="select * from prod p where p.pid=?1")
	public Optional<Product> findByPid(String pid);

}
