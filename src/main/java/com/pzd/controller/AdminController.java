package com.pzd.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.dao.CategoryDTO;
import com.pzd.dao.ProductDTO;
import com.pzd.services.CategoryServiceImpl;
import com.pzd.services.ProductServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@RequestMapping("/home")
    public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView("admin");
		return mv;
	}

	@RequestMapping("/addProduct")
	@ResponseBody
    public ModelAndView addProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request )
	{
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("admin");
			productServiceImpl.addProduct(productDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/addCategory")
	@ResponseBody
    public ModelAndView addCategory(@RequestBody CategoryDTO categoryDTO )
	{
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
    public HashMap<Integer, String> getCategoryList()
	{
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
    public void deleteProduct(@RequestBody Map<String, Object> payload)
	{
		try {
			String productId = (String) payload.get("productId");
			productServiceImpl.deleteProduct(Integer.parseInt(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/getProductList")
	@ResponseBody
    public HashMap<Integer, String> getCategoryList(@RequestBody Map<String, Object> payload)
	{
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

