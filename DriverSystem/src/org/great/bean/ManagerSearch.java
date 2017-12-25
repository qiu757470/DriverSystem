package org.great.bean;

import org.springframework.context.annotation.Scope;

/** 
* @author ���ߣ���ΰ��
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ��������У����Ա����Ҫ�Ĳ������ɵ�bean
*/
@Scope("session")
public class ManagerSearch {

	private String school_manager_account;//��У����Ա�˺�
	private String school_province;//��Уʡ��
	private String school_city;//��У����
	private String school_name;//��У����
	private String school_manager_state;//��У����Ա״̬
	private String orderType;//��������
	private String orderSeq;//����˳��
	
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
	//�ղι���
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
