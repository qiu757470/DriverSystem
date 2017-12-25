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
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��22�� ����11:36:30 
* ��˵�� 
*/

@Controller
public class SchoolVerifyAction extends BaseAction {


	//ÿҳ�ж�����
	private final int PAGENUM = 10;
	//��У���Mapper�ӿ�
	@Resource
	private SchoolMapper schoolMapper;
	//ǰ�˽�����ʾ��У�б�
	private List<School> schoolList;
	//SqlSession�����������������ӣ�ʵ�ַ�ҳ����
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	//���ݿ�����
	private SqlSession sqlSession;
	//��ҳ��
	private int allPage;
	//��ǰҳ��
	private int nowPage;
	//�ܼ�¼����
	private int recordNum;
	//ǰ�˴�������������
	private SchoolSearch searchSchool;
	//�ж��Ƿ���ʾȫ��ȥ
	private String showAll;
	//����������Ϣ
	private String searchErrorMsg;
	//ǰ�˴����ļ�Уid
	private String schoolId;
	//ǰ�˴����ļ�Уid����
	private String schoolIds;
	//���������
	private String orderType;
	//��˽�������ļ�У
	private School school;
	//ǰ�˴������޸ļ�У
	private School alterSchool;
		
		
		
	//��ʾ�û�����ҳ�淽��
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
	
	//�����û��ķ���
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
				searchErrorMsg = "�ܱ�Ǹ���������ݣ������²�ѯ��";
				showAll = "true";
				return showPage();
			}
		}
		return SUCCESS;
	}
		
	//�����ҳ������
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
		
	//������ҳ������
	public void setSchoolPage(int totalNum){
		allPage = getSchoolPage(totalNum);
		if(nowPage < 1){
			nowPage = 1;
		}
		if(nowPage > allPage){
			nowPage = allPage;
		}
	}
	
	//��˼�У�������
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
	//�ύ��˵ķ���
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
	
	
	//���null����
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
