package com.pzd.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.pzd.DTO.CategoryDTO;
import com.pzd.entities.Category;

public interface CategoryService {


	public void save(CategoryDTO categoryDTO);

	public HashMap<Integer, String> getCategoryList();

	public void deleteCategory(int id);
}
