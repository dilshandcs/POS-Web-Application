package com.micropos.dto;

import java.util.HashMap;

public enum SalesEnum {
	SALES(1,"SALES"),
	ITEMS(2,"ITEMS");

	private final String value;
	private final Integer id;

	private static HashMap<Integer, SalesEnum> statusValueMap = new HashMap<Integer,SalesEnum>();


	static {
		for (SalesEnum status : SalesEnum.values()) {
			statusValueMap.put(status.id, status);
		}
	}

	private SalesEnum(final Integer id,String value) {
		this.id=id;
		this.value=value;
	}

	public static SalesEnum getInstance(final Integer codeValue) {
		return statusValueMap.get(codeValue);
	}


	public String value() {
		return value;
	}

	public Integer id() {
		return id;
	}

}
