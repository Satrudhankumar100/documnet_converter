package com.doc.service;

import org.springframework.web.multipart.MultipartFile;

import com.doc.constant.PageNumberLocation;

public interface ImageToPdfService {
	
	
	/**
	 * pdf Generate
	 * @param imageFile
	 */
	public String createPdf(MultipartFile[] imageFile,int startPageNumebr,PageNumberLocation location);
	
	
	
	
	
	
	
	
	
	
	
	
	

}
