package com.progresssoft.cdw.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.InvalidDeal;
import com.progresssoft.cdw.entity.ValidDeal;
import com.progresssoft.cdw.enums.RowType;
import com.progresssoft.cdw.util.CSVLoader;
import com.progresssoft.cdw.validator.CSVDealValidator;

/**
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportDealDaoTest {

	private static final String TEST_FILE_LOC = "./sample-test.csv";

	@Qualifier("csvDealValidator")
	@Autowired
	private CSVDealValidator csvDealValidator;

	@Autowired
	private ImportDealDao importDealDao;

	@Autowired
	private InvalidDealDao invalidDealDao;

	@Autowired
	private ValidDealDao validDealDao;

	@Test
	@Before
	public void initialize() throws IOException {

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TEST_FILE_LOC))) {
			bufferedWriter.append("DEAL_ID,FROM_CURRENCY,TO_CURRENCY,TIMESTAMP,AMOUNT\n");
			bufferedWriter.append("TEST_ID_1,CNY,JOD,2019/11/02 10:50,1.00\n");
			bufferedWriter.append("TEST_ID_2,JOD,AED,2019/11/02 22:50,45.00\n");
			bufferedWriter.append("TEST_ID_3,JOD,AED,2019/11/02 22:50,45.00\n");
			bufferedWriter.append("TEST_ID_4,CNY,XXXX,2019/11/02 10:50,1.00\n");
		}
	}

	@Test
	public void importDealsAndAssertItsValues() throws FileNotFoundException, IOException {

		Map<RowType, List<String[]>> rows = CSVLoader.getAll(Paths.get(TEST_FILE_LOC), ",", 5, true, csvDealValidator,
				(x) -> {
					// Consumer method for every valid row
				});
		assertNotNull(rows);
		assertNotNull(rows.get(RowType.VALID));
		assertEquals(3, rows.get(RowType.VALID).size());
		assertNotNull(rows.get(RowType.INVALID));
		assertEquals(1, rows.get(RowType.INVALID).size());

		String fileName = Paths.get(TEST_FILE_LOC).getFileName().toString();
		importDealDao.insertCSVDeals(rows, fileName);

		InvalidDeal notValidDeal = invalidDealDao.get("TEST_ID_4");
		assertNotNull(notValidDeal);
		assertEquals("TEST_ID_4", notValidDeal.getDealId());

		ValidDeal validDeal1 = validDealDao.get("TEST_ID_1");
		ValidDeal validDeal2 = validDealDao.get("TEST_ID_2");
		ValidDeal validDeal3 = validDealDao.get("TEST_ID_3");
		assertNotNull(validDeal1);
		assertEquals("TEST_ID_1", validDeal1.getDealId());
		assertNotNull(validDeal2);
		assertEquals("TEST_ID_2", validDeal2.getDealId());
		assertNotNull(validDeal3);
		assertEquals("TEST_ID_3", validDeal3.getDealId());

		deleteTestModels(notValidDeal, validDeal1, validDeal2, validDeal3);

	}

	private void deleteTestModels(InvalidDeal notValidDeal, ValidDeal validDeal1, ValidDeal validDeal2,
			ValidDeal validDeal3) {
		invalidDealDao.delete(notValidDeal);
		validDealDao.delete(validDeal1);
		validDealDao.delete(validDeal2);
		validDealDao.delete(validDeal3);

	}

	@Test
	@After
	public void destroy() throws IOException {
		Files.deleteIfExists(Paths.get(TEST_FILE_LOC));
	}
}
