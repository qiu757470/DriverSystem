package org.great.bean;
/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��17�� ����12:02:44 
* ��˵�� :������Ӫƽ̨�������ѧԱ��Ϣ��ʵ��bean
*/
public class StudentSearch {
	
	private String student_identification;//ѧԱ���֤
	private String student_name;//ѧԱ����
	private String school_province;//��Уʡ��
	private String school_city;//��У����
	private String school_name;//��У����
	private String mail_type;//�ʼ�����
	private String mail_theme;//�ʼ�����
	private String mailOrderType;//��������
	private String orderSeq;//����˳��
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
