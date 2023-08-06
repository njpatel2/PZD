package com.pzd.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pzd.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c.categoryId, c.categoryTitle FROM category c")
	public HashMap<Integer, String> getCategoryList();

	@Query("SELECT c.categoryId, c.categoryTitle FROM category c")
	public  ArrayList<Object[]> getCategoryIDlist();

}
