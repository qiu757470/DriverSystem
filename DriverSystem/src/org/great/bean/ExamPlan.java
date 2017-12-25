package org.great.bean;

import java.util.List;

/*考试安排表*/
public class ExamPlan {

	private String exam_plan_id;//考试安排表id  主键
	private String course_id;//科目表  id 外键
	private String teacher_id;//教练id  外键
	private String school_id;//驾校id外键
	private String exam_plan_date;//考试日期
	private String exam_plan_time;//考试时间
	private String exam_plan_province;//考试省份
	private String exam_plan_city;//考试城市
	private String exam_plan_address;//考试地点
	private String exam_plan_state;//考试状态
	private String exam_plan_input;//考试录入
	private Course course; //科目信息
	private Teacher teacher; //教练信息
	private List<Student> student;
	//构造
	public ExamPlan() {
		super();
	}
	
	
	public ExamPlan(String exam_plan_id, String course_id, String teacher_id, String school_id, String exam_plan_date,
			String exam_plan_time, String exam_plan_province, String exam_plan_city, String exam_plan_address,
			String exam_paln_state, String exam_plan_input) {
		super();
		this.exam_plan_id = exam_plan_id;
		this.course_id = course_id;
		this.teacher_id = teacher_id;
		this.school_id = school_id;
		this.exam_plan_date = exam_plan_date;
		this.exam_plan_time = exam_plan_time;
		this.exam_plan_province = exam_plan_province;
		this.exam_plan_city = exam_plan_city;
		this.exam_plan_address = exam_plan_address;
		this.exam_plan_state = exam_paln_state;
		this.exam_plan_input = exam_plan_input;
	}


	//setter  getter
	public String getExam_plan_id() {
		return exam_plan_id;
	}
	public void setExam_plan_id(String exam_plan_id) {
		this.exam_plan_id = exam_plan_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getExam_plan_date() {
		return exam_plan_date;
	}
	public void setExam_plan_date(String exam_plan_date) {
		this.exam_plan_date = exam_plan_date;
	}
	public String getExam_plan_time() {
		return exam_plan_time;
	}
	public void setExam_plan_time(String exam_plan_time) {
		this.exam_plan_time = exam_plan_time;
	}

	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getExam_plan_province() {
		return exam_plan_province;
	}
	public void setExam_plan_province(String exam_plan_province) {
		this.exam_plan_province = exam_plan_province;
	}
	public String getExam_plan_city() {
		return exam_plan_city;
	}
	public void setExam_plan_city(String exam_plan_city) {
		this.exam_plan_city = exam_plan_city;
	}
	public String getExam_plan_address() {
		return exam_plan_address;
	}
	public void setExam_plan_address(String exam_plan_address) {
		this.exam_plan_address = exam_plan_address;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	public String getExam_plan_state() {
		return exam_plan_state;
	}


	public void setExam_plan_state(String exam_plan_state) {
		this.exam_plan_state = exam_plan_state;
	}


	public String getExam_plan_input() {
		return exam_plan_input;
	}
	public void setExam_plan_input(String exam_plan_input) {
		this.exam_plan_input = exam_plan_input;
	}
	
}
