package org.great.bean;

import java.util.List;

/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ��ѧԱ��Ŀ��ʵ��bean
*/
public class StuCourseProject {

	private String stu_course_project_id;//ѧ����Ŀ��Ŀid
	private String student_course_id;//ѧ����Ŀid
	private String project_id;//��Ŀid
	private String stu_course_project_score;//ѧ����Ŀ��Ŀ�ɼ�
	private String stu_course_project_time;//ѧ����Ŀ��Ŀʱ��
	private String stu_course_project_state;//ѧ����Ŀ��Ŀ״̬
	private List<CourseProject> courseProjects;
	private CourseProject courseProject;
	private String stu_course_project_todo;
	private String stu_course_project_score_result;
	public List<CourseProject> getCourseProjects() {
		return courseProjects;
	}
	public void setCourseProjects(List<CourseProject> courseProjects) {
		this.courseProjects = courseProjects;
	}
	public CourseProject getCourseProject() {
		return courseProject;
	}
	public void setCourseProject(CourseProject courseProject) {
		this.courseProject = courseProject;
	}
	public String getStu_course_project_todo() {
		return stu_course_project_todo;
	}
	public void setStu_course_project_todo(String stu_course_project_todo) {
		this.stu_course_project_todo = stu_course_project_todo;
	}
	public String getStu_course_project_score_result() {
		return stu_course_project_score_result;
	}
	public void setStu_course_project_score_result(String stu_course_project_score_result) {
		this.stu_course_project_score_result = stu_course_project_score_result;
	}
	//����
	public StuCourseProject() {
		super();
	}
	public StuCourseProject(String stu_course_project_id, String student_course_id, String project_id,
			String stu_course_project_score, String stu_course_project_time, String stu_course_project_state) {
		super();
		this.stu_course_project_id = stu_course_project_id;
		this.student_course_id = student_course_id;
		this.project_id = project_id;
		this.stu_course_project_score = stu_course_project_score;
		this.stu_course_project_time = stu_course_project_time;
		this.stu_course_project_state = stu_course_project_state;
	}
	//setter  getter
	public String getStu_course_project_id() {
		return stu_course_project_id;
	}
	public void setStu_course_project_id(String stu_course_project_id) {
		this.stu_course_project_id = stu_course_project_id;
	}
	public String getStudent_course_id() {
		return student_course_id;
	}
	public void setStudent_course_id(String student_course_id) {
		this.student_course_id = student_course_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getStu_course_project_score() {
		return stu_course_project_score;
	}
	public void setStu_course_project_score(String stu_course_project_score) {
		this.stu_course_project_score = stu_course_project_score;
	}
	public String getStu_course_project_time() {
		return stu_course_project_time;
	}
	public void setStu_course_project_time(String stu_course_project_time) {
		this.stu_course_project_time = stu_course_project_time;
	}
	public String getStu_course_project_state() {
		return stu_course_project_state;
	}
	public void setStu_course_project_state(String stu_course_project_state) {
		this.stu_course_project_state = stu_course_project_state;
	}
	
	
}
