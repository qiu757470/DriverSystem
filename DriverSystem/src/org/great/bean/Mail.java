package org.great.bean;
/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：邮箱表实体bean
*/
public class Mail {

	private String mail_id;//邮件的id
	private String mail_theme;//邮件的主题
	private String mail_content;//邮件的内容
	private String mail_time;//邮件的时间
	private String student_id;//学生的id  外键
	private String mail_type;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	//学生实体bean
	private Student student;
	public String getMail_type() {
		return mail_type;
	}
	public void setMail_type(String mail_type) {
		this.mail_type = mail_type;
	}
	//无参构造
	public Mail() {
		super();
	}
	//构造方法
	

	//setter  getter
	public String getMail_id() {
		return mail_id;
	}
	public Mail(String mail_id, String mail_theme, String mail_content, String mail_time, String student_id,
			String mail_type) {
		super();
		this.mail_id = mail_id;
		this.mail_theme = mail_theme;
		this.mail_content = mail_content;
		this.mail_time = mail_time;
		this.student_id = student_id;
		this.mail_type = mail_type;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public String getMail_theme() {
		return mail_theme;
	}
	public void setMail_theme(String mail_theme) {
		this.mail_theme = mail_theme;
	}
	public String getMail_content() {
		return mail_content;
	}
	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}
	public String getMail_time() {
		return mail_time;
	}
	public void setMail_time(String mail_time) {
		this.mail_time = mail_time;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	@Override
	public String toString() {
		return "Mail [mail_id=" + mail_id + ", mail_theme=" + mail_theme + ", mail_content=" + mail_content
				+ ", mail_time=" + mail_time + ", student_id=" + student_id + ", mail_type=" + mail_type + ", student="
				+ student + "]";
	}
	
}
