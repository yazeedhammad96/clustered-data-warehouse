package com.progresssoft.cdw.controller.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.validator.CSVDealValidator;

/**
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CSVDealValidatorTest {

	@Autowired
	@Qualifier("csvDealValidator")
	private CSVDealValidator csvDealValidator;

	private final String[][] rows = { { "TEST_ID", "JOD", "AED", "2018/10/02 10:50", "956.0" },
			{ "132", "AED", "SAR", "2018/00/26 10:20", "91.0" } };

	@Test
	public void assertCSVDealValidatorToWorkAsExpected() {

		assertTrue(csvDealValidator.validateRow(rows[0]));
		assertFalse(csvDealValidator.validateRow(rows[1]));

	}
}
