package org.great.bean;
/*驾校二级权限表*/
public class SchoolJurTwo {

	private String school_jur_two_id;//驾校二级权限表id
	private String school_jur_two_name;//驾校二级权限
	private String school_jur_one_id;//驾校一级权限id外键
	
	//构造
	public SchoolJurTwo() {
		super();
	}
	
	public SchoolJurTwo(String school_jur_two_id, String school_jur_two_name, String school_jur_one_id) {
		super();
		this.school_jur_two_id = school_jur_two_id;
		this.school_jur_two_name = school_jur_two_name;
		this.school_jur_one_id = school_jur_one_id;
	}


	//srtter   getter
	public String getSchool_jur_two_id() {
		return school_jur_two_id;
	}
	public void setSchool_jur_two_id(String school_jur_two_id) {
		this.school_jur_two_id = school_jur_two_id;
	}
	public String getSchool_jur_two_name() {
		return school_jur_two_name;
	}
	public void setSchool_jur_two_name(String school_jur_two_name) {
		this.school_jur_two_name = school_jur_two_name;
	}
	public String getSchool_jur_one_id() {
		return school_jur_one_id;
	}
	public void setSchool_jur_one_id(String school_jur_one_id) {
		this.school_jur_one_id = school_jur_one_id;
	}
	
}
