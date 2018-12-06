package com.micropos.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.micropos.dao.BaseDao;
import com.micropos.dao.InoviceDao;
import com.micropos.dao.UserPermissionDao;
import com.micropos.domain.Sales;
import com.micropos.domain.UserPermission;

@Repository
public class UserPermissionDaoImpl extends BaseDaoImpl<UserPermission> implements UserPermissionDao{

	@Autowired
	private SessionFactory sessionFactory;

	public List<UserPermission> getAllUserPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserPermission getUserPermissionById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
