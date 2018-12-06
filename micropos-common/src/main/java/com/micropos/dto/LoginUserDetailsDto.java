package com.micropos.dto;

import java.io.Serializable;
import java.util.List;

public class LoginUserDetailsDto  implements Serializable{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The email. */
	private String email;

	/** The name. */
	private String name;

	/** The user type. */
	private String userType;

	/** The user permission id. */
	private Integer userPermissionId;

	/** The password. */
	private String password;

	/** The username. */
	private String username;

	/** The last modified. */
	private Long lastModified;
	
	/** The micropos permissions. */
	private List<Object> microposPermissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getUserPermissionId() {
		return userPermissionId;
	}

	public void setUserPermissionId(Integer userPermissionId) {
		this.userPermissionId = userPermissionId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	public List<Object> getMicroposPermissions() {
		return microposPermissions;
	}

	public void setMicroposPermissions(List<Object> microposPermissions) {
		this.microposPermissions=microposPermissions;
	}
}
