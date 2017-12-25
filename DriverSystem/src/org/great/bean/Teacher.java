package org.great.bean;
/** 
 * @author 作者：郑永进
 * @version 创建时间：2017年8月10日
 * 类说明 ：教练表实体bean
 */
public class Teacher {

	private String teacher_id;//教练id
	private String teacher_account;//教练账号
	private String teacher_password;//教练密码
	private String teacher_name;//教练名字
	private String teacher_identification;//教练身份证
	private String teacher_sex;//教练性别
	private String teacher_province;//教练省份
	private String teacher_city;//教练城市
	private String teacher_address;//教练详细地址
	private String teacher_phone;//教练电话
	private String teacher_state;//教练状态
	private String teacher_eval_score;//教练总评分
	private String course_id;//科目id  作为外键
	private String school_id;//教练对应的驾校  作为外键
	private School school;

	//空参构造
	public Teacher() {
		super();
	}



	//setter  getter

	public Teacher(String teacher_id, String teacher_account, String teacher_password, String teacher_name,
			String teacher_identification, String teacher_sex, String teacher_province, String teacher_city,
			String teacher_address, String teacher_phone, String teacher_state, String teacher_eval_score,
			String course_id, String school_id) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_account = teacher_account;
		this.teacher_password = teacher_password;
		this.teacher_name = teacher_name;
		this.teacher_identification = teacher_identification;
		this.teacher_sex = teacher_sex;
		this.teacher_province = teacher_province;
		this.teacher_city = teacher_city;
		this.teacher_address = teacher_address;
		this.teacher_phone = teacher_phone;
		this.teacher_state = teacher_state;
		this.teacher_eval_score = teacher_eval_score;
		this.course_id = course_id;
		this.school_id = school_id;
	}


	//setter and getter
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_account() {
		return teacher_account;
	}
	public void setTeacher_account(String teacher_account) {
		this.teacher_account = teacher_account;
	}
	public String getTeacher_password() {
		return teacher_password;
	}
	public void setTeacher_password(String teacher_password) {
		this.teacher_password = teacher_password;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_identification() {
		return teacher_identification;
	}
	public void setTeacher_identification(String teacher_identification) {
		this.teacher_identification = teacher_identification;
	}
	public String getTeacher_sex() {
		return teacher_sex;
	}
	public void setTeacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	public String getTeacher_eval_score() {
		return teacher_eval_score;
	}
	public void setTeacher_eval_score(String teacher_eval_score) {
		this.teacher_eval_score = teacher_eval_score;
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
	public String getTeacher_province() {
		return teacher_province;
	}
	public void setTeacher_province(String teacher_province) {
		this.teacher_province = teacher_province;
	}
	public String getTeacher_city() {
		return teacher_city;
	}
	public void setTeacher_city(String teacher_city) {
		this.teacher_city = teacher_city;
	}
	public String getTeacher_address() {
		return teacher_address;
	}
	public void setTeacher_address(String teacher_address) {
		this.teacher_address = teacher_address;
	}
	public String getTeacher_phone() {
		return teacher_phone;
	}
	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}
	public String getTeacher_state() {
		return teacher_state;
	}
	public void setTeacher_state(String teacher_state) {
		this.teacher_state = teacher_state;
	}



	public School getSchool() {
		return school;
	}



	public void setSchool(School school) {
		this.school = school;
	}



}
