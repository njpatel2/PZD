package com.pzd.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Provider;


@Entity(name ="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	
	private String role;
	
	private long contactNumber;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> cart = new ArrayList<>();
	
	@ManyToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Orders> orders;

	private String provider;
	
	private String address;
	
	
 
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvider() {
        return provider;
    }
 
    public void setProvider(String string) {
        this.provider = string;
    }
	
	
	public User(){};
	
	
	
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
	}

	public User(String name, String email, String password, String role, long contactNumber, String address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
		this.address = address;
	}

	
	public User(String name, String email, String password, String role, long contactNumber, List<Cart> cart) {
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

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
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
				+ ", contactNumber=" + contactNumber + ", cart=" + cart + ", provider=" + provider + ", address="
				+ address + "]";
	}

	

	
	
}