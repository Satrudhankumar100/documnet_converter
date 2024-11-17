package com.doc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imgtopdf")
public class ImageToPdfController {
	
	
	@PostMapping("/save")
	public ResponseEntity<String> imgTopdf(){
		return new ResponseEntity<>("Accepted",HttpStatus.OK);
	}

}
