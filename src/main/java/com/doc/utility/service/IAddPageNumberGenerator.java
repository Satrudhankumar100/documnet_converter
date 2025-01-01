package com.doc.utility.service;

import com.doc.constant.PageNumberLocation;
import com.itextpdf.text.pdf.PdfWriter;

public interface IAddPageNumberGenerator {
	
	
    public void addPageNumber(PdfWriter writer, int pageNumber, PageNumberLocation location);


}
