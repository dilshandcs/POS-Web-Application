package com.micropos.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "module_permissions")
public class ModulePermissions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "Name")
	private String name;

	@Column(name = "CREATE")
	private Integer createAccess;

	@Column(name = "DELETE") 
	private Integer deleteAccess;

	@Column(name = "EDIT")
	private Integer editAccess;

	@Column(name = "VIEW")
	private Integer viewAccess;

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

	public Integer getCreateAccess() {
		return createAccess;
	}

	public void setCreateAccess(Integer createAccess) {
		this.createAccess = createAccess;
	}

	public Integer getDeleteAccess() {
		return deleteAccess;
	}

	public void setDeleteAccess(Integer deleteAccess) {
		this.deleteAccess = deleteAccess;
	}

	public Integer getEditAccess() {
		return editAccess;
	}

	public void setEditAccess(Integer editAccess) {
		this.editAccess = editAccess;
	}

	public Integer getViewAccess() {
		return viewAccess;
	}

	public void setViewAccess(Integer viewAccess) {
		this.viewAccess = viewAccess;
	}
	
	
}
