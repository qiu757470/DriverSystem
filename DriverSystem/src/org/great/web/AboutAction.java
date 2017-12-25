package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.School;
import org.great.mapper.SchoolMapper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
* @author ����: ���ս�
* @version ����ʱ�䣺2017��8��24�� ����9:59:02 
* ��˵�� 
*/

public class AboutAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	SchoolMapper schoolMapper;
	//��½��ѧ����У��Ϣ
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
