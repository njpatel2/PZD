package com.pzd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.entities.User;
import com.pzd.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public User save(UserRegistrationDTO userdao) {
		// TODO Auto-generated method stub
		
		User user = new User(userdao.getName(), userdao.getEmail(),userdao.getPassword(),userdao.getRole(),userdao.getContactNumber());		
		return userRepository.save(user);
	}
	
	

}
