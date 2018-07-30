package com.syn.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.syn.entities.AuthUser;

public class AuthUserDaoImpl extends BaseDao implements AuthUserDao  {

	@Override
	public AuthUser getAuthUser(int userId) {
		System.out.println("in dao getAuthUser method");
		List<AuthUser> result = null;
		Session session = sessionFactory.openSession();
		try {
			ts = session.beginTransaction();
			Query query = session.getNamedQuery("sp_getAuthUser")
					.setParameter("u_id", userId)
					.setFirstResult(0)
					.setMaxResults(1);
			result = query.list();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) 
				ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}	
		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	public List<AuthUser> getAllAuthUsers() {
		System.out.println("in dao getAllAuthUsers method");
		List<AuthUser> result = null;
		
		return result;
	}

	@Override
	public boolean createAuthUser(AuthUser user) {
		System.out.println("in dao insert function");
		boolean success = true;
		Session session = sessionFactory.openSession();
		try {
			ts = session.beginTransaction();
			System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
			Query query = session.getNamedQuery("sp_insertAuthUser")
					.setParameter("u_name", user.getUsername())
					.setParameter("u_password", user.getPassword());
			System.out.println(query.toString());
			List result = query.list();			
			ts.commit();

		} catch (Exception e) {
			if (ts != null) 
				ts.rollback();
			success = false;
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return success;
	}

	@Override
	public AuthUser getPasswordByUsername(String username) {
		System.out.println("in dao getPasswordByUsername method, username: " + username);
		List<AuthUser> result = null;
		Session session = sessionFactory.openSession();
		try {
			ts = session.beginTransaction();
			Query query = session.getNamedQuery("sp_getPasswordByUsername")
					.setParameter("u_name", username)
					.setFirstResult(0)
					.setMaxResults(1);
			result = query.list();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) 
				ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}	
		System.out.println("password: " + (result.size() > 0 ? result.get(0).getPassword() : null));
		return result.size() > 0 ? result.get(0) : null;	
		
	}

}
