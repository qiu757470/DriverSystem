package org.great.bean;
/*科目项目*/
public class CourseProject {

	private String project_id;//科目项目id
	private String project_name;//科目项目名字
	private String project_time;//科目项目时间
	private String course_id;//科目id  外键
	
	//构造
	public CourseProject() {
		super();
	}
	public CourseProject(String project_id, String project_name, String project_time, String course_id) {
		super();
		this.project_id = project_id;
		this.project_name = project_name;
		this.project_time = project_time;
		this.course_id = course_id;
	}
	
	//setter getter
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_time() {
		return project_time;
	}
	public void setProject_time(String project_time) {
		this.project_time = project_time;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	
	
}
