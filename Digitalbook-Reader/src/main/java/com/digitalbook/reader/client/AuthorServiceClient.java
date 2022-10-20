package com.digitalbook.reader.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.digitalbook.reader.entity.AuthorModel;
import com.digitalbook.reader.model.CreateBook;

@FeignClient("digitalbook-author")
public interface AuthorServiceClient {

	// Author can create a book - POST - API 9
	@GetMapping("/api/v1/digitalbooks/author/getAuthor/{authorId}")
	Optional<AuthorModel> getAuthorByID(@PathVariable int authorId);

//	@GetMapping("/api/v1/digitalbooks/author/getAuthorName/{username}")
//	Optional<CreateBook> getAuthorByName(@PathVariable String username);

}
