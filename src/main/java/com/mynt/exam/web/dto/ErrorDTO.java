package com.mynt.exam.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorDTO implements Serializable {
	
	private static final long serialVersionUID = 3941924201163288728L;
	
	private int code;
	private String message;
	private String timestamp;
	
	private List<String> fieldErrors = new ArrayList<>();
	
	public ErrorDTO(int code, String message, String timestamp, List<String> fieldErrors) {
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
		this.fieldErrors = fieldErrors;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
