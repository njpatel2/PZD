package com.pzd.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pzd.entities.User;
import com.pzd.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	HttpServletRequest request;
	
//	public CustomUserDetails customUserDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByName(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("not found user");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		request.getSession().setAttribute("customUserDetails", customUserDetails);
		return customUserDetails;
	}

}
