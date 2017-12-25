package org.great.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.great.bean.StudentBean;
import org.great.bean.StudentCourse;
import org.great.bean.TeacherBean;
import org.great.mapper.StudentMapper;
import org.great.tools.Upload;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class DriveSchoolUserAction extends ActionSupport {

	@Resource
	private  StudentMapper studentMapper;
	private List <StudentBean> userlist;
	private List <TeacherBean> teacherlist;
	private TeacherBean teacher;
	private StudentBean student;
	private String student_id="";
	private String student_name="";
	private String student_identification="";
	private String student_sex="全部";
	private String student_province="";//学员省份
	private String student_city="";//学员城市
	private String student_phone="";
	private String student_password="";
	private String student_address="";
	private String course_id="全部";
	private String teacher_name;
	private String teacher_id;
	private String school;
	private int	 page=1;//传入页码
	private int	 pagemax;//页码最大值
	private int	 pagemin;//页码最小值
	private int	 snum;//当前学生数
	
	
	
	
	
	//查找所有
	public String findAllUser(){
		page=1;
		getnull();
		return searchUser();
		 
	}
	
	//按页查找
	public String findByPage(){
		 pagemax=page*10;
		 pagemin=(page-1)*10+1;
		 System.out.println(page+"  pagefind11111111111111111");
		userlist=(List<StudentBean>) studentMapper.findByPage(pagemax, pagemin);
		return SUCCESS;
	}
	
	//进入修改跳转
	public String toUpdatePage(){
		teacher=studentMapper.findTeacherBySid(student_id);
		student=studentMapper.findStudentBySid(student_id);
		teacherlist=studentMapper.findAllTeacher(teacher.getTeacher_name());
		return SUCCESS;
	}
	public String toCreateUser(){
		System.out.println();
		teacherlist=studentMapper.findAllTeacher("1234444444");
		return SUCCESS;
	}
	
	//修改
	public String updateUser(){
		
		//teacher=studentMapper.findTeacherBySid(student_id);
		teacher=studentMapper.findTeacherByTname(teacher_name);
		teacher_id=teacher.getTeacher_id();
		System.out.println(student_identification+student_name+ 
				student_sex+student_province+student_city+ student_phone+ student_address+student_id+teacher_id);
		 studentMapper.updateUser(student_identification, student_name,  student_sex,student_province,student_city, student_phone, student_address,student_id,teacher_id);
		
		 return findAllUser();
	}
	
	//用户详细信息
	public String allUserInf(){
		
		//查找学生id的studentbean
		System.out.println(student_id);
		student=studentMapper.findStudentBySid(student_id);
		school=studentMapper.searchSchool(student.getSchool_id());
		//查找学生的教练id
		teacher=studentMapper.findTeacherBySid(student_id);
		return SUCCESS;
	}
	//
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getPagemax() {
		return pagemax;
	}

	public void setPagemax(int pagemax) {
		this.pagemax = pagemax;
	}

	public int getPagemin() {
		return pagemin;
	}

	public void setPagemin(int pagemin) {
		this.pagemin = pagemin;
	}

	public String createAllUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		Upload u=new Upload();
		
		try {
			u.doPost(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	//创建用户
	public String createUser(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	String a=df.format(new Date());
	a=a.substring(0,10);
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
	student=studentMapper.findStudentBySidentifiction(student_identification);
	System.out.println(student_identification);
	System.out.println(student);
	if(null==student){
		teacher =studentMapper.findTeacherByTname(teacher_name);
		StudentBean student=new StudentBean(null, student_identification, student_password, student_name, student_sex,student_province,student_city, "1", student_phone, student_address, "审核通过", school_id, teacher.getTeacher_id(),a);
		studentMapper.createUser(student);
		student_id=studentMapper.selectmax();
		StudentCourse s=new StudentCourse(null, student_id, "1", teacher.getTeacher_id(), "", "","未考试");
		studentMapper.insertStuCourse(s);
		
		
		getnull();
		return SUCCESS;
	}else {
		teacher=studentMapper.findTeacherBySid(student.getStudent_id());
		System.out.println("该用户已存在");
		getnull();
		return INPUT;
	}
	
		
		
	}
	
	public String delectUser(){
		System.out.println(student_id+"  z1111111de");
		studentMapper.delectUser(student_id);
		
		return SUCCESS;
	}
	public String searchUser(){
		pagemax=page*10+1;
		 pagemin=(page-1)*10+1;
		 System.out.println(pagemax+" "+pagemin);
		 System.out.println(student_identification+" a");
	 student=new StudentBean(student_id, student_identification, student_password, student_name, student_sex, course_id, student_phone, student_address, null, null, null,pagemax,pagemin);
		
	userlist=	studentMapper.searchUser(student);
	
	
	
		snum=studentMapper.studentNum(student);
		return SUCCESS;
	}
	
	public String lastPage(){
		System.out.println(student_identification);
		
		page=(snum/10)+1;
		return searchUser();
		
	}
	public String afterPage(){
		page++;
		if(page==((snum/10)+2)){
			page=1;
		}
		return SUCCESS;
		}
	public String beforePage(){
		
		page--;
		if(page==0){
			page=((snum/10)+1);
		}
		return SUCCESS;
	}
	

	public void getnull() {
		
		 teacher_name=null;
		 student_id=null;
		 student_name=null;
		 student_identification=null;
		 student_sex="全部";
		 course_id="全部";
	}
	public StudentMapper getStudentMapper() {
		return studentMapper;
	}

	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	public List<StudentBean> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<StudentBean> userlist) {
		this.userlist = userlist;
	}

	public String getStudent_id() {
		return student_id;
	}

	

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	
	public List<TeacherBean> getTeacherlist() {
		return teacherlist;
	}
	public void setTeacherlist(List<TeacherBean> teacherlist) {
		this.teacherlist = teacherlist;
	}
	public String getStudent_password() {
		return student_password;
	}
	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	public String getStudent_identification() {
		return student_identification;
	}
	public void setStudent_identification(String student_identification) {
		this.student_identification = student_identification;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public int getPage() {
		return page;
	}

	public String getStudent_province() {
		return student_province;
	}

	public void setStudent_province(String student_province) {
		this.student_province = student_province;
	}

	public String getStudent_city() {
		return student_city;
	}

	public void setStudent_city(String student_city) {
		this.student_city = student_city;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public StudentBean getStudent() {
		return student;
	}

	public void setStudent(StudentBean student) {
		this.student = student;
	}

	public String getStudent_address() {
		return student_address;
	}
	public void setStudent_address(String student_address) {
		this.student_address = student_address;
	}

	public TeacherBean getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}


	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public int getSnum() {
		return snum;
	}

	public void setSnum(int snum) {
		this.snum = snum;
	}


	
	
	

}
