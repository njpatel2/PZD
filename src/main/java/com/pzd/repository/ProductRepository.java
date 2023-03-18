package com.pzd.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM product p JOIN p.category c WHERE c.categoryld = :categoryId")
	ArrayList<Product> findByCategoryid(int categoryId);
	
	@Query("FROM product p ORDER BY p.category.id ASC")
	ArrayList<Product> findOrderByCategoryId();


//	public void save(ProductDTO productDTO);

}
