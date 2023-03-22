package com.pzd.service;

import java.util.HashMap;
import java.util.List;

public interface CartService {
	
	public List<HashMap<String, String>> getAllCartItemsOfUser(int userId);

	public void addToCart(int userId, int productId, int quantity);

	public float getTotalCartPrice(int userId);

	public void deleteProductFromCart(int userId, int productId);


}
