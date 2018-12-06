package com.micropos.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.micropos.dao.UserDao;
import com.micropos.dao.UserPermissionDao;
import com.micropos.domain.ModulePermissions;
import com.micropos.domain.User;
import com.micropos.domain.UserPermission;
import com.micropos.dto.LoginUserDetailsDto;
import com.micropos.dto.SalesOrdersPermissionDto;
import com.micropos.dto.UserDetailDto;
import com.micropos.dto.UsersPermissionDto;
import com.micropos.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService{

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	@Autowired
	ShaPasswordEncoder shaPasswordEncoder;

	/** The user permission dao. */
	//@Autowired
	//private UserPermissionDao userPermissionDao;

	@Transactional
	public LoginUserDetailsDto readUserDetailsByUserName(String userName) {
		final List<Object> microPOSPermissionList = new ArrayList<Object>();
		final UsersPermissionDto usersPermissionDto = new UsersPermissionDto();
		final SalesOrdersPermissionDto salesOrdersPermissionDto = new SalesOrdersPermissionDto();
		final User user = userDao.readUserByName(userName);

		if (user == null) {
			return null;
		}

		final UserPermission userPermission = user.getUserPermission();

		final ModulePermissions usersPermission = userPermission.getUsersPermissions();
		final ModulePermissions salesOrdersPermission = userPermission.getSalesOrderPermissions();

		if (usersPermission == null) {

			usersPermissionDto.setCreateAccess(0);
			usersPermissionDto.setDeleteAccess(0);
			usersPermissionDto.setEditAccess(0);
			usersPermissionDto.setViewAccess(0);

		} else {

			usersPermissionDto.setCreateAccess(usersPermission.getCreateAccess());
			usersPermissionDto.setDeleteAccess(usersPermission.getDeleteAccess());
			usersPermissionDto.setEditAccess(usersPermission.getEditAccess());
			usersPermissionDto.setViewAccess(usersPermission.getViewAccess());

		}

		if (salesOrdersPermission == null) {

			salesOrdersPermissionDto.setCreateAccess(0);
			salesOrdersPermissionDto.setDeleteAccess(0);
			salesOrdersPermissionDto.setEditAccess(0);
			salesOrdersPermissionDto.setViewAccess(0);
		} else {

			salesOrdersPermissionDto.setCreateAccess(salesOrdersPermission.getCreateAccess());
			salesOrdersPermissionDto.setDeleteAccess(salesOrdersPermission.getDeleteAccess());
			salesOrdersPermissionDto.setEditAccess(salesOrdersPermission.getEditAccess());
			salesOrdersPermissionDto.setViewAccess(salesOrdersPermission.getViewAccess());
		}

		microPOSPermissionList.add(usersPermissionDto);
		microPOSPermissionList.add(salesOrdersPermissionDto);

		final LoginUserDetailsDto dto = new LoginUserDetailsDto();

		getUserDetails(user, dto, microPOSPermissionList);

		return dto;

	}

	private void getUserDetails(User user, LoginUserDetailsDto userDetailsDTO,
			List<Object> fleetminderPermissionList) {
		userDetailsDTO.setId(user.getId());
		userDetailsDTO.setName(user.getName());
		userDetailsDTO.setUsername(user.getUserName());
		userDetailsDTO.setPassword(user.getPassword());
		userDetailsDTO.setUserPermissionId(user.getUserPermission().getId());
		userDetailsDTO.setUserType(user.getUserPermission().getUserType());
		userDetailsDTO.setMicroposPermissions(fleetminderPermissionList);
	}

	public void updatePassword(String email, String password) throws Exception {
		// TODO Auto-generated method stub

	}

	public List<User> findAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public LoginUserDetailsDto findUserById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(LoginUserDetailsDto loginUserDetailsDto) throws Exception {
		// TODO Auto-generated method stub

	}

	public void deleteUserById(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	public String createOrUpdate(UserDetailDto userDetailDto, Boolean isCreate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserPermission> getPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserDetailDto> searchUser(String name, String selectOption, int enterpriseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDetailDto getuserbyid(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void edituser(UserDetailDto userdetaildto) {
		// TODO Auto-generated method stub

	}

	public String getallUserNames(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDetailDto getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public boolean checkOldPassword(Integer userId, String password) {
		User user=userDao.getuserbyid(userId);
		if(user.getPassword().equals(shaPasswordEncoder.encodePassword(password, null))) {
			return true;
		}
		return false;
	}
	@Transactional
	public void changePassword(Integer userId, String currentPassword, String newPassword)
	{
		if (currentPassword == null || currentPassword.isEmpty()) {
			throw new RuntimeException("Current password is set to null, please enter correct password");
		}

		if (newPassword == null || newPassword.isEmpty()) {
			throw new RuntimeException("New password cannot be null");
		}
		User user = userDao.getuserbyid(userId);

		if (user.getPassword().equals(shaPasswordEncoder.encodePassword(currentPassword, null))) {
			user.setPassword(shaPasswordEncoder.encodePassword(newPassword, null));
			user.setLastModified(new Date());
		} else {
			throw new RuntimeException("Current password is incorrect");
		}
	}
	@Transactional
	public List<UserDetailDto> searchUserAll(int enterpriseId) {
		// TODO Auto-generated method stub
		return null;
	}

}
