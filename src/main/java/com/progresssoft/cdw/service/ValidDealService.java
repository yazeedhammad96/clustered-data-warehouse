package com.progresssoft.cdw.service;

import com.progresssoft.cdw.entity.ValidDeal;

/**
 * @author Yazeed 
 * 
 */

public interface ValidDealService {

	/**
	 * To check about the valid deal if it's exists in the data source by calling the required dao.
	 * 
	 * @param dealId
	 * 
	 * @return Boolean
	 */
	public Boolean isExistsByDealId(String dealId);

	/**
	 * To get a valid deal from the data source by calling the required dao.
	 * 
	 * @param dealId
	 * 
	 * @return ValidDeal
	 */
	public ValidDeal get(String dealId);

	/**
	 * To add a valid deal to the data source by calling the required dao.
	 * 
	 * @param deal
	 */
	public void add(ValidDeal deal);

	/**
	 * To delete a valid deal from the data source by calling the required dao.
	 * 
	 * @param deal
	 */
	public void delete(ValidDeal deal);
}
