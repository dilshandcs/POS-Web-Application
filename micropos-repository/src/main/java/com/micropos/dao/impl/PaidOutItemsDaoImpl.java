package com.micropos.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.micropos.dao.PaidOutItemsDao;
import com.micropos.domain.PaidOut;
import com.micropos.domain.PaidOutItems;

public class PaidOutItemsDaoImpl extends BaseDaoImpl<PaidOutItems> implements PaidOutItemsDao{
	@Autowired
	SessionFactory sessionFactory;

	public List<PaidOutItems> getAllPaidOutItems() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(PaidOut.class);
		return criteria.list();
	}
}
