package org.great.bean;
/*��УȨ�ޱ�*/
public class SchoolJur {

	private String school_jur_id;//��УȨ�ޱ�id
	private String school_jur_two_id;//��У����Ȩ�ޱ�id  ���
	private String school_id;//��Уid   ���
	//����
	public SchoolJur() {
		super();
	}
	public SchoolJur(String school_jur_id, String school_jur_two_id, String school_id) {
		super();
		this.school_jur_id = school_jur_id;
		this.school_jur_two_id = school_jur_two_id;
		this.school_id = school_id;
	}
	//setter  getter
	public String getSchool_jur_id() {
		return school_jur_id;
	}
	public void setSchool_jur_id(String school_jur_id) {
		this.school_jur_id = school_jur_id;
	}
	public String getSchool_jur_two_id() {
		return school_jur_two_id;
	}
	public void setSchool_jur_two_id(String school_jur_two_id) {
		this.school_jur_two_id = school_jur_two_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	
}
