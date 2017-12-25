package org.great.web;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action基类，用于封装Action通用的方法。
 */
public class BaseAction extends ActionSupport implements RequestAware,SessionAware,ApplicationAware {
	
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	// 采用接口注入的方式统一获取session
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}
	
}
