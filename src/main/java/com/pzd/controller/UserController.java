package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.dao.UserRegistrationDTO;
import com.pzd.entities.Course;
import com.pzd.entities.User;
import com.pzd.repository.UserRepository;
import com.pzd.security.UserDetailsServiceImpl;
import com.pzd.services.CourseServiceimpl;
import com.pzd.services.UserService;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
    @Autowired
	public CourseServiceimpl courseService = new CourseServiceimpl();
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDetailsServiceImpl detailsServiceImpl;
    
    public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
    

	@RequestMapping("/nikunj")
    public ModelAndView nikunj()
	{
		ModelAndView mv = new ModelAndView("successurl");
		return mv;
	}
	
	
	@RequestMapping("/home")
    public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
//	@RequestMapping("/login")
//    public ModelAndView login(@ModelAttribute User user)
//	{
//		ModelAndView mv = new ModelAndView("login");
		
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
//		detailsServiceImpl.loadUserByUsername(user.getName());
		
//		return mv;
//	}
   
	@PostMapping("/nnn")
	public ModelAndView registration() {
		
			ModelAndView mv = new ModelAndView("login");
//			userService.save(user);
			
			mv.setViewName("login");
			mv.addObject("successMessage", "Successfully registered");
			return mv;
		}
	
//	public String showRegistrationForm()
//	{
//		return "redirect:/registration?sucess";
//	}
    
	@PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDao)
    {    	
    	userService.save(registrationDao);
//    	return "redirect:/registration?sucess";
    	return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	//get the courses. 
	//NOTE : Here, we will not directly return the data. we will instead consult the service layer for the same.
	
//	@GetMapping("/courses")
//	public List<Course> getCourses()
//	{
//		return this.courseService.getCourses();
//	}
	
	
	
	@GetMapping("/admin")
	public ModelAndView getAdmin()
	{
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
//	@GetMapping("/courses/{courseId}")
//	public Course getCourse(@PathVariable String courseId)
//	{
//		return this.courseService.getCourse(Long.parseLong(courseId));
//		//Long.parseLong will convert the String to Long.
//	}
//	@RequestMapping("/error")
//	public String handleError(HttpServletRequest request) {
//	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//	    
//	    if (status != null) {
//	        Integer statusCode = Integer.valueOf(status.toString());
//	    
//	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
//	            return "error-404";
//	        }
//	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//	            return "error-500";
//	        }
//	    }
//	    return "error";
//	    }
//	@GetMapping("/login")
//	@RequestBody
//	public String login(@RequestMapping )
//	{
//		//this course will be returned after once it is added to the database.
//		return "you are logged in";
//	}

	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course)
	{
		//this course will be returned after once it is added to the database.
		return this.courseService.addCourse(course);
	}
	
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course)
	{
		//this course will be returned after once it is added to the database.
		return this.courseService.updateCourse(course);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public Course deleteCourse(@PathVariable String courseId)
	{
		return this.courseService.deleteCourse(Long.parseLong(courseId));
	}
	
//	@ExceptionHandler(BadCredentialsException.class)
//	  public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
//	    ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password");
//	    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
//	  }
	
	
	
}
