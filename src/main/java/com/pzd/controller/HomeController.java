package com.pzd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.mail.EmailSenderService;
import com.pzd.security.CustomUserDetails;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private com.pzd.service.UserService userService;
	
	@Autowired
	private EmailSenderService emails; 


	static String successmsg = "";

	@RequestMapping({ "/", "/login" })
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		if (!successmsg.isBlank()) {
			mv.addObject("successMessage", "Successfully Registered");
		}
		successmsg = "";
		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("login");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return mv;
	}

	/**
	 * @param registrationDao
	 * @return
	 */
	@RequestMapping("/registration")
	@ResponseBody
	public String registerUserAccount(@RequestBody UserRegistrationDTO registrationDao, HttpServletRequest request) {
		successmsg = "";
		try {
			registrationDao.setPassword(passwordEncoder.encode(registrationDao.getPassword()));
			System.out.println(registrationDao.getPassword());
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			emails.sendSimpleEmail(userDetails.getUserEmail(),request);
			userService.save(registrationDao);
			successmsg = "Successfully registered";
		} catch (Exception e) {
			throw e;
		}
		return successmsg;
	}

	@RequestMapping("/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			session.setAttribute("userRole", authentication.getAuthorities().toString());
		}
		return mv;
	}
}
