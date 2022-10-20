package com.digitalbook.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalbookReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalbookReaderApplication.class, args);
	}

}
