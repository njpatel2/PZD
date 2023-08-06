package com.pzd.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Category;
import com.pzd.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM product p JOIN p.category c WHERE c.categoryId = :categoryId")
	ArrayList<Product> findByCategoryid(int categoryId,Pageable pageable);
	
	@Query("FROM product p ORDER BY p.category.id ASC")
	ArrayList<Product> findOrderByCategoryId();
	
//	@Query("SELECT p FROM product p JOIN p.category c WHERE c.categoryId = :categoryId AND " +
//	        "(:searchQuery IS NULL OR " +
//	        "NOT EXISTS (" +
//	        "SELECT 1 FROM product p2 " +
//	        "WHERE p2 IN (" +
//	        "SELECT p3 FROM product p3 JOIN p3.category c2 " +
//	        "WHERE c2.categoryId = :categoryId) " +
//	        "AND NOT (" +
//	        "p2.pName LIKE CONCAT('%', :searchQuery, '%') " +
//	        "OR p2.category.categoryTitle LIKE CONCAT('%', :searchQuery, '%'))))")
//	ArrayList<Product> findOrderByCategoryId(int categoryId, String searchQuery,Pageable pageable);
	
//	@Query("FROM product p where p.category.id=:categoryId")
//	ArrayList<Product> findOrderByCategoryId(int categoryId, Pageable pageable);

	@Query("select p.pId as id, p.pName as name, p.pPrice as price, p.pDiscount as discount, p.pQuantity as quantity, p.category.categoryTitle as category  FROM product p")
	ArrayList<String> getProductList();

	@Query("select COUNT(p) from product p where p.category.id=:categoryId")
	int getProductCountByCategoryId(Integer categoryId);
	
//	@Query("SELECT p FROM product p JOIN p.category c WHERE c.categoryId = :categoryId AND " +
//	        "(:searchQuery IS NULL OR " +
//	        "NOT EXISTS (" +
//	        "SELECT 1 FROM product p2 " +
//	        "WHERE p2 IN (" +
//	        "SELECT p3 FROM product p3 JOIN p3.category c2 " +
//	        "WHERE c2.categoryId = :categoryId) " +
//	        "AND NOT (" +
//	        "p2.pName LIKE CONCAT('%', :searchQuery, '%') " +
//	        "OR p2.category.categoryTitle LIKE CONCAT('%', :searchQuery, '%'))))")
	@Query("SELECT p FROM   product p JOIN   p.category c where  c.categoryId =:categoryId       AND    (              :searchQuery IS NULL       OR     (                     p.pName LIKE concat('%', :searchQuery, '%')              OR     p.category.categoryTitle LIKE concat('%', :searchQuery, '%')))")
	ArrayList<Product> findOrderByCategoryIdBySearchQuery(int categoryId ,String searchQuery, Pageable pageable);

	@Query("FROM product p WHERE p.category.categoryId =:categoryId" )
//	+ "(SELECT MIN(c.categoryId) FROM category c)")
	ArrayList<Product> getInitialProductList(Pageable pageable, int categoryId);

//	@Query("SELECT p FROM product p JOIN p.category c WHERE c.categoryId = (SELECT MIN(c2.categoryId) FROM category c2) AND " +
//	        "(:searchQuery IS NULL OR " +
//	        "NOT EXISTS (" +
//	        "SELECT 1 FROM product p2 " +
//	        "WHERE p2 IN (" +
//	        "SELECT p3 FROM product p3 JOIN p3.category c2 " +
//	        "WHERE c2.categoryId = (SELECT MIN(c3.categoryId) FROM category c3)) " +
//	        "AND NOT (" +
//	        "p2.pName LIKE CONCAT('%', :searchQuery, '%') " +
//	        "OR p2.category.categoryTitle LIKE CONCAT('%', :searchQuery, '%')))) " +
//	        "ORDER BY c.categoryId ASC"
	@Query("SELECT p , c.categoryId FROM product p JOIN p.category c WHERE c.categoryId = " +
	        "(SELECT MIN(c2.categoryId) FROM category c2 " +
	        "WHERE EXISTS (" +
	        "SELECT 1 FROM product p2 " +
	        "WHERE p2.category.categoryId = c2.categoryId " +
	        "AND (p2.pName LIKE CONCAT('%', :searchQuery, '%') OR p2.category.categoryTitle LIKE CONCAT('%', :searchQuery, '%')))) " +
	        "AND (:searchQuery IS NULL OR " +
	        "(p.pName LIKE CONCAT('%', :searchQuery, '%') OR p.category.categoryTitle LIKE CONCAT('%', :searchQuery, '%')))")
	List<ArrayList<Object>> getProductListBySearchQueryOnClick(String searchQuery,Pageable pageable);
//	@Query("SELECT DISTINCT p FROM product p " +
//	        "JOIN FETCH p.category c " +
//	        "WHERE c.categoryId = (SELECT MIN(c2.categoryId) FROM category c2) AND " +
//	        "(:searchQuery IS NULL OR " +
//	        "NOT EXISTS (" +
//	        "SELECT 1 FROM product p2 " +
//	        "WHERE p2 IN (" +
//	        "SELECT p3 FROM product p3 JOIN p3.category c2 " +
//	        "WHERE c2.categoryId = (SELECT MIN(c3.categoryId) FROM category c3)) " +
//	        "AND NOT (" +
//	        "p2.pName LIKE CONCAT('%', :searchQuery, '%') " +
//	        "OR p2.category.categoryTitle LIKE CONCAT('%', :searchQuery, '%')))) " +
//	        "ORDER BY c.categoryId ASC")
//	ArrayList<Product> getProductListBySearchQueryOnClick(String searchQuery);



	@Query("SELECT MIN(c.categoryId) FROM category c")
	int getFirstCategoryId();

//	public void save(ProductDTO productDTO);

}
