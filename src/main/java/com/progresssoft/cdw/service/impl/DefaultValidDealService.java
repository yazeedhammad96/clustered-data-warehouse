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
import com.progresssoft.cdw.service.ValidDealService;

/**
 * @author Yazeed Hammad - yazeed.hammad@erabia.com
 *
 *         Oct 13, 2019
 */

/**
 * @author yhammad
 *
 */
@Service
public class DefaultValidDealService implements ValidDealService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultValidDealService.class);

	@Autowired
	private ValidDealDao validDealDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.progresssoft.cdw.service.ValidDealServiec#isExistsByDealId(java.lang.
	 * String)
	 */
	@Override
	public Boolean isExistsByDealId(String dealId) {
		if (StringUtils.isEmpty(dealId)) {
			throw new IllegalArgumentException("dealId can't be null or empty");
		}
		LOG.info(String.format("Checking If Deal Exists By Deal Id [%s]", dealId));
		return validDealDao.isExistsByDealId(dealId);
	}

}
