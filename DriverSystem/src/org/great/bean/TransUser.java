package org.great.bean;
/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ����Ӫ�û���ʵ��bean
*/
public class TransUser {
  
	private String trans_user_id;//�˹��û���id
	private String trans_user_account;//�˹��û����˺�
	private String trans_user_password;//�˹��û�������
	private String trans_user_name;//�˹��û�������
	private String trans_user_identification;//��Ӫ�û����֤
	private String trans_user_sex;//��Ӫ�û��Ա�
	private String trans_user_province;//�˹��û���ʡ��
	private String trans_user_city;//�˹��û����ڵĳ���
	private String trans_user_address;//�˹��û���ϸ��ַ
	private String trans_user_phone;//�˹��û��绰
	private String trans_user_state;//�˹��û���ǰ��״̬
	private String trans_user_role;//��Ӫ��ɫ��
	//�ղι���
	public TransUser() {
		super();
	}
	
	
	//�вι���
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
