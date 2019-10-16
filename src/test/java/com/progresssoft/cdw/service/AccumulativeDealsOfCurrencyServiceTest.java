package com.progresssoft.cdw.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;

/**
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccumulativeDealsOfCurrencyServiceTest {

	private static final String TEST_CURRENCY_ISO_CODE = "TestCurrencyISOCode";
	@Autowired
	private AccumulativeDealsOfCurrencyService accumulativeDealsOfCurrencyService;
	private AccumulativeDealsOfCurrency accumulativeDealsOfCurrency;

	@Before
	public void addAccumulativeDealsOfCurrencyToBeUseInTesting() {
		accumulativeDealsOfCurrency = new AccumulativeDealsOfCurrency();
		accumulativeDealsOfCurrency.setCurrencyISOCode(TEST_CURRENCY_ISO_CODE);
		accumulativeDealsOfCurrency.setAmountOfDeals(3518.0);
		accumulativeDealsOfCurrencyService.add(accumulativeDealsOfCurrency);
	}

	@Test
	public void getAccumulativeDealsOfCurrencyAndAssertIt() {

		accumulativeDealsOfCurrency = accumulativeDealsOfCurrencyService.getByCurrencyISOCode(TEST_CURRENCY_ISO_CODE);
		assertEquals(new Double(3518.0), accumulativeDealsOfCurrency.getAmountOfDeals());
		assertEquals(accumulativeDealsOfCurrency.getCurrencyISOCode(),TEST_CURRENCY_ISO_CODE);
	}

	@After
	public void deletAccumulativeDealsOfCurrencyAfterTestingIt() {
		accumulativeDealsOfCurrencyService.delete(accumulativeDealsOfCurrency);
	}

}
