package com.progresssoft.cdw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.progresssoft.cdw.dao.InvalidDealDao;
import com.progresssoft.cdw.entity.NotValidDeal;

/**
 * 
 * @author Yazeed
 *
 */
@Repository
public class DefaultInvalidValidDealDao implements InvalidDealDao {


	private static final String GET_INVALID_DEAL_QUERY = "FROM NotValidDeal V WHERE V.dealId = '%s'";
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public NotValidDeal get(String dealId) {
		// TODO Auto-generated method stub
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();

		Query createQuery = statelessSession.createQuery(String.format(GET_INVALID_DEAL_QUERY, dealId));
		List<?> list = createQuery.list();

		statelessSession.close();

		return list == null || list.get(0) == null ? null : (NotValidDeal) list.get(0);
	}

	@Override
	public void add(NotValidDeal deal) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.insert(deal);
		statelessSession.getTransaction().commit();
		statelessSession.close();

	}

	@Override
	public void delete(NotValidDeal deal) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.delete(deal);
		statelessSession.getTransaction().commit();
		statelessSession.close();

	}

}
