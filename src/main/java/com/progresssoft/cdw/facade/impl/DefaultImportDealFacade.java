package com.progresssoft.cdw.facade.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.dto.UploadSummaryDTO;
import com.progresssoft.cdw.entity.ImportedFile;
import com.progresssoft.cdw.facade.ImportDealFacade;
import com.progresssoft.cdw.service.ImportDealService;
import com.progresssoft.cdw.service.ImportedFileService;

/**
 * @author Yazeed
 *
 * 
 */

@Component
public class DefaultImportDealFacade implements ImportDealFacade {

	@Autowired
	ImportDealService importDealService;

	@Autowired
	ImportedFileService importedFileService;

	@Override
	public UploadSummaryDTO getFileSummary(String fileName) {
		if (StringUtils.isEmpty(fileName))
			throw new IllegalArgumentException("fileName can't be null or empty");

		ImportedFile importedFile = importedFileService.getByName(fileName);
		if (importedFile == null)
			return null;
		UploadSummaryDTO summaryDTO = new UploadSummaryDTO();
		summaryDTO.setSourceFileName(importedFile.getName());
		summaryDTO.setValidDealsImportedCount(importedFile.getValidDealsImportedCount());
		summaryDTO.setProcessDuration(importedFile.getProcessDuration());
		summaryDTO.setInvalidDealsImportedCount(importedFile.getInvalidDealsImportedCount());
		return summaryDTO;
	}

	@Override
	public void importCSVDeals(MultipartFile uploadedFile, Boolean includeHeader, String sperator)
			throws FileNotFoundException, IOException {
		if (uploadedFile == null)
			throw new IllegalArgumentException("uploadedFile can't be null");
		importDealService.importCSVDeals(uploadedFile, includeHeader, sperator);
	}
}
