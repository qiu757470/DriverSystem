package org.great.bean;
/*学号信息表*/
public class StudentBean {

	private String student_id;//学生表id
	private String student_identification;//学生身份证
	private String student_password;//学生密码
	private String student_name;//学生名字
	private String student_sex;//学号性别
	private String student_province;//学员省份
	private String student_city;//学员城市
	private String course_id;//学生安排
	private String student_phone;//学号联系方式
	private String student_address;//学生地址
	private String student_state;//学生状态
	private String school_id;//学校的id
	private String teacher_id;//教练的id
	private String student_register_time;//学员注册时间
	private String student_graduate_time;//学员毕业时间
	private String student_punch_time;//学员上次打卡时间
	private int	 pagemax;//页码最大值
	private int	 pagemin;//页码最小值
	private String course_name;
	
	
	//空参构造
	public StudentBean() {
		super();
	}

	public StudentBean(String student_id, String student_identification, String student_password, String student_name,
			String student_sex,String student_province,String student_city,
			String course_id, String student_phone, String student_address,
			String student_state, String school_id, String teacher_id) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.student_province = student_province;
		this.student_city = student_city;
		
		this.course_id = course_id;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_state = student_state;
		this.school_id = school_id;
		this.teacher_id = teacher_id;
	}

	public StudentBean(String student_id, String student_identification, String student_password, String student_name,
			String student_sex, String course_id, String student_phone, String student_address,
			String student_state, String school_id, String teacher_id, int pagemax, int pagemin) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.course_id = course_id;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_state = student_state;
		this.school_id = school_id;
		this.teacher_id = teacher_id;
		this.pagemax = pagemax;
		this.pagemin = pagemin;
	}

	public StudentBean(String student_id, String student_identification, String student_password, String student_name,
			String student_sex, String student_province, String student_city, String course_id, String student_phone,
			String student_address, String student_state, String school_id, String teacher_id,
			String student_register_time) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.student_province = student_province;
		this.student_city = student_city;
		this.course_id = course_id;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_state = student_state;
		this.school_id = school_id;
		this.teacher_id = teacher_id;
		this.student_register_time = student_register_time;
	}

	public String getStudent_id() {
		return student_id;
	}
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
	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
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
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
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
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getTeacher_id() {
		return teacher_id;
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

	public String getStudent_punch_time() {
		return student_punch_time;
	}

	public void setStudent_punch_time(String student_punch_time) {
		this.student_punch_time = student_punch_time;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getPagemax() {
		return pagemax;
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

	public void setPagemax(int pagemax) {
		this.pagemax = pagemax;
	}

	public int getPagemin() {
		return pagemin;
	}

	public void setPagemin(int pagemin) {
		this.pagemin = pagemin;
	}
	
	//setter  getter
	
}
