package org.great.bean;
/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月17日 下午12:02:44 
* 类说明 :用于运营平台邮箱查找学员信息的实体bean
*/
public class StudentSearch {
	
	private String student_identification;//学员身份证
	private String student_name;//学员姓名
	private String school_province;//驾校省份
	private String school_city;//驾校城市
	private String school_name;//驾校名称
	private String mail_type;//邮件类型
	private String mail_theme;//邮件主题
	private String mailOrderType;//排序类型
	private String orderSeq;//排序顺序
	public String getStudent_identification() {
		return student_identification;
	}
	public void setStudent_identification(String student_identification) {
		this.student_identification = student_identification;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getSchool_province() {
		return school_province;
	}
	public void setSchool_province(String school_province) {
		this.school_province = school_province;
	}
	public String getSchool_city() {
		return school_city;
	}
	public void setSchool_city(String school_city) {
		this.school_city = school_city;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	
	public String getMail_type() {
		return mail_type;
	}
	public void setMail_type(String mail_type) {
		this.mail_type = mail_type;
	}
	
	public String getMail_theme() {
		return mail_theme;
	}
	public void setMail_theme(String mail_theme) {
		this.mail_theme = mail_theme;
	}
	public String getMailOrderType() {
		return mailOrderType;
	}
	public void setMailOrderType(String mailOrderType) {
		this.mailOrderType = mailOrderType;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	
	public StudentSearch(String student_identification, String student_name, String school_province, String school_city,
			String school_name, String mail_type, String mail_theme, String mailOrderType, String orderSeq) {
		super();
		this.student_identification = student_identification;
		this.student_name = student_name;
		this.school_province = school_province;
		this.school_city = school_city;
		this.school_name = school_name;
		this.mail_type = mail_type;
		this.mail_theme = mail_theme;
		this.mailOrderType = mailOrderType;
		this.orderSeq = orderSeq;
	}
	public StudentSearch() {
		super();
	}
	
	
	
}
