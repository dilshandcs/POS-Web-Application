package com.micropos.dto;

import java.io.Serializable;

public class UserDetailDto implements Serializable {

	private Long userId;

	private String name;

	private String username;

	private String createdPW;
	private String email;

	private String userPermissionId;

	private String userPermissionType;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedPW() {
		return createdPW;
	}

	public void setCreatedPW(String createdPW) {
		this.createdPW = createdPW;
	}

	public String getUserPermissionId() {
		return userPermissionId;
	}

	public void setUserPermissionId(String userPermissionId) {
		this.userPermissionId = userPermissionId;
	}

	public String getUserPermissionType() {
		return userPermissionType;
	}

	public void setUserPermissionType(String userPermissionType) {
		this.userPermissionType = userPermissionType;
	}

}
