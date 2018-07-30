package com.syn.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.syn.dao.AuthUserDao;
import com.syn.dao.AuthUserDaoImpl;
import com.syn.entities.AuthUser;
import com.syn.helpers.JWTokenHandler;
import com.syn.services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/authusers")
public class TokenTestController {
	private AuthUserDao authUserDao;
	private static final JwtService jwtService = new JwtService();
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public TokenTestController(){
		this.authUserDao = new AuthUserDaoImpl();		  
	}       

	@RequestMapping(value = "/user/{u_id}", method = RequestMethod.GET)
	@ResponseBody
	public AuthUser getUser(@PathVariable int u_id) {
		return authUserDao.getAuthUser(u_id);
	}
	
	@RequestMapping(value = "/userdetailsapi/{u_id}", method = RequestMethod.GET)	
	@ResponseBody
	public AuthUser getUserApi(@PathVariable int u_id) {
		RestTemplate restTemplate = new RestTemplate();
		AuthUser user = restTemplate.getForObject("http://172.16.26.220:8080/EkPay/authusers/user/" + u_id, AuthUser.class);
		
		return user;
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public boolean createUser(@RequestBody AuthUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return authUserDao.createAuthUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public String loginUser(@RequestBody AuthUser user) {
		AuthUser userDetails = authUserDao.getPasswordByUsername(user.getUsername());    
		if(userDetails != null){
			if(bCryptPasswordEncoder.matches(user.getPassword(), userDetails.getPassword())){
				return "Login Successful. Token: " + jwtService.getToken(userDetails);
			}
			else{
				return "Login Failed";
			}
		}
		else{
			return "Login Failed";
		}
	}  

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public String verify() {
		return "verified";
	}

}
