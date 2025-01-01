package com.doc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doc.constant.PageNumberLocation;
import com.doc.service.ImageToPdfService;
import com.doc.utility.TimeUtility;
import com.doc.utility.service.IAddPageNumberGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class ImageToPdfServiceImpl implements ImageToPdfService {

	@Autowired
	private IAddPageNumberGenerator addPageNumberGenerator;

	@Value("${pdf-file-location}")
	String outputDir;

	/**
	 * This method to create a PDF from image files and optinally add page numbers
	 */
	public String createPdf(MultipartFile[] imageFiles, int startPageNumber, PageNumberLocation location) {

		if (imageFiles == null || imageFiles.length == 0) {
			throw new IllegalArgumentException("No image files provided");
		}

		// store pdfFiflePath location
		String pdfFilePath = null;

		try {
			// generate unique file name
			UUID uuid = UUID.randomUUID();
			LocalDateTime time = LocalDateTime.now();
			pdfFilePath = outputDir + "\\" + uuid.toString() + "@" + TimeUtility.setTime() + ".pdf";

			try (FileOutputStream out = new FileOutputStream(pdfFilePath)) {
				Document doc = new Document(PageSize.A4);
				PdfWriter writer = PdfWriter.getInstance(doc, out);
				doc.open();

				int pageNumber = startPageNumber;

				for (int i = 0; i < imageFiles.length; i++) {

					MultipartFile file = imageFiles[i];
					// add image to PDF
					byte[] imageBytes = file.getBytes();
					Image image = Image.getInstance(imageBytes);

					// Scale and position the image to fit A4
					image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
					image.setAbsolutePosition(0, PageSize.A4.getHeight() - image.getScaledHeight());
					doc.add(image);

					
					

					
					// Add page number
					addPageNumberGenerator.addPageNumber(writer, pageNumber + i, location);
					// start a new page if not the lats image
					if (i < imageFiles.length - 1) {
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
