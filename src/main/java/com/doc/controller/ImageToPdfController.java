package com.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doc.service.ImageToPdfService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/imgtopdf")
@Slf4j
public class ImageToPdfController {

	@Autowired
	private ImageToPdfService pdfService;

	/**
	 * this method is used to save and return pdf using /generatepdf url
	 * 
	 * @param files
	 * @return OK.200
	 */
	@PostMapping("/generatepdf")
	public ResponseEntity<String> generatePdf(@RequestParam MultipartFile imagesFile) {
		pdfService.createPdf(imagesFile);
		return new ResponseEntity<String>("PDF generated successfully!", HttpStatus.OK);
	}
	
	@PostMapping("/check")
	public String checkMethod() {
		return "hello";
	}
}
