package com.ibm.ms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCredential {
	
	@Id
	String username;
	String password;

	
	public UserCredential() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public UserCredential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	

}
