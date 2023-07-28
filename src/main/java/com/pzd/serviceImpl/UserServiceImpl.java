package com.pzd.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.pzd.DTO.OrderDTO;
import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.entities.Orders;
import com.pzd.entities.User;
import com.pzd.repository.UserRepository;
import com.pzd.security.CustomUser;
import com.pzd.service.UserService;

@Service
public class UserServiceImpl extends DefaultOAuth2UserService implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	HttpServletRequest request;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void processOAuthPostLogin(CustomUser oauth, Authentication authentication) {
		try {
			String name = oauth.getName();
			String email = oauth.getUserEmail();
			User existUser = userRepository.findByEmail(email);
			List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
			if (existUser == null) {
				User newUser = new User();
				newUser.setName(name);
				newUser.setEmail(email);
				newUser.setProvider("GOOGLE");
				newUser.setRole(roles.toString());

				userRepository.save(newUser);
			}
//			else {
//				oauth.setUser(existUser);
//			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public User save(UserRegistrationDTO userdao) {

		try {
			User user = new User(userdao.getName(), userdao.getEmail(), userdao.getPassword(), userdao.getRole(),
					userdao.getContactNumber(),userdao.getAddress());
			return userRepository.save(user);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail){
		try {
			User user = userRepository.findByEmail(userEmail);

			if (user == null) {
				throw new UsernameNotFoundException("User : '"+userEmail+"'  is not registered, Please register first");
			}
			else if (user.getProvider() == "GOOGLE")
			{
				throw new Exception("User : '"+userEmail+"' is registered with Google sign in, Please use Google signin to login");
			}
			CustomUser customUserDetails = new CustomUser(user);
			request.getSession().setAttribute("customUserDetails", customUserDetails);
			return customUserDetails;
		}
		catch (UsernameNotFoundException e) {
	        throw e;
	    } catch (Exception e) {
	    	throw new RuntimeException("User : '"+userEmail+"' is registered with Google sign in, Please use Google signin to login", e);
		}
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {

		
		User existUser;
		
			OAuth2User auth2User;
			try {
				auth2User = super.loadUser(userRequest);
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//				processOAuthPostLogin((CustomUser) authentication.getPrincipal(), authentication);
				if (auth2User == null) {
					throw new OAuth2AuthenticationException(
							"Exception occured while loading user using Google authentication");
				}
				existUser = userRepository.findByEmail(auth2User.getAttribute("email"));
				if (existUser == null) {
					 User user = new User();
				        user.setName(auth2User.getAttribute("name"));
				        user.setEmail(auth2User.getAttribute("email"));
				        List<String> roles = auth2User.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				                        .collect(Collectors.toList());
				        user.setRole(roles.toString());
				        user.setProvider("GOOGLE");
				        return new CustomUser(auth2User,user);
				} 
				else {
					return new CustomUser(auth2User,existUser);
				}
			} catch (Exception e) {
				throw e;
			}
		
	}

	@Override
	public HashMap<String,String> getUserProfileDetails(String email) {
		
		HashMap<String,String>  userDetails = new HashMap();
		
		User user = userRepository.findByEmail(email);
		
		userDetails.put("name", user.getName());
		userDetails.put("email", user.getEmail());
		userDetails.put("contact", Long.toString(user.getContactNumber()));
		userDetails.put("address", user.getAddress());
		userDetails.put("itemsInCart", Integer.toString(user.getCart().size()));
		
		return userDetails;
	}
	
	@Override
	public HashMap<String,String> getUserDetails(String email) {
		
		HashMap<String,String>  userDetails = new HashMap();
		
		User user = userRepository.findByEmail(email);
		
		userDetails.put("contact", Long.toString(user.getContactNumber()));
		userDetails.put("address", user.getAddress());
		
		return userDetails;
	}

	@Override
	public void updateUserByUserEmail(String email,String name,long contact, String address) {
		userRepository.updateUserByEmail(email, name, contact, address);
	}

	@Override
	public int getCustomerCount() {
		
//		userRepository.getCustomerCount();
		return userRepository.getCustomerCount();
	}

	@Override
	public ArrayList<HashMap<String, String>> getCustomerList() {
		ArrayList<String> userList = null;
		HashMap < ArrayList< String>, String> userDetails = new HashMap<>();
//		ArrayList<ArrayList< String>>  = new ArrayList<>();
		ArrayList<HashMap<String, String>> userList2 = new ArrayList<>();
		userList = userRepository.getCustomerNameList();
		for (String u : userList) {
			
			String[] s = u.split(",");
			HashMap<String, String> singleUser = new HashMap<>();
			
			singleUser.put("id", s[0]);
			singleUser.put("username", s[1]);
			singleUser.put("email",  s[2]);
			singleUser.put("contact",  s[3]);
			if(s[4].contains("ADMIN")) {
				singleUser.put("role","Admin");
			}
			else {
				singleUser.put("role","User");
			}
			
			userList2.add(singleUser);
		}
		return userList2;
	}
	@Override
	public HashMap<String,Integer> getCountOfCustomerProductCategory() {
		
		String result = userRepository.getCountOfCustomerProductCategory();
		String[] spillteResult = result.split(",");
		HashMap<String, Integer> hmap = new HashMap<>();
		
		hmap.put("userCount", Integer.parseInt(spillteResult[0]));
		hmap.put("productCount", Integer.parseInt(spillteResult[1]));
		hmap.put("categoryCount", Integer.parseInt(spillteResult[2]));
		
		return hmap;
	}

	@Override
	public void updatePassword(String email, String password) {
		
		userRepository.updatePassword(email, password);
		
	}

	@Override
	public void updateUserContactAndAddress(String email,long contact, String address) {
		
		userRepository.updateUserContactAndAddress(email,contact,address);
		
	}
}
