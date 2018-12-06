package com.micropos.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.PaidOutDao;
import com.micropos.domain.PaidOut;

@Repository
public class PaidOutDaoImpl extends BaseDaoImpl<PaidOut> implements PaidOutDao{
	@Autowired
	SessionFactory sessionFactory;

	public void SavePaidOut(PaidOut paidOut) {
		Session session1=sessionFactory.getCurrentSession();
		session1.saveOrUpdate(paidOut);

	}

	public List<PaidOut> getAllPaidOuts() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(PaidOut.class);
		return criteria.list();

	}

}
