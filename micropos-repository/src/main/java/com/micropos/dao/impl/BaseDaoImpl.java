package com.micropos.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.micropos.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		// T's actual type. This is only used when type obj
		// cannot be sent in the method parameter
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public void create(T model) {
		getCurrentSession().save(model);

	}

	public Long merge(T model) {
		return (Long) getCurrentSession().merge(model);
	}

	public T read(Integer id) {
		return (T) getCurrentSession().get(type, id);
	}

	public void update(T model) {
		getCurrentSession().update(model);

	}

	public void delete(T model) {
		getCurrentSession().delete(model);

	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


}
