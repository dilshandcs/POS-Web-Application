package com.micropos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.ItemDao;
import com.micropos.domain.Items;

@Repository
public class ItemDaoImpl extends BaseDaoImpl<Items> implements ItemDao{
	@Autowired
	SessionFactory sessionFactory;
	public List<Items> searchParts(String query) {
		List<Items> resultList = new ArrayList<Items>();
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.disjunction().add(Restrictions.like("itemNumber", query, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("name", query, MatchMode.ANYWHERE).ignoreCase()));
		criteria.add(Restrictions.eq("isDelete", false));
		resultList = criteria.list();
		return resultList;
	}
	public Items getPartDetailsByItemNumber(String itemNumber) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.eq("itemNumber", itemNumber));
		return (Items) criteria.uniqueResult();
	}
	public List<Items> getAllActiveItems() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.eq("isDelete", false));
		return criteria.list();
	}
	public Items getItemById(Integer itemId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.eq("id", itemId));
		return (Items) criteria.uniqueResult();

	}
	public Items getItemByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.eq("name", name));
		return (Items) criteria.uniqueResult();
	}

}
