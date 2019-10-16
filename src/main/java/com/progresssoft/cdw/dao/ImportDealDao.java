package com.progresssoft.cdw.dao;

import java.util.List;
import java.util.Map;

import com.progresssoft.cdw.enums.RowType;

/**
 * 
 * @author Yazeed
 *
 */

public interface ImportDealDao {

	/**
	 * To insert a deals from a map that contains valid & invalid deals to the data source.
	 * 
	 * @param deals
	 * @param sourceName
	 */
	public void insertCSVDeals(Map<RowType, List<String[]>> deals,String sourceName);
}
