package com.progresssoft.cdw.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
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
public class ImportedFileServiceTest {

	private static final String SAMPLE_TEST_CSV_NAME = "sample-test.csv";

	@Autowired
	private ImportedFileService importedFileService;

	private ImportedFile importedFile;

	@Before
	public void addImportedFileToBeUseInTesting() {
		importedFile = new ImportedFile();
		importedFile.setName(SAMPLE_TEST_CSV_NAME);
		importedFile.setProcessDuration(2000l);
		importedFile.setValidDealsImportedCount(65);
		importedFile.setInvalidDealsImportedCount(21);
		importedFileService.add(importedFile);
	}

	@Test
	public void getImpotedFileAndAssertIt() {

		importedFile = importedFileService.getByName(SAMPLE_TEST_CSV_NAME);
		assertNotNull(importedFile);
		assertEquals(SAMPLE_TEST_CSV_NAME, importedFile.getName());
		assertEquals(new Long(2000), importedFile.getProcessDuration());
		assertEquals(65, importedFile.getValidDealsImportedCount());
		assertEquals(21, importedFile.getInvalidDealsImportedCount());

	}

	@After
	public void deletImpotedFileAfterTestingIt() {
		importedFileService.delete(importedFile);
	}
}
