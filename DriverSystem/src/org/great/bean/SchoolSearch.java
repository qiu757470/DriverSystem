package org.great.bean;
/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��21�� ����1:40:52 
* ��˵�� :����������У
*/
public class SchoolSearch {
	
	private String school_name;//��У����
	private String school_province;//��Уʡ��
	private String school_city;//��У����
	private String school_state;//��У״̬
	private String schoolOrderType;
	private String orderSeq;
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
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
	public String getSchool_state() {
		return school_state;
	}
	public void setSchool_state(String school_state) {
		this.school_state = school_state;
	}
	public String getSchoolOrderType() {
		return schoolOrderType;
	}
	public void setSchoolOrderType(String schoolOrderType) {
		this.schoolOrderType = schoolOrderType;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}


	public SchoolSearch(String school_name, String school_province, String school_city, String school_state,
			String schoolOrderType, String orderSeq) {
		super();
		this.school_name = school_name;
		this.school_province = school_province;
		this.school_city = school_city;
		this.school_state = school_state;
		this.schoolOrderType = schoolOrderType;
		this.orderSeq = orderSeq;
	}

	@Override
	public String toString() {
		return "SchoolSearch [school_name=" + school_name + ", school_province=" + school_province + ", school_city="
				+ school_city + ", school_state=" + school_state + ", schoolOrderType=" + schoolOrderType
				+ ", orderSeq=" + orderSeq + "]";
	}
	
	public SchoolSearch() {
		super();
	}
	
	
}
