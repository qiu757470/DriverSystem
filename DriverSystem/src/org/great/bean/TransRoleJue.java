package org.great.bean;
/*�˹ܽ�ɫȨ��id��
*/
public class TransRoleJue {
  
	  private String trans_role_id;//�˹�Ȩ�ޱ�
	  private String trans_jue_two_id;//�˹ܶ���Ȩ�ޱ�
	  //�ղι���
	public TransRoleJue() {
		super();
	}
	public TransRoleJue(String trans_role_id, String trans_jue_two_id) {
		super();
		this.trans_role_id = trans_role_id;
		this.trans_jue_two_id = trans_jue_two_id;
	}
	//setter  getter
	public String getTrans_role_id() {
		return trans_role_id;
	}
	public void setTrans_role_id(String trans_role_id) {
		this.trans_role_id = trans_role_id;
	}
	public String getTrans_jue_two_id() {
		return trans_jue_two_id;
	}
	public void setTrans_jue_two_id(String trans_jue_two_id) {
		this.trans_jue_two_id = trans_jue_two_id;
	}
	
	  
}
