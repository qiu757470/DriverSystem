package org.great.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.great.bean.School;
import org.great.bean.Student;
import org.great.bean.Teacher;
import org.great.mapper.SchoolMapper;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonArray;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
@Controller
public class StudentRegisterAction extends ActionSupport{

	private Student student;
	private String identi;
	private String name;
	private String sex;
	private String phone;
	private String cmbProvince;
	private String cmbCity;
	private String school_name;
	private String register_time;
	private String teacher_name;
	@Resource
	private SchoolMapper schoolMapper;
	
	
	
	public void schoolselect(){
		System.out.println("学生注册");
		System.out.println(cmbProvince);
		System.out.println(cmbCity);
		try {
			List<School>list= schoolMapper.selectSchoolByProcity(cmbProvince, cmbCity);	
			JSONArray json=JSONArray.fromObject(list);
			HttpServletResponse resopnse=ServletActionContext.getResponse();
			resopnse.setContentType("text/html;charset=utf-8");
			resopnse.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectteacher(){
		System.out.println("教师选择页面");
		System.out.println(school_name);
	try {
		List<Teacher> list=	schoolMapper.selectTeacherBySchool(school_name);
		JSONArray json=JSONArray.fromObject(list);
		HttpServletResponse resopnse=ServletActionContext.getResponse();
		resopnse.setContentType("text/html;charset=utf-8");
		resopnse.getWriter().print(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	
	public String register(){
		System.out.println("确认注册");
		System.out.println(identi);
		System.out.println(name);
		System.out.println(sex);
		System.out.println(phone);
		System.out.println(cmbProvince);
		System.out.println(cmbCity);
		System.out.println(school_name);
		System.out.println(register_time);
		System.out.println(teacher_name);
		String pwd=identi.substring(12, 18);
		System.out.println(pwd);

		Student student=new Student();
		student.setStudent_id(null);
		student.setStudent_identification(identi);
		student.setStudent_password(pwd);
		student.setStudent_name(name);
		student.setStudent_sex(sex);
		student.setStudent_province(cmbProvince);
		student.setStudent_city(cmbCity);
		student.setStudent_phone(phone);
		student.setStudent_state("未审核");
		student.setSchool_id(school_name);
		student.setTeacher_id(teacher_name);
		student.setStudent_register_time(register_time);
		student.setCourse_id("1");
		int a=schoolMapper.insertStudent(student);
		if(a>0){
			System.out.println("插入成功");
		}else{
			System.out.println("插入失败");
		}
		return SUCCESS;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public String getCmbProvince() {
		return cmbProvince;
	}



	public void setCmbProvince(String cmbProvince) {
		this.cmbProvince = cmbProvince;
	}



	public String getCmbCity() {
		return cmbCity;
	}



	public void setCmbCity(String cmbCity) {
		this.cmbCity = cmbCity;
	}


	public String getSchool_name() {
		return school_name;
	}


	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}


	public String getIdenti() {
		return identi;
	}


	public void setIdenti(String identi) {
		this.identi = identi;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getRegister_time() {
		return register_time;
	}


	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	
	
	
	
	
}
