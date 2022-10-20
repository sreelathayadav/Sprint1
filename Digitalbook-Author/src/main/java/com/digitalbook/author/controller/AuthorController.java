package com.digitalbook.author.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbook.author.exception.AuthorException;
import com.digitalbook.author.model.AuthorDetails;
import com.digitalbook.author.service.AuthorService;


@RestController
@RequestMapping("/api/v1/digitalbooks/author")
public class AuthorController {
	
	
	
	
	
	@Autowired
	AuthorService authService;
	
	//Author can create account - POST - API 7
	@PostMapping("/signup")
	public List<String> saveCredentials(@RequestBody AuthorDetails author)
    {
		 authService.save(author);
		 List<String> ll=new ArrayList<String>();
		 ll.add("User registred successfully");
		 return ll;
    }
	
	
	
	//Author can login -- POST - API 8
	@PostMapping("/login")
    public List<String> authorLogin(@RequestBody AuthorDetails author)
    {
        String username=author.getUsername();
        System.out.println("login controller" +username);
        List<String> ll=new ArrayList<String>();
        String password=author.getPassword();
        System.out.println("login controller password" +password);
        String s=authService.validateUser(username,password);
        if(s.equalsIgnoreCase("success"))
        {
           ll.add("Login Success");    
        }
        else if(s.equalsIgnoreCase("failure"))
        {
             ll.add("Password is invalid,Please enter correct password");
        }
        else
        {
            ll.add( "User is not available,Please register");
        }
        return ll;
        
    }
	
	@GetMapping("/getAuthor/{authorId}")
	public Optional<AuthorDetails> getAuthorByID(@PathVariable int authorId) {
		return authService.getAuthorById(authorId);
//		Optional<AuthorDetails> ad = authService.getAuthorById(authorId);
//		if (ad == null & ad.isEmpty()) {
//			System.out.println("Author exception occurred");
//			throw new AuthorException();
//		}
//		return ad;
	}
}
