package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.Student;
import org.great.mapper.StudentMapper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author 作者: 王艺杰
 * @version 创建时间：2017年8月15日 上午10:33:46 
 * 类说明 
 */
public class ChangePassAction extends ActionSupport{
	private String oldStudentPassword;
	private String newStudentPassword;

	@Resource
	private StudentMapper studentMapper;
	private Student student;
	public String init(){
		Map<String, Object> map=ActionContext.getContext().getSession();
		String student_id=String.valueOf(map.get("student_id"));

		student = studentMapper.selectStudentNameByStudentId(student_id);
		return SUCCESS;
	}

	public String updatePwd() {
		System.out.println("updatePwd");
		Map<String, Object> map=ActionContext.getContext().getSession();
		String student_id=String.valueOf(map.get("student_id"));

		//判断密码
		Object object=studentMapper.judgPassword(oldStudentPassword,student_id);

		if(null!=object){
			
			int i=studentMapper.updatePwd(newStudentPassword,student_id);
			return SUCCESS;
		}
		return ERROR;



	}


	public String getOldStudentPassword() {
		return oldStudentPassword;
	}
	public void setOldStudentPassword(String oldStudentPassword) {
		this.oldStudentPassword = oldStudentPassword;
	}
	public String getNewStudentPassword() {
		return newStudentPassword;
	}
	public void setNewStudentPassword(String newStudentPassword) {
		this.newStudentPassword = newStudentPassword;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}



}
