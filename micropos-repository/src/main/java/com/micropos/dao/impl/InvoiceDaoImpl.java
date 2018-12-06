package com.micropos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.InoviceDao;
import com.micropos.domain.Items;
import com.micropos.domain.Sales;

@Repository
public class InvoiceDaoImpl extends BaseDaoImpl<Sales> implements InoviceDao{
	@Autowired
	SessionFactory sessionFactory;
	public List<Sales> SearchAdvanceInvoices(String query) {
		List<Sales> resultList = new ArrayList<Sales>();
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.disjunction().add(Restrictions.like("invoiceNo", query, MatchMode.ANYWHERE).ignoreCase()));
		criteria.add(Restrictions.eq("isDelete", false));
		criteria.add(Restrictions.eq("isAdvance", true));
		resultList = criteria.list();
		return resultList;
	}

	public List<Sales> SearchInvoices(String query) {
		List<Sales> resultList = new ArrayList<Sales>();
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.disjunction().add(Restrictions.like("invoiceNo", query, MatchMode.ANYWHERE).ignoreCase()));
		criteria.add(Restrictions.eq("isDelete", false));
		resultList = criteria.list();
		return resultList;
	}


	public List<Sales> getInvoice(String voidinvoiceId) {
		List<Sales> resultList = new ArrayList<Sales>();
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.eq("invoiceNo", voidinvoiceId));
		resultList = criteria.list();
		return resultList;

}}
