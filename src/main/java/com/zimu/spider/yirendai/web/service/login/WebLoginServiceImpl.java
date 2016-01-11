package com.zimu.spider.yirendai.web.service.login;

import org.springframework.stereotype.Service;

import com.zimu.spider.yirendai.app.constant.YirendaiConstants;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
@Service("webLoginService")
public class WebLoginServiceImpl implements WebLoginService {

	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.base.BaseService#getUrl()
	 */
	@Override
	public String getUrl() {
		return YirendaiConstants.LOGIN_URL;
	}
}
