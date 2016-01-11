package com.zimu.spider.yirendai.app.model;
/**
 * 
 * @title 宜人贷抓取接口响应的公共字段
 * 
 * @author JasonChiu
 * @time 2016年1月11日上午12:55:16
 * @version 1.0
 */
public class BaseResp {
	
	private String errorCode;
	private String msg;
	private String result;

	public String getErrorCode() {
		return errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public String getResult() {
		return result;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toString() {
		return "BaseResp [result=" + this.result + ", errorCode="
				+ this.errorCode + ", msg=" + this.msg + "]";
	}
}
