package org.great.bean;
/*�˹ܽ�ɫ�������˵���
 * */
public class TransJueTwo {

	 private String trans_jue_two_id;//�˹ܶ����˵�
	 private String trans_jue_two_name;//�˹ܶ����˵�����
	 private String trans_jue_one_id;//��Ϊ���  �˹� һ���˵�
	 private ManagerJurOne managerJurOne;//��У����Աһ��Ȩ��ʵ��bean
	 public ManagerJurOne getManagerJurOne() {
		return managerJurOne;
	}

	public void setManagerJurOne(ManagerJurOne managerJurOne) {
		this.managerJurOne = managerJurOne;
	}

	//�ղι���
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
