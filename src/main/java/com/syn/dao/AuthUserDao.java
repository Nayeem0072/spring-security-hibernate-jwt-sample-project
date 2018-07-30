package com.syn.dao;

import java.util.List;

import com.syn.entities.AuthUser;

public interface AuthUserDao {
	public AuthUser getAuthUser(int userId);
	public List<AuthUser> getAllAuthUsers();
	public boolean createAuthUser(AuthUser user);
	public AuthUser getPasswordByUsername(String username);
}
