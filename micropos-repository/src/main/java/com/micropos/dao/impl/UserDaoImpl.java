package com.micropos.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micropos.dao.UserDao;
import com.micropos.domain.User;
import com.micropos.domain.UserPermission;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	private final static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public User readUserByName(String userName) {
		LOGGER.info("readUserByName in  UserDaoImpl.userName is " + userName);

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));

		return (User) criteria.uniqueResult();

	}

	public UserPermission getUserPermissionById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getuserbyname(String name, String selectOption) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.ilike(selectOption, name, MatchMode.ANYWHERE));
		criteria.createAlias("userPermission", "userPermission").add(Restrictions.eq("userPermission.id", 3));

		return criteria.list();
	}

	public User getuserbyid(Integer userId) {
		Criteria criteria =sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id",userId));
		return (User) criteria.uniqueResult();
	}

	public void editUser(User user) {
		// TODO Auto-generated method stub

	}

	public void save(User user) {
		Session session=sessionFactory.openSession();
				session.save(user);
	}

	public List<User> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.createAlias("userPermission", "userPermission");
		criteria.add(Restrictions.eq("userPermission.id", 3));
		return criteria.list();
	}
}
