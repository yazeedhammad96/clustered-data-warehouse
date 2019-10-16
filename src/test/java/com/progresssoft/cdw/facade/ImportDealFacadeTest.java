package com.progresssoft.cdw.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.cdw.dao.AccumulativeDealsOfCurrencyDao;
import com.progresssoft.cdw.dao.ImportedFileDao;
import com.progresssoft.cdw.dao.InvalidDealDao;
import com.progresssoft.cdw.dao.ValidDealDao;
import com.progresssoft.cdw.dto.UploadSummaryDTO;
import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
import com.progresssoft.cdw.entity.ImportedFile;
import com.progresssoft.cdw.entity.InvalidDeal;
import com.progresssoft.cdw.entity.ValidDeal;
import com.progresssoft.cdw.enums.ISOCurrencyCode;

/**
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportDealFacadeTest {

	@Autowired
	private ImportDealFacade importDealFacade;
	@Autowired
	private ImportedFileDao importedFileDao;
	@Autowired
	private InvalidDealDao invalidDealDao;
	@Autowired
	private ValidDealDao validDealDao;
	@Autowired
	private AccumulativeDealsOfCurrencyDao accumulativeDealsOfCurrencyDao;

	private static final String TEST_FILE_LOC = "./sample-test1.csv";

	private static final String SAMPLE_TEST_CSV_NAME = "sample-test1.csv";

	@Before
	public void initializeTestFileToBeUsedInTestingAndImportIt() throws IOException {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TEST_FILE_LOC))) {
			bufferedWriter.append("DEAL_ID,FROM_CURRENCY,TO_CURRENCY,TIMESTAMP,AMOUNT\n");
			bufferedWriter.append("TEST_ID_1,JOD,JOD,2019/11/02 10:50,1.00\n");
			bufferedWriter.append("TEST_ID_2,JOD,AED,2019/11/02 22:50,45.00\n");
			bufferedWriter.append("TEST_ID_3,CNY,XXX,2019/11/02 10:50,1.00\n");
		}
		File file = new File(TEST_FILE_LOC);
		URLConnection urlConn = null;
		MultipartFile multipartFile = null;
		try {
			urlConn = file.toURI().toURL().openConnection();
			multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(urlConn));
		} finally {
			IOUtils.close(urlConn);
		}
		importDealFacade.importCSVDeals(multipartFile, true, ",");

	}

	@Test
	public void getSummaryOfImportedFileAndAssertIt() {
		UploadSummaryDTO fileSummary = importDealFacade.getFileSummary(SAMPLE_TEST_CSV_NAME);
		assertNotNull(fileSummary);
		assertEquals(SAMPLE_TEST_CSV_NAME, fileSummary.getSourceFileName());
		assertEquals(new Integer(2), fileSummary.getValidDealsImportedCount());
		assertEquals(new Integer(1), fileSummary.getInvalidDealsImportedCount());
	}

	@After
	public void destroy() throws IOException {
		ImportedFile byName = importedFileDao.getByName(SAMPLE_TEST_CSV_NAME);
		importedFileDao.delete(byName);

		ValidDeal deal = validDealDao.get("TEST_ID_1");
		validDealDao.delete(deal);
		deal = validDealDao.get("TEST_ID_2");
		validDealDao.delete(deal);

		InvalidDeal invalidDeal = invalidDealDao.get("TEST_ID_3");
		invalidDealDao.delete(invalidDeal);

		AccumulativeDealsOfCurrency byCurrencyISOCode = accumulativeDealsOfCurrencyDao
				.getByCurrencyISOCode(ISOCurrencyCode.JOD.toString());
		byCurrencyISOCode.setAmountOfDeals(byCurrencyISOCode.getAmountOfDeals() - 46.0);

		accumulativeDealsOfCurrencyDao.update(byCurrencyISOCode);

		Files.deleteIfExists(Paths.get(TEST_FILE_LOC));

	}

}
