package org.great.bean;
/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ����У����Ա��ʵ��bean
*/
public class SchoolManager {
 
	 private String school_manager_id;//��У����Ա
	 private String school_manager_account;//��У����Ա�˺�
	 private String school_manager_password;//��У����Ա����
	 private String school_manager_name;//��У����Ա����
	 private String school_manager_identification;//��У����Ա����
	 private String school_manager_sex;//��У����Ա����
	 private String school_manager_province;//��У����Աʡ��
	 private String school_manager_city;//��У����Ա����
	 private String school_manager_address;//��У����Ա��ϸ��ַ
	 private String school_manager_phone;//��У����Ա�绰
	 private String school_manager_state;//��У����Ա�˺�״̬
	 private String school_id;//���    ��Уid
	 //ʵ��Bean
	 private School school;
	 //����
	public SchoolManager() {
		super();
	}

	//setter and getter
	public String getSchool_manager_id() {
		return school_manager_id;
	}
	public void setSchool_manager_id(String school_manager_id) {
		this.school_manager_id = school_manager_id;
	}
	public String getSchool_manager_account() {
		return school_manager_account;
	}
	public void setSchool_manager_account(String school_manager_account) {
		this.school_manager_account = school_manager_account;
	}
	public String getSchool_manager_password() {
		return school_manager_password;
	}
	public void setSchool_manager_password(String school_manager_password) {
		this.school_manager_password = school_manager_password;
	}
	public String getSchool_manager_name() {
		return school_manager_name;
	}
	public void setSchool_manager_name(String school_manager_name) {
		this.school_manager_name = school_manager_name;
	}
	public String getSchool_manager_identification() {
		return school_manager_identification;
	}
	public void setSchool_manager_identification(String school_manager_identification) {
		this.school_manager_identification = school_manager_identification;
	}
	public String getSchool_manager_sex() {
		return school_manager_sex;
	}
	public void setSchool_manager_sex(String school_manager_sex) {
		this.school_manager_sex = school_manager_sex;
	}
	public String getSchool_manager_province() {
		return school_manager_province;
	}
	public void setSchool_manager_province(String school_manager_province) {
		this.school_manager_province = school_manager_province;
	}
	public String getSchool_manager_city() {
		return school_manager_city;
	}
	public void setSchool_manager_city(String school_manager_city) {
		this.school_manager_city = school_manager_city;
	}
	public String getSchool_manager_address() {
		return school_manager_address;
	}
	public void setSchool_manager_address(String school_manager_address) {
		this.school_manager_address = school_manager_address;
	}
	public String getSchool_manager_phone() {
		return school_manager_phone;
	}
	public void setSchool_manager_phone(String school_manager_phone) {
		this.school_manager_phone = school_manager_phone;
	}
	public String getSchool_manager_state() {
		return school_manager_state;
	}
	public void setSchool_manager_state(String school_manager_state) {
		this.school_manager_state = school_manager_state;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	//�вι���
	public SchoolManager(String school_manager_id, String school_manager_account, String school_manager_password,
			String school_manager_name, String school_manager_identification, String school_manager_sex,
			String school_manager_province, String school_manager_city, String school_manager_address,
			String school_manager_phone, String school_manager_state, String school_id, School school) {
		super();
		this.school_manager_id = school_manager_id;
		this.school_manager_account = school_manager_account;
		this.school_manager_password = school_manager_password;
		this.school_manager_name = school_manager_name;
		this.school_manager_identification = school_manager_identification;
		this.school_manager_sex = school_manager_sex;
		this.school_manager_province = school_manager_province;
		this.school_manager_city = school_manager_city;
		this.school_manager_address = school_manager_address;
		this.school_manager_phone = school_manager_phone;
		this.school_manager_state = school_manager_state;
		this.school_id = school_id;
		this.school = school;
	}
	
	@Override
	public String toString() {
		return "SchoolManager [school_manager_id=" + school_manager_id + ", school_manager_account="
				+ school_manager_account + ", school_manager_password=" + school_manager_password
				+ ", school_manager_name=" + school_manager_name + ", school_manager_identification="
				+ school_manager_identification + ", school_manager_sex=" + school_manager_sex
				+ ", school_manager_province=" + school_manager_province + ", school_manager_city="
				+ school_manager_city + ", school_manager_address=" + school_manager_address + ", school_manager_phone="
				+ school_manager_phone + ", school_manager_state=" + school_manager_state + ", school_id=" + school_id
				+ ", school=" + school + "]";
	}
	




}
