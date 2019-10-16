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

	/**
	 * To take the multipart file and forward it to the service, then doing the
	 * required process to import the deals.
	 * 
	 * @param file
	 * @param includeHeader
	 * @param sperator
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void importCSVDeals(MultipartFile file, Boolean includeHeader, String sperator)
			throws FileNotFoundException, IOException;

	/**
	 * To inquire about the summary of the imported files.
	 * 
	 * @param fileName
	 * 
	 * @return UploadSummaryDTO
	 */
	public UploadSummaryDTO getFileSummary(String fileName);
}