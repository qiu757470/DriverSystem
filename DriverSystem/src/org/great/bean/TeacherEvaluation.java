package org.great.bean;
/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ���������۱�ʵ��bean
*/
public class TeacherEvaluation {

	private String teacher_eval_id;//����
	private String student_id;//ѧԱ���
	private String teacher_id;//�������
	private String t_service_grade;//��������
	private String t_standard_grade;//�淶��ѧ
	private String t_honest_grade;//�����ѧ
	private String t_eval_comment;//����
	
	//�ղι���
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
