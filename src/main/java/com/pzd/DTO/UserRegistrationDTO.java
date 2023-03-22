package com.pzd.DTO;

public class UserRegistrationDTO {

	private String name;
	private String email;
	private String password;
	private String role;
	private int contactNumber;
//	private boolean enabled;

	public UserRegistrationDTO() {
	}

	public UserRegistrationDTO(String name, String email, String password, String role, int contactNumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
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

	@Override
	public String toString() {
		return "UserRegistrationDTO [name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", contactNumber=" + contactNumber + "]";
	}

}
