package com.progresssoft.cdw.service;

import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;

public interface AccumulativeDealsOfCurrencyService {


	public void add(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);
	
	public void delete(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);

	public void update(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency);

	public AccumulativeDealsOfCurrency getByCurrencyISOCode(String isoCode);
}
