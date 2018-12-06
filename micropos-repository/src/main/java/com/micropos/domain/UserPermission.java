package com.micropos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_permissions")
public class UserPermission implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "Name")
	private String userType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ModulePermissions getUsersPermissions() {
		return usersPermissions;
	}

	public void setUsersPermissions(ModulePermissions usersPermissions) {
		this.usersPermissions = usersPermissions;
	}

	public ModulePermissions getSalesOrderPermissions() {
		return salesOrderPermissions;
	}

	public void setSalesOrderPermissions(ModulePermissions salesOrderPermissions) {
		this.salesOrderPermissions = salesOrderPermissions;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Users")
	@NotFound(action = NotFoundAction.IGNORE)
	private ModulePermissions usersPermissions;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sales_orders")
	@NotFound(action = NotFoundAction.IGNORE)
	private ModulePermissions salesOrderPermissions;
}
