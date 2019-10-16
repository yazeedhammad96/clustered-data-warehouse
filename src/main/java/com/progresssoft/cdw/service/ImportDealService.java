package com.progresssoft.cdw.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Yazeed
 *
 */
public interface ImportDealService {

	/**
	 * To import a deals from a multipart file by calling the CSVLoader helper class
	 * ,then do the required process to let the process be done.
	 * 
	 * @param deals
	 * @param sourceName
	 */
	public void importCSVDeals(MultipartFile file, Boolean includeHeader, String sperator)
			throws FileNotFoundException, IOException;
}
