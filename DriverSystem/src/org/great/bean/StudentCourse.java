package org.great.bean;
/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ��ѧԱ��Ŀ��ʵ��bean
*/
public class StudentCourse {
 
	private String student_course_id;//ѧ���ɼ���id
	private String student_id;//���   ѧ��id
	private String course_id;//��Ŀid  ���
	private String teacher_id;//ѧԱ�ÿ�Ŀ�Ľ���
	private String student_course_score;//ѧ����Ŀ�ɼ�
	private String student_course_time;//��Ӧ�Ŀ�Ŀʱ��
	private String student_course_state;//��Ӧ�Ŀ�Ŀ״̬
	
	private Student student;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	//�ղι���
	public StudentCourse() {
		super();
	}
	//�вι���
	

	//setter  getter
	public String getStudent_course_id() {
		return student_course_id;
	}
	public StudentCourse(String student_course_id, String student_id, String course_id, String teacher_id,
			String student_course_score, String student_course_time, String student_course_state) {
		super();
		this.student_course_id = student_course_id;
		this.student_id = student_id;
		this.course_id = course_id;
		this.teacher_id = teacher_id;
		this.student_course_score = student_course_score;
		this.student_course_time = student_course_time;
		this.student_course_state = student_course_state;
		
	}
	public void setStudent_course_id(String student_course_id) {
		this.student_course_id = student_course_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public String getStudent_course_state() {
		return student_course_state;
	}
	public void setStudent_course_state(String student_course_state) {
		this.student_course_state = student_course_state;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getStudent_course_score() {
		return student_course_score;
	}
	public void setStudent_course_score(String student_course_score) {
		this.student_course_score = student_course_score;
	}
	public String getStudent_course_time() {
		return student_course_time;
	}
	public void setStudent_course_time(String student_course_time) {
		this.student_course_time = student_course_time;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	
}
