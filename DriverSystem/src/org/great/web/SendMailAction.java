package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.Mail;
import org.great.mapper.MailMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author 作者: 王艺杰
 * @version 创建时间：2017年8月14日 上午9:14:05 
 * 类说明 
 */
@Controller
public class SendMailAction extends ActionSupport{
	@Resource
	MailMapper mailMapper;
	//学生发送邮箱数据
	private Mail mail;
	
	public String estimateSendMail(){
		System.out.println("estimateSendMail");
		//获取登录学生id
		Map<String, Object> map=ActionContext.getContext().getSession();
		String student_id=String.valueOf(map.get("student_id"));
		mail.setStudent_id(student_id);
		int isInsert=mailMapper.insertMail(mail);
		return "subSuccess";
	}
	//点击发送邮件
	public String clickSendMail(){
		return SUCCESS;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}


	
}
