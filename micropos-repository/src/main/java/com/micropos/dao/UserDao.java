package com.micropos.dao;

import java.util.List;

import com.micropos.domain.User;
import com.micropos.domain.UserPermission;

public interface UserDao extends BaseDao<User>{
	User readUserByName(String userName);
	UserPermission getUserPermissionById(Integer id);
	List<User> getuserbyname(String name, String selectOption);
	User getuserbyid(Integer userId);
	void editUser(User user);
	void save(User user);
	List<User> getAll();

}
