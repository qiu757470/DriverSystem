package org.great.bean;

/** 
* @author ���ߣ�֣����
* @version ����ʱ�䣺2017��8��10��
* ��˵�� �������������Ҫ���ֶε���
*/
public class NoticeAndTransuser {

	private String trans_notice_id;//�����id
	private String trans_notice_theme;//���������
	private String trans_notice_content;//���������
	private String trans_notice_time;//�����ʱ��
	private String trans_notice_province;//�����ʡ��
	private String trans_notice_city;//����ĳ���
	private String trans_notice_target;//����Ŀ��
	private String trans_user_id;//�˹ܵ�id  ��Ϊ���
	private String trans_user_name;//�˹��û�������
	private String trans_user_sex;//��Ӫ�û��Ա�
	private String trans_user_phone;//�˹��û��绰
	private String trans_user_state;//�˹��û���ǰ��״̬
	private String trans_user_role;//��Ӫ��ɫ��
	
	//���캯��
	public NoticeAndTransuser() {
		super();
	}

	public NoticeAndTransuser(String trans_notice_id, String trans_notice_theme, String trans_notice_content,
			String trans_notice_time, String trans_notice_province, String trans_notice_city,
			String trans_notice_target, String trans_user_id, String trans_user_name, String trans_user_sex,
			String trans_user_phone, String trans_user_state, String trans_user_role) {
		super();
		this.trans_notice_id = trans_notice_id;
		this.trans_notice_theme = trans_notice_theme;
		this.trans_notice_content = trans_notice_content;
		this.trans_notice_time = trans_notice_time;
		this.trans_notice_province = trans_notice_province;
		this.trans_notice_city = trans_notice_city;
		this.trans_notice_target = trans_notice_target;
		this.trans_user_id = trans_user_id;
		this.trans_user_name = trans_user_name;
		this.trans_user_sex = trans_user_sex;
		this.trans_user_phone = trans_user_phone;
		this.trans_user_state = trans_user_state;
		this.trans_user_role = trans_user_role;
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

	public void setTrans_notice_content(String trans_notice_content) {
		this.trans_notice_content = trans_notice_content;
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

	public String getTrans_notice_city() {
		return trans_notice_city;
	}

	public void setTrans_notice_city(String trans_notice_city) {
		this.trans_notice_city = trans_notice_city;
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

	public String getTrans_user_name() {
		return trans_user_name;
	}

	public void setTrans_user_name(String trans_user_name) {
		this.trans_user_name = trans_user_name;
	}

	public String getTrans_user_sex() {
		return trans_user_sex;
	}

	public void setTrans_user_sex(String trans_user_sex) {
		this.trans_user_sex = trans_user_sex;
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
	
	
	
}
