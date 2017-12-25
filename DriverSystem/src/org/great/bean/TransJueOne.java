package org.great.bean;
/*运管端一级菜单表
 * */
public class TransJueOne {

	private String trans_jue_one_id;//运管端一级角色菜单id
	private String trans_jue_one_name;//运管端一级角名
	
	//空参构造
	public TransJueOne() {
		super();
	}

	public TransJueOne(String trans_jue_one_id, String trans_jue_one_name) {
		super();
		this.trans_jue_one_id = trans_jue_one_id;
		this.trans_jue_one_name = trans_jue_one_name;
	}
	
	//setter  getter

	public String getTrans_jue_one_id() {
		return trans_jue_one_id;
	}

	public void setTrans_jue_one_id(String trans_jue_one_id) {
		this.trans_jue_one_id = trans_jue_one_id;
	}

	public String getTrans_jue_one_name() {
		return trans_jue_one_name;
	}

	public void setTrans_jue_one_name(String trans_jue_one_name) {
		this.trans_jue_one_name = trans_jue_one_name;
	}
	
}
