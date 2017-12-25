package org.great.bean;

import java.util.List;

/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：学员表实体bean
*/
public class Student {

	private String student_id;//学员表id
	private String student_identification;//学员身份证
	private String student_password;//学员密码
	private String student_name;//学生名字
	private String student_sex;//学员性别
	private String student_province;//学员省份
	private String student_city;//学员城市
	private String student_address;//学员地址
	private String student_phone;//学号联系方式
	private String student_state;//学员状态
	private String school_id;//学校的id
	private String course_id;//科目的id（当前进度）
	private String teacher_id;//教练的id（当前进度）
	private String student_register_time;//学员注册时间
	private String student_graduate_time;//学员毕业时间
	private Course course;
	private StudentCourse studentCouse;

	private String student_punch_time;//学员上次打卡时间
	//驾校的实体bean
	private School school;
	public String getStudent_punch_time() {
		return student_punch_time;
	}


	public void setStudent_punch_time(String student_punch_time) {
		this.student_punch_time = student_punch_time;
	}


	public School getSchool() {
		return school;
	}


	public void setSchool(School school) {
		this.school = school;
	}


	private List<TransNotice> transNotices;//运营公告多表
	public List<TransNotice> getTransNotices() {
		return transNotices;
	}


	public void setTransNotices(List<TransNotice> transNotices) {
		this.transNotices = transNotices;
	}


	//空参构造
	public Student() {
		super();
	}
	

	//构造方法
	public Student(String student_id, String student_identification, String student_password, String student_name,
			String student_sex, String student_province, String student_city, String student_address,
			String student_phone, String student_state, String school_id, String course_id, String teacher_id) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.student_province = student_province;
		this.student_city = student_city;
		this.student_address = student_address;
		this.student_phone = student_phone;
		this.student_state = student_state;
		this.school_id = school_id;
		this.course_id = course_id;
		this.teacher_id = teacher_id;
	}
	//setter and getter
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_identification() {
		return student_identification;
	}
	public void setStudent_identification(String student_identification) {
		this.student_identification = student_identification;
	}
	public String getStudent_password() {
		return student_password;
	}
	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getStudent_address() {
		return student_address;
	}
	public void setStudent_address(String student_address) {
		this.student_address = student_address;
	}
	public String getStudent_state() {
		return student_state;
	}
	public void setStudent_state(String student_state) {
		this.student_state = student_state;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getStudent_province() {
		return student_province;
	}
	public void setStudent_province(String student_province) {
		this.student_province = student_province;
	}
	public String getStudent_city() {
		return student_city;
	}
	public void setStudent_city(String student_city) {
		this.student_city = student_city;
	}
	public String getStudent_id() {
		return student_id;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public StudentCourse getStudentCouse() {
		return studentCouse;
	}


	public void setStudentCouse(StudentCourse studentCouse) {
		this.studentCouse = studentCouse;
	}


	public String getStudent_register_time() {
		return student_register_time;
	}


	public void setStudent_register_time(String student_register_time) {
		this.student_register_time = student_register_time;
	}


	public String getStudent_graduate_time() {
		return student_graduate_time;
	}


	public void setStudent_graduate_time(String student_graduate_time) {
		this.student_graduate_time = student_graduate_time;
	}
	
	
}
