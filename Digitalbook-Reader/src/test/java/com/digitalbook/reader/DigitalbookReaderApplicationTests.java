package com.digitalbook.reader;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digitalbook.reader.controller.BookController;
import com.digitalbook.reader.exception.BookException;
import com.digitalbook.reader.model.Book;
import com.digitalbook.reader.model.CreateBook;
import com.digitalbook.reader.service.BookService;

@SpringBootTest
class DigitalbookReaderApplicationTests {

	@Test
	void contextLoads() {
	
	}
	
		@Mock
		BookService bookService;

		@InjectMocks
		BookController bookController;
		
		
		//Buy book test case
		public void buyBookTest() {
			Book book = new Book();
			book.setBookId(1);
			book.setBookName("java");
			book.setEmailId("srilatha@gmail.com");
			book.setName("srilatha");
			book.setDate("10-05-1997");
			when(bookService.saveBookData(book)).thenReturn(book.toString());
			bookService.saveBookData(book);

		}
		
		//create book testcase
		public void createBookTest() {
			CreateBook cbook = new CreateBook();
			cbook.setBookId(1);
			cbook.setBookName("java");
			cbook.setCategory("language");
			cbook.setContents("java is a programming language");
			cbook.setPrice(200);
			cbook.setAuthorId(1);
			cbook.setAuthorName("james");
			try {
				when(bookService.createBookByAuthor(1,cbook)).thenReturn(cbook);
			} catch (BookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	

}
