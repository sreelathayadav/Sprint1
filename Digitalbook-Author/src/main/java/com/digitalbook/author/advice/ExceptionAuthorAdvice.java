package com.digitalbook.author.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalbook.author.exception.AuthorException;

@ControllerAdvice
public class ExceptionAuthorAdvice {

	@ExceptionHandler(value = AuthorException.class)
	public 	ResponseEntity<Object> exception(AuthorException me) {
		
		return new ResponseEntity<>("Author exception occurred", HttpStatus.NOT_FOUND);
	}


}
