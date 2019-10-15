package com.progresssoft.cdw.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
import com.progresssoft.cdw.enums.ISOCurrencyCode;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccumulativeDealsOfCurrencyServiceTest {

	@Autowired
	private AccumulativeDealsOfCurrencyService accumulativeDealsOfCurrencyService;

	@Test
	public void test() {
		AccumulativeDealsOfCurrency accumulativeDealsOfCurrency = new AccumulativeDealsOfCurrency();
		accumulativeDealsOfCurrency.setCurrencyISOCode(ISOCurrencyCode.JOD.toString());
		accumulativeDealsOfCurrency.setAmountOfDeals(3518.0);
		accumulativeDealsOfCurrencyService.add(accumulativeDealsOfCurrency);
		AccumulativeDealsOfCurrency byCurrencyISOCode = accumulativeDealsOfCurrencyService
				.getByCurrencyISOCode(ISOCurrencyCode.JOD.toString());
		assertEquals(new Double(3518.0), byCurrencyISOCode.getAmountOfDeals());
		assertEquals(byCurrencyISOCode.getCurrencyISOCode(), ISOCurrencyCode.JOD.toString());
		accumulativeDealsOfCurrencyService.delete(accumulativeDealsOfCurrency);
	}

}
