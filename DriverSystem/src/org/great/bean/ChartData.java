package org.great.bean;

import java.util.ArrayList;
import java.util.Map;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��28�� ����12:46:43 
* ��˵�� 
*/
public class ChartData {

	private String registerNum;
	private String graduateNum;
	private String registerMoney;
	private Map<String,String> schoolNum;

	
	public String getRegisterNum() {
		return registerNum;
	}
	public void setRegisterNum(String registerNum) {
		this.registerNum = registerNum;
	}
	public String getGraduateNum() {
		return graduateNum;
	}
	public void setGraduateNum(String graduateNum) {
		this.graduateNum = graduateNum;
	}
	public String getRegisterMoney() {
		return registerMoney;
	}
	public void setRegisterMoney(String registerMoney) {
		this.registerMoney = registerMoney;
	}
	public Map<String, String> getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(Map<String, String> schoolNum) {
		this.schoolNum = schoolNum;
	}

	public ChartData(String registerNum, String graduateNum, String registerMoney, Map<String, String> schoolNum) {
		super();
		this.registerNum = registerNum;
		this.graduateNum = graduateNum;
		this.registerMoney = registerMoney;
		this.schoolNum = schoolNum;
	}
	public ChartData() {
		super();
	}
	
	
	

}
