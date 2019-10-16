/**
 * 
 */
package com.progresssoft.cdw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.progresssoft.cdw.dao.ValidDealDao;
import com.progresssoft.cdw.entity.ValidDeal;
import com.progresssoft.cdw.service.ValidDealService;

/**
 * @author yhammad
 *
 */
@Service
public class DefaultValidDealService implements ValidDealService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultValidDealService.class);

	@Autowired
	private ValidDealDao validDealDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isExistsByDealId(String dealId) {
		if (StringUtils.isEmpty(dealId)) {
			throw new IllegalArgumentException("dealId can't be null or empty");
		}
		LOG.info(String.format("Checking If Deal Exists By Deal Id [%s]", dealId));
		return validDealDao.isExistsByDealId(dealId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValidDeal get(String dealId) {
		if (StringUtils.isEmpty(dealId)) {
			throw new IllegalArgumentException("dealId can't be null or empty");
		}

		LOG.info(String.format("Get Valid Deal By Deal Id [%s]", dealId));
		return validDealDao.get(dealId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(ValidDeal deal) {
		if (deal == null || StringUtils.isEmpty(deal.getDealId())) {
			throw new IllegalArgumentException("deal or it's id can't be null or empty");
		}

		LOG.info(String.format("Add a Valid Deal with Deal Id [%s]", deal.getDealId()));
		 validDealDao.add(deal);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ValidDeal deal) {
		if (deal == null || StringUtils.isEmpty(deal.getDealId())) {
			throw new IllegalArgumentException("deal or it's id can't be null or empty");
		}

		LOG.info(String.format("Delete a Valid Deal with Deal Id [%s]", deal.getDealId()));
		 validDealDao.delete(deal);

	}

}
