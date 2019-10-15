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

	public void importCSVDeals(Map<RowType, List<String[]>> deals,String sourceName);
}
