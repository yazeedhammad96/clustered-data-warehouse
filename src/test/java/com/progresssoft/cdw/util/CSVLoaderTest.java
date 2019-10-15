package com.progresssoft.cdw.util;

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

import com.progresssoft.cdw.enums.RowType;
import com.progresssoft.cdw.validator.CSVDealValidator;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CSVLoaderTest {

	private static final String TEST_FILE_LOC = "./sample-test.csv";
	
	@Qualifier("csvDealValidator")
	@Autowired
	CSVDealValidator csvDealValidator;

	@Test
	@Before
	public void initialize() throws IOException {

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TEST_FILE_LOC))) {
			bufferedWriter.append("DEAL_ID,FROM_CURRENCY,TO_CURRENCY,TIMESTAMP,AMOUNT\n");
			bufferedWriter.append("1,CNY,JOD,2019/11/02 10:50,1.00\n");
			bufferedWriter.append("2,JOD,AED,2019/11/02 22:50,45.00\n");
			bufferedWriter.append("3,CNY,XXX,2019/11/02 10:50,1.00\n");
	}
	}

	@Test
	public void  loadRows() throws FileNotFoundException, IOException {

		Map<RowType, List<String[]>> rows = CSVLoader.getAll(Paths.get(TEST_FILE_LOC), ",", 5, true, csvDealValidator,(x)->{
			//Consumer method for every valid row
		});
		assertNotNull(rows);
		assertNotNull(rows.get(RowType.VALID));
		assertEquals(2, rows.get(RowType.VALID).size());
		assertNotNull(rows.get(RowType.INVALID));
		assertEquals(1, rows.get(RowType.INVALID).size());

	}

	@Test
	@After
	public void destroy() throws IOException {
		Files.deleteIfExists(Paths.get(TEST_FILE_LOC));
	}
}
