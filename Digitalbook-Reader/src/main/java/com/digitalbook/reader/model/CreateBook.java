package com.digitalbook.reader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CreateBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String authorName;
	private int authorId;
	private String bookName;
	private double price;
	private String publisher;
	private String publishedDate;
	private String category;
	private boolean active;
	public CreateBook() {
		super();
	}
	public CreateBook(int bookId, String authorName, int authorId, String bookName, double price, String publisher,
			String publishedDate, String category, boolean active, String contents, String updatedDate) {
		super();
		this.bookId = bookId;
		this.authorName = authorName;
		this.authorId = authorId;
		this.bookName = bookName;
		this.price = price;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.category = category;
		this.active = active;
		this.contents = contents;
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "CreateBook [bookId=" + bookId + ", authorName=" + authorName + ", authorId=" + authorId + ", bookName="
				+ bookName + ", price=" + price + ", publisher=" + publisher + ", publishedDate=" + publishedDate
				+ ", category=" + category + ", active=" + active + ", contents=" + contents + ", updatedDate="
				+ updatedDate + "]";
	}
	private String contents;
	private String updatedDate;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	

}
