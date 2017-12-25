package org.great.bean;
/*教练表*/
public class TeacherBean {

	private String teacher_id;//教练id
	private String teacher_account;//教练账号
	private String teacher_identification;//
	private String teacher_password;//教练密码
	private String teacher_name;//教练名字
	private String teacher_sex;//教练性别
	private String teacher_province;//教练省份
	private String teacher_city;//教练城市
	private String teacher_address;//教练详细地址
	private String teacher_phone;//教练电话
	private String teacher_state;//教练状态
	private String teacher_eval_score;//
	private String course_id;//科目id  作为外键
	private String school_id;//教练对应的驾校  作为外键
	
	private int	 pagemax;//页码最大值
	private int	 pagemin;//页码最小值
	private int	 maxgoal=10;//最大分
	private int	 mingoal=0;//最小分
	private String course_name;
	

	
	
	//空参构造
	public TeacherBean() {
		super();
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getTeacher_account() {
		return teacher_account;
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

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
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

	public String getTeacher_identification() {
		return teacher_identification;
	}

	public void setTeacher_identification(String teacher_identification) {
		this.teacher_identification = teacher_identification;
	}

	

	public int getPagemax() {
		return pagemax;
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

	public int getMaxgoal() {
		return maxgoal;
	}

	public void setMaxgoal(int maxgoal) {
		this.maxgoal = maxgoal;
	}

	public int getMingoal() {
		return mingoal;
	}

	public void setMingoal(int mingoal) {
		this.mingoal = mingoal;
	}

	

	public TeacherBean(String teacher_id, String teacher_account, String teacher_password, String teacher_name,
			String teacher_sex, String course_id, String school_id, String teacher_identification,
			String teacher_eval_score) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_account = teacher_account;
		this.teacher_password = teacher_password;
		this.teacher_name = teacher_name;
		this.teacher_sex = teacher_sex;
		this.course_id = course_id;
		this.school_id = school_id;
		this.teacher_identification = teacher_identification;
		this.teacher_eval_score = teacher_eval_score;
	}

	public TeacherBean(String teacher_id, String teacher_account, String teacher_password, String teacher_name,
			String teacher_sex, String course_id, String school_id, String teacher_identification,
			String teacher_eval_score, int pagemax, int pagemin, int maxgoal, int mingoal) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_account = teacher_account;
		this.teacher_password = teacher_password;
		this.teacher_name = teacher_name;
		this.teacher_sex = teacher_sex;
		this.course_id = course_id;
		this.school_id = school_id;
		this.teacher_identification = teacher_identification;
		this.teacher_eval_score = teacher_eval_score;
		this.pagemax = pagemax;
		this.pagemin = pagemin;
		this.maxgoal = maxgoal;
		this.mingoal = mingoal;
	}

	public TeacherBean(String teacher_id, String teacher_account, String teacher_identification,
			String teacher_password, String teacher_name, String teacher_sex, String teacher_province,
			String teacher_city, String teacher_address, String teacher_phone, String teacher_state,
			String teacher_eval_score, String course_id, String school_id) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_account = teacher_account;
		this.teacher_identification = teacher_identification;
		this.teacher_password = teacher_password;
		this.teacher_name = teacher_name;
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

	

	

	

	

}
