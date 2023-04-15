package com.pzd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.security.CustomUser;
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

	// on checkout place tha page to get the address. and save it to the databse.

	// get all cart items ,
	// perameters : user id
	// returns: cart
	@RequestMapping("/getAllCartItems")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getAllCartItems() {

		ArrayList<HashMap<String, String>> CartItems = new ArrayList<>();
		try {
			CartItems = cartServiceImpl.getAllCartItemsOfUserToPopulateOnUi(
					((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
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

			cartServiceImpl.addToCart(
					((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId(),
					productId, quantity);
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

			totalPrice = cartServiceImpl.getTotalCartPrice(
					((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());

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
			int productId = Integer.parseInt((String) payload.get("productId").toString());
			cartServiceImpl.deleteProductFromCart(
					((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId(),
					productId);
		} catch (Exception e) {
			throw e;
		}

	}

	// update the quantity
	@RequestMapping("/updateCartProductQuantity")
	@ResponseBody
	public void updateCartProductQuantity(@RequestBody Map<String, Object> payload) {

		try {
			int productId = Integer.parseInt((String) payload.get("productId").toString());
			int quantity = Integer.parseInt((String) payload.get("quantity").toString());
			cartServiceImpl.updateCartProductQuantity(
					((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId(),
					productId, quantity);
		} catch (Exception e) {
			throw e;
		}
	}

	// checkout
	//
}
