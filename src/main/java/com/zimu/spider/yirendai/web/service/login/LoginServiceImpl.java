package com.zimu.spider.yirendai.web.service.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.security.CryptAES;
import com.zimu.javacore.security.MD5Utils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.javacore.utils.SignUtils;
import com.zimu.spider.yirendai.app.constant.YirendaiConstants;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.base.BaseService#getUrl()
	 */
	@Override
	public String getUrl() {
		return YirendaiConstants.LOGIN_URL;
	}
}
