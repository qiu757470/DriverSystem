package org.great.bean;
/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：教练评价表实体bean
*/
public class TeacherEvaluation {

	private String teacher_eval_id;//主键
	private String student_id;//学员编号
	private String teacher_id;//教练编号
	private String t_service_grade;//文明服务
	private String t_standard_grade;//规范教学
	private String t_honest_grade;//廉洁教学
	private String t_eval_comment;//评语
	
	//空参构造
	public TeacherEvaluation() {
		super();
	}

	public TeacherEvaluation(String teacher_eval_id, String student_id, String teacher_id, String t_service_grade,
			String t_standard_grade, String t_honest_grade, String t_eval_comment) {
		super();
		this.teacher_eval_id = teacher_eval_id;
		this.student_id = student_id;
		this.teacher_id = teacher_id;
		this.t_service_grade = t_service_grade;
		this.t_standard_grade = t_standard_grade;
		this.t_honest_grade = t_honest_grade;
		this.t_eval_comment = t_eval_comment;
	}
	//setter   getter

	public String getTeacher_eval_id() {
		return teacher_eval_id;
	}

	public void setTeacher_eval_id(String teacher_eval_id) {
		this.teacher_eval_id = teacher_eval_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getT_service_grade() {
		return t_service_grade;
	}

	public void setT_service_grade(String t_service_grade) {
		this.t_service_grade = t_service_grade;
	}

	public String getT_standard_grade() {
		return t_standard_grade;
	}

	public void setT_standard_grade(String t_standard_grade) {
		this.t_standard_grade = t_standard_grade;
	}

	public String getT_honest_grade() {
		return t_honest_grade;
	}

	public void setT_honest_grade(String t_honest_grade) {
		this.t_honest_grade = t_honest_grade;
	}

	public String getT_eval_comment() {
		return t_eval_comment;
	}

	public void setT_eval_comment(String t_eval_comment) {
		this.t_eval_comment = t_eval_comment;
	}
	
	
}
