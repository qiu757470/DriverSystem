package org.great.bean;
/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��21�� ����7:46:46 
* ��˵�� 
*/
public class SchoolNotice {

	private String trans_notice_id;//����id���
	private String school_id;//��Уid���
	private String school_notice_state;//����״̬���Ѷ���δ��
	public String getTrans_notice_id() {
		return trans_notice_id;
	}
	public void setTrans_notice_id(String trans_notice_id) {
		this.trans_notice_id = trans_notice_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getSchool_notice_state() {
		return school_notice_state;
	}
	public void setSchool_notice_state(String school_notice_state) {
		this.school_notice_state = school_notice_state;
	}
	public SchoolNotice() {
		super();
	}
	public SchoolNotice(String trans_notice_id, String school_id, String school_notice_state) {
		super();
		this.trans_notice_id = trans_notice_id;
		this.school_id = school_id;
		this.school_notice_state = school_notice_state;
	}
	@Override
	public String toString() {
		return "SchoolNotice [trans_notice_id=" + trans_notice_id + ", school_id=" + school_id
				+ ", school_notice_state=" + school_notice_state + "]";
	}
	
	
}
