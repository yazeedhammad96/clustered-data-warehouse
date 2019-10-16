package com.progresssoft.cdw.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.ValidDeal;
import com.progresssoft.cdw.enums.ISOCurrencyCode;

/**
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidDealServiceTest {

	private static final String SAMPLE_TEST_CSV = "sample-test.csv";

	private static final Double DEAL_AMOUNT = 123.0;

	private static final Timestamp DEAL_TIMESTAMP = Timestamp.valueOf("2018-2-02 12:10:00.0");

	private static final String DEAL_ID = "TEST_ID_123";

	private ValidDeal validDeal;

	@Autowired
	private ValidDealService validDealService;

	@Before
	public void addValidDealToBeUseInTesting() {
		ValidDeal validDeal = new ValidDeal();
		validDeal.setDealId(DEAL_ID);
		validDeal.setDealTimestamp(DEAL_TIMESTAMP);
		validDeal.setFromCurrencyISOCode(ISOCurrencyCode.JOD);
		validDeal.setToCurrencyISOCode(ISOCurrencyCode.AED);
		validDeal.setDealAmountInOrderingCurrency(DEAL_AMOUNT);
		validDeal.setSourceFile(SAMPLE_TEST_CSV);
		validDealService.add(validDeal);

	}

	@Test
	public void getValidDealAndAssertIt() {
		validDeal = validDealService.get(DEAL_ID);
		assertNotNull(validDeal);
		assertEquals(DEAL_ID, validDeal.getDealId());
		assertEquals(DEAL_TIMESTAMP, validDeal.getDealTimestamp());
		assertEquals(ISOCurrencyCode.JOD, validDeal.getFromCurrencyISOCode());
		assertEquals(ISOCurrencyCode.AED, validDeal.getToCurrencyISOCode());
		assertEquals(DEAL_AMOUNT, validDeal.getDealAmountInOrderingCurrency());
		assertEquals(SAMPLE_TEST_CSV, validDeal.getSourceFile());
	}

	@After
	public void deleteValidDealAfterTestingIt() {
		validDealService.delete(validDeal);
	}

}
