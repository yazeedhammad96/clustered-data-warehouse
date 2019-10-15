/**
 * 
 */
package com.progresssoft.cdw.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.progresssoft.cdw.dao.ImportDealDao;
import com.progresssoft.cdw.enums.RowType;

/**
 * @author Yazeed
 *
 */
@Repository
public class DefaultImportDealDao implements ImportDealDao {

	@Value("csv.numofcols")
	private String numOfCols;

	private static final String VALID_DEAL_TABLE_NAME = "valid_deal";

	private static final String INVALID_DEAL_TABLE_NAME = "not_valid_deal";

	private static final String  INSERT_QUERY = "INSERT INTO `bloomberg_deals`.`%s`(`dealId`,`fromCurrencyISOCode`,`toCurrencyISOCode`,`dealTimestamp`,`dealAmountInOrderingCurrency`,`sourceFile`)VALUES";
	
	private static final String VALUES_QUERY = "('%s','%s','%s','%s','%s','%s')";
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void importCSVDeals(Map<RowType, List<String[]>> deals, String sourceName) {

		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();

		insertBulkOfDeals(VALID_DEAL_TABLE_NAME, deals.get(RowType.VALID), sourceName, statelessSession);
		statelessSession.getTransaction().commit();
		statelessSession.getTransaction().begin();
		insertBulkOfDeals(INVALID_DEAL_TABLE_NAME, deals.get(RowType.INVALID), sourceName, statelessSession);
		statelessSession.getTransaction().commit();
	}

	private void insertBulkOfDeals(String tableName, List<String[]> deals, String sourceName, StatelessSession statelessSession) {
		final int[] count = { 0 };
		StringBuilder builder = new StringBuilder(String.format(INSERT_QUERY, tableName));
		
		int length = builder.length();
		deals.stream().forEach(row -> {
			count[0]++;
			builder.append(String.format(VALUES_QUERY, row[0], row[1], row[2], row[3], row[4],
					sourceName));
			if (count[0] % 3000 == 0) {
				SQLQuery nativeQuery = statelessSession.createSQLQuery(builder.toString());
				nativeQuery.executeUpdate();
				builder.setLength(0);
			builder.append(String.format(INSERT_QUERY, tableName));
			} else
				builder.append(",");
		});
		if (builder.length() > length) {
			builder.replace(builder.lastIndexOf(","), builder.length(), "");
			SQLQuery nativeQuery = statelessSession.createSQLQuery(builder.toString());
			nativeQuery.executeUpdate();
		}
	}

}
