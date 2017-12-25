package org.great.bean;

public class StuExamPlan {
	private String student_id;
	private String exam_plan_id;
	public StuExamPlan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StuExamPlan(String student_id, String exam_plan_id) {
		super();
		this.student_id = student_id;
		this.exam_plan_id = exam_plan_id;
	}

	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getExam_plan_id() {
		return exam_plan_id;
	}
	public void setExam_plan_id(String exam_plan_id) {
		this.exam_plan_id = exam_plan_id;
	}
	
	

}
