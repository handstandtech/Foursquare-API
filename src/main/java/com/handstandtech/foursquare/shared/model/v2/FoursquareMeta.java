package com.handstandtech.foursquare.shared.model.v2;

import java.io.Serializable;

public class FoursquareMeta implements Serializable {

	/**
	 * Default Serialization UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorType;
	private String errorDetail;
	private Integer code;

	public FoursquareMeta() {
		super();
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
