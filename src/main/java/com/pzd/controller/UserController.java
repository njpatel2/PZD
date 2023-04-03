package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.ProductDTO;
import com.pzd.mail.EmailSenderService;
import com.pzd.security.CustomUser;
import com.pzd.service.UserService;
import com.pzd.serviceImpl.ProductServiceImpl;
import com.pzd.serviceImpl.UserServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	

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
	
	@RequestMapping("/getProfile")
	public ModelAndView getProfile() {
		ModelAndView mv = new ModelAndView("UserProfile");
		
		String email = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEmail();
		HashMap<String,String> userProfileDetails = userServiceImpl.getUserProfileDetails(email);
		
		mv.addObject("name", userProfileDetails.get("name"));
		mv.addObject("email", userProfileDetails.get("email"));
		mv.addObject("contact", userProfileDetails.get("contact"));
		mv.addObject("address", userProfileDetails.get("address"));
		mv.addObject("itemsInCart", userProfileDetails.get("itemsInCart"));
		return mv;

		}

	@RequestMapping("/updateUserProfile")
	@ResponseBody
	public void updateUserProfile(@RequestBody Map<String, Object> payload) {

		try {
			String name = (String) payload.get("name");
			String email = (String) payload.get("email");
			Long contact = Long.parseLong((String) payload.get("contact"));

			String address = (String) payload.get("address");

			userServiceImpl.updateUserByUserEmail(email, name, contact, address);
			
		} catch (Exception e) {
			throw e;
		}
	}
}