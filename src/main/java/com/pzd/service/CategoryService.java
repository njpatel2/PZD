package com.pzd.service;

import java.util.HashMap;

import com.pzd.DTO.CategoryDTO;

public interface CategoryService {


	public void save(CategoryDTO categoryDTO);

	public HashMap<Integer, String> getCategoryList();

	public void deleteCategory(int id);
}
