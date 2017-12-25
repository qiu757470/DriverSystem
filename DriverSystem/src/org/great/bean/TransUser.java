package org.great.bean;
/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：运营用户表实体bean
*/
public class TransUser {
  
	private String trans_user_id;//运管用户的id
	private String trans_user_account;//运管用户的账号
	private String trans_user_password;//运管用户的密码
	private String trans_user_name;//运管用户的名字
	private String trans_user_identification;//运营用户身份证
	private String trans_user_sex;//运营用户性别
	private String trans_user_province;//运管用户的省份
	private String trans_user_city;//运管用户所在的城市
	private String trans_user_address;//运管用户详细地址
	private String trans_user_phone;//运管用户电话
	private String trans_user_state;//运管用户当前的状态
	private String trans_user_role;//运营角色名
	//空参构造
	public TransUser() {
		super();
	}
	
	
	//有参构造
	public TransUser(String trans_user_id, String trans_user_account, String trans_user_password,
			String trans_user_name, String trans_user_identification, String trans_user_sex, String trans_user_province,
			String trans_user_city, String trans_user_address, String trans_user_phone, String trans_user_state,
			String trans_user_role) {
		super();
		this.trans_user_id = trans_user_id;
		this.trans_user_account = trans_user_account;
		this.trans_user_password = trans_user_password;
		this.trans_user_name = trans_user_name;
		this.trans_user_identification = trans_user_identification;
		this.trans_user_sex = trans_user_sex;
		this.trans_user_province = trans_user_province;
		this.trans_user_city = trans_user_city;
		this.trans_user_address = trans_user_address;
		this.trans_user_phone = trans_user_phone;
		this.trans_user_state = trans_user_state;
		this.trans_user_role = trans_user_role;
	}


	//setter and getter
	public String getTrans_user_id() {
		return trans_user_id;
	}



	public void setTrans_user_id(String trans_user_id) {
		this.trans_user_id = trans_user_id;
	}



	public String getTrans_user_account() {
		return trans_user_account;
	}



	public void setTrans_user_account(String trans_user_account) {
		this.trans_user_account = trans_user_account;
	}



	public String getTrans_user_password() {
		return trans_user_password;
	}



	public void setTrans_user_password(String trans_user_password) {
		this.trans_user_password = trans_user_password;
	}



	public String getTrans_user_name() {
		return trans_user_name;
	}



	public void setTrans_user_name(String trans_user_name) {
		this.trans_user_name = trans_user_name;
	}



	public String getTrans_user_identification() {
		return trans_user_identification;
	}



	public void setTrans_user_identification(String trans_user_identification) {
		this.trans_user_identification = trans_user_identification;
	}



	public String getTrans_user_sex() {
		return trans_user_sex;
	}



	public void setTrans_user_sex(String trans_user_sex) {
		this.trans_user_sex = trans_user_sex;
	}



	public String getTrans_user_province() {
		return trans_user_province;
	}



	public void setTrans_user_province(String trans_user_province) {
		this.trans_user_province = trans_user_province;
	}



	public String getTrans_user_city() {
		return trans_user_city;
	}



	public void setTrans_user_city(String trans_user_city) {
		this.trans_user_city = trans_user_city;
	}



	public String getTrans_user_address() {
		return trans_user_address;
	}



	public void setTrans_user_address(String trans_user_address) {
		this.trans_user_address = trans_user_address;
	}



	public String getTrans_user_phone() {
		return trans_user_phone;
	}



	public void setTrans_user_phone(String trans_user_phone) {
		this.trans_user_phone = trans_user_phone;
	}



	public String getTrans_user_state() {
		return trans_user_state;
	}



	public void setTrans_user_state(String trans_user_state) {
		this.trans_user_state = trans_user_state;
	}



	public String getTrans_user_role() {
		return trans_user_role;
	}



	public void setTrans_user_role(String trans_user_role) {
		this.trans_user_role = trans_user_role;
	}



	@Override
	public String toString() {
		return "TransUser [trans_user_id=" + trans_user_id + ", trans_user_account=" + trans_user_account
				+ ", trans_user_password=" + trans_user_password + ", trans_user_name=" + trans_user_name
				+ ", trans_user_identification=" + trans_user_identification + ", trans_user_sex=" + trans_user_sex
				+ ", trans_user_province=" + trans_user_province + ", trans_user_city=" + trans_user_city
				+ ", trans_user_address=" + trans_user_address + ", trans_user_phone=" + trans_user_phone
				+ ", trans_user_state=" + trans_user_state + ", trans_user_role=" + trans_user_role + "]";
	}



	
	
}
