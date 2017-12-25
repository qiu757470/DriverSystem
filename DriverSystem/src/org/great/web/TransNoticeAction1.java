package org.great.web;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.Student;
import org.great.bean.TransNotice;
import org.great.mapper.TransNoticeMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author 作者: 王艺杰
 * @version 创建时间：2017年8月11日 下午11:44:13 
 * 查看公告消息
 */
@Controller
public class TransNoticeAction1 extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	TransNoticeMapper transNoticeMapper;

	//获取学生已读公告
	private List<Student> students;
	//获取学生未读公告
	private List<Student> unReadStudents;
	//是否已读标记
	private String isRead;
	private String click_trans_notice_id;
	//查看公告显示的数据
	private TransNotice transNotice;
	//查看公告消息点击动作
	public String getNotice(){
		try {
		
			System.out.println("getNotice");
			students = transNoticeMapper.getUnreadNotice("已读");
			unReadStudents = transNoticeMapper.getUnreadNotice("未读");
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
		return SUCCESS;
	}
	//已读点击动作
	public String selectByClickTransNoticeId(){
		System.out.println("selectByClickTransNoticeId");
		System.out.println(click_trans_notice_id);
		transNotice = transNoticeMapper.selectByClickTransNoticeId(click_trans_notice_id);
		return "toNoticeShow";
	}
	//未读点击动作
	public String unreadClickAction(){
		System.out.println("unreadClickAction");
		transNotice = transNoticeMapper.selectByClickTransNoticeId(click_trans_notice_id);
		if(isRead.equals("true")){
			isRead = "已读";
		}
		transNoticeMapper.upDateIsRead(isRead, click_trans_notice_id);
		return "toNoticeShow";
	}

	
	
	

	public TransNoticeMapper getTransNoticeMapper() {
		return transNoticeMapper;
	}
	public void setTransNoticeMapper(TransNoticeMapper transNoticeMapper) {
		this.transNoticeMapper = transNoticeMapper;
	}

	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Student> getUnReadStudents() {
		return unReadStudents;
	}
	public void setUnReadStudents(List<Student> unReadStudents) {
		this.unReadStudents = unReadStudents;
	}
	public String getClick_trans_notice_id() {
		return click_trans_notice_id;
	}


	public void setClick_trans_notice_id(String click_trans_notice_id) {
		this.click_trans_notice_id = click_trans_notice_id;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public TransNotice getTransNotice() {
		return transNotice;
	}
	public void setTransNotice(TransNotice transNotice) {
		this.transNotice = transNotice;
	}

}
