package com.digitalbook.reader.entity;

public class AuthorModel {
	private int authorId;
	private String authorEmailId;
	private String username;
	private String password;
	
	public int getAuthorId() {
		return authorId;
	}
	public void setaId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorEmailId() {
		return authorEmailId;
	}
	public void setAuthorEmailId(String authorEmailId) {
		this.authorEmailId = authorEmailId;
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
	public String toString() {
		return "AuthorModel [aId=" + authorId + ", authorEmailId=" + authorEmailId + ", username=" + username + ", password="
				+ password + "]";
	}
	
	

}
