package org.great.bean;
/*运管角色表*/
public class TransRole {

	private String trans_role_id;//运管角色id
	private String trans_role_name;//运管角色名称
	//空参构造
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
