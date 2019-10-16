/**
 * 
 */
package com.progresssoft.cdw.service;

import com.progresssoft.cdw.entity.ImportedFile;

/**
 * @author Yazeed
 *
 */
public interface ImportedFileService {

	/**
	 * To add an imported file to the data source by calling the required dao.
	 * 
	 * @param importedFile
	 */
	public void add(ImportedFile importedFile);

	/**
	 * To get an imported file by it name from the data source by calling the required dao.
	 * 
	 * @param importedFile
	 * 
	 * @return ImportedFile
	 */
	public ImportedFile getByName(String name);
	
	/**
	 * To delete an imported file from the data source by calling the required dao.
	 * 
	 * @param importedFile
	 */
	public void delete(ImportedFile importedFile);
}
