package com.digitalbook.reader.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbook.reader.model.Book;
import com.digitalbook.reader.model.CreateBook;

public interface BookRepository extends JpaRepository<Book, Integer> {
	// Reader can get all purchased books -GET - API 3
	@Query("select D.bookName from Book D where D.emailId=:emailId")
	public String findByEmail(String emailId);

	// Reader can read book by passing email is and Book id -GET - API 4
	@Query("select D.bookName from Book D where D.emailId=emailId and D.bookId=bookId")
	public String filterByBookId(String emailId, int bookId);

	// Query for getting bookname by passing emailid and bookid -GET - API 5
	@Query("select D.bookName from Book D where D.emailId=?1 and D.bookId=?2")
	public String filterById(String emailId, int bookId);

	// reader can ask for refund within 24 hoursof payment - POST - API 6
	@Query("select D.date from Book D where D.bookId=?1")
	public String getDateFromBuyBook(int bookId);

	// reader can ask for refund within 24 hoursof payment - POST - API 6
	@Transactional
	@Modifying
	@Query("delete from Book D where D.bookId=?1")
	public void deleteById(int bookId);

}
