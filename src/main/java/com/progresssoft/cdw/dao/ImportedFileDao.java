package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.ImportedFile;

/**
 * 
 * @author Yazeed
 *
 */
public interface ImportedFileDao {

	/**
	 * To add an imported file to the data source.
	 * 
	 * @param importedFile
	 */
	public void add(ImportedFile importedFile);

	/**
	 * To inquire about an imported file by it's name
	 * 
	 * @param name
	 * @return ImportedFile
	 */
	public ImportedFile getByName(String name);

	/**
	 * To delete an imported file from the data source.
	 * 
	 * @param importedFile
	 */
	public void delete(ImportedFile importedFile);

}
