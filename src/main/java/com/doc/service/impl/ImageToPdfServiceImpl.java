package com.doc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doc.service.ImageToPdfService;
import com.doc.utility.TimeUtility;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class ImageToPdfServiceImpl implements ImageToPdfService {

	 @Value("${pdf-file-location}")
		String outputDir; 

	
	/**
	 * This method is used to generate image to pdf file
	 */
	public String createPdf(MultipartFile[] imageFiles) {

		String pdfFilePath = null;
		
	   
		if (imageFiles == null || imageFiles.length == 0) {
			throw new IllegalArgumentException("No image files provided");
		}

		try {
			UUID uuid = UUID.randomUUID();
			LocalDateTime time = LocalDateTime.now();
			pdfFilePath = outputDir + "\\" + uuid.toString() +"@"+TimeUtility.setTime()+".pdf";

			try (FileOutputStream out = new FileOutputStream(pdfFilePath)) {
				Document doc = new Document(PageSize.A4);
				PdfWriter writer = PdfWriter.getInstance(doc, out);

				doc.open();
				int pageNumber = 1;
				
				for (MultipartFile file : imageFiles) {
					byte[] imageBytes = file.getBytes();
					Image image = Image.getInstance(imageBytes);

					// Scale and position the image to fit A4
					image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
					image.setAbsolutePosition(0, PageSize.A4.getHeight() - image.getScaledHeight());

					// Add the image to the document
					doc.add(image);
					boolean pdfPrint=true;
					// Add page number before starting a new page
					if(pdfPrint) {
						PdfContentByte cb = writer.getDirectContent();
						cb.beginText();
						BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
						cb.setFontAndSize(baseFont, 12);
						cb.showTextAligned(Element.ALIGN_MIDDLE, "Page No " + pageNumber, PageSize.A4.getWidth() / 2, 20, // Y-coordinate
								// bottom
								0);
						cb.endText();
						// Move to the next page
						if (pageNumber < imageFiles.length) {
							doc.newPage();
							pageNumber++;
						}
					}else {
						doc.newPage();
					}
				}
				doc.close();
			}

			log.info("PDF created successfully at: " + pdfFilePath);

			// Return the PDF path for further use (sending it as response or downloading)
			return pdfFilePath;

		} catch (FileNotFoundException e) {
			log.error("File not found: " + e.getMessage());
			throw new RuntimeException("Failed to create PDF", e);
		} catch (DocumentException e) {
			log.error("Error in PDF creation: " + e.getMessage());
			throw new RuntimeException("Failed to create PDF", e);
		} catch (IOException e) {
			log.error("I/O error: " + e.getMessage());
			throw new RuntimeException("Failed to create PDF", e);
		}
		
		
	}

}
