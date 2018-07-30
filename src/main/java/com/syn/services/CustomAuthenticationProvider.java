package com.syn.services;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.syn.dao.AuthUserDao;
import com.syn.dao.AuthUserDaoImpl;
import com.syn.entities.AuthUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private static final AuthUserDao authUserDao = new AuthUserDaoImpl();
	
	@Autowired
	private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	@Override 
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		AuthUser user = authUserDao.getPasswordByUsername(username);

		if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("Username not found.");
		}
		if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
			throw new BadCredentialsException("Wrong password.");
		}
		
		return new UsernamePasswordAuthenticationToken(user, password, null);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}