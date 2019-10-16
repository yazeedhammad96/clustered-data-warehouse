package com.progresssoft.cdw.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.ImportedFile;

/**
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportedFileDaoTest {

	private static final String SAMPLE_TEST_CSV_NAME = "sample-test.csv";
	@Autowired
	ImportedFileDao importedFileDao;

	@Test
	public void main() {
		ImportedFile importedFile = new ImportedFile();
		importedFile.setName(SAMPLE_TEST_CSV_NAME);
		importedFile.setProcessDuration(2000l);
		importedFile.setValidDealsImportedCount(65);
		importedFile.setInvalidDealsImportedCount(21);
		importedFileDao.add(importedFile);

		ImportedFile importedFileByName = importedFileDao.getByName(SAMPLE_TEST_CSV_NAME);
		assertNotNull(importedFileByName);
		assertEquals("sample-test.csv", SAMPLE_TEST_CSV_NAME);
		assertEquals(new Long(2000), importedFileByName.getProcessDuration());
		assertEquals(65, importedFileByName.getValidDealsImportedCount());
		assertEquals(21, importedFileByName.getInvalidDealsImportedCount());
		importedFileDao.delete(importedFileByName);
	}
}
