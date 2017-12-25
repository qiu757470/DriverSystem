package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.School;
import org.great.mapper.SchoolMapper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
* @author 作者: 王艺杰
* @version 创建时间：2017年8月24日 上午9:59:02 
* 类说明 
*/

public class AboutAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	SchoolMapper schoolMapper;
	//登陆的学生驾校信息
		private School school;
	public String init(){
		Map<String, Object> map=ActionContext.getContext().getSession();
		school = schoolMapper.selectStudentAndSchoolByStudentId(String.valueOf(map.get("student_id")));
		return SUCCESS;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	
	
}
