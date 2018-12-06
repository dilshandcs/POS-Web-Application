package com.micropos.dao;

import java.util.List;

import com.micropos.domain.UserPermission;

public interface UserPermissionDao extends BaseDao<UserPermission>{
	List<UserPermission> getAllUserPermissions();
	
	UserPermission getUserPermissionById(Integer id);
}
