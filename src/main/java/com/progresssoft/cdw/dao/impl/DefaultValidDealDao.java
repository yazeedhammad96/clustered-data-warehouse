package com.progresssoft.cdw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.progresssoft.cdw.dao.ValidDealDao;
import com.progresssoft.cdw.entity.ValidDeal;

/**
 * 
 * @author Yazeed
 *
 */
@Repository
public class DefaultValidDealDao implements ValidDealDao {

	private static final String IS_EXISTS_VALID_DEAL_QUERY = "select count(*) from ValidDeal V WHERE V.dealId = '%s'";

	private static final String GET_VALID_DEAL_QUERY = "FROM ValidDeal V WHERE V.dealId = '%s'";
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isExistsByDealId(String dealId) {
		// TODO Auto-generated method stub
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();

		Query query = statelessSession.createQuery(String.format(IS_EXISTS_VALID_DEAL_QUERY, dealId));
		boolean isExists = (Long) query.uniqueResult() > 0;

		statelessSession.close();

		return isExists;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValidDeal get(String dealId) {
		// TODO Auto-generated method stub
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();

		Query createQuery = statelessSession.createQuery(String.format(GET_VALID_DEAL_QUERY, dealId));
		List<?> list = createQuery.list();

		statelessSession.close();

		return list == null || list.get(0) == null ? null : (ValidDeal) list.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(ValidDeal deal) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.insert(deal);
		statelessSession.getTransaction().commit();
		statelessSession.close();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ValidDeal deal) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.delete(deal);
		statelessSession.getTransaction().commit();
		statelessSession.close();

	}

}
