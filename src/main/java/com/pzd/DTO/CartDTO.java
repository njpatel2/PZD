package com.pzd.DTO;

import com.pzd.entities.Orders;
import com.pzd.entities.Product;
import com.pzd.entities.User;

public class CartDTO {

	private int cartld;

	private User user;

	private Product product;

	private int productQuatity;

	
	
	public CartDTO() {
		super();
	}

	public CartDTO(User user, Product product, int productQuatity) {
		super();
		this.user = user;
		this.product = product;
		this.productQuatity = productQuatity;
	}

	
	public CartDTO(Product product, int productQuatity) {
		super();
		this.product = product;
		this.productQuatity = productQuatity;
	}

	public int getCartld() {
		return cartld;
	}

	public void setCartld(int cartld) {
		this.cartld = cartld;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductQuatity() {
		return productQuatity;
	}

	public void setProductQuatity(int productQuatity) {
		this.productQuatity = productQuatity;
	}

	@Override
	public String toString() {
		return "CartDTO [cartld=" + cartld + ", user=" + user + ", product=" + product + ", productQuatity="
				+ productQuatity + "]";
	}

}
