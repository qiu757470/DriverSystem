package org.great.web;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.School;
import org.great.bean.SchoolNotice;
import org.great.bean.StudentBean;
import org.great.bean.TransNotice;
import org.great.mapper.SchoolNoticeMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class SchoolNoticeAction extends ActionSupport{
	@Resource
	private  SchoolNoticeMapper noticeMapper;
	 
	  private String trans_notice_id;//公告的id
	  private String trans_notice_theme;//公告的主题
	  private String trans_notice_content;//公告的内容
	  private String trans_notice_time;//公告的时间
	  private String trans_notice_province;//公告的省份
	  private String trans_notice_city;//公告的城市
	  private String trans_notice_target;//公告目标
	  private String trans_user_id;//运管的id  作为外键
	  private String school_manager_id;//驾校管理员id
	
	  private School school;
	private TransNotice transNotice;
	private List<SchoolNotice> schoolNoticeList;
	private List<TransNotice> transNoticeList;
	private List<TransNotice> transhasNoticeList;

	
	
	public String findNotice(){
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		System.out.println(school_id+" id");
		school=noticeMapper.schoolCity(school_id);
		System.out.println(school_id+" id" +" city+"+trans_notice_city);
		transNoticeList=noticeMapper.findNotice(school.getSchool_province(),school.getSchool_city(),school_id);
		transhasNoticeList=noticeMapper.findhasNotice(school.getSchool_province(),school.getSchool_city(),school_id);
		return SUCCESS;
		
		
	}

	public String checkNotice(){
		System.out.println(trans_notice_id+"     z");
		noticeMapper.updatestu(trans_notice_id);
		transNotice=noticeMapper.checkNotice(trans_notice_id);
		
		trans_notice_content=transNotice.getTrans_notice_content();
		System.out.println(trans_notice_content);
		return "check";
		
		
	}
	public String checkNoticed(){
		System.out.println(trans_notice_id+"     z");
		noticeMapper.updatestu(trans_notice_id);
		transNotice=noticeMapper.checkNotice(trans_notice_id);
		
		trans_notice_content=transNotice.getTrans_notice_content();
		System.out.println(trans_notice_content);
		return "checkd";
		
		
	}
	public String sendNotice(){
		return "sendNotice";
	}
	public String insertNotice(){
	
		String time=trans_notice_time.substring(0, 4)+"年"+trans_notice_time.substring(4, 6)+
		"月"+trans_notice_time.substring(6, 8)
		+"日"+trans_notice_time.substring(8, 10)+":"+trans_notice_time.substring(10, 12)+":"+trans_notice_time.substring(12, 14);
		System.out.println(time);
		
		
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		String schoolmanagerid=(String) ActionContext.getContext().getSession().get("schoolmanager_id");
		List<StudentBean>list=noticeMapper.student(school_id);
		school=noticeMapper.schoolCity(school_id);
		TransNotice transNotice=new TransNotice(null,trans_notice_theme,trans_notice_content,trans_notice_time,school.getSchool_province(),school.getSchool_city(),"学员",null,schoolmanagerid);
		
		noticeMapper.insertTnotice(transNotice);
		System.out.println("success");
		
		String max=noticeMapper.selectmax();
		
		System.out.println(max+" max");
		
		for(StudentBean s:list){
			System.out.println( s.getStudent_id());
			noticeMapper.insertsNote(max,s.getStudent_id());
		}
		
		return toSendedNotice();
	}

	public String toSendedNotice(){
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		transNoticeList=noticeMapper.NoticehasSend(school_id);
		return "toSendedNotice";
	}


	public SchoolNoticeMapper getNoticeMapper() {
		return noticeMapper;
	}




	public void setNoticeMapper(SchoolNoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}







	public String getTrans_notice_content() {
		return trans_notice_content;
	}




	public List<TransNotice> getTranshasNoticeList() {
		return transhasNoticeList;
	}

	public void setTranshasNoticeList(List<TransNotice> transhasNoticeList) {
		this.transhasNoticeList = transhasNoticeList;
	}

	public void setTrans_notice_content(String trans_notice_content) {
		this.trans_notice_content = trans_notice_content;
	}




	



	public String getTrans_notice_time() {
		return trans_notice_time;
	}




	public void setTrans_notice_time(String trans_notice_time) {
		this.trans_notice_time = trans_notice_time;
	}




	public List<SchoolNotice> getSchoolNoticeList() {
		return schoolNoticeList;
	}




	public String getTrans_notice_theme() {
		return trans_notice_theme;
	}




	public void setTrans_notice_theme(String trans_notice_theme) {
		this.trans_notice_theme = trans_notice_theme;
	}




	public TransNotice getTransNotice() {
		return transNotice;
	}

	public void setTransNotice(TransNotice transNotice) {
		this.transNotice = transNotice;
	}

	public List<TransNotice> getTransNoticeList() {
		return transNoticeList;
	}

	public void setTransNoticeList(List<TransNotice> transNoticeList) {
		this.transNoticeList = transNoticeList;
	}

	public String getTrans_notice_id() {
		return trans_notice_id;
	}




	public void setTrans_notice_id(String trans_notice_id) {
		this.trans_notice_id = trans_notice_id;
	}




	public String getTrans_notice_province() {
		return trans_notice_province;
	}




	public void setTrans_notice_province(String trans_notice_province) {
		this.trans_notice_province = trans_notice_province;
	}




	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getTrans_notice_city() {
		return trans_notice_city;
	}




	public void setTrans_notice_city(String trans_notice_city) {
		this.trans_notice_city = trans_notice_city;
	}




	public String getTrans_notice_target() {
		return trans_notice_target;
	}




	public void setTrans_notice_target(String trans_notice_target) {
		this.trans_notice_target = trans_notice_target;
	}




	public String getTrans_user_id() {
		return trans_user_id;
	}




	public void setTrans_user_id(String trans_user_id) {
		this.trans_user_id = trans_user_id;
	}




	public String getSchool_manager_id() {
		return school_manager_id;
	}




	public void setSchool_manager_id(String school_manager_id) {
		this.school_manager_id = school_manager_id;
	}




	public void setSchoolNoticeList(List<SchoolNotice> schoolNoticeList) {
		this.schoolNoticeList = schoolNoticeList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
