package org.great.web;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.Course;
import org.great.bean.StuCourseProject;
import org.great.bean.StudentCourse;
import org.great.mapper.CourseMapper;
import org.great.mapper.StuCourseProjectMapper;
import org.great.mapper.StudentCourseMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
@author  ������
@date ����ʱ�䣺2017��8��12�� ����3:30:55 
@version 1.0 
@parameter  
@since  
@return  
����ҵ��
 */
@Controller
public class FindStudentTimeAction extends ActionSupport{
	@Resource
	private StuCourseProjectMapper stuCourseProjectMapper;
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private StudentCourseMapper studentCourseMapper;
	private List<StuCourseProject> stuCourseProjects;
	private List<Course> courses;
	private String currentPage="1";
	//����ѡ��Ŀγ�id
	private String courseSelect="1";
	//��ѧʱ

	private Course course;
	//ѡ��Ŀ�Ŀ״̬
	private String courseState;
	public String findAllByCourseProject(){
		try {
			
		
		String student_id=String.valueOf(ActionContext.getContext().getSession().get("student_id"));
		System.out.println("findAllByCourseProject");
		courses = (List<Course>) courseMapper.findAll();
		
		System.out.println(courses);
		//��ҳ����
		//�ּ�ҳ
		int pageSize=10;
		System.out.println(currentPage);
		//��С
		int min=(Integer.parseInt(currentPage)-1)*pageSize;
		//���ҳ
		int max=(Integer.parseInt(currentPage)-1)*pageSize+pageSize;
		System.out.println(currentPage+min+max);
		stuCourseProjects = stuCourseProjectMapper.findAllByCourseProject(courseSelect,min,max);
		//����ѡ��Ŀ�Ŀ��Ϣ
		course = courseMapper.selectCourseState(courseSelect);
		StudentCourse studentCourse=studentCourseMapper.findAllById(course.getCourse_id(),student_id);
		//��Ŀ����״̬
		try {
			courseState=Integer.parseInt(course.getCourse_time())>Integer.parseInt(studentCourse.getStudent_course_time())?"������":"����";
		} catch (NullPointerException e) {
			courseState="δ��ʼ";
		}
		} catch (NullPointerException e) {
			return SUCCESS;
		}
		return SUCCESS;
	}
	public List<StuCourseProject> getStuCourseProjects() {
		return stuCourseProjects;
	}
	public void setStuCourseProjects(List<StuCourseProject> stuCourseProjects) {
		this.stuCourseProjects = stuCourseProjects;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public String getCourseSelect() {
		return courseSelect;
	}
	public void setCourseSelect(String courseSelect) {
		this.courseSelect = courseSelect;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}



}
