package com.pzd.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.security.core.Authentication;

import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.entities.User;
import com.pzd.security.CustomUser;


public interface UserService {
	
	public User save(UserRegistrationDTO userdao);

	public void processOAuthPostLogin(CustomUser oauth, Authentication authentication);

	public HashMap<String, String> getUserProfileDetails(String email);

	public void updateUserByUserEmail(String email, String name, long contact, String address);

	int getCustomerCount();

	ArrayList<String> getCustomerList();

	HashMap<String, Integer> getCountOfCustomerProductCategory();

	void updatePassword(String attribute, String password);

	HashMap<String, String> getUserDetails(String email);


	void updateUserContactAndAddress(String email, long contact, String address);


}
