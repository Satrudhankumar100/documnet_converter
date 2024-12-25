package com.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DocumentConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentConverterApplication.class, args);
	}

}
