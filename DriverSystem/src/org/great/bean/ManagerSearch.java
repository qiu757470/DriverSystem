package org.great.bean;

import org.springframework.context.annotation.Scope;

/** 
* @author 作者：陈伟鹏
* @version 创建时间：2017年8月10日
* 类说明 ：搜索驾校管理员表需要的参数构成的bean
*/
@Scope("session")
public class ManagerSearch {

	private String school_manager_account;//驾校管理员账号
	private String school_province;//驾校省份
	private String school_city;//驾校城市
	private String school_name;//驾校名称
	private String school_manager_state;//驾校管理员状态
	private String orderType;//排序类型
	private String orderSeq;//排序顺序
	
	//setter and getter
	public String getSchool_manager_account() {
		return school_manager_account;
	}
	public void setSchool_manager_account(String school_manager_account) {
		this.school_manager_account = school_manager_account;
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
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_manager_state() {
		return school_manager_state;
	}
	public void setSchool_manager_state(String school_manager_state) {
		this.school_manager_state = school_manager_state;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	//空参构造
	public ManagerSearch() {
		super();
	}
	@Override
	public String toString() {
		return "ManagerSearch [school_manager_account=" + school_manager_account + ", school_province="
				+ school_province + ", school_city=" + school_city + ", school_name=" + school_name
				+ ", school_manager_state=" + school_manager_state + ", orderType=" + orderType + ", orderSeq="
				+ orderSeq + "]";
	}
	
	
	
	
	
}
