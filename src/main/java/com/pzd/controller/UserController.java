package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pzd.DTO.ProductDTO;
import com.pzd.mail.EmailSenderService;
import com.pzd.serviceImpl.ProductServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	

	@RequestMapping("/getItems")
	@ResponseBody
	public ArrayList<ProductDTO> getItems() {

		ArrayList<ProductDTO> productDTOs = new ArrayList<>();

		try {
			productDTOs = productServiceImpl.getProductList();
		} catch (Exception e) {
			throw e;
		}
		return productDTOs;
	}

}