package com.progresssoft.cdw.form;

import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.form.validations.FileExisted;
import com.progresssoft.cdw.form.validations.FileFormat;
import com.progresssoft.cdw.form.validations.NotEmptyFile;

/**
 * 
 * @author Yazeed
 *
 */

public class CSVFileForm {
	
	
	@FileExisted
	@FileFormat
	@NotEmptyFile
	private MultipartFile file;
	
	private Boolean includeHeader;
	
	private String separator;

	public Boolean getIncludeHeader() {
		return includeHeader;
	}

	public void setIncludeHeader(Boolean includeHeader) {
		this.includeHeader = includeHeader;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
