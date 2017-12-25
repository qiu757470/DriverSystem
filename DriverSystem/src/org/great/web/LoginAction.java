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

/*�˹�ƽ̨��Action������*/
@Controller
public class LoginAction extends BaseAction{
	private String user_account;
	private String user_password;
	private String msg="";
	private String loginType;
	private List<ManagerJurOne> managerJurOne;
	private School school;
	//mapper�ӿ�
	@Resource
	private TransUserMapper transUserMapper;
	@Resource
    private SchoolManagerMapper schoolManagerMapper;
	@Resource
	private StudentMapper studentMapper;
	//���˹ܵ���
	@SuppressWarnings("unused")
	public String login(){
		
//		//�Ѿ���¼���˹�ƽ̨��ֱ�ӵ��˹�ƽ̨��ҳ
//				if(session.get("isLogin") != null && session.get("loginTransUser") != null){
//					return "transSuccess";
//				}
//				if(user_account == null || user_password == null || logintype == null){
//					System.out.println("����ķ��ʵ�ַ�����ȵ�¼��");
//					msg="���ȵ�¼��";
//					request.put("errorSource", "true");
//					return INPUT;
//				}
			System.out.println("loginType:" + loginType);
		if(loginType == null){
			msg="���ȵ�¼��";
			request.put("msg", msg);  
			return INPUT;
		}		
		//�˹ܵ�¼
		if(loginType.equals("1")){
			
			/*System.out.println(user_account);
			System.out.println(user_password);*/
			//ͨ���˺ź������ҵ���Ӫ�û�
			TransUser loginTransUser = transUserMapper.findTransUser(user_account,user_password);
			if(null != loginTransUser){
				System.out.println("��Ӫƽ̨��¼�ɹ���");
				session.put("loginTransUser", loginTransUser);
				session.put("isLogin", "true");
				return "transSuccess"; 
			}else{
				System.out.println("��Ӫƽ̨�˺��������");
				msg="�˺Ż��������"; 
				return INPUT;
			}
		//��У��¼
		}else if(loginType.equals("2")){
			SchoolManager schoolmanager=schoolManagerMapper.findUser(user_account, user_password);//��У����Ա�˺�����
			String schoolmanagerid=schoolmanager.getSchool_manager_id();
			school=schoolManagerMapper.findSchoolInfoByAccount(schoolmanagerid);//������У����Ϣ
			if(null!=schoolmanager){
				if(school.getSchool_state().equals("����")){
					System.out.println("��У���գ�");
					msg="��У���գ�";
					request.put("msg", msg);  
					return INPUT;
				}else{
				System.out.println("1111======"+schoolmanager.getSchool_manager_name());
				ActionContext.getContext().getSession().put("schoolmanager", schoolmanager.getSchool_manager_name());
				ActionContext.getContext().getSession().put("school_id", school.getSchool_id());
				ActionContext.getContext().getSession().put("school_name", school.getSchool_name());
				ActionContext.getContext().getSession().put("schoolmanager_id", school.getSchoolManager().getSchool_manager_id());
			
				//���״̬�ж�
				if(school.getSchool_state().equals("δ���")){
					return "schoolFail";
				}else if(school.getSchool_state().equals("��˾ܾ�")){
				   return "schoolFail";
				}else{
				//ͨ���˺��Ҳ˵��б�
				managerJurOne=schoolManagerMapper.findMenuByAccount(user_account);
				for(int i=0;i<managerJurOne.size();i++){
					System.out.println(managerJurOne.get(i).getManager_jur_one_name());
	
				}
				
				return "schoolSuccess"; 
				}
			}	
			}else{
				System.out.println("��Уƽ̨�˺��������");
				msg="�˺Ż��������";
				request.put("msg", msg);  
				return INPUT;
			}
			
			
			
		//������¼
		}else if(loginType.equals("3")){
			
			
			
		//ѧԱ��¼
		}else if(loginType.equals("4")){
			try {
				System.out.println("ѧԱ��¼");
				//ͨ���˺��Ҳ˵��б�
				Student student=studentMapper.findStudentByAccAndPassword(user_account, user_password);
				System.out.println(student.getStudent_id()+"student_id");
				ActionContext.getContext().getSession().put("loginedStudent",student);
				ActionContext.getContext().getSession().put("student_id",student.getStudent_id());
				if(null!=student){
					return "studentSuccess"; 
				}else{
					System.out.println("ѧԱƽ̨�˺��������");
					msg="�˺Ż��������"; 
					return INPUT;
				}
			} catch (Exception e) {
				System.out.println("ѧԱƽ̨�˺��������");
				msg="�˺Ż��������"; 
				return INPUT;
			}
			

		}
		return INPUT;
	}
	//���ͨ���˵���ʾ
	public String menu(){
		return "menu";
	}
	//��˲�ͨ���˵���ʾ
	public String menu2(){
		return "menu";
	}
	public String logout(){
		return INPUT;
	}
	//δ��˺���˲�ͨ������
	public String certificted(){
	System.out.println("===+++++=========");
	String schoolmanagerid=	(String) ActionContext.getContext().getSession().get("schoolmanager_id");
	if(null==schoolmanagerid){
		return "returnlogin";
	}
	school=schoolManagerMapper.findSchoolInfoByAccount(schoolmanagerid);//������У����Ϣ
	if(school.getSchool_state().equals("δ���")){
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
		}else if(school.getSchool_state().equals("��˾ܾ�")){
		ActionContext.getContext().getSession().put("state", school.getSchool_state());
		return "certificed";
		}	
	return "certificed";
	}
	//���ͨ������
	public String certifictedpass(){
		String schoolmanagerid=	(String) ActionContext.getContext().getSession().get("schoolmanager_id");
		if(null==schoolmanagerid){
			return "returnlogin";
		}
		school=schoolManagerMapper.findSchoolInfoByAccount(schoolmanagerid);//������У����Ϣ
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
