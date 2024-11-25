package com.doc.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doc.service.ImageToPdfService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageToPdfServiceImpl implements ImageToPdfService {

	/**
	 * This method is used to generate imaget to pdf file
	 */
	public void createPdf(MultipartFile[] imageFile) {
		
		

		try {

			File file = new File("output.pdf");
			FileOutputStream out = new FileOutputStream(file);
			Document doc = new Document(PageSize.A4);
			doc.setMargins(5, 5, 5, 5);
			doc.leftMargin();
			PdfWriter.getInstance(doc, out);
			
			doc.open();
			for(MultipartFile ob:imageFile) {
			// Convert image to PDF
			byte[] imageBytes = ob.getBytes();
			Image image = Image.getInstance(imageBytes);
			image.scaleToFit(PageSize.A4.getWidth(),PageSize.A4.getHeight());
			image.setAbsolutePosition(0,PageSize.A4.getHeight()-image.getScaledHeight());
			image.getBorder();
			
			
			
			// Add the image to the document
			doc.add(image);
			 doc.newPage();
			
			
			
			}
			//doc.add(paragraph);
			doc.close();
			log.info(file.getAbsolutePath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
