/**
 * 
 */
package com.progresssoft.cdw.dto;

/**
 * @author Yazeed
 *
 */
public class UploadSummaryDTO {

	private String sourceFileName;

	private Integer validDealsImportedCount;

	private Integer invalidDealsImportedCount;

	private Long processDuration;

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public Integer getValidDealsImportedCount() {
		return validDealsImportedCount;
	}

	public void setValidDealsImportedCount(Integer validDealsImportedCount) {
		this.validDealsImportedCount = validDealsImportedCount;
	}

	public Integer getInvalidDealsImportedCount() {
		return invalidDealsImportedCount;
	}

	public void setInvalidDealsImportedCount(Integer invalidDealsImportedCount) {
		this.invalidDealsImportedCount = invalidDealsImportedCount;
	}

	public Long getProcessDuration() {
		return processDuration;
	}

	public void setProcessDuration(Long processDuration) {
		this.processDuration = processDuration;
	}
}
