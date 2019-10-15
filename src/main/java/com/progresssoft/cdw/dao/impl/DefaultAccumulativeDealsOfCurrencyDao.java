/**
 * 
 */
package com.progresssoft.cdw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.progresssoft.cdw.dao.AccumulativeDealsOfCurrencyDao;
import com.progresssoft.cdw.entity.AccumulativeDealsOfCurrency;

/**
 * @author Yazeed
 *
 */
@Repository
public class DefaultAccumulativeDealsOfCurrencyDao implements AccumulativeDealsOfCurrencyDao {

	private static final String GET_ACC_DEAL_QUERY = "FROM AccumulativeDealsOfCurrency A WHERE A.currencyISOCode = '%s'";
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.insert(accumulativeDealsOfCurrency);
		statelessSession.getTransaction().commit();
		statelessSession.close();
		

	}

	@Override
	public void update(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.update(accumulativeDealsOfCurrency);
		statelessSession.getTransaction().commit();
		statelessSession.close();

	}

	@Override
	public AccumulativeDealsOfCurrency getByCurrencyISOCode(String isoCode) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		Query createQuery = statelessSession
				.createQuery(String.format(GET_ACC_DEAL_QUERY, isoCode));
		List<?> result = createQuery.list();
		statelessSession.close();
		if (result == null || result.isEmpty())
			return null;
		return (AccumulativeDealsOfCurrency) result.get(0); 
	}

	@Override
	public void delete(AccumulativeDealsOfCurrency accumulativeDealsOfCurrency) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.delete(accumulativeDealsOfCurrency);
		statelessSession.getTransaction().commit();
		statelessSession.close();
		
	}

}
