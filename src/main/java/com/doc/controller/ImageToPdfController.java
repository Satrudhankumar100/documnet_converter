package com.doc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * this method is used to save and return pdf using /generate-pdf url
	 * @param files
	 * @return OK.200
	 */
	@PostMapping("/generatepdf")
	public ResponseEntity<InputStreamResource> generatePdf(@RequestParam MultipartFile[] imagesFile) {

		try {
			String pdfFilePath = pdfService.createPdf(imagesFile);

			File pdfFile = new File(pdfFilePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=image.pdf");

			

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.contentLength(pdfFile.length()).body(resource);

		} catch (IOException e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	/**
	 * This method is used only testing purp
	 * @return
	 */

	@GetMapping("/check")
	public ResponseEntity<InputStreamResource> checkMethod() {

		try {
			// Specify the path to the PDF file
			String pdfFilePath = "D:\\STUDY\\RAUSHAN_PROJECT\\document_converter\\output.pdf";

			// Create a File object from the path
			File pdfFile = new File(pdfFilePath);

			// Prepare InputStreamResource to serve the file
			InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

			// Set response headers
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=output.pdf");

			// Return the file as a response
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.contentLength(pdfFile.length()).body(resource);

		} catch (IOException e) {
			// Handle errors gracefully
			return ResponseEntity.status(500).body(null);
		}
	}

}
