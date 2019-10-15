package com.progresssoft.cdw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Yazeed
 *
 */
@Entity
@Table(name = "imported_file")
public class ImportedFile extends BaseEntity  {

	private static final long serialVersionUID = 3878785211316297730L;

	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Column(name="processDuration")
	private Long processDuration;
	
	@Column(name="validDealsImportedCount")
	private int validDealsImportedCount;
	
	@Column(name="invalidDealsImportedCount")
	private int invalidDealsImportedCount;
		
	
	public int getValidDealsImportedCount() {
		return validDealsImportedCount;
	}

	public void setValidDealsImportedCount(int validDealsImportedCount) {
		this.validDealsImportedCount = validDealsImportedCount;
	}

	public int getInvalidDealsImportedCount() {
		return invalidDealsImportedCount;
	}

	public void setInvalidDealsImportedCount(int invalidDealsImportedCount) {
		this.invalidDealsImportedCount = invalidDealsImportedCount;
	}

	public Long getProcessDuration() {
		return processDuration;
	}

	public void setProcessDuration(Long processDuration) {
		this.processDuration = processDuration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ImportedFile [name=" + name + ", processDuration=" + processDuration + ", validDealsImportedCount="
				+ validDealsImportedCount + ", invalidDealsImportedCount=" + invalidDealsImportedCount + "]";
	}

}
