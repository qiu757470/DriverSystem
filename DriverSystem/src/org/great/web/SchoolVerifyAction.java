package org.great.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.great.bean.School;
import org.great.bean.SchoolSearch;
import org.great.mapper.ExamPlanMapper;
import org.great.mapper.MailMapper;
import org.great.mapper.ManagerJurMapper;
import org.great.mapper.SchoolEvaluationMapper;
import org.great.mapper.SchoolManagerMapper;
import org.great.mapper.SchoolMapper;
import org.great.mapper.SchoolNoticeMapper;
import org.great.mapper.StuExamPlanMapper;
import org.great.mapper.StudentCourseMapper;
import org.great.mapper.StudentMapper;
import org.great.mapper.StudentNoticeMapper;
import org.great.mapper.TeacherEvaluationMapper;
import org.great.mapper.TeacherMapper;
import org.great.mapper.TransNoticeMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月22日 下午11:36:30 
* 类说明 
*/

@Controller
public class SchoolVerifyAction extends BaseAction {


	//每页有多少条
	private final int PAGENUM = 10;
	//驾校表的Mapper接口
	@Resource
	private SchoolMapper schoolMapper;
	//前端界面显示驾校列表
	private List<School> schoolList;
	//SqlSession工厂，用来创建连接，实现分页操作
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	//数据库连接
	private SqlSession sqlSession;
	//总页数
	private int allPage;
	//当前页数
	private int nowPage;
	//总记录条数
	private int recordNum;
	//前端传来的搜索数据
	private SchoolSearch searchSchool;
	//判断是否显示全部去
	private String showAll;
	//搜索错误信息
	private String searchErrorMsg;
	//前端传来的驾校id
	private String schoolId;
	//前端传来的驾校id集合
	private String schoolIds;
	//排序的类型
	private String orderType;
	//审核界面详情的驾校
	private School school;
	//前端传来的修改驾校
	private School alterSchool;
		
		
		
	//显示用户管理页面方法
	public String showPage(){
		if(orderType != null && orderType != ""){
			session.put("schoolOrderType", orderType);
		}else if(orderType != null && orderType != ""){
			session.put("schoolOrderType", null);	
		}
		if(session.get("orderSeq") == null){
			session.put("orderSeq", "DESC");
		}
		if(showAll != null && showAll.equals("true")){
			searchSchool = null;
			session.put("backSearchSchool", null);
		}
		SchoolSearch backSearchSchool = (SchoolSearch) session.get("backSearchSchool");
		if(backSearchSchool != null){
			searchSchool = backSearchSchool;
			return searchSchool();
		}
		searchSchool = null;
		session.put("backSearchUser", null);
		int totalNum = 0;
		String numStr = schoolMapper.getTotalNumUnverify();
		if(numStr != null){
			totalNum = Integer.parseInt(numStr);
		}
		setSchoolPage(totalNum);
		@SuppressWarnings("unchecked")
		List<School> schools = (List<School>) schoolMapper.findAll();
		if(schools == null || schools.size() == 0){
			request.put("emptySchool", "true");
			return SUCCESS;
		}
		try {
			sqlSession = sqlSessionFactory.getObject().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String orderType = (String) session.get("schoolOrderType");
		if(orderType != null && orderType != ""){
			String orderSeq = (String) session.get("orderSeq");
			if(orderSeq.equals("DESC")){
				orderSeq = "ASC";
			}else if(orderSeq.equals("ASC")){
				orderSeq = "DESC";
			}
			session.put("orderSeq", orderSeq);
			Map<String, String> map = new HashMap<String, String>();
	        map.put("schoolOrderType", orderType);
	        map.put("orderSeq", orderSeq);
			schoolList = sqlSession.selectList("findAllSchoolsUnverify", map, new RowBounds((nowPage-1)*10, 10));
		}else{
			schoolList = sqlSession.selectList("findAllSchoolsUnverify", null, new RowBounds((nowPage-1)*10, 10));
		}
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
		if(schoolList == null || schoolList.size() == 0){
			request.put("noUnverifySchool", "true");
		}
		String numStr1 = schoolMapper.getTotalNumUnverify();
		if(numStr1 != null){
			recordNum = Integer.parseInt(numStr1);
		}
		sqlSession.close();
		return SUCCESS;
	}
	
	//搜索用户的方法
	public String searchSchool(){
		showAll = null;
		if(searchSchool != null){
			String orderType = (String) session.get("schoolOrderType");
			if(orderType != null){
				searchSchool.setSchoolOrderType(orderType);
				String orderSeq = (String) session.get("orderSeq");
				if(orderSeq.equals("DESC")){
					orderSeq = "ASC";
				}else if(orderSeq.equals("ASC")){
					orderSeq = "DESC";
				}
				if(orderSeq == null || orderSeq == ""){
					orderSeq = "ASC";
				}
				session.put("orderSeq", orderSeq);
				searchSchool.setOrderSeq(orderSeq);
				//System.out.println(orderSeq);
			}
			try {
				sqlSession = sqlSessionFactory.getObject().openSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String, String> map = new HashMap<String, String>();
	        map.put("school_name", searchSchool.getSchool_name());
	        map.put("school_province", searchSchool.getSchool_province());
	        map.put("school_city", searchSchool.getSchool_city());
	        map.put("school_state", searchSchool.getSchool_state());
	        map.put("schoolOrderType", searchSchool.getSchoolOrderType());
	        map.put("orderSeq", searchSchool.getOrderSeq());
			schoolList = sqlSession.selectList("searchSchoolUnverify", map, new RowBounds((nowPage-1)*10, 10));
			sqlSession.commit();
			sqlSession.clearCache();
			sqlSession.close();
			setSchoolPage(schoolList.size());
			if(schoolList != null && schoolList.size() > 0){
				session.put("backSearchSchool", searchSchool);
				searchErrorMsg = null;
			}else{
				searchErrorMsg = "很抱歉，查无数据，请重新查询。";
				showAll = "true";
				return showPage();
			}
		}
		return SUCCESS;
	}
		
	//获得总页数方法
	public int getSchoolPage(int totalNum){
		/*int totalNum = 0;
		String numStr = schoolMapper.getTotalNum();
		if(numStr != null){
			totalNum = Integer.parseInt(numStr);
		}*/
		recordNum = totalNum;
		int page = 0;
		if(totalNum == 0){
			return page;
		}
		if(totalNum % 10 == 0){
			page = totalNum / 10;
		}else{
			page = totalNum / 10 + 1;
		}
		return page;
	}
		
	//设置总页数方法
	public void setSchoolPage(int totalNum){
		allPage = getSchoolPage(totalNum);
		if(nowPage < 1){
			nowPage = 1;
		}
		if(nowPage > allPage){
			nowPage = allPage;
		}
	}
	
	//审核驾校详情界面
	public String verifySchoolDetailPage(){
		if(schoolId != null){
			school = schoolMapper.findById(schoolId);
			school = setSchoolData(school);
			if(school != null){
				return "verifySchoolDetailPage";
			}
		}
		request.put("emptyDetail", "true");
		return showPage();
	}
	//提交审核的方法
	public String verifySchool(){
		if(alterSchool != null){
			int count = schoolMapper.alterStateAndReason(alterSchool.getSchool_id(), alterSchool.getSchool_state(), alterSchool.getSchool_refuse_reason());
			if(count > 0){
				request.put("updateSuccess", "true");
				schoolId = alterSchool.getSchool_id();
				return verifySchoolDetailPage();
			}
		}
		request.put("updateSuccess", "false");
		schoolId = alterSchool.getSchool_id();
		return verifySchoolDetailPage();
	}
	
	
	//清除null数据
	public School setSchoolData(School school){
		if(school.getSchool_name() == null){
			school.setSchool_name("");
		}
		if(school.getSchool_representative_name() == null){
			school.setSchool_representative_name("");
		}
		if(school.getSchool_representative_id() == null){
			school.setSchool_representative_id("");
		}
		if(school.getSchool_representative_phone() == null){
			school.setSchool_representative_phone("");
		}
		if(school.getSchool_province() == null){
			school.setSchool_province("");
		}
		if(school.getSchool_city() == null){
			school.setSchool_city("");
		}
		if(school.getSchool_address() == null){
			school.setSchool_address("");
		}
		if(school.getSchool_phone() == null){
			school.setSchool_phone("");
		}
		if(school.getSchool_certificate() == null){
			school.setSchool_certificate("");
		}
		if(school.getSchool_code() == null){
			school.setSchool_code("");
		}
		if(school.getSchool_state() == null){
			school.setSchool_state("");
		}
		if(school.getSchool_picture_url() == null){
			school.setSchool_picture_url("");
		}
		if(school.getSchool_homepage() == null){
			school.setSchool_homepage("");
		}
		if(school.getSchool_refuse_reason() == null){
			school.setSchool_refuse_reason("");
		}
		if(school.getSchool_file_url() == null){
			school.setSchool_file_url("");
		}
		if(school.getSchool_file_url().contains("\\")){
			school.setSchool_file_url(school.getSchool_file_url().replace("\\", "/"));
		}
		if(school.getSchool_charge() == null){
			school.setSchool_charge("");
		}
		return school;
	}
	
	
	
	public List<School> getSchoolList() {
		return schoolList;
	}
	public void setSchoolList(List<School> schoolList) {
		this.schoolList = schoolList;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
	public SchoolSearch getSearchSchool() {
		return searchSchool;
	}
	public void setSearchSchool(SchoolSearch searchSchool) {
		this.searchSchool = searchSchool;
	}
	public String getShowAll() {
		return showAll;
	}
	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}
	public String getSearchErrorMsg() {
		return searchErrorMsg;
	}
	public void setSearchErrorMsg(String searchErrorMsg) {
		this.searchErrorMsg = searchErrorMsg;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolIds() {
		return schoolIds;
	}
	public void setSchoolIds(String schoolIds) {
		this.schoolIds = schoolIds;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public School getAlterSchool() {
		return alterSchool;
	}
	public void setAlterSchool(School alterSchool) {
		this.alterSchool = alterSchool;
	}
	
	
	
	
	
	
}
