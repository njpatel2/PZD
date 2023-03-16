package com.pzd.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.dao.ProductDTO;
import com.pzd.entities.Category;
import com.pzd.entities.Product;
import com.pzd.repository.CategoryRepository;
import com.pzd.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void addProduct(ProductDTO productDTO) {
		try {

			Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
			Category category = optionalCategory.orElseThrow(() -> new RuntimeException("Category not found"));

			productRepository.save(new Product(productDTO.getpName(), productDTO.getpDesc(), productDTO.getpPhoto(),
					productDTO.getpPrice(), productDTO.getpDiscount(), productDTO.getpQuantity(), category));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public HashMap<Integer, String> getProductListByCategoryid(int categoryId) {

		ArrayList<Product> productsList = new ArrayList<>();
		HashMap<Integer, String> products = new HashMap<>();
		try {
			
			

			productsList = productRepository.findByCategoryid(categoryId);
			for (Product product : productsList) {
				
				products.put(product.getPiId(), product.getpName());
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return products;
	}
	
@Override
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
		
	}

}
