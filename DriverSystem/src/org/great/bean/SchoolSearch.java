package org.great.bean;
/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月21日 下午1:40:52 
* 类说明 :用于搜索驾校
*/
public class SchoolSearch {
	
	private String school_name;//驾校名称
	private String school_province;//驾校省份
	private String school_city;//驾校城市
	private String school_state;//驾校状态
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
