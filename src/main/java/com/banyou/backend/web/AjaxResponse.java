package com.banyou.backend.web;

public class AjaxResponse<T> {
	public final static int SUCCESS = 200;
	public final static int ERROR = 500;
	private int code = SUCCESS;
	private String message;
	private T result;
	private Object extInfo;
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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Object getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(Object extInfo) {
		this.extInfo = extInfo;
	}
}
