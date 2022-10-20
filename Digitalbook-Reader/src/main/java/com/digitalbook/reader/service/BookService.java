package com.digitalbook.reader.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbook.reader.client.AuthorServiceClient;
import com.digitalbook.reader.entity.AuthorModel;
import com.digitalbook.reader.exception.BookException;
import com.digitalbook.reader.model.Book;
import com.digitalbook.reader.model.CreateBook;
import com.digitalbook.reader.repository.BookRepository;
import com.digitalbook.reader.repository.CreateBookRepository;


@Service
public class BookService {
	@Autowired
	BookRepository bookRepo;
	@Autowired
	CreateBookRepository createBook;
	
	@Autowired
	AuthorServiceClient authorServiceClient;
	
	//User can search books by providing category,author,price,publisher  - POST - API 1
	 public List<CreateBook> getAllBooks(String category, String author, long price, String publisher,boolean isActive)
	    {
		 	System.out.println("API-1 Service");
	        List<CreateBook> allBooks=createBook.findAll();
	        System.out.println("All books" +allBooks  + "size  "+ allBooks.size());
	        if(category !=null && author !=null && price !=0 && publisher !=null)
	        {
	            System.out.println("inside if block");
	            allBooks=allBooks.stream()
	                        .filter(bo ->bo.getCategory().equals(category))
	                        .filter(bo ->bo.getAuthorName().equals(author))
	                        .filter(bo ->bo.getPrice() <= price)
	                        .filter(bo ->bo.getPublisher().equals(publisher))
	                        .filter(bo ->bo.isActive() ==isActive)
	                        .collect(Collectors.toList());
	        }
	        System.out.println("allbooks "+allBooks);
	        return allBooks;
	    }

	// Buy Book - POST -API 2
	public String saveBookData(Book book)
    {
		System.out.println("API-2 Service");
		  int bookId = book.getBookId();
	        System.out.println("book id API 2" + bookId);
	        String bookName = createBook.filterByBookId(bookId);
	        System.out.println("bookName API 2" + bookName);
		int a = ThreadLocalRandom.current().nextInt();
        int d=Math.abs(a);
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date=new Date();
		book.setDate(formatter.format(date));
		book.setBookName(bookName);
		bookRepo.save(book);
        return "you have successfully purchased book and your book id is " + d;
        
    }
	
	//Reader can get all purchased books -GET - API 3
		public String getAllPurchasedBooks(String emailId) {
			System.out.println("API-3 Service");
			return bookRepo.findByEmail(emailId);
		}
		
		//Reader can read book by passing email is and Book id -GET - API 4
		public String getByEmailandId(String emailId, int bookId) {
			System.out.println("API-4 Service");
			return bookRepo.filterByBookId(emailId, bookId);
		}
		
		// Reader can read a book by passing email id and purchased id - POST - API 5
	    public String getBookName(String emailId, int bookId) {
	    	System.out.println("API-5 Service");
	        return bookRepo.filterById(emailId, bookId);
	    }
		
		//reader can ask for refund within 24 hoursof payment - POST - API 6
		public String checkingRefund(String emailId, int bookId) throws BookException {
			System.out.println("API-6 Service");
			long days = 0;
			String start = bookRepo.getDateFromBuyBook(bookId);
			System.out.println("date from buybook table" + start);
			if (start != null) {
				DateTimeFormatter dlf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				String end = dlf.format(now);
				// logic for find time difference
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

				try {
					Date d1 = sdf.parse(start);
					Date d2 = sdf.parse(end);
					long difference_In_Time = d2.getTime() - d1.getTime();

					days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;

					System.out.println("days" + days);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (days < 1) {
					bookRepo.deleteById(bookId);
					return "unsubscription success";
				} else {
				throw new BookException("unsubscription is valid for upto 24 hours");
				}

			} else {
				throw new BookException( "book id doesn't exist,Please enter existing boook id");
			}

		}

	
	// Author can create a book - POST - API 9 
	public CreateBook createBookByAuthor(int authorId, CreateBook book) throws BookException {
		System.out.println("API-9 Service");
		Optional<AuthorModel> authorEntity = authorServiceClient.getAuthorByID(authorId);
		System.out.println("Author id" + authorEntity.get().getAuthorId());
		System.out.println("Author username" + authorEntity.get().getUsername());
		CreateBook bookdetails = new CreateBook();
		if (!authorEntity.isEmpty()) {
			if (book.getCategory() != null) {
				bookdetails.setActive(book.isActive());
				//bookdetails.setAuthorId(authorId);
				//bookdetails.setAuthorName(book.getAuthorName());
				bookdetails.setAuthorId(authorEntity.get().getAuthorId());
                bookdetails.setAuthorName(authorEntity.get().getUsername());
				bookdetails.setCategory(book.getCategory());
				bookdetails.setContents(book.getContents());
				bookdetails.setPrice(book.getPrice());
				bookdetails.setPublishedDate(book.getPublishedDate());
				bookdetails.setPublisher(book.getPublisher());
				bookdetails.setBookName(book.getBookName());
				createBook.save(bookdetails);
			} else {
				throw new BookException("give all details");
			}
		} else {
			throw new BookException("Invalid Book ID or Author ID...");
		}
		return bookdetails;
	}
	
	// Reader can update book -PUT - API 10
	public CreateBook updateBookByAuthor(int authorId, int bookId, CreateBook book) throws BookException {
		System.out.println("API-10 Service");
		Optional<CreateBook> bookDetails = createBook.getBookDetailsByBookIdAndAuthorId(authorId, bookId);

		if (!bookDetails.isEmpty()) {
			if (book.getCategory() != null) {
				bookDetails.get().setCategory(book.getCategory());
			}

			if (Objects.nonNull(book.getContents())) {
				bookDetails.get().setContents(book.getContents());
			}

			if (Objects.nonNull(book.getPublishedDate())) {
				bookDetails.get().setPublishedDate(book.getPublishedDate());
			}

			if (Objects.nonNull(book.getPublisher())) {
				bookDetails.get().setPublisher(book.getPublisher());
			}

			if ((book.getPrice()!=0)) {
				bookDetails.get().setPrice(book.getPrice());
			}
			if (Objects.nonNull(book.isActive())) {
				bookDetails.get().setActive(book.isActive());
			}
			return createBook.save(bookDetails.get());

		} else {
			throw new BookException("Invalid Book ID or Author ID... Please check and retry");
		}
	}
	
	public List<CreateBook> getAllBooks() throws BookException {
        // TODO Auto-generated method stub
        return createBook.findAll();
    }

	public String blockBook(int authorId, int bookId) throws BookException {
		Optional<CreateBook> optionalBook = createBook.blockBook(authorId, bookId);

		if (optionalBook.isEmpty()) {
			throw new BookException("Book doesn't exit");
		} else {
			optionalBook.get().setActive(false);
			createBook.save(optionalBook.get());
		}

		return "Book Blocked Successfullly";
	}
	
	
}
