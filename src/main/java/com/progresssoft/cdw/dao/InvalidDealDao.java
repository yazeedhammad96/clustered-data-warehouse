package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.InvalidDeal;

/**
 * 
 * @author Yazeed
 *
 */
public interface InvalidDealDao {

	/**
	 * To get an invalid file
	 * 
	 * @param dealId
	 * @return
	 */
	public InvalidDeal get(String dealId);

	public void add(InvalidDeal deal);

	public void delete(InvalidDeal deal);

}
