package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
/**
 * 
 * @author Yazeed
 *
 */
public interface AccumulativeDealsOfCurrencyDao {

	/**
	 * To add a accumulative deals of currency to the data source.
	 * 
	 * @param accumulativeDealsOfCurrency
	 */
	public void add(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);
	
	/**
	 * To delete a accumulative deals Of currency from the data source.
	 * 
	 * @param accumulativeDealsOfCurrency
	 */
	public void delete(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);

	/**
	 * To update a accumulative deals Of currency in the data source.
	 * 
	 * @param accumulativeDealsOfCurrency
	 */
	public void update(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);

	/**
	 * To inquire about a accumulative deals Of currency in the data source
	 * @param isoCode
	 * @return
	 */
	public AccumulativeDealsOfCurrency getByCurrencyISOCode(String isoCode);
}
