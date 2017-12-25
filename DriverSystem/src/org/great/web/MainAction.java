package org.great.web;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.great.bean.School;
import org.great.bean.Student;
import org.great.bean.StudentCourse;
import org.great.mapper.SchoolMapper;
import org.great.mapper.StudentCourseMapper;
import org.great.mapper.StudentMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author ����: ���ս�
 * @version ����ʱ�䣺2017��8��12�� ����2:57:43 
 * ��˵�� 
 */
@Controller
public class MainAction extends ActionSupport{
	@Resource
	StudentMapper studentMapper;
	@Resource
	SchoolMapper schoolMapper;
	@Resource
	StudentCourseMapper studentCourseMapper;
	static int c=0;
	//��½��ѧ����Ϣ
	private Student student;
	//��½��ѧ����У��Ϣ
	private School school;




	//ѧ��ƽ̨���
	public String mainInit (){
		System.out.println("mainInit");
		Map<String, Object> map=ActionContext.getContext().getSession();
		student = studentMapper.selectStudentNameByStudentId(String.valueOf(map.get("student_id")));
		school = schoolMapper.selectStudentAndSchoolByStudentId(String.valueOf(map.get("student_id")));
		System.out.println(map.get("student_id")+"========");
		return SUCCESS;
	}
	//��ڽ�����Ϣ
	public String welcomeToStudent(){
		System.out.println("welcomeToStudent");
		Map<String, Object> map=ActionContext.getContext().getSession();
		student = studentMapper.selectStudentNameByStudentId(String.valueOf(map.get("student_id")));
		school = schoolMapper.selectStudentAndSchoolByStudentId(String.valueOf(map.get("student_id")));
		map.put("loginedStudent",student);
		return "welcomeToStudent";
	}
	//ѧ��ǩ������
	public void studentSignIn(){
		try {


			System.out.println("studentSignIn");
			Map<String, Object> map=ActionContext.getContext().getSession();
			student = studentMapper.selectStudentNameByStudentId(String.valueOf(map.get("student_id")));
			Student student=(Student)map.get("loginedStudent");

			StudentCourse studentCourse=studentCourseMapper.selectAStudentCourseAndStudent(student.getCourse_id(),student.getStudent_id());
			System.out.println(studentCourse+"?????");
			studentCourse.setStudent_course_time(String.valueOf(Integer.parseInt(studentCourse.getStudent_course_time())+1));

			int isSucceed=studentCourseMapper.updateStudentCourse(studentCourse);
			System.out.println(isSucceed);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			
			c++;
			System.out.println(c+"XN");
			if(c<3){
				System.out.println("why");
				ServletActionContext.getResponse().getWriter().print("ǩ���ɹ�");
			}
			else{
				
				ServletActionContext.getResponse().getWriter().print("����ǩ������");
			}

		} catch (Exception e) {
			try {
				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
				ServletActionContext.getResponse().getWriter().print("ǩ��ʧ��");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}



	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public StudentMapper getStudentMapper() {
		return studentMapper;
	}
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}


}
