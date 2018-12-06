package com.micropos.dto;

import java.io.Serializable;

public class SalesOrdersPermissionDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int createAccess;

	private int editAccess;

	private int deleteAccess;

	private int viewAccess;

	public int getCreateAccess() {
		return createAccess;
	}

	public void setCreateAccess(int createAccess) {
		this.createAccess = createAccess;
	}

	public int getEditAccess() {
		return editAccess;
	}

	public void setEditAccess(int editAccess) {
		this.editAccess = editAccess;
	}

	public int getDeleteAccess() {
		return deleteAccess;
	}

	public void setDeleteAccess(int deleteAccess) {
		this.deleteAccess = deleteAccess;
	}

	public int getViewAccess() {
		return viewAccess;
	}

	public void setViewAccess(int viewAccess) {
		this.viewAccess = viewAccess;
	}
}
