package com.progresssoft.cdw.facade;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.dto.UploadSummaryDTO;

/**
 * @author Yazeed
 * 
 */

public interface ImportDealFacade {

	public void importCSVDeals(MultipartFile file, Boolean includeHeader, String sperator) throws FileNotFoundException, IOException;
	
	public UploadSummaryDTO getFileSummary(String fileName) ;
}