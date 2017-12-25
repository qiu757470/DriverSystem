package org.great.bean;

import java.util.List;

/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ��ѧԱ��ʵ��bean
*/
public class Student {

	private String student_id;//ѧԱ��id
	private String student_identification;//ѧԱ���֤
	private String student_password;//ѧԱ����
	private String student_name;//ѧ������
	private String student_sex;//ѧԱ�Ա�
	private String student_province;//ѧԱʡ��
	private String student_city;//ѧԱ����
	private String student_address;//ѧԱ��ַ
	private String student_phone;//ѧ����ϵ��ʽ
	private String student_state;//ѧԱ״̬
	private String school_id;//ѧУ��id
	private String course_id;//��Ŀ��id����ǰ���ȣ�
	private String teacher_id;//������id����ǰ���ȣ�
	private String student_register_time;//ѧԱע��ʱ��
	private String student_graduate_time;//ѧԱ��ҵʱ��
	private Course course;
	private StudentCourse studentCouse;

	private String student_punch_time;//ѧԱ�ϴδ�ʱ��
	//��У��ʵ��bean
	private School school;
	public String getStudent_punch_time() {
		return student_punch_time;
	}


	public void setStudent_punch_time(String student_punch_time) {
		this.student_punch_time = student_punch_time;
	}


	public School getSchool() {
		return school;
	}


	public void setSchool(School school) {
		this.school = school;
	}


	private List<TransNotice> transNotices;//��Ӫ������
	public List<TransNotice> getTransNotices() {
		return transNotices;
	}


	public void setTransNotices(List<TransNotice> transNotices) {
		this.transNotices = transNotices;
	}


	//�ղι���
	public Student() {
		super();
	}
	

	//���췽��
	public Student(String student_id, String student_identification, String student_password, String student_name,
			String student_sex, String student_province, String student_city, String student_address,
			String student_phone, String student_state, String school_id, String course_id, String teacher_id) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.student_province = student_province;
		this.student_city = student_city;
		this.student_address = student_address;
		this.student_phone = student_phone;
		this.student_state = student_state;
		this.school_id = school_id;
		this.course_id = course_id;
		this.teacher_id = teacher_id;
	}
	//setter and getter
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_identification() {
		return student_identification;
	}
	public void setStudent_identification(String student_identification) {
		this.student_identification = student_identification;
	}
	public String getStudent_password() {
		return student_password;
	}
	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getStudent_address() {
		return student_address;
	}
	public void setStudent_address(String student_address) {
		this.student_address = student_address;
	}
	public String getStudent_state() {
		return student_state;
	}
	public void setStudent_state(String student_state) {
		this.student_state = student_state;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getStudent_province() {
		return student_province;
	}
	public void setStudent_province(String student_province) {
		this.student_province = student_province;
	}
	public String getStudent_city() {
		return student_city;
	}
	public void setStudent_city(String student_city) {
		this.student_city = student_city;
	}
	public String getStudent_id() {
		return student_id;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public StudentCourse getStudentCouse() {
		return studentCouse;
	}


	public void setStudentCouse(StudentCourse studentCouse) {
		this.studentCouse = studentCouse;
	}


	public String getStudent_register_time() {
		return student_register_time;
	}


	public void setStudent_register_time(String student_register_time) {
		this.student_register_time = student_register_time;
	}


	public String getStudent_graduate_time() {
		return student_graduate_time;
	}


	public void setStudent_graduate_time(String student_graduate_time) {
		this.student_graduate_time = student_graduate_time;
	}
	
	
}
