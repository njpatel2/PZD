package com.pzd.service;

import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.entities.User;


public interface UserService {
	
	User save(UserRegistrationDTO userdao); 

}
