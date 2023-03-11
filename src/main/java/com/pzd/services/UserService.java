package com.pzd.services;

import com.pzd.dao.UserRegistrationDTO;
import com.pzd.entities.User;


public interface UserService {
	
	User save(UserRegistrationDTO userdao); 

}
