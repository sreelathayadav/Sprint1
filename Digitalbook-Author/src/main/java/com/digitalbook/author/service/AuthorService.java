package com.digitalbook.author.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbook.author.model.AuthorDetails;
import com.digitalbook.author.repository.AuthorRepository;
@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepo;

	//AUthor Signin
	public String save(AuthorDetails author)
	{
		authorRepo.save(author);
		return "Registration success , Please Login";

	}
	
	//Author Login
	public String validateUser(String username, String password) {
		String dbPassword = authorRepo.filterByPassword(username);
		if (dbPassword != null) {
			if (dbPassword.equals(password)) {
				return "success";
			} else {
				return "failure";
			}
		} else {
			return "false";
		}

	}
	
	public Optional<AuthorDetails> getAuthorById(int authorId) {
		return authorRepo.findById(authorId);

	}

}
