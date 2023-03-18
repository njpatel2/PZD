package com.pzd.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.stereotype.Service;

import com.pzd.DTO.CategoryDTO;
import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Cart;
import com.pzd.entities.Category;
import com.pzd.entities.Product;
import com.pzd.repository.CartRepository;
import com.pzd.repository.CategoryRepository;
import com.pzd.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public void addProduct(ProductDTO productDTO) {
		try {

			Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory().getId());
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

				products.put(product.getpId(), product.getpName());

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

	@Override
	public ArrayList<ProductDTO> getProductList()
	{

		ArrayList<Product> list = productRepository.findOrderByCategoryId();
		ArrayList<ProductDTO> productDTO = new ArrayList<>();
		for (Product product : list) {
			ProductDTO dto = new ProductDTO();
		    dto.setpId(product.getpId());
		    dto.setpName(product.getpName());
		    
		    CategoryDTO categoryDTO =  new CategoryDTO();
		    categoryDTO.setId(product.getCategory().getCategoryld());
		    categoryDTO.setCategoryTitle(product.getCategory().getCategoryTitle());
		    categoryDTO.setCategoryDescription(product.getCategory().getCategoryDescription());
		    
		    dto.setCategory(categoryDTO);
		    dto.setpPrice(product.getpPrice());
		    dto.setpDesc(product.getpDesc());
		    dto.setpDiscount(product.getpDiscount());
		    dto.setpPhoto(product.getpPhoto());
		    dto.setpQuantity(product.getpQuantity());
		    
		    // set other fields as needed
		    productDTO.add(dto);
		}
		return productDTO;
		
	}

	
}
