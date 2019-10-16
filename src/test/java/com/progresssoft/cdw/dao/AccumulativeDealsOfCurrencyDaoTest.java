package com.progresssoft.cdw.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
import com.progresssoft.cdw.enums.ISOCurrencyCode;

/***
 * 
 * @author Yazeed
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccumulativeDealsOfCurrencyDaoTest {

	@Autowired
	private AccumulativeDealsOfCurrencyDao accumulativeDealsOfCurrencyDao;

	@Test
	public void addAccumulativeDealsOfCurrencyAndAssertIt() {
		AccumulativeDealsOfCurrency accumulativeDealsOfCurrency = new AccumulativeDealsOfCurrency();
		accumulativeDealsOfCurrency.setCurrencyISOCode(ISOCurrencyCode.ZWD.toString());
		accumulativeDealsOfCurrency.setAmountOfDeals(3518.0);
		accumulativeDealsOfCurrencyDao.add(accumulativeDealsOfCurrency);
		AccumulativeDealsOfCurrency byCurrencyISOCode = accumulativeDealsOfCurrencyDao
				.getByCurrencyISOCode(ISOCurrencyCode.ZWD.toString());
		assertEquals(new Double(3518.0), byCurrencyISOCode.getAmountOfDeals());
		assertEquals(byCurrencyISOCode.getCurrencyISOCode(), ISOCurrencyCode.ZWD.toString());
		accumulativeDealsOfCurrencyDao.delete(accumulativeDealsOfCurrency);
	}

}
