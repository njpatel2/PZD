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

}
