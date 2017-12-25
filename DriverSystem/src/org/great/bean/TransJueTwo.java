package org.great.bean;
/*运管角色，二级菜单表
 * */
public class TransJueTwo {

	 private String trans_jue_two_id;//运管二级菜单
	 private String trans_jue_two_name;//运管二级菜单名字
	 private String trans_jue_one_id;//作为外键  运管 一级菜单
	 private ManagerJurOne managerJurOne;//驾校管理员一级权限实体bean
	 public ManagerJurOne getManagerJurOne() {
		return managerJurOne;
	}

	public void setManagerJurOne(ManagerJurOne managerJurOne) {
		this.managerJurOne = managerJurOne;
	}

	//空参构造
	public TransJueTwo() {
		super();
	}

	public TransJueTwo(String trans_jue_two_id, String trans_jue_two_name, String trans_jue_one_id) {
		super();
		this.trans_jue_two_id = trans_jue_two_id;
		this.trans_jue_two_name = trans_jue_two_name;
		this.trans_jue_one_id = trans_jue_one_id;
	}
	 //setter  getter

	public String getTrans_jue_two_id() {
		return trans_jue_two_id;
	}

	public void setTrans_jue_two_id(String trans_jue_two_id) {
		this.trans_jue_two_id = trans_jue_two_id;
	}

	public String getTrans_jue_two_name() {
		return trans_jue_two_name;
	}

	public void setTrans_jue_two_name(String trans_jue_two_name) {
		this.trans_jue_two_name = trans_jue_two_name;
	}

	public String getTrans_jue_one_id() {
		return trans_jue_one_id;
	}

	public void setTrans_jue_one_id(String trans_jue_one_id) {
		this.trans_jue_one_id = trans_jue_one_id;
	}
	
	 
}
