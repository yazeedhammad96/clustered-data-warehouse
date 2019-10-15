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

	public void importCSVDeals(MultipartFile file, Boolean includeHeader, String sperator)
			throws FileNotFoundException, IOException;
}
