package org.great.bean;

public class ManagerJurTwo {
 private String manager_jur_two_id;
 private String manager_jur_two_name;
 private String manager_jur_one_id;
 
public ManagerJurTwo() {
	super();
	// TODO Auto-generated constructor stub
}

public ManagerJurTwo(String manager_jur_two_id, String manager_jur_two_name, String manager_jur_one_id) {
	super();
	this.manager_jur_two_id = manager_jur_two_id;
	this.manager_jur_two_name = manager_jur_two_name;
	this.manager_jur_one_id = manager_jur_one_id;
}

public String getManager_jur_two_id() {
	return manager_jur_two_id;
}

public void setManager_jur_two_id(String manager_jur_two_id) {
	this.manager_jur_two_id = manager_jur_two_id;
}

public String getManager_jur_two_name() {
	return manager_jur_two_name;
}

public void setManager_jur_two_name(String manager_jur_two_name) {
	this.manager_jur_two_name = manager_jur_two_name;
}

public String getManager_jur_one_id() {
	return manager_jur_one_id;
}

public void setManager_jur_one_id(String manager_jur_one_id) {
	this.manager_jur_one_id = manager_jur_one_id;
}

@Override
public String toString() {
	return "ManagerJurTwo [manager_jur_two_id=" + manager_jur_two_id + ", manager_jur_two_name=" + manager_jur_two_name
			+ ", manager_jur_one_id=" + manager_jur_one_id + "]";
}



 
 
}
