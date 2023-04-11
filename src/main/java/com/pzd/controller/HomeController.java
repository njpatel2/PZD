package com.pzd.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.UserRegistrationDTO;
import com.pzd.mail.EmailSenderService;
import com.pzd.security.CustomUser;

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
			emails.sendRegistrationEmail(registrationDao.getEmail(), request);
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
			session.setAttribute("userId", ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
			session.setAttribute("email", ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEmail());
		}
		
		return mv;
	}

	@RequestMapping("/getRegistrationPage")
	public ModelAndView getRegistrationPage() {
		ModelAndView mv = new ModelAndView("reg");
		return mv;
	}

	@RequestMapping("/getForgotPasswordPage")
	public ModelAndView getForgotPasswordPage() {
		ModelAndView mv = new ModelAndView("ForgotPassword");
		return mv;
	}

	@RequestMapping("/sendForgotPasswordEmail")
	@ResponseBody
	public ModelAndView sendForgotPasswordEmail(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ForgotPassword");

		Random random = new Random();
		int randomNumber = random.nextInt(900000) + 100000;
		emails.sendForgotPasswordEmail((String) payload.get("email"), request, randomNumber);
		request.getSession().setAttribute("OTP", randomNumber);
		request.getSession().setAttribute("email", (String) payload.get("email"));
		return mv;
	}

	@RequestMapping("/verifyOTP")
	@ResponseBody
	public ModelAndView verifyOTP(@RequestParam("OTP") String OTP, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String isOTPValid = "Invalid OTP";
		if (OTP.equals(request.getSession().getAttribute("OTP").toString())) {
			mv.setViewName("ChangePassword");
			request.getSession().removeAttribute("OTP");
			isOTPValid = "Verification Successful";
		} else {
			mv.setViewName("ForgotPassword");

		}
		mv.addObject("isOTPValid", isOTPValid);
		mv.addObject("email", request.getSession().getAttribute("email").toString());
		return mv;
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public ModelAndView updatePassword(@RequestParam("password") String password, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		userService.updatePassword(request.getSession().getAttribute("email").toString(),
				passwordEncoder.encode(password));

		return mv;
	}
	@RequestMapping("/thankyou")
	@ResponseBody
	public ModelAndView thankyou() {
		ModelAndView mv = new ModelAndView("Thankyou");

		return mv;
	}

	/*
	 * @RequestMapping("/user")
	 * 
	 * @ResponseBody public Principal user(Principal principal) { return principal;
	 * }
	 */

}
