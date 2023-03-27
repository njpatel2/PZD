//package com.pzd.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import com.pzd.entities.User;
//import com.pzd.repository.UserRepository;
//
//@Service
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		OAuth2User auth2User = super.loadUser(userRequest);
//		User user = userRepository.findByName(auth2User.getName());
//		return new CustomOAuth2User(auth2User,user);
//	}
//
//}