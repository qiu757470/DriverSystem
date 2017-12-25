package org.great.bean;
/*科目实体类*/
public class Course {

	private String course_id;//科目的id
	private String course_name;//科目的名字
	private String course_time;//科目的学时
	//构造
	public Course() {
		super();
	}
	public Course(String course_id, String course_name, String course_time) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_time = course_time;
	}
	//setter  getter
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_time() {
		return course_time;
	}
	public void setCourse_time(String course_time) {
		this.course_time = course_time;
	}
	
	
}
