package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.ProductDTO;
import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.entities.Course;
import com.pzd.entities.User;
import com.pzd.repository.UserRepository;
import com.pzd.security.UserDetailsServiceImpl;
import com.pzd.services.CategoryServiceImpl;
import com.pzd.services.CourseServiceimpl;
import com.pzd.services.ProductServiceImpl;
import com.pzd.services.UserService;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
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