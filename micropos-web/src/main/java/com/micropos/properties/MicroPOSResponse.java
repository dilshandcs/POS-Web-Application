package com.micropos.properties;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MicroPOSResponse implements Serializable{

	private Object data;
	private String message;
	private String title;
	private Boolean success;

	public MicroPOSResponse(Object data, String message, Boolean success,String title) {
		this.data = data;
		this.message = message;
		this.success = success;
		this.title=title;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
