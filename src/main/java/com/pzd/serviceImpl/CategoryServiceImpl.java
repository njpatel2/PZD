package com.pzd.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.DTO.CategoryDTO;
import com.pzd.entities.Category;
import com.pzd.repository.CategoryRepository;
import com.pzd.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void save(CategoryDTO categoryDTO) {
		categoryRepository.save(new Category(categoryDTO.getCategoryTitle(), categoryDTO.getCategoryDescription()));

	}

	@Override
	public HashMap<Integer, String> getCategoryList() {

		HashMap<Integer, String> categoryList = new HashMap<>();

		List<Category> category = categoryRepository.findAll();

		for (Category categoryItem : category) {

			categoryList.put(categoryItem.getCategoryld(), categoryItem.getCategoryTitle());

		}
		return categoryList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteCategory(int id) {
		categoryRepository.delete(categoryRepository.getById(id));
	}

}
