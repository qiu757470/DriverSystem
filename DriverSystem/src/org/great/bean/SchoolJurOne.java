package org.great.bean;
/*��У��ɫ��
 * */
public class SchoolJurOne {

	private String school_jur_one_id;//һ��Ȩ�ޱ�id
	private String school_jur_one_name;//һ��Ȩ�ޱ�����
	
	//����
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
