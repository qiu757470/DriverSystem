package org.great.web;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.ManagerJurOne;
import org.great.bean.School;
import org.great.bean.SchoolManager;
import org.great.bean.Student;
import org.great.bean.TransUser;
import org.great.mapper.SchoolManagerMapper;
import org.great.mapper.StudentMapper;
import org.great.mapper.TransUserMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/*运管平台的Action操作类*/
@Controller
public class LoginAction extends BaseAction{
	private String user_account;
	private String user_password;
	private String msg="";
	private String loginType;
	private List<ManagerJurOne> managerJurOne;
	private School school;
	//mapper接口
	@Resource
	private TransUserMapper transUserMapper;
	@Resource
    private SchoolManagerMapper schoolManagerMapper;
	@Resource
	private StudentMapper studentMapper;
	//做运管登入
	@SuppressWarnings("unused")
	public String login(){
		
//		//已经登录过运管平台，直接到运管平台主页
//				if(session.get("isLogin") != null && session.get("loginTransUser") != null){
//					return "transSuccess";
//				}
//				if(user_account == null || user_password == null || logintype == null){
//					System.out.println("错误的访问地址，请先登录！");
//					msg="请先登录！";
//					request.put("errorSource", "true");
//					return INPUT;
//				}
			System.out.println("loginType:" + loginType);
		if(loginType == null){
			msg="请先登录！";
			request.put("msg", msg);  
			return INPUT;
		}		
		//运管登录
		if(loginType.equals("1")){
			
			/*System.out.println(user_account);
			System.out.println(user_password);*/
			//通过账号和密码找到运营用户
			TransUser loginTransUser = transUserMapper.findTransUser(user_account,user_password);
			if(null != loginTransUser){
				System.out.println("运营平台登录成功！");
				session.put("loginTransUser", loginTransUser);
				session.put("isLogin", "true");
				return "transSuccess"; 
			}else{
				System.out.println("运营平台账号密码错误！");
				msg="账号或密码错误！"; 
				return INPUT;
			}
		//驾校登录
		}else if(loginType.equals("2")){
			SchoolManager schoolmanager=schoolManagerMapper.findUser(user_account, user_password);//驾校管理员账号密码
			String schoolmanagerid=schoolmanager.getSchool_manager_id();
			school=schoolManagerMapper.findSchoolInfoByAccount(schoolmanagerid);//所属驾校的信息
			if(null!=schoolmanager){
				if(school.getSchool_state().equals("倒闭")){
					System.out.println("驾校倒闭！");
					msg="驾校倒闭！";
					request.put("msg", msg);  
					return INPUT;
				}else{
				System.out.println("1111======"+schoolmanager.getSchool_manager_name());
				ActionContext.getContext().getSession().put("schoolmanager", schoolmanager.getSchool_manager_name());
				ActionContext.getContext().getSession().put("school_id", school.getSchool_id());
				ActionContext.getContext().getSession().put("school_name", school.getSchool_name());
				ActionContext.getContext().getSession().put("schoolmanager_id", school.getSchoolManager().getSchool_manager_id());
			
				//审核状态判断
				if(school.getSchool_state().equals("未审核")){
					return "schoolFail";
				}else if(school.getSchool_state().equals("审核拒绝")){
				   return "schoolFail";
				}else{
				//通过账号找菜单列表
				managerJurOne=schoolManagerMapper.findMenuByAccount(user_account);
				for(int i=0;i<managerJurOne.size();i++){
					System.out.println(managerJurOne.get(i).getManager_jur_one_name());
	
				}
				
				return "schoolSuccess"; 
				}
			}	
			}else{
				System.out.println("驾校平台账号密码错误！");
				msg="账号或密码错误！";
				request.put("msg", msg);  
				return INPUT;
			}
			
			
			
		//教练登录
		}else if(loginType.equals("3")){
			
			
			
		//学员登录
		}else if(loginType.equals("4")){
			try {
				System.out.println("学员登录");
				//通过账号找菜单列表
				Student student=studentMapper.findStudentByAccAndPassword(user_account, user_password);
				System.out.println(student.getStudent_id()+"student_id");
				ActionContext.getContext().getSession().put("loginedStudent",student);
				ActionContext.getContext().getSession().put("student_id",student.getStudent_id());
				if(null!=student){
					return "studentSuccess"; 
				}else{
					System.out.println("学员平台账号密码错误！");
					msg="账号或密码错误！"; 
					return INPUT;
				}
			} catch (Exception e) {
				System.out.println("学员平台账号密码错误！");
				msg="账号或密码错误！"; 
				return INPUT;
			}
			

		}
		return INPUT;
	}
	//审核通过菜单显示
	public String menu(){
		return "menu";
	}
	//审核不通过菜单显示
	public String menu2(){
		return "menu";
	}
	public String logout(){
		return INPUT;
	}
	//未审核和审核不通过界面
	public String certificted(){
	System.out.println("===+++++=========");
	String schoolmanagerid=	(String) ActionContext.getContext().getSession().get("schoolmanager_id");
	if(null==schoolmanagerid){
		return "returnlogin";
	}
	school=schoolManagerMapper.findSchoolInfoByAccount(schoolmanagerid);//所属驾校的信息
	if(school.getSchool_state().equals("未审核")){
		ActionContext.getContext().getSession().put("state", school.getSchool_state());
		school.setSchool_address(null);
		school.setSchool_certificate(null);
		//school.setSchool_city(null);
		school.setSchool_code(null);
		school.setSchool_file_url(null);
		school.setSchool_homepage(null);
		school.setSchool_name(null);
		school.setSchool_phone(null);
		school.setSchool_picture_url(null);
		//school.setSchool_province(null);
		school.setSchool_refuse_reason(null);
		school.setSchool_representative_id(null);
		school.setSchool_representative_name(null);
		school.setSchool_representative_phone(null);
		school.setSchool_charge(null);
		return "certificed";
		}else if(school.getSchool_state().equals("审核拒绝")){
		ActionContext.getContext().getSession().put("state", school.getSchool_state());
		return "certificed";
		}	
	return "certificed";
	}
	//审核通过界面
	public String certifictedpass(){
		String schoolmanagerid=	(String) ActionContext.getContext().getSession().get("schoolmanager_id");
		if(null==schoolmanagerid){
			return "returnlogin";
		}
		school=schoolManagerMapper.findSchoolInfoByAccount(schoolmanagerid);//所属驾校的信息
		return SUCCESS;
	}
	
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public TransUserMapper getTransUserMapper() {
		return transUserMapper;
	}
	public void setTransUserMapper(TransUserMapper transUserMapper) {
		this.transUserMapper = transUserMapper;
	}

	public List<ManagerJurOne> getManagerJurOne() {
		return managerJurOne;
	}

	public void setManagerJurOne(List<ManagerJurOne> managerJurOne) {
		this.managerJurOne = managerJurOne;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
}
