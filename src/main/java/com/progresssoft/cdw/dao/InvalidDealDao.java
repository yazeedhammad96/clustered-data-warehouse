package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.NotValidDeal;

/**
 * 
 * @author Yazeed
 *
 */
public interface InvalidDealDao {

	public NotValidDeal get(String dealId);

	public void add(NotValidDeal deal);

	public void delete(NotValidDeal deal);

}
