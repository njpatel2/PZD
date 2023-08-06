package com.pzd.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pzd.DTO.ProductDTO;
import com.pzd.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@RequestMapping("/getCategoryId")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getItems() {

		ArrayList<HashMap<String, String>> categoryIdList = new ArrayList<>();

		try {
			categoryIdList = productServiceImpl.getCategoryIdList();

		} catch (Exception e) {
			throw e;
		}
		return categoryIdList;
	}

	@RequestMapping("/getItems")
	@ResponseBody
	public ArrayList<ProductDTO> getItems(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "categoryId") Integer categoryId,
			@RequestParam(name = "searchQuery") String searchQuery) {

		ArrayList<ProductDTO> productDTOs = new ArrayList<>();

		HashMap<String, ArrayList<ProductDTO>> hh = new HashMap<>();
		hh.put("CategoryId", productDTOs);
		hh.put("productList", productDTOs);
		try {
			productDTOs = productServiceImpl.getProductList(categoryId, page, searchQuery);
		} catch (Exception e) {
			throw e;
		}
		return productDTOs;
	}

	@RequestMapping("/getItemsBySearchQuery")
	@ResponseBody
	public HashMap<String, Object> getItemsBySearchQuery(@RequestParam(name = "searchQuery") String searchQuery) {

		HashMap<String, Object> productDTOs = new HashMap<>();

		try {
			productDTOs = productServiceImpl.getProductListBySearchQueryOnClick(searchQuery);
		} catch (Exception e) {
			throw e;
		}
		return productDTOs;
	}

	@RequestMapping("/getInitialItems")
	@ResponseBody
	public HashMap<String, Object> getInitialItems() {

		HashMap<String, Object> productDTOs = new HashMap<>();

		try {
			productDTOs = productServiceImpl.getInitialProductList(0);
		} catch (Exception e) {
			throw e;
		}

		return productDTOs;
	}

	@RequestMapping("/getPageCount")
	@ResponseBody
	public int getPageCount(@RequestParam(name = "categoryId") Integer categoryId) {

		int productCount = 0;

		try {
			productCount = productServiceImpl.getProductCount(categoryId);
		} catch (Exception e) {
			throw e;
		}
		return productCount;
	}

}
