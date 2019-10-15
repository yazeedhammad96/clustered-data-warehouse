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

	public void add(ImportedFile importedFile);

	public ImportedFile getByName(String name);
	
	public void delete(ImportedFile importedFile);
}
