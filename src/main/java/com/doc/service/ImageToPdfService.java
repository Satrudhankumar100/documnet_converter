package com.doc.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageToPdfService {
	
	
	/**
	 * pdf Generate
	 * @param imageFile
	 */
	public void createPdf(MultipartFile imageFile);
	
	
	
	

}
