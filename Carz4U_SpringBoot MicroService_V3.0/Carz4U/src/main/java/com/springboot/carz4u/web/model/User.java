package com.springboot.carz4u.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="user_roles")
	private String userRoles;
	
	@Column(name="active")
	private boolean activeUser;

	@Column(name="id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isActiveUser() {
		return activeUser;
	}

	public void setActiveUser(boolean activeUser) {
		this.activeUser = activeUser;
	}	
}
