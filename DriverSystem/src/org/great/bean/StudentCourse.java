package org.great.bean;
/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：学员科目表实体bean
*/
public class StudentCourse {
 
	private String student_course_id;//学生成绩表id
	private String student_id;//外键   学生id
	private String course_id;//科目id  外键
	private String teacher_id;//学员该科目的教练
	private String student_course_score;//学生科目成绩
	private String student_course_time;//对应的科目时间
	private String student_course_state;//对应的科目状态
	
	private Student student;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	//空参构造
	public StudentCourse() {
		super();
	}
	//有参构造
	

	//setter  getter
	public String getStudent_course_id() {
		return student_course_id;
	}
	public StudentCourse(String student_course_id, String student_id, String course_id, String teacher_id,
			String student_course_score, String student_course_time, String student_course_state) {
		super();
		this.student_course_id = student_course_id;
		this.student_id = student_id;
		this.course_id = course_id;
		this.teacher_id = teacher_id;
		this.student_course_score = student_course_score;
		this.student_course_time = student_course_time;
		this.student_course_state = student_course_state;
		
	}
	public void setStudent_course_id(String student_course_id) {
		this.student_course_id = student_course_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public String getStudent_course_state() {
		return student_course_state;
	}
	public void setStudent_course_state(String student_course_state) {
		this.student_course_state = student_course_state;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getStudent_course_score() {
		return student_course_score;
	}
	public void setStudent_course_score(String student_course_score) {
		this.student_course_score = student_course_score;
	}
	public String getStudent_course_time() {
		return student_course_time;
	}
	public void setStudent_course_time(String student_course_time) {
		this.student_course_time = student_course_time;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	
}
