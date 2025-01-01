package com.doc.utility;

import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PageNumberGenerator {

	
	//generate PDF with page numbers starting from user choice
	public String generatePdfWithPageNumber(int startNumber,int totalPages,String outputFilePath) {
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
			document.open();
			
			for(int i=0;i<=totalPages;i++) {
				int pageNumber=startNumber+i;
				document.add(new Paragraph("This is content for page" + pageNumber));
				if(i<totalPages-1) {
					document.newPage();
				}
				
			}
			
			document.close();
			writer.close();
			System.out.println("PDF generated successfully with"+ totalPages+"page");
			return outputFilePath;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}



