/**
 * 
 */
package com.progresssoft.cdw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.progresssoft.cdw.dao.AccumulativeDealsOfCurrencyDao;
import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;
import com.progresssoft.cdw.service.AccumulativeDealsOfCurrencyService;

/**
 * @author Yazeed
 *
 */
@Service
public class DefaultAccumulativeDealsOfCurrencyService implements AccumulativeDealsOfCurrencyService {

	@Autowired
	private AccumulativeDealsOfCurrencyDao accumulativeDealsOfCurrencyDao;

	private final static Logger LOG = LoggerFactory.getLogger(DefaultAccumulativeDealsOfCurrencyService.class);

	@Override
	public void add(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency) {
		if (accumulativeDealsOfCurrency == null
				|| StringUtils.isEmpty(accumulativeDealsOfCurrency.getCurrencyISOCode())) {
			throw new IllegalArgumentException("accumulativeDealsOfCurrency or it's currency isoCode can't be null");
		}
		LOG.info(String.format("Add AccumulativeDealsOfCurrency with isoCurrency",
				accumulativeDealsOfCurrency.getCurrencyISOCode()));
		accumulativeDealsOfCurrencyDao.add(accumulativeDealsOfCurrency);
	}

	@Override
	public void update(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency) {
		if (accumulativeDealsOfCurrency == null
				|| StringUtils.isEmpty(accumulativeDealsOfCurrency.getCurrencyISOCode())) {
			throw new IllegalArgumentException("accumulativeDealsOfCurrency or it's currency isoCode can't be null");
		}
		LOG.info(String.format("Update AccumulativeDealsOfCurrency with isoCurrency",
				accumulativeDealsOfCurrency.getCurrencyISOCode()));
		accumulativeDealsOfCurrencyDao.update(accumulativeDealsOfCurrency);
	}

	@Override
	public AccumulativeDealsOfCurrency getByCurrencyISOCode(String isoCode) {
		if (StringUtils.isEmpty(isoCode)) {
			throw new IllegalArgumentException("isoCode can't be null or empty");
		}
		LOG.info(String.format("Get AccumulativeDealsOfCurrency with isoCurrency", isoCode));
		return accumulativeDealsOfCurrencyDao.getByCurrencyISOCode(isoCode);
	}

	@Override
	public void delete(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency) {
		if (accumulativeDealsOfCurrency == null
				|| StringUtils.isEmpty(accumulativeDealsOfCurrency.getCurrencyISOCode())) {
			throw new IllegalArgumentException("accumulativeDealsOfCurrency or it's currency isoCode can't be null");
		}
		LOG.info(String.format("Delete AccumulativeDealsOfCurrency with isoCurrency",
				accumulativeDealsOfCurrency.getCurrencyISOCode()));
		accumulativeDealsOfCurrencyDao.delete(accumulativeDealsOfCurrency);
		
	}

}
