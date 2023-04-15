package com.pzd.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity(name ="OrderDetails")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailsId;
	
	@ManyToOne
	private Orders Order;
	
	@ManyToOne
	private Product product;
	
	private int productQuantity;
	
	public OrderDetails() {
		super();
	}

	public OrderDetails(Orders order, Product product, int productQuantity) {
		super();
		this.Order = order;
		this.product = product;
		this.productQuantity = productQuantity;
	}

	
	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Orders getOrder() {
		return Order;
	}

	public void setOrder(Orders order) {
		Order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "OrderDetails [Order=" + Order + ", product=" + product + "]";
	}
	
	
	
}
