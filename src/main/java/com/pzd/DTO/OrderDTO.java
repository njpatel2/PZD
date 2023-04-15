package com.pzd.DTO;

import com.pzd.entities.User;

public class OrderDTO {
	
	
	private long OrderId;
	
	private User user;
	
	private String date;
	
	private float price;
	
	private boolean isCompleted;
	


	public OrderDTO() {
		super();
	}

	public OrderDTO(long id, User user, String date, float price, boolean isCompleted) {
		super();
		this.OrderId = id;
		this.user = user;
		this.date = date;
		this.price = price;
		this.isCompleted = isCompleted;
	}

	public long getId() {
		return OrderId;
	}

	public void setId(long id) {
		this.OrderId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + OrderId + ", user=" + user + ", date=" + date + ", price=" + price + ", isCompleted="
				+ isCompleted + "]";
	}

	
}
