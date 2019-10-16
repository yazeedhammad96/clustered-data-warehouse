package com.progresssoft.cdw.validator;

/**
 * 
 * @author Yazeed
 *
 */

public interface CSVValidator {

	/**
	 * To validate a CSV row that will be called in the CSVLoader helper class.
	 * 
	 * @param row
	 * 
	 * @return boolean
	 */
	public boolean validateRow(String[] row);

}
