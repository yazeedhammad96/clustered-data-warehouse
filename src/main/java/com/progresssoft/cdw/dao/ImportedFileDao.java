package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.ImportedFile;

/**
 * 
 * @author Yazeed
 *
 */
public interface ImportedFileDao {

	public void add(ImportedFile importedFile);

	public ImportedFile getByName(String name);
	
	public void delete(ImportedFile importedFile);

}
