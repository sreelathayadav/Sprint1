package com.digitalbook.reader.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbook.reader.entity.AuthorResponse;
import com.digitalbook.reader.exception.BookException;
import com.digitalbook.reader.model.Book;
import com.digitalbook.reader.model.CreateBook;
import com.digitalbook.reader.service.BookService;

@RestController
@RequestMapping("/api/v1/digitalbooks/books/readers")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	private KafkaTemplate<String, Book> kafkaTemplate;

	private static final String TOPIC = "kafka1-topic";

	@GetMapping("/publish")
	public String publishBook() {

		// int id = (int)(Math.floor(Math.random()*100));
		kafkaTemplate.send(TOPIC, new Book(1, "", "srilatha@gmail.com", "10-05-1997", "java"));

		return "Published Book entity successfully: " + LocalDateTime.now();
	}

	// User can search books by providing category,author,price,publisher - POST -
	// API 1
	@GetMapping("/books/search")
	public List<CreateBook> getBooks(@RequestParam String category, @RequestParam String authorName,
			@RequestParam long price, @RequestParam String publisher) {
		boolean isActive = true;
		System.out.println("API-1 Controller");
		return bookService.getAllBooks(category, authorName, price, publisher, isActive);
	}

	// Buy Book - POST -API 2
	@PostMapping("/buy")
	public List<String> buyBook(@RequestBody Book book) {
		System.out.println("API-2 Controller");
		int id = book.getBookId();
		String name = book.getName();
		String email = book.getEmailId();
		List<String> ll = new ArrayList<String>();
		// kafkaTemplate.send(TOPIC, new Book(id,"java",name,"10-05-1997",email));
		String response = bookService.saveBookData(book);
		System.out.println("Buybook details published successfully");
		ll.add(response);
		return ll;
	}

	// Reader can get all purchased books -GET - API 3
	@GetMapping("/{emailId}/books")
	public String getAllPurchasedBooks(@PathVariable String emailId) {
		System.out.println("API-3 Controller");
		return bookService.getAllPurchasedBooks(emailId);
	}

	// Reader can read book by passing email is and Book id -GET - API -4
	@GetMapping("/{emailId}/books/{bookId}")
	public String getPurchasedId(@PathVariable String emailId, @PathVariable int bookId) {
		System.out.println("API-4 Controller");
		return bookService.getByEmailandId(emailId, bookId);
	}

	// Query for getting bookname by passing emailid and bookid -GET - API 5
	@GetMapping("/books/{emailId}/books/{bookId}")
	public String getPurchasedBook(@PathVariable String emailId, @PathVariable int bookId) {
		System.out.println("API-5 Controller");
		return bookService.getBookName(emailId, bookId);
	}

	// reader can ask for refund within 24 hoursof payment - POST - API 6
	@GetMapping("/{emailId}/books/{bookId}/refund")
	public List<String> checkIfRefundIsValid(@PathVariable String emailId, @PathVariable int bookId) throws BookException {
		System.out.println("API-6 Controller");
		List<String> ll=new ArrayList<String>();
		String response= bookService.checkingRefund(emailId, bookId);
		 ll.add(response);
		 return ll;
	}

	// Author can create a book - POST - API 9
	@PostMapping("/{authorId}/books")
	public AuthorResponse createBook(@PathVariable int authorId, @RequestBody CreateBook book) {
		System.out.println("API-9 Controller");
		AuthorResponse response = new AuthorResponse();

		try {
			CreateBook bookEntity = bookService.createBookByAuthor(authorId, book);
			response.setBookData(bookEntity);
			response.setMessage("Bookcreated");
		} catch (BookException e) {

			response.setException(e.getMessage());
		}

		return response;
	}

	// Reader can update book -PUT - API 10
	@PutMapping("/{authorId}/books/{bookId}")
	public AuthorResponse updateBook(@PathVariable int authorId, @PathVariable int bookId,
			@RequestBody CreateBook book) {
		System.out.println("API-10 Controller");
		AuthorResponse response = new AuthorResponse();
		try {
			CreateBook bookentity = bookService.updateBookByAuthor(authorId, bookId, book);
			response.setBookData(bookentity);
			response.setMessage("Book is updated");
		} catch (BookException e) {

			response.setException(e.getMessage());
		}
		return response;

	}

	@GetMapping("/getBooks")
	public List<CreateBook> getbooks() throws BookException {
		return bookService.getAllBooks();
	}
	
	//Block book
	@PostMapping("/block/{authorId}/{bookId}")
    public AuthorResponse blockBook(@PathVariable int authorId, @PathVariable int bookId) {
        AuthorResponse response = new AuthorResponse();
        try {
            String message = bookService.blockBook(authorId, bookId);
            response.setMessage(message);
        } catch (Exception e) {
            response.setException(e.getMessage());
        }
        return response;
    }

}
