package com.pzd.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pzd.DTO.CategoryDTO;
import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Category;
import com.pzd.entities.Product;
import com.pzd.repository.CategoryRepository;
import com.pzd.repository.ProductRepository;
import com.pzd.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
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
	public ArrayList<ProductDTO> getProductList(int categoryId, int page)
	{
		Pageable pageable = PageRequest.of(page, 1);

		ArrayList<Product> list = productRepository.findOrderByCategoryId(categoryId, pageable);
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
	@Override
	public ArrayList<HashMap<String, String>> getProductDetails()
	{

		ArrayList<String> productList = productRepository.getProductList();
		ArrayList<HashMap<String, String>> productListForGrid = new ArrayList<>();
		
		for (String u : productList) {
			String[] s = u.split(",");
			HashMap<String, String> singleProduct = new HashMap<>();
			
			singleProduct.put("id", s[0]);
			singleProduct.put("name", s[1]);
			singleProduct.put("price", s[2]);
			singleProduct.put("discount",  s[3]);
			singleProduct.put("quantity",  s[4]);
			singleProduct.put("category",  s[5]);
			
			productListForGrid.add(singleProduct);
		}
		return productListForGrid;
		
	}

	@Override
	public ArrayList<HashMap<String, String>> getCategoryIdList() {
			
		
		ArrayList<Object[]> nn = categoryRepository.getCategoryIDlist();
		ArrayList<HashMap<String, String>> ListOfCategories = new ArrayList<>();
		for (Object[] object : nn) {
			HashMap<String, String> category = new HashMap<>();
			
			category.put("categoryId", object[0].toString());
			category.put("categoryTitle", (String)object[1]);
			ListOfCategories.add(category);
		}
		
		return ListOfCategories;
	}

	public int getProductCount(Integer categoryId) {
		
		productRepository.getProductCountByCategoryId(categoryId);
		
		return productRepository.getProductCountByCategoryId(categoryId);
	}


	
}
