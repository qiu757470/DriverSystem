package org.great.bean;

import java.util.List;

public class ManagerJur {

	private String manager_jur_two_id;
	private String school_manager_id;
	private List<ManagerJurTwo> managerjurtwo;
	private SchoolManager schoolmanager;
	public ManagerJur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerJur(String manager_jur_two_id, String school_manager_id) {
		super();
		this.manager_jur_two_id = manager_jur_two_id;
		this.school_manager_id = school_manager_id;
	}
	public String getManager_jur_two_id() {
		return manager_jur_two_id;
	}
	public void setManager_jur_two_id(String manager_jur_two_id) {
		this.manager_jur_two_id = manager_jur_two_id;
	}
	public String getSchool_manager_id() {
		return school_manager_id;
	}
	public void setSchool_manager_id(String school_manager_id) {
		this.school_manager_id = school_manager_id;
	}

	public SchoolManager getSchoolmanager() {
		return schoolmanager;
	}
	public void setSchoolmanager(SchoolManager schoolmanager) {
		this.schoolmanager = schoolmanager;
	}
	public List<ManagerJurTwo> getManagerjurtwo() {
		return managerjurtwo;
	}
	public void setManagerjurtwo(List<ManagerJurTwo> managerjurtwo) {
		this.managerjurtwo = managerjurtwo;
	}
	
}
