package com.doc.utility.service.impl;

import org.springframework.stereotype.Service;

import com.doc.constant.PageNumberLocation;
import com.doc.utility.service.IAddPageNumberGenerator;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class AddPageNumberGeneratorImpl implements IAddPageNumberGenerator {
	
	
	
	

	@Override
	public void addPageNumber(PdfWriter writer, int pageNumber, PageNumberLocation location) {
		PdfContentByte cb = writer.getDirectContentUnder();
		cb.beginText();

		try {
			BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
			cb.setFontAndSize(baseFont, 12);
			
			float[] position=setPageNumberLocation(location);

			// page number adding
			cb.showTextAligned(Element.ALIGN_CENTER, "Page " + pageNumber, position[0],position[1], 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cb.endText();
		}

	}

	private float[] setPageNumberLocation(PageNumberLocation location) {

		float x = 0;
		float y = 0;
		switch (location) {
		case BOTTOM_LEFT_SIDE:
			x = 50;
			y = 30;
			break;
		case BOTTOM_CENTER_SIDE:
			x = PageSize.A4.getWidth() / 2;
			y = 30;
			break;
		case BOTTOM_RIGHT_SIDE:
			x = PageSize.A4.getWidth() - 50;
			y = 30;
			break;
		case TOP_LEFT_SIDE:
			x = 50;
			y = PageSize.A4.getHeight() - 30;
			break;
		case TOP_CENTER_SIDE:
			x = PageSize.A4.getWidth() / 2;
			y = PageSize.A4.getHeight() - 30;
			break;
		case TOP_RIGHT_SIDE:
			x = PageSize.A4.getWidth() - 50;
			y = PageSize.A4.getHeight() - 30;
			break;
		}
		return new float[] {x,y};

	}

}
