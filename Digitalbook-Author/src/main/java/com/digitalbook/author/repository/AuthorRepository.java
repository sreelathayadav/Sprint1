package com.digitalbook.author.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digitalbook.author.model.AuthorDetails;

public interface AuthorRepository extends JpaRepository<AuthorDetails, Integer> {
	
	@Query("select u.password from AuthorDetails u where u.username=?1")
	public String filterByPassword(String username);
	
	//Getting Author details for Book Service
	@Query("select u from AuthorDetails u where u.username=?1")
	public Optional<AuthorDetails> findByName(String authorName);

}
