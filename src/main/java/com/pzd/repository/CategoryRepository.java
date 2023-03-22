package com.pzd.repository;

import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pzd.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c.categoryld, c.categoryTitle FROM category c")
	public HashMap<Integer, String> getCategoryList();

}
