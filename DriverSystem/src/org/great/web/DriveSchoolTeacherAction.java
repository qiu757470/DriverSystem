package org.great.web;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.Course;
import org.great.bean.StudentBean;
import org.great.bean.TeacherBean;
import org.great.mapper.TeacherMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class DriveSchoolTeacherAction extends ActionSupport{

	@Resource
	private  TeacherMapper teacherMapper;

	private String teacher_id;//教练id
	private String teacher_account;//教练账号
	private String teacher_password;//教练密码
	private String teacher_name="";//教练名字
	private String teacher_sex="全部";//教练性别
	private String course_id="全部";//科目id  作为外键
	private String school_id;//教练对应的驾校  作为外键
	private String teacher_eval_score;//教练分数
	private String teacher_identification;//教练身份证
	
	private String teacher_province;//教练省份
	private String teacher_city;//教练城市
	private String teacher_address;//教练地址
	private String teacher_phone;//教练电话
	private String teacher_state;//教练状态
	private String course_name;//科目名
	
	private List <TeacherBean> teacherlist;
	private TeacherBean teacher;
	private Course course;
	private List<TeacherBean> teacherBeans;
	private List<StudentBean> studentlist;

	private int	 page=1;//传入页码
	private int	 pagemax;//页码最大值
	private int	 pagemin;//页码最小值
	private int	 tnum;//当前学生数
	private int	 maxgoal;//最大分
	private int	 mingoal;//最小分
	
	

	public String findTeacher(){
		page=1;
		teacher_name="";
		teacher_sex="全部";
		course_id="全部";
			 maxgoal=5;//最大分
			 mingoal=0;//最小分
		return seachTeacher();
	}

	public String firstPage(){

		page=1;
		return seachTeacher();

	}
	public String lastPage(){

		page=(tnum/10)+1;
		return seachTeacher();

	}
	public String afterPage(){
		page++;
		System.out.println(page+"  "+tnum);
		if(page==((tnum/10)+2)){
			page=1;
		}
		return seachTeacher();
	}
	public String beforePage(){

		page--;
		if(page==0){
			page=((tnum/10)+1);
		}
		return seachTeacher();
	}

	public String seachTeacher(){
		pagemax=page*10+1;
		pagemin=(page-1)*10+1;
		//course_id="1";
		System.out.println(course_id+"  course_id");
		 teacher=new TeacherBean(teacher_id, teacher_account, teacher_password, teacher_name, teacher_sex, course_id, school_id, teacher_identification,
				teacher_eval_score, pagemax, pagemin, maxgoal, mingoal);
		teacherlist=teacherMapper.searchTeacher(teacher);
		
		tnum=teacherMapper.teacherNum(teacher);
		return "success";
	}


	public String teacherInfo(){

			System.out.println(teacher_id+" teacherid");
			teacher=(TeacherBean) teacherMapper.selectByTeacherId(teacher_id);
			studentlist=teacherMapper.selectStudent(teacher_id);
			System.out.println(studentlist.size()+"       size");
			return "teacherInfo";

	}

	public String teacherDelete(){
		
		teacherMapper.teacherDelete(teacher_id);
		
		return seachTeacher();
				}
	public String toCreateUser(){
		
		return "toCreatepage";
	}
	public String createTeacher(){
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		System.out.println("teacher_identification "+teacher_identification);
		System.out.println("teacher_identification "+teacher_identification);
		System.out.println("teacher_password "+teacher_password);
		System.out.println("teacher_name "+teacher_name);
		System.out.println("teacher_sex "+teacher_sex);
		System.out.println("teacher_province "+teacher_province);
		System.out.println("teacher_city "+teacher_city);
		System.out.println("teacher_identification "+teacher_identification);
		teacher=new TeacherBean(null, teacher_identification, teacher_identification, teacher_password, teacher_name, teacher_sex, teacher_province, teacher_city, teacher_address, "123", "正常", "5", "1", school_id);
		teacherMapper.createTeacher(teacher);
		
		return findTeacher();
	}

	
	public String toUpdateTeacher(){
		teacher=(TeacherBean) teacherMapper.selectByTeacherId(teacher_id);
		
		return "teacherUpdate";
		}
	
	public String updateTeacher(){
		System.out.println(teacher_phone+" p+...");
		System.out.println(teacher.getTeacher_name()+" "+teacher_name);
		teacher.setTeacher_name(teacher_name);
		teacher.setTeacher_identification(teacher_identification);
		teacher.setTeacher_phone(teacher_phone);
		teacher.setTeacher_address(teacher_address);
		teacher.setTeacher_city(teacher_city);
		teacher.setTeacher_province(teacher_province);
		teacher.setTeacher_sex(teacher_sex);
		//teacher.setCourse_id(course_id);
		System.out.println(course_name);
		course=teacherMapper.searchCourseId(course_name);
		teacher.setCourse_id(course.getCourse_id());
		teacherMapper.upadateTeacher(teacher);
		
		return findTeacher();
		}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_account() {
		return teacher_account;
	}
	public void setTeacher_account(String teacher_account) {
		this.teacher_account = teacher_account;
	}

	public String getTeacher_identification() {
		return teacher_identification;
	}

	public void setTeacher_identification(String teacher_identification) {
		this.teacher_identification = teacher_identification;
	}

	public String getTeacher_password() {
		return teacher_password;
	}


	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getTeacher_eval_score() {
		return teacher_eval_score;
	}

	public void setTeacher_eval_score(String teacher_eval_score) {
		this.teacher_eval_score = teacher_eval_score;
	}

	public void setTeacher_password(String teacher_password) {
		this.teacher_password = teacher_password;
	}
	public List<StudentBean> getStudentlist() {
		return studentlist;
	}

	public void setStudentlist(List<StudentBean> studentlist) {
		this.studentlist = studentlist;
	}

	public String getTeacher_name() {
		return teacher_name;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public int getPagemax() {
		return pagemax;
	}

	public int getMaxgoal() {
		return maxgoal;
	}

	public void setMaxgoal(int maxgoal) {
		this.maxgoal = maxgoal;
	}

	public int getMingoal() {
		return mingoal;
	}

	public void setMingoal(int mingoal) {
		this.mingoal = mingoal;
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


	public int getTnum() {
		return tnum;
	}



	public void setTnum(int tnum) {
		this.tnum = tnum;
	}


	public List<TeacherBean> getTeacherBeans() {
		return teacherBeans;
	}

	public void setTeacherBeans(List<TeacherBean> teacherBeans) {
		this.teacherBeans = teacherBeans;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_sex() {
		return teacher_sex;
	}
	public void setTeacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}


	public String getTeacher_province() {
		return teacher_province;
	}

	public void setTeacher_province(String teacher_province) {
		this.teacher_province = teacher_province;
	}

	public String getTeacher_city() {
		return teacher_city;
	}

	public void setTeacher_city(String teacher_city) {
		this.teacher_city = teacher_city;
	}

	public String getTeacher_phone() {
		return teacher_phone;
	}

	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}

	public String getTeacher_state() {
		return teacher_state;
	}

	public void setTeacher_state(String teacher_state) {
		this.teacher_state = teacher_state;
	}

	public TeacherMapper getTeacherMapper() {
		return teacherMapper;
	}


	public void setTeacherMapper(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}

	public List<TeacherBean> getTeacherlist() {
		return teacherlist;
	}





	public TeacherBean getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}


	public String getTeacher_address() {
		return teacher_address;
	}

	public void setTeacher_address(String teacher_address) {
		this.teacher_address = teacher_address;
	}

	public void setTeacherlist(List<TeacherBean> teacherlist) {
		this.teacherlist = teacherlist;
	}
}
