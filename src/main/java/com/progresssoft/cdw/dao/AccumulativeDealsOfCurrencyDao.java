package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
/**
 * 
 * @author Yazeed
 *
 */
public interface AccumulativeDealsOfCurrencyDao {

	public void add(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);
	
	public void delete(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);

	public void update(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);

	public AccumulativeDealsOfCurrency getByCurrencyISOCode(String isoCode);
}
