package org.great.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.great.bean.Course;
import org.great.bean.ExamPlan;
import org.great.bean.School;
import org.great.bean.SchoolManager;
import org.great.bean.StuExamPlan;
import org.great.bean.Student;
import org.great.bean.StudentBean;
import org.great.bean.Teacher;
import org.great.mapper.SchoolManagerMapper;
import org.great.mapper.StudentMapper;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

@Controller
public class DriveSchoolUserAction1 extends ActionSupport{
	private  Student student;
	private  StudentMapper studentMapper;
	@Resource
	private SchoolManagerMapper achoolManagerMapper;
	List <StudentBean>list;
	private List<ExamPlan> eplist;
	private String epid;
	private ExamPlan examPlan;
	private ExamPlan examPlanInfo;
	private List<Teacher> teachers;
	private List<Course> courses;
	private String select1;
	private String date;
	private String time;
	private String address;
	private String select2;
	private String oldepi;
	private String examdate;
	private String examtime;
	private String examplace;
	private String exam_name;
	private String cource_id;
	private String result;
	private String coatch;
	private String manager_id;
	private String manager_name;
	private String manager_iden;
	private String manager_sex;
	private String manager_province;
	private String manager_city;
	private String manager_address;
	private String manager_phone;
	private String manager_school_id;
	private SchoolManager schoolManager;
	private List<Student> exam_student;
	private List<Student> unveifystudents;
    private List<Teacher> exam_teacher;
    private String oldpwd;
    private String newpwd;
    private String confirmpwd;
    private String pwd_id;
    private School school;
    private String cmbProvince;
    private String cmbCity;
    private String exam_pid;
    private String page;
    private String maxnum;
    private String minnum;
    private String verified_id;
    private String s_id;
    private String score;
    private String check;
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(String maxnum) {
		this.maxnum = maxnum;
	}
	public String getMinnum() {
		return minnum;
	}
	public void setMinnum(String minnum) {
		this.minnum = minnum;
	}
	public String findUser(){
		list=studentMapper.findAllUser();
		System.out.println(list.get(0));
		return SUCCESS;
	}
	//���԰���
	public String examplan(){
		page="1";
		minnum=String.valueOf((Integer.valueOf(page)-1)*10);
		maxnum=String.valueOf(Integer.valueOf(page)*10-1);
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		if(null==school_id){
			return "returnlogin";
		}
		eplist =achoolManagerMapper.findExamPlan(school_id,"δ��ʼ",minnum,maxnum);
		
		if(eplist.size()==0){
			System.out.println("�޿��˰���");
		}
		return "plan";	
	}
	 //���Խ����ҳ��ѯ
	   public String scoerlist(){
	   minnum=String.valueOf((Integer.valueOf(page)-1)*10);
	   maxnum=String.valueOf(Integer.valueOf(page)*10-Integer.valueOf(page));  
	   String school_id=(String) ActionContext.getContext().getSession().get("school_id");
	   if(null==school_id){
			return "returnlogin";
		}
	   eplist =achoolManagerMapper.findExamPlan(school_id,"δ��ʼ",minnum,maxnum);   
		return "scoerlist";
	   }
	//���԰���ɾ��
	public String examDelete(){
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		if(null==school_id){
			return "returnlogin";
		}
		achoolManagerMapper.deletestu_examplan(epid);
		achoolManagerMapper.deleteExam(school_id,epid);
		
		return "deleteplan";	
	}
	//���԰����޸Ľ�����ʾ
	public String examUpdate(){
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		if(null==school_id){
			return "returnlogin";
		}
		System.out.println(epid);
		examPlan= achoolManagerMapper.updateExam(epid);
	    String teacher=examPlan.getTeacher().getTeacher_name();
		String course=examPlan.getCourse().getCourse_name();
	    teachers=achoolManagerMapper.selectTeacher(teacher,school_id);
	    courses=achoolManagerMapper.selectCourse(course);
		return "upexam";
	}
	//���԰����޸�
	public String confirm(){
		System.out.println(select1);
		System.out.println(date);
		System.out.println(time);
		System.out.println(address);
		System.out.println(select2);
		System.out.println(oldepi);
		String couese_id=achoolManagerMapper.selectcourseIdByname(select1).getCourse_id();
		System.out.println(couese_id+"===kemu ");
		String teacher_id=achoolManagerMapper.selectteacherIdByname(select2).getTeacher_id();
		System.out.println(teacher_id+"===jiaolian ");
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		if(null==school_id){
			return "returnlogin";
		}
		ExamPlan examplan=new ExamPlan(oldepi, couese_id, teacher_id, school_id, date, time, null, null, address,"δ��ʼ","δ¼��");
		achoolManagerMapper.updateExamPlan(examplan);
		return "confirm";
	}
	//���԰�������鿴
	public String examInfo(){
	page="1";
	minnum=String.valueOf((Integer.valueOf(page)-1)*7);
    maxnum=String.valueOf(Integer.valueOf(page)*7-1);
	 examPlan= achoolManagerMapper.updateExam(epid);
	 examPlanInfo= achoolManagerMapper.studentExamInfo(epid,minnum,maxnum);
	System.out.println(examPlanInfo.getStudent().size()+"=====");
		return "info";	
	}
	//���԰�������鿴�����ҳ
	   public String examInfolist(){
		 minnum=String.valueOf((Integer.valueOf(page)-1)*7);
		 maxnum=String.valueOf(Integer.valueOf(page)*7-Integer.valueOf(page));
	     examPlan= achoolManagerMapper.updateExam(epid);
		 examPlanInfo= achoolManagerMapper.studentExamInfo(epid,minnum,maxnum);      
		 return "examInfolist";   
		   
	   }
	   
	//�½����Խ���
	public String newExam(){
		String times=achoolManagerMapper.selectcourseTimeById("1").getCourse_time();
		String name=achoolManagerMapper.selectcourseTimeById("1").getCourse_name();
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");
		if(null==school_id){
			return "returnlogin";
		}
		exam_student=achoolManagerMapper.selectExamStudent(times, name, school_id);
		
		exam_teacher=achoolManagerMapper.selectexamTeacher(school_id);
		return "newExam";
	}
	
	
	//ͨ����Ŀ��ѧ��
	public String select_exam_stu(){
	System.out.println(cource_id+"=======");
	String times=achoolManagerMapper.selectcourseTimeById(cource_id).getCourse_time();
	System.out.println(times+"====time===");
	
	String name=achoolManagerMapper.selectcourseTimeById(cource_id).getCourse_name();
	System.out.println(name+"====name===");
	
	String school_id=(String) ActionContext.getContext().getSession().get("school_id");	
	if(null==school_id){
		return "returnlogin";
	}
	
	exam_student=achoolManagerMapper.selectExamStudent(times, name, school_id);	
	System.out.println(exam_student.size()+"====size===");
	
	JSONArray json = JSONArray.fromObject(exam_student);
	System.out.println(json.toString()+"=====json=======");
	result= json.toString();
		return "selectstu";
	}
	
	
	
	//ȷ���½�����
	public String conNewExam(){
		System.out.println("ȷ���½�");
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println(request);
		String [] item=request.getParameterValues("check");
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");	
		System.out.println(school_id+"ѧУID");
		if(null==school_id){
			return "returnlogin";
		}
		System.out.println(coatch+"����");
		System.out.println(examdate+"==����");
		System.out.println(examtime+"==ʱ��");
		System.out.println(examplace+"==�ص�");
		System.out.println(exam_name+"==��Ŀ");
		String teacher_id=achoolManagerMapper.selectteacherIdByname(coatch).getTeacher_id();
		achoolManagerMapper.insertNewExamPlan(new ExamPlan(null, exam_name, teacher_id, school_id, examdate, examtime, "����ʡ", "����", examplace,"δ��ʼ","δ¼��"));
		for(int i=0;i<item.length;i++){
		String examplan_id=achoolManagerMapper.selectExam_planId();
		achoolManagerMapper.insertNewStu_exam_plan(new StuExamPlan(item[i], examplan_id));	
		}
		return "conNewExam";	
	}
	
	//��У����Ա�޸ĸ�����Ϣ
	public String SchoolManagerInfo(){
		String id= (String) ActionContext.getContext().getSession().get("schoolmanager_id");
		schoolManager=achoolManagerMapper.selectSchoolManager(id);
		return "SchoolManagerInfo";
		
	}
	//��У����Ա�޸ĸ�����Ϣ
	public String SchoolManagerInfoUpdate(){	
		System.out.println(manager_id+"====guanliayuan====");
	schoolManager=achoolManagerMapper.selectSchoolManager(manager_id);	
		return "SchoolManagerInfoupdate";
	}
	//��У����Ա�޸ĸ�����Ϣ
	public String Update(){
		achoolManagerMapper.updateSchoolManager(manager_id, manager_iden, manager_name, manager_sex, cmbProvince, cmbCity, manager_address, manager_phone);
		
		return "update";
	}
	
	//��У����Ա�޸�����
	public String updatePWD(){
		
	String id= (String) ActionContext.getContext().getSession().get("schoolmanager_id");
	schoolManager=achoolManagerMapper.selectSchoolManager(id);
		return "updatepwd";	
	}
	
   public String about(){
   String id= (String) ActionContext.getContext().getSession().get("schoolmanager_id");	
   school=achoolManagerMapper.findSchoolInfoByAccount(id);
		return "about";
	}
	
	public String exit(){
		return "exit";
	}
	//�����޸��ύ
	public void pwdcoinfim(){
	System.out.println(pwd_id);
	SchoolManager schoolmanager= achoolManagerMapper.selectSchoolManager(pwd_id);
	String pwd=schoolmanager.getSchool_manager_password();
	System.out.println("����Ա������"+schoolmanager.getSchool_manager_name());
	System.out.println("����Ա������"+pwd);
	System.out.println(oldpwd);
	System.out.println(newpwd);
	System.out.println(confirmpwd);
		try {
			if(schoolmanager!=null){
				if(oldpwd.equals(pwd)){
					if(newpwd.equals(confirmpwd)){
						ServletActionContext.getResponse().getWriter().print("correct");
						ServletActionContext.getResponse().getWriter().flush();
						ServletActionContext.getResponse().getWriter().close();
						System.out.println("��ȷ���޸�����");
						achoolManagerMapper.updatePwd(pwd_id, newpwd);	
					}else{
						System.out.println("�������벻��ȷ");
						ServletActionContext.getResponse().getWriter().print("notas");
						ServletActionContext.getResponse().getWriter().flush();
						ServletActionContext.getResponse().getWriter().close();	
					}
					
				}else{
					System.out.println("ԭʼ�����������");
					ServletActionContext.getResponse().getWriter().print("notcorr");
					ServletActionContext.getResponse().getWriter().flush();
					ServletActionContext.getResponse().getWriter().close();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//����ɼ��������
   public String scoer(){
   page="1";
   minnum=String.valueOf((Integer.valueOf(page)-1)*10);
   maxnum=String.valueOf(Integer.valueOf(page)*10-1);
   String school_id=(String) ActionContext.getContext().getSession().get("school_id");
   if(null==school_id){
		return "returnlogin";
	}
   eplist =achoolManagerMapper.findExamPlan(school_id,"�ѽ���",minnum,maxnum);
   System.out.println(eplist.size()+"���ų���");
   System.out.println("�ɼ��������");
   JSONArray json=JSONArray.fromObject(eplist);
   ServletActionContext.getRequest().getSession().setAttribute("json",json.toString());
		return "scoerManager";
	}
   //���Խ����ҳ��ѯ
   public String scoermanage(){
   minnum=String.valueOf((Integer.valueOf(page)-1)*10);
   maxnum=String.valueOf(Integer.valueOf(page)*10-Integer.valueOf(page));  
   String school_id=(String) ActionContext.getContext().getSession().get("school_id");
   if(null==school_id){
		return "returnlogin";
	}
   eplist =achoolManagerMapper.findExamPlan(school_id,"�ѽ���",minnum,maxnum);   
	return "scoermanage";
   }
   
	//�ɼ�¼�����
   public String scoreinput(){
	   page="1";
		minnum=String.valueOf((Integer.valueOf(page)-1)*9);
	    maxnum=String.valueOf(Integer.valueOf(page)*9-1);
   System.out.println("�ɼ�¼�����");
   examPlan= achoolManagerMapper.updateExam(epid);
   examPlanInfo= achoolManagerMapper.studentExamInfo(epid,minnum,maxnum);   
	return "scoreinput";
   }
   //�ɼ�¼������ҳ
   public String scoreinputlist(){
	minnum=String.valueOf((Integer.valueOf(page)-1)*9);
	maxnum=String.valueOf(Integer.valueOf(page)*9-Integer.valueOf(page));   
	examPlan= achoolManagerMapper.updateExam(epid);
	examPlanInfo= achoolManagerMapper.studentExamInfo(epid,minnum,maxnum);   
	return "scoreinputlist";  
   }
   
   
   
   //�ɼ�����鿴
   public String scorecheck(){
	    page="1";
		minnum=String.valueOf((Integer.valueOf(page)-1)*7);
	    maxnum=String.valueOf(Integer.valueOf(page)*7-1);
       examPlan= achoolManagerMapper.updateExam(epid);
	   examPlanInfo= achoolManagerMapper.studentExamInfo(epid,minnum,maxnum);  
	   return "scorecheck"; 
   }
   //�ɼ���������ҳ����
   public String scorechecklist(){
		minnum=String.valueOf((Integer.valueOf(page)-1)*7);
	    maxnum=String.valueOf(Integer.valueOf(page)*7-Integer.valueOf(page));
        examPlan= achoolManagerMapper.updateExam(epid);
	    examPlanInfo= achoolManagerMapper.studentExamInfo(epid,minnum,maxnum);  
	   return "scorechecklist"; 
  }
  //ѧ���ɼ�ʵʱ¼��
   public void stuscoreinput(){
	   System.out.println("ʵʱ¼�����");
	   System.out.println(s_id);
	   System.out.println(score);
	   achoolManagerMapper.updateStuscore(s_id, score);
   }
   
   
   
   
   
	//�ɼ��������ݿ�
	public String sinput(){
	System.out.println("���԰��ŵ�id"+exam_pid);
//	HttpServletRequest requetst=ServletActionContext.getRequest();	
//	String [] s_id=requetst.getParameterValues("stu_id");
//	String [] s_score=requetst.getParameterValues("score");	
//	for(int i=0;i<s_id.length;i++){
//		System.out.println(s_id[i]);
//		System.out.println(s_score[i]);
//		achoolManagerMapper.updateStuscore(s_id[i], s_score[i]);
//	}	
	//¼��״̬�ı�	
	achoolManagerMapper.updateExamPlanInput(exam_pid, "��¼��");
		return "confirmscore";
	}
   //ѧԱ��˽���
	public String studentVerify(){	
	page="1";
	minnum=String.valueOf((Integer.valueOf(page)-1)*10);
	maxnum=String.valueOf(Integer.valueOf(page)*10-1);
	String school_id=(String) ActionContext.getContext().getSession().get("school_id");	
	if(null==school_id){
		return "returnlogin";
	}
	unveifystudents=achoolManagerMapper.selectStudents(school_id, "δ���",minnum,maxnum);
		return "studentVerify";
	}
	//ѧԱ��˽����ҳ
	public String studentVerifylist(){
		
		minnum=String.valueOf((Integer.valueOf(page)-1)*10);
		maxnum=String.valueOf(Integer.valueOf(page)*10-Integer.valueOf(page));
		String school_id=(String) ActionContext.getContext().getSession().get("school_id");	
		if(null==school_id){
			return "returnlogin";
		}
		unveifystudents=achoolManagerMapper.selectStudents(school_id, "δ���",minnum,maxnum);
		return "studentVerifylist";	
	}
	
	
   //��ѡͨ�����
	public String verified(){
	System.out.println(verified_id+"��˽���");
	achoolManagerMapper.updateStudentState(verified_id, "���ͨ��");	

		return "verified";
	}
	//ȫѡ���ͨ��
	public String verifiedall(){
	HttpServletRequest requetst=ServletActionContext.getRequest();	
	String [] s_id=requetst.getParameterValues("check");
	if(s_id.length>0){
		for(int i=0;i<s_id.length;i++){
			System.out.println(s_id[i]);
			achoolManagerMapper.updateStudentState(s_id[i], "���ͨ��");	
		}
	}else{
		System.out.println("δѡ��");
	}
		return "verifiedall";	
	}
	
	
	
	
	
   
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<ExamPlan> getEplist() {
		return eplist;
	}
	public void setEplist(List<ExamPlan> eplist) {
		this.eplist = eplist;
	}
	public String getEpid() {
		return epid;
	}
	public void setEpid(String epid) {
		this.epid = epid;
	}
	public ExamPlan getExamPlan() {
		return examPlan;
	}
	public void setExamPlan(ExamPlan examPlan) {
		this.examPlan = examPlan;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public String getSelect1() {
		return select1;
	}
	public void setSelect1(String select1) {
		this.select1 = select1;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSelect2() {
		return select2;
	}
	public void setSelect2(String select2) {
		this.select2 = select2;
	}
	public String getOldepi() {
		return oldepi;
	}
	public void setOldepi(String oldepi) {
		this.oldepi = oldepi;
	}
	public ExamPlan getExamPlanInfo() {
		return examPlanInfo;
	}
	public void setExamPlanInfo(ExamPlan examPlanInfo) {
		this.examPlanInfo = examPlanInfo;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	public String getExamtime() {
		return examtime;
	}
	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}
	public String getExamplace() {
		return examplace;
	}
	public void setExamplace(String examplace) {
		this.examplace = examplace;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	public List<Student> getExam_student() {
		return exam_student;
	}
	public void setExam_student(List<Student> exam_student) {
		this.exam_student = exam_student;
	}
	public String getCource_id() {
		return cource_id;
	}
	public void setCource_id(String cource_id) {
		this.cource_id = cource_id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<Teacher> getExam_teacher() {
		return exam_teacher;
	}
	public void setExam_teacher(List<Teacher> exam_teacher) {
		this.exam_teacher = exam_teacher;
	}
	public String getCoatch() {
		return coatch;
	}
	public void setCoatch(String coatch) {
		this.coatch = coatch;
	}
	public SchoolManager getSchoolManager() {
		return schoolManager;
	}
	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_iden() {
		return manager_iden;
	}
	public void setManager_iden(String manager_iden) {
		this.manager_iden = manager_iden;
	}
	public String getManager_sex() {
		return manager_sex;
	}
	public void setManager_sex(String manager_sex) {
		this.manager_sex = manager_sex;
	}
	public String getManager_province() {
		return manager_province;
	}
	public void setManager_province(String manager_province) {
		this.manager_province = manager_province;
	}
	public String getManager_city() {
		return manager_city;
	}
	public void setManager_city(String manager_city) {
		this.manager_city = manager_city;
	}
	public String getManager_address() {
		return manager_address;
	}
	public void setManager_address(String manager_address) {
		this.manager_address = manager_address;
	}
	public String getManager_phone() {
		return manager_phone;
	}
	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}
	public String getManager_school_id() {
		return manager_school_id;
	}
	public void setManager_school_id(String manager_school_id) {
		this.manager_school_id = manager_school_id;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getPwd_id() {
		return pwd_id;
	}
	public void setPwd_id(String pwd_id) {
		this.pwd_id = pwd_id;
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
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getConfirmpwd() {
		return confirmpwd;
	}
	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	public String getExam_pid() {
		return exam_pid;
	}
	public void setExam_pid(String exam_pid) {
		this.exam_pid = exam_pid;
	}
	public List<Student> getUnveifystudents() {
		return unveifystudents;
	}
	public void setUnveifystudents(List<Student> unveifystudents) {
		this.unveifystudents = unveifystudents;
	}
	public String getVerified_id() {
		return verified_id;
	}
	public void setVerified_id(String verified_id) {
		this.verified_id = verified_id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
   
	
	
}
