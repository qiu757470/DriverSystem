package org.great.bean;
/*��Ŀʵ����*/
public class Course {

	private String course_id;//��Ŀ��id
	private String course_name;//��Ŀ������
	private String course_time;//��Ŀ��ѧʱ
	//����
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
