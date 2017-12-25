package org.great.bean;
/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月27日 上午10:58:15 
* 类说明 
*/
public class SearchChart {

	private String chartType;
	private String school_province;
	private String school_city;
	private String school_name;
	private String searchYear;
	
	
	public String getSchool_province() {
		return school_province;
	}
	public void setSchool_province(String school_province) {
		this.school_province = school_province;
	}
	public String getSchool_city() {
		return school_city;
	}
	public void setSchool_city(String school_city) {
		this.school_city = school_city;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSearchYear() {
		return searchYear;
	}
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
	public SearchChart() {
		super();
	}
	public SearchChart(String chartType, String school_province, String school_city, String school_name,
			String searchYear) {
		super();
		this.chartType = chartType;
		this.school_province = school_province;
		this.school_city = school_city;
		this.school_name = school_name;
		this.searchYear = searchYear;
	}
	
	
}
