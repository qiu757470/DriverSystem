package org.great.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.great.bean.Mail;
import org.great.mapper.MailMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author 作者: 王艺杰
 * @version 创建时间：2017年8月14日 下午4:01:32 
 * 类说明 
 */
@Controller
public class ReplyMailAction extends ActionSupport{

	@Resource
	MailMapper mailMapper;
	private List<Mail> mails;
	//点击回复信息id
	private String delRreplyInfoId;
	//点击查看回复信息
	private Mail mail;

	private String student_id;
	//当前页数
	private String currentPage;

	public String getMailInfoByStudentId(){
		System.out.println("getMailInfoByStudentId");
		currentPage="1";
		Map<String, Object> map=ActionContext.getContext().getSession();
		//分页换算
		//分几页
		int pageSize=10;
		//最小
		int min=(Integer.parseInt(currentPage)-1)*pageSize;
		//最大页
		int max=(Integer.parseInt(currentPage)-1)*pageSize+pageSize;
		mails = mailMapper.getMailInfoByStudentId(String.valueOf(map.get("student_id")),min,max);
		for (Mail mail : mails) {
			mail.setMail_time(mail.getMail_time().substring(0,4)+"/"+mail.getMail_time().substring(4,6)+"/"+mail.getMail_time().substring(6,8)+" "+mail.getMail_time().substring(8,10)+":"+mail.getMail_time().substring(10,12));
		}
		return SUCCESS;
	}
	public String delRreplyInfo(){
		System.out.println("delRreplyInfo");
		int isDel=mailMapper.delRreplyInfoById(delRreplyInfoId);
		getMailInfoByStudentId();
		return SUCCESS;

	}
	public String checkMailContent(){

		mail = mailMapper.selectClickMailById(delRreplyInfoId);
		for (Mail mail : mails) {
			mail.setMail_time(mail.getMail_time().substring(0,4)+"/"+mail.getMail_time().substring(4,6)+"/"+mail.getMail_time().substring(6,8)+" "+mail.getMail_time().substring(8,10)+":"+mail.getMail_time().substring(10,12));
		}
		return "toSeeTheMail";
	}
	//学生查看回复信息详情
	public String toSeeReply(){
		mail = mailMapper.selectClickMailById(delRreplyInfoId);
		mail.setMail_time(mail.getMail_time().substring(0,4)+"/"+mail.getMail_time().substring(4,6)+"/"+mail.getMail_time().substring(6,8)+" "+mail.getMail_time().substring(8,10)+":"+mail.getMail_time().substring(10,12));
		return "toSeeReply";
	}
	//全选删除
	public String  deleteAll(){
		System.out.println("=============");
		HttpServletRequest request=ServletActionContext.getRequest();
		String[] item=request.getParameterValues("checks");
		List<String> delList = new ArrayList<String>();
		System.out.println(delList+"00000000000");
		for (String str : item) {
			delList.add(str);
			System.out.println(delList.add(str)+"11111111");
		}
		int i=mailMapper.deleteAll(delList);
		System.out.println(i+"222222");
		getMailInfoByStudentId();
		return SUCCESS;
	}
	public String paging(){

		System.out.println("paging");
		Map<String, Object> map=ActionContext.getContext().getSession();
		//分页换算
		//分几页
		int pageSize=10;
		System.out.println(currentPage);
		//最小
		int min=(Integer.parseInt(currentPage)-1)*pageSize;
		//最大页
		int max=(Integer.parseInt(currentPage)-1)*pageSize+pageSize;
		System.out.println(min+"???"+max);
		System.out.println(currentPage+"currentPage");
		mails = mailMapper.getMailInfoByStudentId(String.valueOf(map.get("student_id")),min,max);
		for (Mail mail : mails) {
			mail.setMail_time(mail.getMail_time().substring(0,4)+"/"+mail.getMail_time().substring(4,6)+"/"+mail.getMail_time().substring(6,8)+" "+mail.getMail_time().substring(8,10)+":"+mail.getMail_time().substring(10,12));
		}
		return SUCCESS;
	}

	public List<Mail> getMails() {
		return mails;
	}
	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}
	public String getDelRreplyInfoId() {
		return delRreplyInfoId;
	}
	public void setDelRreplyInfoId(String delRreplyInfoId) {
		this.delRreplyInfoId = delRreplyInfoId;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

}
