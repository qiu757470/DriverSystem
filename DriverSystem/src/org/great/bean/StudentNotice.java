package org.great.bean;
/** 
* @author 作者：郑永进
* @version 创建时间：2017年8月10日
* 类说明 ：学员公告外键表实体bean
*/
public class StudentNotice {

	private String trans_notice_id;//运管公告id
	private String student_id;//学生id
	private String student_notice_state;//学生公告状态

	//空参构造
	public StudentNotice() {
		super();
	}

	public StudentNotice(String trans_notice_id, String student_id, String student_notice_state) {
		super();
		this.trans_notice_id = trans_notice_id;
		this.student_id = student_id;
		this.student_notice_state = student_notice_state;
	}
	
	//setter  getter

	public String getTrans_notice_id() {
		return trans_notice_id;
	}

	public void setTrans_notice_id(String trans_notice_id) {
		this.trans_notice_id = trans_notice_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_notice_state() {
		return student_notice_state;
	}

	public void setStudent_notice_state(String student_notice_state) {
		this.student_notice_state = student_notice_state;
	}
	
	
	
}
