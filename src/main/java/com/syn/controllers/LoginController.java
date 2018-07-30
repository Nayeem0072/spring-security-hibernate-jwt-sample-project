package com.syn.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.syn.dao.AuthUserDao;
import com.syn.dao.AuthUserDaoImpl;
import com.syn.entities.AuthUser;

@Controller
@RequestMapping("/home")
public class LoginController {
	private static final AuthUserDao authUserDao = new AuthUserDaoImpl();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();			
		
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}
		
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		String referrer = this.getReferrer(request);		
		if(referrer != null) {
			model.addObject("referrer", referrer);
		}
		
		model.setViewName("home/login");
		return model;

	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/home/login?logout";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest request){
		AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userId = user.getId();
		String username = user.getUsername();
		
		ModelAndView model = new ModelAndView("home/dashboard");
		model.addObject("user", username);
		model.addObject("userId", userId);
		return model;
	}
	
	
	private String getReferrer(HttpServletRequest request){
		String referrer = request.getHeader("referer");
		System.out.println("referer: " + referrer);
		return referrer;
	}
}
