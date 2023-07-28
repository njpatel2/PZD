package com.pzd.repository;

import java.util.ArrayList;

import javax.persistence.ManyToOne;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pzd.entities.Category;
import com.pzd.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM product p JOIN p.category c WHERE c.categoryld = :categoryId")
	ArrayList<Product> findByCategoryid(int categoryId);
	
//	@Query("FROM product p ORDER BY p.category.id ASC")
//	ArrayList<Product> findOrderByCategoryId();
	
	@Query("FROM product p where p.category.id=:categoryId")
	ArrayList<Product> findOrderByCategoryId(int categoryId, Pageable pageable);

	@Query("select p.pId as id, p.pName as name, p.pPrice as price, p.pDiscount as discount, p.pQuantity as quantity, p.category.categoryTitle as category  FROM product p")
	ArrayList<String> getProductList();

//	public void save(ProductDTO productDTO);

}
