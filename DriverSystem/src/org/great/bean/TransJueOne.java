package org.great.bean;
/*�˹ܶ�һ���˵���
 * */
public class TransJueOne {

	private String trans_jue_one_id;//�˹ܶ�һ����ɫ�˵�id
	private String trans_jue_one_name;//�˹ܶ�һ������
	
	//�ղι���
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
