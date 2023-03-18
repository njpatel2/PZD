package com.pzd.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Cart;
import com.pzd.entities.Product;

public interface ProductService {
	
	public void addProduct(ProductDTO productDTO);

	public HashMap<Integer, String> getProductListByCategoryid(int categoryId);

	public void deleteProduct(int productId);

	public ArrayList<ProductDTO> getProductList();

	
}
