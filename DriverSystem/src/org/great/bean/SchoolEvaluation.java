package org.great.bean;
/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ����У���۱�ʵ��bean
*/
public class SchoolEvaluation {

	private String school_eval_id;//����
	private String student_id;//ѧԱid
	private String s_service_grade;//��������
	private String s_standard_grade;//�淶��ѧ
	private String s_place_grade;//������ʩ
	private String s_security_grade;//��ȫ����
	private String s_eval_comment;//����
	//����
	public SchoolEvaluation() {
		super();
	}
	public SchoolEvaluation(String school_eval_id, String student_id, String s_service_grade, String s_standard_grade,
			String s_place_grade, String s_security_grade, String s_eval_comment) {
		super();
		this.school_eval_id = school_eval_id;
		this.student_id = student_id;
		this.s_service_grade = s_service_grade;
		this.s_standard_grade = s_standard_grade;
		this.s_place_grade = s_place_grade;
		this.s_security_grade = s_security_grade;
		this.s_eval_comment = s_eval_comment;
	}
	//setter  getter  
	public String getSchool_eval_id() {
		return school_eval_id;
	}
	public void setSchool_eval_id(String school_eval_id) {
		this.school_eval_id = school_eval_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getS_service_grade() {
		return s_service_grade;
	}
	public void setS_service_grade(String s_service_grade) {
		this.s_service_grade = s_service_grade;
	}
	public String getS_standard_grade() {
		return s_standard_grade;
	}
	public void setS_standard_grade(String s_standard_grade) {
		this.s_standard_grade = s_standard_grade;
	}
	public String getS_place_grade() {
		return s_place_grade;
	}
	public void setS_place_grade(String s_place_grade) {
		this.s_place_grade = s_place_grade;
	}
	public String getS_security_grade() {
		return s_security_grade;
	}
	public void setS_security_grade(String s_security_grade) {
		this.s_security_grade = s_security_grade;
	}
	public String getS_eval_comment() {
		return s_eval_comment;
	}
	public void setS_eval_comment(String s_eval_comment) {
		this.s_eval_comment = s_eval_comment;
	}
	
	
}
