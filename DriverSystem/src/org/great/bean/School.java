package org.great.bean;
/*��Уʵ��id*/
public class School {

	private String school_id;//��У��id
	private String school_name;//��У������
	private String school_representative_name;//��У�����ͷ��˵�����
	private String school_representative_id;//��У�����ͷ��˵�id
	private String school_representative_phone;//��У�����Ϸ��˵ĵ绰
	private String school_province;//��У���ڵ�ʡ��
	private String school_city;//��У���ڵĳ���
	private String school_address;//��У��ϸ��ַ
	private String school_phone;//��У����ϵ��ʽ
	private String school_certificate;//��У�ľ�Ӫ���֤
	private String school_code;//��У����֯����
	private String school_state;//��У�����״̬
	private String school_picture_url;//��У��Ӧ��ͼƬ·��
	private String school_homepage;//��У����ҳ
	private String school_refuse_reason;
	private String school_file_url;//��У��֤���ϵ�ַ
	private String school_charge;//��У�շ�
	private SchoolManager schoolManager;
	private Integer createId;//������������ʱid
	private Integer managerNum;//����Ա����
	private Integer teacherNum;//��������
	private Integer studentNum;//ѧԱ����
	public Integer getCreateId() {
		return createId;
	}








	public void setCreateId(Integer createId) {
		this.createId = createId;
	}








	public Integer getManagerNum() {
		return managerNum;
	}








	public void setManagerNum(Integer managerNum) {
		this.managerNum = managerNum;
	}








	public Integer getTeacherNum() {
		return teacherNum;
	}








	public void setTeacherNum(Integer teacherNum) {
		this.teacherNum = teacherNum;
	}








	public Integer getStudentNum() {
		return studentNum;
	}








	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}








	//����
	public School() {
		super();
	}
	
	
	
	
	
	
	
	
	public School(String school_id, String school_name, String school_representative_name,
			String school_representative_id, String school_representative_phone, String school_province,
			String school_city, String school_address, String school_phone, String school_certificate,
			String school_code, String school_state, String school_picture_url, String school_homepage,
			String school_refuse_reason, String school_file_url) {
		super();
		this.school_id = school_id;
		this.school_name = school_name;
		this.school_representative_name = school_representative_name;
		this.school_representative_id = school_representative_id;
		this.school_representative_phone = school_representative_phone;
		this.school_province = school_province;
		this.school_city = school_city;
		this.school_address = school_address;
		this.school_phone = school_phone;
		this.school_certificate = school_certificate;
		this.school_code = school_code;
		this.school_state = school_state;
		this.school_picture_url = school_picture_url;
		this.school_homepage = school_homepage;
		this.school_refuse_reason = school_refuse_reason;
		this.school_file_url = school_file_url;
	}








	public String getSchool_file_url() {
		return school_file_url;
	}


	public void setSchool_file_url(String school_file_url) {
		this.school_file_url = school_file_url;
	}


	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_representative_name() {
		return school_representative_name;
	}
	public void setSchool_representative_name(String school_representative_name) {
		this.school_representative_name = school_representative_name;
	}
	public String getSchool_representative_id() {
		return school_representative_id;
	}
	public void setSchool_representative_id(String school_representative_id) {
		this.school_representative_id = school_representative_id;
	}
	public String getSchool_representative_phone() {
		return school_representative_phone;
	}
	public void setSchool_representative_phone(String school_representative_phone) {
		this.school_representative_phone = school_representative_phone;
	}
	public String getSchool_province() {
		return school_province;
	}
	public void setSchool_province(String school_province) {
		this.school_province = school_province;
	}
	public String getSchool_city() {
		return school_city;
	}
	public void setSchool_city(String school_city) {
		this.school_city = school_city;
	}
	public String getSchool_certificate() {
		return school_certificate;
	}
	public void setSchool_certificate(String school_certificate) {
		this.school_certificate = school_certificate;
	}
	public String getSchool_code() {
		return school_code;
	}
	public void setSchool_code(String school_code) {
		this.school_code = school_code;
	}
	public String getSchool_state() {
		return school_state;
	}
	public void setSchool_state(String school_state) {
		this.school_state = school_state;
	}
	public String getSchool_picture_url() {
		return school_picture_url;
	}
	public void setSchool_picture_url(String school_picture_url) {
		this.school_picture_url = school_picture_url;
	}
	public String getSchool_phone() {
		return school_phone;
	}
	public void setSchool_phone(String school_phone) {
		this.school_phone = school_phone;
	}
	public String getSchool_homepage() {
		return school_homepage;
	}
	public void setSchool_homepage(String school_homepage) {
		this.school_homepage = school_homepage;
	}
	public String getSchool_address() {
		return school_address;
	}
	public void setSchool_address(String school_address) {
		this.school_address = school_address;
	}
	public String getSchool_refuse_reason() {
		return school_refuse_reason;
	}
	public void setSchool_refuse_reason(String school_refuse_reason) {
		this.school_refuse_reason = school_refuse_reason;
	}
	public SchoolManager getSchoolManager() {
		return schoolManager;
	}
	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}

	public String getSchool_charge() {
		return school_charge;
	}

	public void setSchool_charge(String school_charge) {
		this.school_charge = school_charge;
	}
	
	
	
	
}
