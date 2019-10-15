package com.progresssoft.cdw.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.dao.ImportedFileDao;
import com.progresssoft.cdw.dto.UploadSummaryDTO;
import com.progresssoft.cdw.entity.ImportedFile;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportDealFacadeTest {

	@Autowired
	ImportDealFacade importDealFacade;

	private static final String SAMPLE_TEST_CSV_NAME = "sample-test.csv";
	@Autowired
	private ImportedFileDao importedFileService;

	private ImportedFile importedFile;

	@Before
	public void initialize() {
		importedFile = new ImportedFile();
		importedFile.setName(SAMPLE_TEST_CSV_NAME);
		importedFile.setProcessDuration(2000l);
		importedFile.setValidDealsImportedCount(65);
		importedFile.setInvalidDealsImportedCount(21);
		

	}

	@Test
	public void main() {
		importedFileService.add(importedFile);
		UploadSummaryDTO fileSummary = importDealFacade.getFileSummary(SAMPLE_TEST_CSV_NAME);
		assertNotNull(fileSummary);
		assertEquals(SAMPLE_TEST_CSV_NAME, fileSummary.getSourceFileName());
		assertEquals(new Long(2000), fileSummary.getProcessDuration());
		assertEquals(new Integer(65), fileSummary.getValidDealsImportedCount());
		assertEquals(new Integer(21), fileSummary.getInvalidDealsImportedCount());
	}

	@After
	public void destroy() {

		importedFileService.delete(importedFile);
	}

}
