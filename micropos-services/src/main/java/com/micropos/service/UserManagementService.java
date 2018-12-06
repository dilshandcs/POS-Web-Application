package com.micropos.service;

import java.util.List;

import com.micropos.domain.User;
import com.micropos.domain.UserPermission;
import com.micropos.dto.LoginUserDetailsDto;
import com.micropos.dto.UserDetailDto;


public interface UserManagementService {

	LoginUserDetailsDto readUserDetailsByUserName(String userName);

	void updatePassword(String email, String password) throws Exception;

	List<User> findAllUsers() throws Exception;

	LoginUserDetailsDto findUserById(Long id) throws Exception;

	void update(LoginUserDetailsDto loginUserDetailsDto) throws Exception;

	void deleteUserById(Long id) throws Exception;

	String createOrUpdate(UserDetailDto userDetailDto, Boolean isCreate);

	List<UserPermission> getPermissions();

	List<UserDetailDto> searchUser(String name, String selectOption,int enterpriseId);

	UserDetailDto getuserbyid(int userId);

	void edituser(UserDetailDto userdetaildto);

	String getallUserNames(String userName);

	UserDetailDto getUserByUserName(String userName);

	boolean checkOldPassword( final Integer userId, final String password);

	void changePassword(final Integer userId,final String currentPassword,final String newPassword);

	public List<UserDetailDto> searchUserAll(int enterpriseId);
}
