package com.micropos.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.PastSalesDao;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;

@Repository
public class PastSalesDaoImpl  extends BaseDaoImpl<SoldItems> implements PastSalesDao{
	@Autowired
	private SessionFactory sessionFactory;

	public List<SoldItems> getAllSales() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SoldItems.class);
		return criteria.list();
	}

	public List<SoldItems> getAllPastSalesHistory() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SoldItems.class);
		criteria.createAlias("sale", "sale");
		criteria.add(Restrictions.eq("sale.isDelete",false));

		return criteria.list();
	}

	public List<SoldItems> getAllPastSalesHistorySaleId(int parseInt) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SoldItems.class);
		criteria.createAlias("sale", "sale");
		criteria.add(Restrictions.eq("sale.id",parseInt));
		return criteria.list();
	}

	public List<SoldItems> getAllPastSalesHistoryBiItemId(int parseInt) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SoldItems.class);
		criteria.createAlias("items", "item");
		criteria.add(Restrictions.eq("item.id",parseInt));
		return criteria.list();

	}



}
