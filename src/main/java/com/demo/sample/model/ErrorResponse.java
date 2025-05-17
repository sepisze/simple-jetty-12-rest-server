package com.demo.sample.model;

public class ErrorResponse {

	public String title;
	public Integer status;
	public String detail;

	public ErrorResponse() {
	}

	public ErrorResponse(String title, Integer status, String detail) {
		this.title = title;
		this.status = status;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ErrorResponse [title=" + title + ", status=" + status + ", detail=" + detail + "]";
	}

}
