package org.great.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/*�˹�ƽ̨����������*/
public class TransportLoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		//���˸��˺ţ������û�ʵ��
		if(null==ServletActionContext.getRequest().getSession().getAttribute("TransportUser")){
			return "input";
			
		}
		return arg0.invoke();
	}
	
}
