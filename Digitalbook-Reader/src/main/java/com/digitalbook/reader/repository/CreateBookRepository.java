package com.digitalbook.reader.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digitalbook.reader.model.CreateBook;



public interface CreateBookRepository extends JpaRepository<CreateBook, Integer> {
	
	
	
	//Getting book name for storing Book table
	 @Query("select b.bookName from CreateBook b where b.bookId=?1")
	    public String filterByBookId(int bookId);
    
 // Reader can update book -PUT - API 10
 	@Query("select D from CreateBook D where D.authorId=?1 and D.bookId=?2")
 	Optional<CreateBook> getBookDetailsByBookIdAndAuthorId(int authorId, int bookId);
 	
 	@Query("select D from CreateBook D where D.authorId=?1 and D.bookId=?2")
    Optional<CreateBook> blockBook(int authorId, int bookId);
 	

}
