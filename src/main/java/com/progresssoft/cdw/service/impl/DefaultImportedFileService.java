/**
 * 
 */
package com.progresssoft.cdw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.progresssoft.cdw.dao.ImportedFileDao;
import com.progresssoft.cdw.entity.ImportedFile;
import com.progresssoft.cdw.service.ImportedFileService;

/**
 * @author Yazeed
 *
 */
@Service
public class DefaultImportedFileService implements ImportedFileService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultImportedFileService.class);

	@Autowired
	private ImportedFileDao importedFileDao;

	@Override
	public void add(ImportedFile importedFile) {
		if (importedFile == null || StringUtils.isEmpty(importedFile.getName())) {
			throw new IllegalArgumentException("importedFile can't be null");
		}
		LOG.info(String.format("Add ImportedFile with name [%s]", importedFile.getName()));
		importedFileDao.add(importedFile);
	}

	@Override
	public ImportedFile getByName(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("importedFile name can't be null or empty");
		}
		LOG.info(String.format("Get ImportedFile with name [%s]", name));
		return importedFileDao.getByName(name);
	}

	@Override
	public void delete(ImportedFile importedFile) {
		if (importedFile==null||StringUtils.isEmpty(importedFile.getName())) {
			throw new IllegalArgumentException("importedFile or it's name can't be null or empty");
		}
		LOG.info(String.format("Delete ImportedFile with name [%s]", importedFile.getName()));
		importedFileDao.delete(importedFile);
		
	}

}
