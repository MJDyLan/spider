package com.zimu.javacore.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.io.MyInputStreamUtils;

public class HttpResponse {
	
	private int responseCode;
	private String location;
	private InputStream in;
	private String responseBody;
	private String url;
	private Map<String,Object> responseHeaderMap = new HashMap<String, Object>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
	public void setResponseHeader(String key,String value){
		responseHeaderMap.put(key, value);
	}
}