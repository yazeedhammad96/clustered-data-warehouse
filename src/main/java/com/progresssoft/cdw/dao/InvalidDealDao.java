package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.InvalidDeal;

/**
 * 
 * @author Yazeed
 *
 */
public interface InvalidDealDao {

	/**
	 * To get an invalid deal from the data source.
	 * 
	 * @param dealId
	 * 
	 * @return InvalidDeal
	 */
	public InvalidDeal get(String dealId);

	/**
	 * To add an invalid deal to the data source.
	 * 
	 * @param deal
	 */
	public void add(InvalidDeal deal);

	/**
	 * To delete an invalid deal to the data source.
	 *
	 * @param deal
	 */

	public void delete(InvalidDeal deal);

}
