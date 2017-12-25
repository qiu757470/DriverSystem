package org.great.web;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.great.bean.ChartData;
import org.great.bean.SearchChart;
import org.great.bean.Student;
import org.great.mapper.SchoolMapper;
import org.great.mapper.StudentMapper;
import org.springframework.stereotype.Controller;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月27日 上午10:23:26 
* 类说明 
*/
@Controller
public class StatisticsAction extends BaseAction{
	
	private SearchChart searchChart;//前端传来的搜索条件
	@Resource
	private StudentMapper studentMapper;//student表操作接口
	@Resource
	private SchoolMapper schoolMapper;//school表操作接口
	
	private ChartData chartData;//搜索得到的图表数据
	public String statisticsView(){
		return "statisticsPage";
	}
	//进入界面的方法
	public String showChart(){
		chartData = new ChartData();
		String registerNum = "";
		String graduateNum = "";
		DecimalFormat df = new DecimalFormat("00");
		for(int i=0; i<12; i++){
			int num1 = studentMapper.searchByRegisterTimeNum("2017" + df.format(i));
			registerNum += num1 + ",";
			ArrayList<Student> students = studentMapper.searchByRegisterTime("2017" + df.format(i));
			if(students !=null){
				
			}
			int num2 = studentMapper.searchByGraduateTimeNum("2017" + df.format(i));
			graduateNum += num2 + ",";
		}
		if(registerNum.endsWith(",")){
			registerNum = registerNum.substring(0, registerNum.length()-1);	
		}
		if(graduateNum.endsWith(",")){
			graduateNum = graduateNum.substring(0, graduateNum.length()-1);	
		}
		return "statisticsPage";
	}
	//搜索的方法
	public String search(){
		return "statisticsPage";
	}
	
	
	public SearchChart getSearchChart() {
		return searchChart;
	}

	public void setSearchChart(SearchChart searchChart) {
		this.searchChart = searchChart;
	}
	
	
}
