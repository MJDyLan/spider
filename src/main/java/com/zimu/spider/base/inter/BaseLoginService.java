package com.zimu.spider.base.inter;

import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.http.HttpPostUtils;
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
		//TODO 302
		
		//TODO 处理Location没有域名的情况
		String result = HttpPostUtils.sendPostReq(getLoginUrl(), MapUtils.getParamStringEncoder(requestMap), isHttps());
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
