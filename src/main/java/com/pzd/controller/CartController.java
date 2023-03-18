package com.pzd.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.CartDTO;
import com.pzd.entities.Cart;
import com.pzd.services.CartServiceImpl;
import com.pzd.services.ProductServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private CartServiceImpl cartServiceImpl;

	@RequestMapping("/cart")
	public ModelAndView adminMainPage() {
		ModelAndView mv = new ModelAndView("Cart");;
		return mv;
	}
	
	// get all cart items ,
	// perameters : user id
	// returns: cart
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllCartItems")
	@ResponseBody
	public ArrayList<CartDTO> getAllCartItems() {

		ArrayList<CartDTO> CartDTOs = new ArrayList<>();

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			int userId = Integer.parseInt(((Map<String, String>) authentication.getDetails()).get("userId"));

			CartDTOs = cartServiceImpl.getAllCartItemsOfUser(userId);
		} catch (Exception e) {
			throw e;
		}
		return CartDTOs;
	}

	// insert into cart ,
	// perameters: product id, quantity , user id
	// returns: void
	@RequestMapping("/addToCart")
	@ResponseBody
	public void addToCart(@RequestBody Map<String, Object> payload) {

		try {
			int productId = (Integer) payload.get("productId");
			int quantity = (Integer) payload.get("quantity");

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			int userId =Integer.parseInt( ((Map<String, String>) authentication.getDetails()).get("userId"));

			cartServiceImpl.addToCart(userId, productId, quantity);
		} catch (Exception e) {
			throw e;
		}
	}

	// get updated price for nav bar
	// perameters user id
	// return string
	@RequestMapping("/getTotalCartPrice")
	@ResponseBody
	public void getTotalCartPrice() {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			int userId = Integer.parseInt(((Map<String, String>) authentication.getDetails()).get("userId"));

			cartServiceImpl.getTotalCartPrice(userId);
		} catch (Exception e) {
			throw e;
		}
	}

	//delete from cart
	//product id ,user id
	@RequestMapping("/deleteProductFromCart")
	@ResponseBody
	public void deleteProductFromCart(@RequestBody Map<String, Object> payload) {

		try {
			int productId = (Integer) payload.get("productId");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			int userId = Integer.parseInt(((Map<String, String>) authentication.getDetails()).get("userId"));

			cartServiceImpl.deleteProductFromCart(userId,productId);
		} catch (Exception e) {
			throw e;
		} 
	}
	
	//update the quantity
	@RequestMapping("/updateCartProductQuantity")
	@ResponseBody
	public void updateCartProductQuantity(@RequestBody Map<String, Object> payload) {

		try {
			int productId = (Integer) payload.get("productId");
			int quantity = (Integer) payload.get("quantity");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			int userId = Integer.parseInt(((Map<String, String>) authentication.getDetails()).get("userId"));

			cartServiceImpl.updateCartProductQuantity(userId,productId,quantity);
		} catch (Exception e) {
			throw e;
		} 
	}
	
	
	//checkout 
	//
}
