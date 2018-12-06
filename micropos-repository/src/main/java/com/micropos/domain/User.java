package com.micropos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false, unique=true)
	private Integer id;

	@Column(name="Name")
	private String name;

	@Column(name="Username")
	private String userName;

	@Column(name="Password")
	private String password;

	@Column(name = "Status")
	private String sasus;

	@Column(name = "email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_permissions_id", nullable = true)
	private UserPermission userPermission;

	@Column(name = "Last_modified")
	private Date lastModified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSasus() {
		return sasus;
	}

	public void setSasus(String sasus) {
		this.sasus = sasus;
	}

	public UserPermission getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(UserPermission userPermission) {
		this.userPermission = userPermission;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





}
