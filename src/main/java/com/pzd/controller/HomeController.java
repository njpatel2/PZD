package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.dao.UserRegistrationDTO;
import com.pzd.services.UserService;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
    public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
//	@RequestMapping("/register")
//    public ModelAndView register()
//	{
//		ModelAndView mv = new ModelAndView("reg");
//		return mv;
//	}
	
	@RequestMapping("/registration")
	public ModelAndView registerUserAccount(@RequestBody UserRegistrationDTO registrationDao)
    {    	
		ModelAndView mv = new ModelAndView("successurl");
		
		registrationDao.setPassword(passwordEncoder.encode(registrationDao.getPassword()));
		System.out.println(registrationDao.getPassword());
    	userService.save(registrationDao);
    	return mv;
    }

	@RequestMapping("/successurl")
    public ModelAndView successurl()
	{
		ModelAndView mv = new ModelAndView("successurl");
		return mv;
	}
}
