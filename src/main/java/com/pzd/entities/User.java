package com.pzd.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name ="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	
	private String role;
	
	private int contactNumber;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> cart = new ArrayList<>();

	
	
	User(){};
	
	
	
	public User(int id) {
		super();
		this.id = id;
	}



	public User(String name, String email, String password, String role) {
		super();
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
//		this.enabled = enabled;
	}

	public User(String name, String email, String password, String role, int contactNumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
	}

	
	public User(String name, String email, String password, String role, int contactNumber, List<Cart> cart) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", contactNumber=" + contactNumber + ", cart=" + cart + "]";
	}

	
	
}