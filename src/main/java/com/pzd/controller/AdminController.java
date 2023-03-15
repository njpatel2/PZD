package com.pzd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/home")
    public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView("admin");
		return mv;
	}

}
