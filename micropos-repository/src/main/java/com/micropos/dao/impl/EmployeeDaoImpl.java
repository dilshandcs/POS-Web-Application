package com.micropos.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.EmployeeDao;
import com.micropos.domain.Employee;
import com.micropos.domain.SoldItems;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao{
	@Autowired
	SessionFactory sessionFactory;

	public void save(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);

	}

	public List<Employee> employeeRecords(Integer empId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
		criteria.createAlias("user", "user");
		criteria.add(Restrictions.eq("user.id",empId));
		criteria.createAlias("sale", "sale");
		criteria.add(Restrictions.eq("sale.isDelete",false));
		return criteria.list();
	}

	public List<Employee> getEmployeeBySale(String invoiceNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
		criteria.createAlias("sale", "sale");
		criteria.add(Restrictions.eq("sale.invoiceNo",invoiceNo));
		return criteria.list();
	}




}
