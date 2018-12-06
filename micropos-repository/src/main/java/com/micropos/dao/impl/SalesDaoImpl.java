package com.micropos.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.SalesDao;
import com.micropos.domain.Employee;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;
@Repository
public class SalesDaoImpl extends BaseDaoImpl<Sales> implements SalesDao{
	@Autowired
	SessionFactory sessionFactory;
	public Integer maximumInvoiceNumber() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.setProjection(Projections.max("id"));
		return (Integer) criteria.uniqueResult();
	}
	public void saveSales(Sales sale) {
		Session session1=sessionFactory.getCurrentSession();
		session1.saveOrUpdate(sale);
	}

	public void saveSoldItem(SoldItems soldItem) {
		Session session1=sessionFactory.getCurrentSession();
		session1.saveOrUpdate(soldItem);
	}
	public Sales getAdvanceSale(Integer invoiceId) {

		Sales advanceSale= read(invoiceId);
		return advanceSale;
	}

	public List<Sales> getSale(String invoiceId) {

		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.eq("invoiceNo", invoiceId));
		return criteria.list();
	}

	public Sales getById(Integer invoiceId) {

		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.eq("id", invoiceId));
		return (Sales) criteria.uniqueResult();
	}

	public List<Sales> getAllSale() {

		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.eq("isDelete", false));
		return criteria.list();
	}

	public List<Sales> lastTenEmployeeRecords() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sales.class);
		criteria.add(Restrictions.eq("isEmp", false));
		criteria.add(Restrictions.eq("isDelete", false));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(10);
		return criteria.list();
	}


}
