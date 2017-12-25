package org.great.bean;

import java.util.List;

public class ManagerJurOne {

	private String manager_jur_one_id;
	private String manager_jur_one_name;
	private List<ManagerJurTwo> managerjurtwo;
	public ManagerJurOne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerJurOne(String manager_jur_one_id, String manager_jur_one_name) {
		super();
		this.manager_jur_one_id = manager_jur_one_id;
		this.manager_jur_one_name = manager_jur_one_name;
	}
	public String getManager_jur_one_id() {
		return manager_jur_one_id;
	}
	public void setManager_jur_one_id(String manager_jur_one_id) {
		this.manager_jur_one_id = manager_jur_one_id;
	}
	public String getManager_jur_one_name() {
		return manager_jur_one_name;
	}
	public void setManager_jur_one_name(String manager_jur_one_name) {
		this.manager_jur_one_name = manager_jur_one_name;
	}
	public List<ManagerJurTwo> getManagerjurtwo() {
		return managerjurtwo;
	}
	public void setManagerjurtwo(List<ManagerJurTwo> managerjurtwo) {
		this.managerjurtwo = managerjurtwo;
	}
	
	
	
	
}
