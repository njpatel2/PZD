package com.pzd.dao;

import javax.persistence.Column;

public class UserRegistrationDTO {
	
	private String name;
	private String email;
	private String password;
	private String role;
//	private boolean enabled;
	
	
	
	public UserRegistrationDTO(String name,String email, String password, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
//		this.enabled = true;
	}
	
	
	public UserRegistrationDTO() {
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
//	public boolean isEnabled() {
//		return enabled;
//	}
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}


	@Override
	public String toString() {
		return "UserRegistrationDao [email=" + email + ", password=" + password + ", role=" + role +  ", name=" + name + "]";
	}
	
	

}
