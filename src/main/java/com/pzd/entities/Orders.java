package com.pzd.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import com.pzd.controller.IdGenerator;



@Entity(name ="Orders")
public class Orders {
	
	@Id
	private long OrderId;
	
	@ManyToOne
	private User user;
	
	@Column
	private String date;
	
	@Column
	private float price;
	
	@Column
	private boolean isCompleted;
	
	
	@OneToMany(mappedBy = "Order", cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetails;
	
	public Orders() {
		
		super();
	}

	public Orders(long id, User user, String date, float price, boolean isCompleted) {
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
		return "Order [id=" + OrderId + ", user=" + user + ", date=" + date + ", price=" + price + ", isCompleted="
				+ isCompleted + "]";
	}
	
	
	

}
