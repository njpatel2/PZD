package com.pzd.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.DTO.CartDTO;
import com.pzd.entities.Cart;
import com.pzd.entities.Product;
import com.pzd.entities.User;
import com.pzd.repository.CartRepository;
import com.pzd.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public ArrayList<HashMap<String, String>> getAllCartItemsOfUserToPopulateOnUi(int userId) {

		ArrayList<Object[]> CartProducts = cartRepository.getAllCartItemsOfUserToPopulateOnUi(userId);

		ArrayList<HashMap<String, String>> listOfCartItems = new ArrayList<>();

		
		for (Object[] objects : CartProducts) {
			HashMap<String, String> cartItems = new HashMap<>();
			cartItems.put("productName", (String) objects[0]);
			cartItems.put("productImage", (String) objects[1]);
			cartItems.put("productPrice", Float.toString((Float) objects[2]));
			cartItems.put("productQuantity", Integer.toString((Integer) objects[3]));
			cartItems.put("productId", Integer.toString((Integer) objects[4]));
			cartItems.put("subTotal", Float.toString((Float) objects[2] * (Integer) objects[3]));
			listOfCartItems.add(cartItems);
		}
		return listOfCartItems;
	}

	@Override
	public void addToCart(int userId, int productId, int quantity) {

		// check if the product is available or not in cart
		Cart product = cartRepository.getSingleProductFromCart(userId, productId);
		if (product != null) {
			int totalQuantity = product.getProductQuatity() + quantity;
			cartRepository.updateCartProductQuantity(totalQuantity, userId, productId);
		} else {
			Cart c = new Cart(new User(userId), new Product(productId), quantity);
			cartRepository.save(c);
		}

	}

	@Override
	public float getTotalCartPrice(int userId) {
		ArrayList<Object[]> cartItems = cartRepository.getAllCartItemsOfUserToPopulateOnUi(userId);
		float totalPrice = 0;

		for (Object[] objects : cartItems) {
			totalPrice = totalPrice
					+ (Float.parseFloat(objects[2].toString()) * Integer.parseInt(objects[3].toString()));
		}
		return totalPrice;
	}

	@Override
	public void deleteProductFromCart(int userId, int productId) {

		cartRepository.deleteProductFromCart(productId, userId);

	}

	public void updateCartProductQuantity(int userId, int productId, int quantity) {

		cartRepository.updateCartProductQuantity(quantity, userId, productId);

	}

	public ArrayList<CartDTO> getAllCartItemsOfUser(int userId) {

		ArrayList<Cart> CartProducts = cartRepository.getAllCartItemsByUserId(userId);

		ArrayList<CartDTO> cartDTOs = new ArrayList<>();

		for (Cart CartProduct : CartProducts) {

			CartDTO cartDto = new CartDTO();

			BeanUtils.copyProperties(CartProduct, cartDto);
			cartDTOs.add(cartDto);
		}

		return cartDTOs;
	}

	public void deleteProductFromCartByUserId(int userId) {

		cartRepository.deleteAllByUserId(userId);
	}

}
