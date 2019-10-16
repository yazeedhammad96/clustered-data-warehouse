package com.progresssoft.cdw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.progresssoft.cdw.dao.ImportedFileDao;
import com.progresssoft.cdw.entity.ImportedFile;

/**
 * 
 * @author Yazeed
 *
 */
@Repository
public class DefaultImportedFileDao implements ImportedFileDao {

	private static final String GET_IMPORTED_FILE_QUERY = "FROM ImportedFile I WHERE I.name = '%s'";
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(ImportedFile importedFile) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.insert(importedFile);
		statelessSession.getTransaction().commit();
		statelessSession.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImportedFile getByName(String name) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		Query query = statelessSession.createQuery(String.format(GET_IMPORTED_FILE_QUERY, name));
		List<?> list = query.list();
		statelessSession.close();
		if (list == null || list.isEmpty())
			return null;
		return (ImportedFile) list.get(0);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ImportedFile importedFile) {
		StatelessSession statelessSession = sessionFactory.openStatelessSession();
		statelessSession.beginTransaction();
		statelessSession.delete(importedFile);
		statelessSession.getTransaction().commit();
		statelessSession.close();
	}

}
