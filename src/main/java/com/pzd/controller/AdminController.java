package com.pzd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.CategoryDTO;
import com.pzd.DTO.ProductDTO;
import com.pzd.serviceImpl.CategoryServiceImpl;
import com.pzd.serviceImpl.ProductServiceImpl;
import com.pzd.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/admin")
	public ModelAndView adminMainPage() {
		ModelAndView mv = new ModelAndView("admin");
		;
		return mv;
	}

	@RequestMapping("/addProduct")
	@ResponseBody
	public void addProduct(@RequestParam("pName") String pName, @RequestParam("pDesc") String pDesc,
			@RequestParam("pPhoto") MultipartFile productPhoto, @RequestParam("pPrice") float pPrice,
			@RequestParam("pDiscount") float pDiscount, @RequestParam("categoryId") int categoryId,
			@RequestParam("pQuantity") int pQuantity, HttpServletRequest request) {

		try {

			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(categoryId);

			ProductDTO productDTO = new ProductDTO();
			productDTO.setpName(pName);
			productDTO.setpDesc(pDesc);
			productDTO.setpPhoto(productPhoto.getOriginalFilename());
			productDTO.setpPrice(pPrice);
			productDTO.setpDiscount(pDiscount);
			productDTO.setCategory(categoryDTO);
			productDTO.setpQuantity(pQuantity);

			@SuppressWarnings("deprecation")
			String path = request.getRealPath("images") + File.separator;
			System.out.println(path);

			FileOutputStream fileOutputStream = new FileOutputStream(path + productPhoto.getOriginalFilename());
			InputStream inputStream = productPhoto.getInputStream();

			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);

			fileOutputStream.write(data);

			fileOutputStream.close();
			inputStream.close();
			productServiceImpl.addProduct(productDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addCategory")
	@ResponseBody
	public ModelAndView addCategory(@RequestBody CategoryDTO categoryDTO) {
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("admin");
			categoryServiceImpl.save(categoryDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/getCategoryList")
	@ResponseBody
	public HashMap<Integer, String> getCategoryList() {
		HashMap<Integer, String> categoryList = new HashMap<>();
		try {
			categoryList = categoryServiceImpl.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@RequestMapping("/deleteCategory")
	@ResponseBody
	public void deleteCategory(@RequestBody Map<String, Object> payload) {
		try {
			String categoryId = (String) payload.get("categoryId");
			categoryServiceImpl.deleteCategory(Integer.parseInt(categoryId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteProduct")
	@ResponseBody
	public void deleteProduct(@RequestBody Map<String, Object> payload) {
		try {
			String productId = (String) payload.get("productId");
			productServiceImpl.deleteProduct(Integer.parseInt(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getProductList")
	@ResponseBody
	public HashMap<Integer, String> getCategoryList(@RequestBody Map<String, Object> payload) {
		HashMap<Integer, String> categoryList = new HashMap<>();
		try {
			String categoryId = (String) payload.get("categoryId");
			categoryList = productServiceImpl.getProductListByCategoryid(Integer.parseInt(categoryId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@RequestMapping("/getCustomerDetails")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getCustomerList() {
		ArrayList<HashMap<String, String>> customerList = null;
		try {
			customerList = userServiceImpl.getCustomerList();
			
			
//			id, name,email,number, address, role
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}
	@RequestMapping("/getProductDetails")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getProductList() {
		ArrayList<HashMap<String, String>> customerList = null;
		try {
			customerList = productServiceImpl.getProductDetails();
			
			
//			id, name,price ,quantity, category
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}
	@RequestMapping("/getCountOfCustomerProductCategory")
	@ResponseBody
	public HashMap<String, Integer> getCountOfCustomerProductCategory() {
		HashMap<String, Integer> systemDetails = null;
		try {
			systemDetails = userServiceImpl.getCountOfCustomerProductCategory();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemDetails;
	}

}
