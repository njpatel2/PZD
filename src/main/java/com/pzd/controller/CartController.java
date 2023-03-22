package com.pzd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.security.CustomUserDetails;
import com.pzd.serviceImpl.CartServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartServiceImpl cartServiceImpl;

	@RequestMapping("/cart")
	public ModelAndView adminMainPage() {
		ModelAndView mv = new ModelAndView("Cart");
		return mv;
	}

	// get all cart items ,
	// perameters : user id
	// returns: cart
	@RequestMapping("/getAllCartItems")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getAllCartItems() {

//		HashMap<String, String> CartItems = new HashMap<>();
		ArrayList<HashMap<String, String>> CartItems = new ArrayList<>();
		try {
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			int userId = userDetails.getUserId();
			CartItems = cartServiceImpl.getAllCartItemsOfUser(userId);
		} catch (Exception e) {
			throw e;
		}
		return CartItems;
	}

	// insert into cart ,
	// perameters: product id, quantity , user id
	// returns: void
	@RequestMapping("/addToCart")
	@ResponseBody
	public void addToCart(@RequestBody Map<String, Object> payload) {

		try {
			int productId = Integer.parseInt((String) payload.get("productId"));
			int quantity = Integer.parseInt((String) payload.get("quantity"));

			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			int userId = userDetails.getUserId();

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
	public float getTotalCartPrice() {
		float totalPrice;
		try {
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			int userId = userDetails.getUserId();
			totalPrice = cartServiceImpl.getTotalCartPrice(userId);
		} catch (Exception e) {
			throw e;
		}
		return totalPrice;
	}

	// delete from cart
	// product id ,user id
	@RequestMapping("/deleteProductFromCart")
	@ResponseBody
	public void deleteProductFromCart(@RequestBody Map<String, Object> payload) {

		try {
			int productId = Integer.parseInt((String) payload.get("productId"));
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			int userId = userDetails.getUserId();
			cartServiceImpl.deleteProductFromCart(userId, productId);
		} catch (Exception e) {
			throw e;
		}
	}

	// update the quantity
	@RequestMapping("/updateCartProductQuantity")
	@ResponseBody
	public void updateCartProductQuantity(@RequestBody Map<String, Object> payload) {

		try {
			int productId = Integer.parseInt((String) payload.get("productId"));
			int quantity = Integer.parseInt((String) payload.get("quantity"));
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			int userId = userDetails.getUserId();
			cartServiceImpl.updateCartProductQuantity(userId, productId, quantity);
		} catch (Exception e) {
			throw e;
		}
	}

	// checkout
	//
}
