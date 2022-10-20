//package com.digitalbook.author;
//
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.digitalbook.author.controller.AuthorController;
//import com.digitalbook.author.model.AuthorDetails;
//import com.digitalbook.author.service.AuthorService;
//
//@SpringBootTest
//class DigitalbookAuthorApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Mock
//	AuthorService authorservice;
//
//	@InjectMocks
//	AuthorController authorController;
//
//	public void registerTest() {
//		AuthorDetails authorEntity = new AuthorDetails();
//		authorEntity.setEmailId("srilatha@gmail.com");
//		authorEntity.setauthorId(1);
//		authorEntity.setUsername("srilatha");
//		authorEntity.setPassword("sri@123");
//		when(authorservice.save(authorEntity)).thenReturn(authorEntity.toString());
//		authorController.saveCredentials(authorEntity);
//
//	}
//}
