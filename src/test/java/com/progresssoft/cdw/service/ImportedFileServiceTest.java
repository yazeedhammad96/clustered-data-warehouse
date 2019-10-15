package com.progresssoft.cdw.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.ImportedFile;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportedFileServiceTest {

	private static final String SAMPLE_TEST_CSV_NAME = "sample-test.csv";
	@Autowired
	ImportedFileService importedFileService;

	@Test
	public void test() {
		ImportedFile importedFile = new ImportedFile();
		importedFile.setName(SAMPLE_TEST_CSV_NAME);
		importedFile.setProcessDuration(2000l);
		importedFile.setValidDealsImportedCount(65);
		importedFile.setInvalidDealsImportedCount(21);
		importedFileService.add(importedFile);

		ImportedFile importedFileByName = importedFileService.getByName(SAMPLE_TEST_CSV_NAME);
		assertNotNull(importedFileByName);
		assertEquals("sample-test.csv", SAMPLE_TEST_CSV_NAME);
		assertEquals(new Long(2000), importedFileByName.getProcessDuration());
		assertEquals(65, importedFileByName.getValidDealsImportedCount());
		assertEquals(21, importedFileByName.getInvalidDealsImportedCount());
		importedFileService.delete(importedFileByName);
	}
}
