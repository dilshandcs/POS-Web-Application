/*
 *================================================================
 * Copyright  (c)     : 2016 Auxenta Inc, All Rights Reserved
 *================================================================
 */
package com.micropos.email.dto;


import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * The Class EmailMetaData.
 */
public class EmailMetaData implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5476366337258358234L;

	/** The to email addresses. */
	private String toEmailAddresses;

	/** The subject. */
	private String subject;

	/** The vm file. */
	private String vmFile;

	private String fileName;

	byte[] output;

	private String path;

	/** The data. */
	private Map<String, Object> data;

	private boolean withAttachment;

	public EmailMetaData(){
		this.withAttachment = false;
	}

	/**
	 * Gets the to email addresses.
	 *
	 * @return the to email addresses
	 */
	public String getToEmailAddresses() {
		return toEmailAddresses;
	}

	/**
	 * Sets the to email addresses.
	 *
	 * @param toEmailAddresses
	 *            the new to email addresses
	 */
	public void setToEmailAddresses(String toEmailAddresses) {
		this.toEmailAddresses = toEmailAddresses;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject
	 *            the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the vm file.
	 *
	 * @return the vm file
	 */
	public String getVmFile() {
		return vmFile;
	}

	/**
	 * Sets the vm file.
	 *
	 * @param vmFile
	 *            the new vm file
	 */
	public void setVmFile(String vmFile) {
		this.vmFile = vmFile;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Emails:[%s] Subject:[%s] vmFile:[%s]", this.toEmailAddresses, this.subject,
				this.vmFile);
	}

	public boolean isWithAttachment() {
		return withAttachment;
	}

	public void setWithAttachment(boolean withAttachment) {
		this.withAttachment = withAttachment;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[]  getOutput() {
		return output;
	}

	public void setOutput(byte[] output) {
		this.output = output;
	}




}
