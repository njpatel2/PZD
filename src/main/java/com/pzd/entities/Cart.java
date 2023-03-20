package com.pzd.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartld;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;
	
	
	private int productQuatity;


	public Cart() {
		super();
	}


	public Cart(User user, Product product, int productQuatity) {
		super();
		this.user = user;
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
		return "Cart [cartld=" + cartld + ", user=" + user + ", product=" + product + ", productQuatity="
				+ productQuatity + "]";
	}
	
	

	
	
	
	
	
}
