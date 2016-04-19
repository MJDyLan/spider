package com.zimu.spider.base.inter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zimu.javacore.http.ConstHttp;
import com.zimu.javacore.http.HttpGetUtils;
import com.zimu.javacore.http.HttpPostUtils;
import com.zimu.javacore.http.HttpResponse;
import com.zimu.javacore.utils.MapUtils;

public abstract class BaseLoginService<T> implements IBaseLoginSevice<T>{
	//父类默认登录之前什么都不做,一般是登录之前，请求保存cookie
	public void doBefore(){}
	//父类默认登录之前什么都不做,一般登录请求时，需要指明http请求的来源
	public void buildHeader(Map<String,Object> map){}
	
	public abstract String getLoginUrl();
	
	public abstract void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);
	
	public abstract void buildRequestParam(Map<String,Object> requestMap);
	
	public T doLogin(String username,String password,String authcode){
		doBefore();
		Map<String,Object> header = new HashMap<String,Object>();
		//收集头信息
		buildHeader(header);
		Map<String,Object> requestMap = new HashMap<String,Object>();
		buildLoginParam(requestMap, username, password, authcode);
		HttpResponse response = HttpPostUtils.sendPost(getLoginUrl(), requestMap, isHttps(),header);
		String result = StringUtils.EMPTY;
		//TODO 302
		if(response.getResponseCode() == ConstHttp.STATUS_CODE_REDIRECT_302 ||response.getResponseCode() ==ConstHttp.STATUS_CODE_REDIRECT_301){
			if(isRedirect302()){
				String location = response.getLocation();
				//TODO 是否自动跳转
				result = HttpGetUtils.sendGetStrReq(location, isHttps());
			}
		}else if(response.getResponseCode() == ConstHttp.STATUS_CODE_SUCCESS){
			result = response.getResponseBody();
		}
		//父类组合登录请求步骤
		return toModel(result);
	}
	
	public T toModel(T resultStr){
		return resultStr;
	}
	
	public  boolean isHttps(){
		return true;
	}
	
	public  boolean isRedirect302(){
		return true;
	}

}
