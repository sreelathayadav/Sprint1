package com.digitalbook.author.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AuthorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;
	private String emailId;
	private String username;
	private String password;
	public AuthorDetails() {
		super();
	}
	public AuthorDetails(int authorId, String emailId, String username, String password) {
		super();
		this.authorId = authorId;
		this.emailId = emailId;
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthorDetails [authorId=" + authorId + ", emailId=" + emailId + ", username=" + username + ", password="
				+ password + "]";
	}
	
	public int getauthorId() {
		return authorId;
	}
	public void setauthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
