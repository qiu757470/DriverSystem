package org.great.bean;
/*ѧ����Ϣ��*/
public class StudentBean {

	private String student_id;//ѧ����id
	private String student_identification;//ѧ�����֤
	private String student_password;//ѧ������
	private String student_name;//ѧ������
	private String student_sex;//ѧ���Ա�
	private String student_province;//ѧԱʡ��
	private String student_city;//ѧԱ����
	private String course_id;//ѧ������
	private String student_phone;//ѧ����ϵ��ʽ
	private String student_address;//ѧ����ַ
	private String student_state;//ѧ��״̬
	private String school_id;//ѧУ��id
	private String teacher_id;//������id
	private String student_register_time;//ѧԱע��ʱ��
	private String student_graduate_time;//ѧԱ��ҵʱ��
	private String student_punch_time;//ѧԱ�ϴδ�ʱ��
	private int	 pagemax;//ҳ�����ֵ
	private int	 pagemin;//ҳ����Сֵ
	private String course_name;
	
	
	//�ղι���
	public StudentBean() {
		super();
	}

	public StudentBean(String student_id, String student_identification, String student_password, String student_name,
			String student_sex,String student_province,String student_city,
			String course_id, String student_phone, String student_address,
			String student_state, String school_id, String teacher_id) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.student_province = student_province;
		this.student_city = student_city;
		
		this.course_id = course_id;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_state = student_state;
		this.school_id = school_id;
		this.teacher_id = teacher_id;
	}

	public StudentBean(String student_id, String student_identification, String student_password, String student_name,
			String student_sex, String course_id, String student_phone, String student_address,
			String student_state, String school_id, String teacher_id, int pagemax, int pagemin) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.course_id = course_id;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_state = student_state;
		this.school_id = school_id;
		this.teacher_id = teacher_id;
		this.pagemax = pagemax;
		this.pagemin = pagemin;
	}

	public StudentBean(String student_id, String student_identification, String student_password, String student_name,
			String student_sex, String student_province, String student_city, String course_id, String student_phone,
			String student_address, String student_state, String school_id, String teacher_id,
			String student_register_time) {
		super();
		this.student_id = student_id;
		this.student_identification = student_identification;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_sex = student_sex;
		this.student_province = student_province;
		this.student_city = student_city;
		this.course_id = course_id;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_state = student_state;
		this.school_id = school_id;
		this.teacher_id = teacher_id;
		this.student_register_time = student_register_time;
	}

	public String getStudent_id() {
		return student_id;
	}
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
	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
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
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
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
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getTeacher_id() {
		return teacher_id;
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

	public String getStudent_punch_time() {
		return student_punch_time;
	}

	public void setStudent_punch_time(String student_punch_time) {
		this.student_punch_time = student_punch_time;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getPagemax() {
		return pagemax;
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

	public void setPagemax(int pagemax) {
		this.pagemax = pagemax;
	}

	public int getPagemin() {
		return pagemin;
	}

	public void setPagemin(int pagemin) {
		this.pagemin = pagemin;
	}
	
	//setter  getter
	
}
