package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.Mail;
import org.great.mapper.MailMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author ����: ���ս�
 * @version ����ʱ�䣺2017��8��14�� ����9:14:05 
 * ��˵�� 
 */
@Controller
public class SendMailAction extends ActionSupport{
	@Resource
	MailMapper mailMapper;
	//ѧ��������������
	private Mail mail;
	
	public String estimateSendMail(){
		System.out.println("estimateSendMail");
		//��ȡ��¼ѧ��id
		Map<String, Object> map=ActionContext.getContext().getSession();
		String student_id=String.valueOf(map.get("student_id"));
		mail.setStudent_id(student_id);
		int isInsert=mailMapper.insertMail(mail);
		return "subSuccess";
	}
	//��������ʼ�
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
