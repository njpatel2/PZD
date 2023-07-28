package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pzd.DTO.ProductDTO;
import com.pzd.entities.AlertMessage;
import com.pzd.entities.User;
import com.pzd.mail.EmailSenderService;
import com.pzd.security.CustomUser;
import com.pzd.serviceImpl.ProductServiceImpl;
import com.pzd.serviceImpl.UserServiceImpl;
import com.pzd.utils.PaginationUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/getItems")
	@ResponseBody
	public ArrayList<ProductDTO> getItems(
	        @RequestParam(name = "page") Integer page,
	        @RequestParam(name = "categoryId") Integer categoryId) {

	    ArrayList<ProductDTO> productDTOs = new ArrayList<>();

	    try {
	        productDTOs = productServiceImpl.getProductList(categoryId, page);
	    } catch (Exception e) {
	        throw e;
	    }
	    return productDTOs;
	}

	
	@RequestMapping("/getProfile")
	public ModelAndView getProfile() {
		ModelAndView mv = new ModelAndView("UserProfile");

		String email = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUserEmail();
		HashMap<String, String> userProfileDetails = userServiceImpl.getUserProfileDetails(email);

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

	@RequestMapping("/getUserDetails")
	@ResponseBody
	public HashMap<String, String> getUserDetails(HttpServletRequest request) {

		HashMap<String, String> userDetails = null;

		try {
			userDetails = userServiceImpl.getUserDetails(request.getSession().getAttribute("email").toString());
			request.getSession().setAttribute("contact", userDetails.get("contact"));
			request.getSession().setAttribute("address", userDetails.get("address"));
		} catch (Exception e) {
			throw e;
		}
		return userDetails;
	}

	@RequestMapping("/confirmUserDetails")
	@ResponseBody
	public ModelAndView confirmUserDetails(@RequestParam("contactNumber") String contact, @RequestParam("address") String address, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			// Decode the URL-encoded data

			/*
			 * String jsonData = URLDecoder.decode(encodedData, "UTF-8");
			 * 
			 * // Parse the JSON data using Jackson ObjectMapper objectMapper = new
			 * ObjectMapper(); JsonNode jsonNode = objectMapper.readTree(jsonData);
			 * 
			 * // Access the JSON data using keys String contact =
			 * jsonNode.get("contact").asText(); String address =
			 * jsonNode.get("address").asText();
			 * 
			 * request.getSession().getAttribute("contact").toString();
			 * request.getSession().getAttribute("address").toString();
			 */
			if (!(contact.equals(request.getSession().getAttribute("contact").toString())
					&& address.equals(request.getSession().getAttribute("address").toString()))) {
				userServiceImpl.updateUserContactAndAddress(request.getSession().getAttribute("email").toString(),
						Long.parseLong(contact), address);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("forward:/orders/placeOrder");
		return mv;
	}

}