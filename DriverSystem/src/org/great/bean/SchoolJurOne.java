package org.great.bean;
/*驾校角色表
 * */
public class SchoolJurOne {

	private String school_jur_one_id;//一级权限表id
	private String school_jur_one_name;//一级权限表名字
	
	//构造
	public SchoolJurOne() {
		super();
	}
	public SchoolJurOne(String school_jur_one_id, String school_jur_one_name) {
		super();
		this.school_jur_one_id = school_jur_one_id;
		this.school_jur_one_name = school_jur_one_name;
	}
	//setter  getter
	public String getSchool_jur_one_id() {
		return school_jur_one_id;
	}
	public void setSchool_jur_one_id(String school_jur_one_id) {
		this.school_jur_one_id = school_jur_one_id;
	}
	public String getSchool_jur_one_name() {
		return school_jur_one_name;
	}
	public void setSchool_jur_one_name(String school_jur_one_name) {
		this.school_jur_one_name = school_jur_one_name;
	}
	
}
