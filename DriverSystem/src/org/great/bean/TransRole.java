package org.great.bean;
/*�˹ܽ�ɫ��*/
public class TransRole {

	private String trans_role_id;//�˹ܽ�ɫid
	private String trans_role_name;//�˹ܽ�ɫ����
	//�ղι���
	public TransRole() {
		super();
	}
	public TransRole(String trans_role_id, String trans_role_name) {
		super();
		this.trans_role_id = trans_role_id;
		this.trans_role_name = trans_role_name;
	}
	//setter  getter
	public String getTrans_role_id() {
		return trans_role_id;
	}
	public void setTrans_role_id(String trans_role_id) {
		this.trans_role_id = trans_role_id;
	}
	public String getTrans_role_name() {
		return trans_role_name;
	}
	public void setTrans_role_name(String trans_role_name) {
		this.trans_role_name = trans_role_name;
	}
	
}
