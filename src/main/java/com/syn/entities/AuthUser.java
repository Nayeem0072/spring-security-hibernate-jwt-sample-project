package com.syn.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "sp_getAuthUser",
		query = "SELECT * FROM get_auth_user(:u_id)",
		resultClass = AuthUser.class
	),
	@NamedNativeQuery(
		name = "sp_getAllAuthUser",
		query = "SELECT * FROM get_all_auth_user()",
		resultClass = AuthUser.class
	),
	@NamedNativeQuery(
		name = "sp_getPasswordByUsername",
		query = "SELECT * FROM get_password_by_username(:u_name)",
		resultClass = AuthUser.class
	),
	@NamedNativeQuery(
		name = "sp_insertAuthUser",
		query = "SELECT * FROM insert_auth_user(:u_name, :u_password)",
		resultClass = AuthUser.class
	)
})

@Entity
@Table(name = "auth_user")
public class AuthUser {
	@Id
	@Column(name = "user_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	public Integer getId() {
		return id;
	} 
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "Username: " + this.getUsername() + " Password: " + this.getPassword();
	}
	
}

