package org.great.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.great.bean.Course;
import org.great.bean.Teacher;
import org.great.bean.TeacherEvaluation;
import org.great.mapper.CourseMapper;
import org.great.mapper.TeacherEvaluationMapper;
import org.great.mapper.TeacherMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

/** 
 * @author 作者: 王艺杰
 * @version 创建时间：2017年8月12日 下午4:25:58 
 * 评价教练action
 */
@Controller
public class TeacherEvalutionAction extends ActionSupport{

	@Resource
	TeacherMapper teacherMapper;
	@Resource
	private CourseMapper courseMapper;
	//所有教练数据
	List<Teacher> teachers;
	//学生所选教练
	private Teacher teacherFromStudentChoise;
	//学生所选教练id
	private String teacher_id;
	//评价教练数据
	private List<Course> courses;
	//	//下拉选择的课程id
	private String courseSelect;

	@Resource
	private TeacherEvaluationMapper teacherEvalutionMapper;
	        
	private TeacherEvaluation teacherEvaluation;
	//	//选择的科目状态
	private Course course;
	public String selectFromTeacher(){
		courses = (List<Course>) courseMapper.findAll();

		return SUCCESS;
	}
	//插入教练评价信息
	public String insertStudentEvaTeacher(){
		System.out.println("insertStudentEvaTeacher");
		String student_id=String.valueOf(ActionContext.getContext().getSession().get("student_id"));
		String evaTeacherId=String.valueOf(ActionContext.getContext().getSession().get("evaTeacherId"));
		teacherEvaluation.setStudent_id(student_id);
		teacherEvaluation.setTeacher_id(evaTeacherId);


		int rezult=teacherEvalutionMapper.insertStudentEvaTeacher(teacherEvaluation);



		if(rezult>0)
			return "successToSuccess";
		else
			return ERROR;
	}
	//进入教练评价界面
	public String successToevaluateTeacher(){
		System.out.println("successToevaluateTeacher");

		//获取老师姓名
		teacherFromStudentChoise=teacherMapper.selectTeacherName(teacher_id);
		ActionContext.getContext().getSession().put("evaTeacherId",teacher_id);
		System.out.println(teacherFromStudentChoise.getTeacher_name());
		return "successToevaluateTeacher";
	}
	public void selectTeacherByCourseId(){
		System.out.println("selectTeacherByCourseId");
		teachers = teacherMapper.selectFromTeacher(courseSelect);
		try {
			JSON json=JSONArray.fromObject(teachers);
			HttpServletResponse httpServletResponse=ServletActionContext.getResponse();
			httpServletResponse.setContentType("text/html;charset=utf-8");
			httpServletResponse.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		course = courseMapper.selectCourseState(courseSelect);
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public Teacher getTeacherFromStudentChoise() {
		return teacherFromStudentChoise;
	}
	public void setTeacherFromStudentChoise(Teacher teacherFromStudentChoise) {
		this.teacherFromStudentChoise = teacherFromStudentChoise;
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
	public TeacherEvaluation getTeacherEvaluation() {
		return teacherEvaluation;
	}
	public void setTeacherEvaluation(TeacherEvaluation teacherEvaluation) {
		this.teacherEvaluation = teacherEvaluation;
	}

}
