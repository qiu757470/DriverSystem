package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.SchoolEvaluation;
import org.great.mapper.SchoolEvaluationMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/** 
 * @author 作者: 王艺杰
 * @version 创建时间：2017年8月12日 下午4:26:29 
 * 类说明 
 */
@Controller
public class SchoolEvaluationAction extends ActionSupport{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	SchoolEvaluationMapper schoolEvaluationMapper;
	private String studentFromSchool;
	//驾校评论数据
  
	private SchoolEvaluation schoolEvaluation;
	//评价驾校点击事件
	public String selectAllFromSchool(){
		System.out.println("selectAllFromSchool");
		Map<String, Object> map=ActionContext.getContext().getSession();
		studentFromSchool = String.valueOf(map.get("studentFromSchool"));

		return SUCCESS;
	}

	//提交驾校评价结果
	public String submitDrivingEvaluation(){
		System.out.println("submitDrivingEvaluation");
		System.out.println(schoolEvaluation);
		System.out.println(schoolEvaluation.getS_eval_comment());
		System.out.println(schoolEvaluation.getS_service_grade());
		schoolEvaluation.setStudent_id(String.valueOf(ActionContext.getContext().getSession().get("student_id")));

		int updateResult=schoolEvaluationMapper.updateStudentToSchoolEvaluatton(schoolEvaluation);
        

			return "successResult";
	}
	public String getStudentFromSchool() {
		return studentFromSchool;
	}
	public void setStudentFromSchool(String studentFromSchool) {
		this.studentFromSchool = studentFromSchool;
	}

	public SchoolEvaluation getSchoolEvaluation() {
		return schoolEvaluation;
	}

	public void setSchoolEvaluation(SchoolEvaluation schoolEvaluation) {
		this.schoolEvaluation = schoolEvaluation;
	}



	




}
