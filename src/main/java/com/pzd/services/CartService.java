package com.pzd.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pzd.DTO.CartDTO;
import com.pzd.entities.Cart;

public interface CartService {
	
	public List<HashMap<String, String>> getAllCartItemsOfUser(int userId);

	public void addToCart(int userId, int productId, int quantity);

	public float getTotalCartPrice(int userId);

	public void deleteProductFromCart(int userId, int productId);


}
