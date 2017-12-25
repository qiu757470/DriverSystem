package org.great.bean;
/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：驾校管理员表实体bean
*/
public class SchoolManager {
 
	 private String school_manager_id;//驾校管理员
	 private String school_manager_account;//驾校管理员账号
	 private String school_manager_password;//驾校管理员密码
	 private String school_manager_name;//驾校管理员名字
	 private String school_manager_identification;//驾校管理员名字
	 private String school_manager_sex;//驾校管理员名字
	 private String school_manager_province;//驾校管理员省份
	 private String school_manager_city;//驾校管理员城市
	 private String school_manager_address;//驾校管理员详细地址
	 private String school_manager_phone;//驾校管理员电话
	 private String school_manager_state;//驾校管理员账号状态
	 private String school_id;//外键    驾校id
	 //实体Bean
	 private School school;
	 //构造
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
	//有参构造
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
