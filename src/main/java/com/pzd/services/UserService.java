package com.pzd.services;

import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.entities.User;


public interface UserService {
	
	User save(UserRegistrationDTO userdao); 

}
