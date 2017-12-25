package org.great.web;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.Student;
import org.great.bean.TransNotice;
import org.great.mapper.TransNoticeMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author ����: ���ս�
 * @version ����ʱ�䣺2017��8��11�� ����11:44:13 
 * �鿴������Ϣ
 */
@Controller
public class TransNoticeAction1 extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	TransNoticeMapper transNoticeMapper;

	//��ȡѧ���Ѷ�����
	private List<Student> students;
	//��ȡѧ��δ������
	private List<Student> unReadStudents;
	//�Ƿ��Ѷ����
	private String isRead;
	private String click_trans_notice_id;
	//�鿴������ʾ������
	private TransNotice transNotice;
	//�鿴������Ϣ�������
	public String getNotice(){
		try {
		
			System.out.println("getNotice");
			students = transNoticeMapper.getUnreadNotice("�Ѷ�");
			unReadStudents = transNoticeMapper.getUnreadNotice("δ��");
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
		return SUCCESS;
	}
	//�Ѷ��������
	public String selectByClickTransNoticeId(){
		System.out.println("selectByClickTransNoticeId");
		System.out.println(click_trans_notice_id);
		transNotice = transNoticeMapper.selectByClickTransNoticeId(click_trans_notice_id);
		return "toNoticeShow";
	}
	//δ���������
	public String unreadClickAction(){
		System.out.println("unreadClickAction");
		transNotice = transNoticeMapper.selectByClickTransNoticeId(click_trans_notice_id);
		if(isRead.equals("true")){
			isRead = "�Ѷ�";
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
