package com.pzd.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.pzd.DTO.ProductDTO;

public interface ProductService {
	
	public void addProduct(ProductDTO productDTO);

	public HashMap<Integer, String> getProductListByCategoryid(int categoryId);

	public void deleteProduct(int productId);

	ArrayList<HashMap<String, String>> getProductDetails();

	ArrayList<HashMap<String, String>> getCategoryIdList();

	ArrayList<ProductDTO> getProductList(int categoryId, int page, String searchQuery);

	ArrayList<ProductDTO> getProductListBySearchQuery(int page, String searchQuery);

	ArrayList<ProductDTO> getProductListBySearchQuery(int categoryId, int page, String searchQuery);

	
}
