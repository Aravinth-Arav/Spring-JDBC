package com.jdbc.demo.model;

public class Response {
	private int response_code;
	private String response_msg;
	private Object jData;
	private String data;
	
	
	public int getResponse_code() {
		return response_code;
	}
	public void setResponse_code(int response_code) {
		this.response_code = response_code;
	}
	public String getResponse_msg() {
		return response_msg;
	}
	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}
	public Object getjData() {
		return jData;
	}
	public void setjData(Object jData) {
		this.jData = jData;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	

}
