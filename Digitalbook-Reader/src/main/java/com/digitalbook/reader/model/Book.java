package com.digitalbook.reader.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Book {
	@Id
    private int bookId;
	private String name;
    private String emailId;
    private String date;
    private String bookName;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Book(int bookId, String name, String emailId, String date, String bookName) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.emailId = emailId;
		this.date = date;
		this.bookName = bookName;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", emailId=" + emailId + ", date=" + date + ", bookName="
				+ bookName + "]";
	}
	public Book() {
		super();
	}
	
}
