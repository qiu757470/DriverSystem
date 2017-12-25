package org.great.web;

import java.util.Map;

import javax.annotation.Resource;

import org.great.bean.SchoolEvaluation;
import org.great.mapper.SchoolEvaluationMapper;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/** 
 * @author ����: ���ս�
 * @version ����ʱ�䣺2017��8��12�� ����4:26:29 
 * ��˵�� 
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
	//��У��������
  
	private SchoolEvaluation schoolEvaluation;
	//���ۼ�У����¼�
	public String selectAllFromSchool(){
		System.out.println("selectAllFromSchool");
		Map<String, Object> map=ActionContext.getContext().getSession();
		studentFromSchool = String.valueOf(map.get("studentFromSchool"));

		return SUCCESS;
	}

	//�ύ��У���۽��
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
