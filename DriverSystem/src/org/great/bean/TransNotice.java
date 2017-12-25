package org.great.bean;
/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ����Ӫƽ̨�����ʵ��bean
*/
public class TransNotice {

	  private String trans_notice_id;//�����id
	  private String trans_notice_theme;//���������
	  private String trans_notice_content;//���������
	  private String trans_notice_time;//�����ʱ��
	  private String trans_notice_province;//�����ʡ��
	  private String trans_notice_city;//����ĳ���
	  private String trans_notice_target;//����Ŀ��
	  private String trans_user_id;//�˹ܵ�id  ��Ϊ���
	  private String school_manager_id;//��У����Աid
	  //�ղι���
	public TransNotice() {
		super();
	}
	
	 public TransNotice(String trans_notice_id, String trans_notice_theme, String trans_notice_content,
			String trans_notice_time, String trans_notice_province, String trans_notice_city,
			String trans_notice_target, String trans_user_id, String school_manager_id) {
		super();
		this.trans_notice_id = trans_notice_id;
		this.trans_notice_theme = trans_notice_theme;
		this.trans_notice_content = trans_notice_content;
		this.trans_notice_time = trans_notice_time;
		this.trans_notice_province = trans_notice_province;
		this.trans_notice_city = trans_notice_city;
		this.trans_notice_target = trans_notice_target;
		this.trans_user_id = trans_user_id;
		this.school_manager_id = school_manager_id;
	}

	//setter  getter
	public String getTrans_notice_id() {
		return trans_notice_id;
	}
	public void setTrans_notice_id(String trans_notice_id) {
		this.trans_notice_id = trans_notice_id;
	}
	public String getTrans_notice_theme() {
		return trans_notice_theme;
	}
	public void setTrans_notice_theme(String trans_notice_theme) {
		this.trans_notice_theme = trans_notice_theme;
	}
	public String getTrans_notice_content() {
		return trans_notice_content;
	}
	public void setTrans_notice_content(String trans_notice_context) {
		this.trans_notice_content = trans_notice_context;
	}
	public String getTrans_notice_time() {
		return trans_notice_time;
	}
	public void setTrans_notice_time(String trans_notice_time) {
		this.trans_notice_time = trans_notice_time;
	}
	public String getTrans_notice_province() {
		return trans_notice_province;
	}
	public void setTrans_notice_province(String trans_notice_province) {
		this.trans_notice_province = trans_notice_province;
	}
	public String getTrans_notice_target() {
		return trans_notice_target;
	}
	public void setTrans_notice_target(String trans_notice_target) {
		this.trans_notice_target = trans_notice_target;
	}
	public String getTrans_user_id() {
		return trans_user_id;
	}
	public void setTrans_user_id(String trans_user_id) {
		this.trans_user_id = trans_user_id;
	}
	public String getSchool_manager_id() {
		return school_manager_id;
	}
	public void setSchool_manager_id(String school_manager_id) {
		this.school_manager_id = school_manager_id;
	}
	public String getTrans_notice_city() {
		return trans_notice_city;
	}
	public void setTrans_notice_city(String trans_notice_city) {
		this.trans_notice_city = trans_notice_city;
	}

	@Override
	public String toString() {
		return "TransNotice [trans_notice_id=" + trans_notice_id + ", trans_notice_theme=" + trans_notice_theme
				+ ", trans_notice_content=" + trans_notice_content + ", trans_notice_time=" + trans_notice_time
				+ ", trans_notice_province=" + trans_notice_province + ", trans_notice_city=" + trans_notice_city
				+ ", trans_notice_target=" + trans_notice_target + ", trans_user_id=" + trans_user_id
				+ ", school_manager_id=" + school_manager_id + "]";
	}
	
}
