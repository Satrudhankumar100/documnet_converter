package com.doc.schedular;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.doc.utility.TimeUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PdfSchedular {

	@Value("${pdf-file-location}")
	String outputDir;

	@Scheduled(fixedDelay = 1000*60)
	public void deletePdf() {
		
		File path=new File(outputDir);
		String[] fileNames=path.list();
		if(fileNames.length==0) return;
		//finding current time
		LocalDateTime currentDateTime = LocalDateTime.now().minusMinutes(1);
		
		
		Arrays.stream(fileNames)
		.filter(fileName->{
			String fileNamePdfExtnsRemoved = fileName.replaceAll(".pdf","");
			LocalDateTime fileInfo = TimeUtility.getTime(fileNamePdfExtnsRemoved);
			return fileInfo.isBefore(currentDateTime);
		}).forEach(fileName->{
			 boolean deleted = new File(outputDir+"\\"+fileName).delete();
			 log.info(fileName+" "+deleted);
		});
		
		
		
		/*deleting file
		for (String fileName : fileNames) {
			String fileNamePdfExtnsRemoved = fileName.replaceAll(".pdf","");
			log.info(fileNamePdfExtnsRemoved);
			LocalDateTime fileInfo = TimeUtility.getTime(fileNamePdfExtnsRemoved);
			if(fileInfo.isBefore(currentDateTime)) {
				new File(outputDir+"\\"+fileName).delete();
				log.info("DELETED-----------------");
				
			}
		}*/
		
		
	
		//printing information
		
	//	log.info("r"+r);
		//log.info(fileInfo.toString());
		log.error("PDF DELETED SUCCESSFULLY....");

	}

}
