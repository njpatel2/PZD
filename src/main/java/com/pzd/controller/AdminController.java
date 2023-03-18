package com.pzd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.CategoryDTO;
import com.pzd.DTO.ProductDTO;
import com.pzd.services.CategoryServiceImpl;
import com.pzd.services.ProductServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@RequestMapping("/admin")
	public ModelAndView adminMainPage() {
		ModelAndView mv = new ModelAndView("admin");;
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
}
