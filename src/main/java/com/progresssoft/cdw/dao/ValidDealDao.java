package com.progresssoft.cdw.dao;

import com.progresssoft.cdw.entity.ValidDeal;

/**
 * 
 * @author Yazeed
 *
 */
public interface ValidDealDao {

	public Boolean isExistsByDealId(String dealId);
	
	public ValidDeal get(String dealId);
	
	public void add(ValidDeal deal);
	
	public void delete(ValidDeal deal);
	
}
