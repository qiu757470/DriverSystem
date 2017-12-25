package org.great.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.great.bean.ManagerJur;
import org.great.bean.ManagerJurTwo;
import org.great.bean.ManagerSearch;
import org.great.bean.School;
import org.great.bean.SchoolManager;
import org.great.mapper.ManagerJurMapper;
import org.great.mapper.ManagerJurTwoMapper;
import org.great.mapper.SchoolManagerMapper;
import org.great.mapper.SchoolMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
/** 
* @author ���ߣ���ΰ��
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ����Ӫƽ̨�Լ�У����ԱȨ�޷����Action
*/
@Controller
public class ManagerJurisdictionAction extends BaseAction {
	//��У����Ա���Mapper�ӿ�
	@Resource
	private SchoolManagerMapper managerMapper;
	//��У���Mapper�ӿ�
	@Resource
	private SchoolMapper schoolMapper;
	//��У����ԱȨ�ޱ��Mapper�ӿ�
	@Resource
	private ManagerJurMapper managerJurMapper;
	//��У����ԱȨ�޶������Mapper�ӿ�
	@Resource
	private ManagerJurTwoMapper managerJurTwoMapper;
	//ǰ�˽�����ʾ�ļ�У����Ա�б�
	private List<SchoolManager> managerUsers;
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
	private ManagerSearch searchUser;
	//�ж��Ƿ���ʾȫ��ȥ
	private String showAll;
	//����������Ϣ
	private String searchErrorMsg;
	//json��������
	private String jsonResult;
	//ajaxʡ��
	private String ajaxProvince;
	//ajax����
	private String ajaxCity;
	//�鿴Ȩ�޵��˺�
	private String managerAccount;
	//�鿴Ȩ�޵�Managerʵ��bean
	private SchoolManager manager;
	//��У����Ա������Ȩ��
	private List<String> managerJurisdictions;
	//ǰ�˴����Ķ���Ȩ����
	private String jurTwoNames;
	//ǰ�˴����ļ�У����Աid
	private String jurManagerId;
	//����ɹ���ʧ����Ϣ
	private String saveMsg;
	//�����޸�ƴ�ӵ��ַ���
	private String userAccounts;
	//��������
	private String orderType;
	
	//��ʾ�鿴Ȩ��ҳ��ķ���
	public String showJurisdictionDetailPage(){
		return getJurList("detail",managerAccount);
	}
	//��ʾ�޸�Ȩ��ҳ��ķ���
	public String showJurisdictionAlterPage(){
		return getJurList("alter",managerAccount);
	}
	//�õ�Ȩ���б�
	public String getJurList(String type, String account){
		if(managerAccount != null){
			manager = managerMapper.findByAccount(account);
			SchoolManager manager = managerMapper.findByAccount(account);
			List<ManagerJur> jurs = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
			if(jurs != null && jurs.size() > 0){
				managerJurisdictions = new ArrayList<String>();
				for(ManagerJur m : jurs){
					ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoId(m.getManager_jur_two_id());
					managerJurisdictions.add(jurTwo.getManager_jur_two_name());
				}
			}
			if(type != null && type.equals("alter")){
				return "jurisdictionAlterPage";
			}else if(type != null && type.equals("detail")){
				return "jurisdictionDetailPage";
			}
		}
		return SUCCESS;
	}
	//ͨ���˺��޸�Ȩ�޵ķ���
	public boolean alterSelectJur(String account){
		System.out.println("account:" + account);
		System.out.println("jurTwoNames:" + jurTwoNames);
		if(jurTwoNames != null && jurTwoNames != "" && account != null && account != ""){			
			SchoolManager manager = managerMapper.findByAccount(account);
			if(manager != null){
				if(jurTwoNames.equals("empty")){
					managerJurMapper.deleteByManagerId(manager.getSchool_manager_id());
					List<ManagerJur> lists = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
					if(lists == null || lists.size() == 0){
						return true;
					}
				}else{
					managerJurMapper.deleteByManagerId(manager.getSchool_manager_id());
					List<ManagerJur> lists = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
					if(lists == null || lists.size() == 0){
						if(jurTwoNames.contains(",")){
							String[] names = jurTwoNames.split(",");
							for(String n : names){
								ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoName(n);
								if(jurTwo != null){
									managerJurMapper.insertManagerJur(manager.getSchool_manager_id(), jurTwo.getManager_jur_two_id());
								}
							}
							List<ManagerJur> lists1 = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
							if(lists1 != null && lists1.size() == names.length){
								return true;
							}
						}else{
							ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoName(jurTwoNames);
							if(jurTwo != null){
								managerJurMapper.insertManagerJur(manager.getSchool_manager_id(), jurTwo.getManager_jur_two_id());
							}
							List<ManagerJur> lists1 = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
							if(lists1 != null && lists1.size() == 1){
								return true;
							}
						}
						
					}
				}
			}
		}
		return false;	
	}
	//�����޸�Ȩ�޵ķ���
	public String saveJurisdiction(){
		if(jurTwoNames != null && jurTwoNames != ""){
			try {
				jurTwoNames = java.net.URLDecoder.decode(jurTwoNames, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*System.out.println("jurTwoNames" + jurTwoNames);
			System.out.println("userAccounts" + userAccounts);
			System.out.println("jurManagerId" + jurManagerId);*/
			if(jurTwoNames.endsWith(",")){
				jurTwoNames = jurTwoNames.substring(0, jurTwoNames.length()-1);
			}
		}
		if(userAccounts != null && userAccounts != ""){
			String[] accs = userAccounts.split(",");
			int count = 0;
			for(String acc: accs){
				if(alterSelectJur(acc)){
					count++;
				}
			}
			if(count == accs.length){
				saveMsg = "����ɹ���";
				manager = managerMapper.findByAccount(accs[0]);
				List<ManagerJur> jurs = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
				if(jurs != null && jurs.size() > 0){
					managerJurisdictions = new ArrayList<String>();
					for(ManagerJur m : jurs){
						ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoId(m.getManager_jur_two_id());
						managerJurisdictions.add(jurTwo.getManager_jur_two_name());
					}
				}
				request.put("alterSelectSuccess", "true");
				return "jurisdictionAlterPage";
			}
			
		}else{
			if(jurTwoNames != null && jurTwoNames != "" && jurManagerId != null && jurManagerId != ""){
				manager = managerMapper.findById(jurManagerId);
				if(jurTwoNames.equals("empty")){
					managerJurMapper.deleteByManagerId(jurManagerId);
					List<ManagerJur> lists = managerJurMapper.findByManagerId(jurManagerId);
					if(lists == null || lists.size() == 0){
						saveMsg = "����ɹ���";
						List<ManagerJur> jurs = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
						if(jurs != null && jurs.size() > 0){
							managerJurisdictions = new ArrayList<String>();
							for(ManagerJur m : jurs){
								ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoId(m.getManager_jur_two_id());
								managerJurisdictions.add(jurTwo.getManager_jur_two_name());
							}
						}
						return "jurisdictionAlterPage";
					}
				}else{
					managerJurMapper.deleteByManagerId(jurManagerId);
					List<ManagerJur> lists = managerJurMapper.findByManagerId(jurManagerId);
					if(lists == null || lists.size() == 0){
						if(jurTwoNames.contains(",")){
							String[] names = jurTwoNames.split(",");
							for(String n : names){
								ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoName(n);
								//System.out.println(jurTwo);
								if(jurTwo != null){
									managerJurMapper.insertManagerJur(jurManagerId, jurTwo.getManager_jur_two_id());
								}
							}
							List<ManagerJur> lists1 = managerJurMapper.findByManagerId(jurManagerId);
							//System.out.println(lists1.size() + "||" + names.length);
							if(lists1 != null && lists1.size() == names.length){
								saveMsg = "����ɹ���";
								List<ManagerJur> jurs = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
								if(jurs != null && jurs.size() > 0){
									managerJurisdictions = new ArrayList<String>();
									for(ManagerJur m : jurs){
										ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoId(m.getManager_jur_two_id());
										managerJurisdictions.add(jurTwo.getManager_jur_two_name());
									}
								}
								return "jurisdictionAlterPage";
							}
						}else{
							ManagerJurTwo jurTwo = managerJurTwoMapper.findByJurTwoName(jurTwoNames);
							if(jurTwo != null){
								managerJurMapper.insertManagerJur(jurManagerId, jurTwo.getManager_jur_two_id());
							}
							List<ManagerJur> lists1 = managerJurMapper.findByManagerId(jurManagerId);
							if(lists1 != null && lists1.size() == 1){
								saveMsg = "����ɹ���";
								List<ManagerJur> jurs = managerJurMapper.findByManagerId(manager.getSchool_manager_id());
								if(jurs != null && jurs.size() > 0){
									managerJurisdictions = new ArrayList<String>();
									for(ManagerJur m : jurs){
										ManagerJurTwo jur = managerJurTwoMapper.findByJurTwoId(m.getManager_jur_two_id());
										managerJurisdictions.add(jur.getManager_jur_two_name());
									}
								}
								return "jurisdictionAlterPage";
							}
						}
						
						
					}
				}
			}
		}
		saveMsg = "����ʧ�ܣ�";
		return "jurisdictionAlterPage";
	}
	//��ʾȨ�޹���ҳ�淽��
		public String showPage(){
			if(orderType != null && orderType != ""){
				session.put("orderType", orderType);
			}else if(orderType != null && orderType != "" && orderType.equals("defaultOrder")){
				session.put("orderType", null);	
			}
			if(session.get("orderSeq") == null){
				session.put("orderSeq", "DESC");
			}
			if(showAll != null && showAll.equals("true")){
				searchUser = null;
				session.put("backSearchUser", null);
			}
			ManagerSearch backSearchUser = (ManagerSearch) session.get("backSearchUser");
			if(backSearchUser != null){
				searchUser = backSearchUser;
				return searchUser();
			}
			searchUser = null;
			session.put("backSearchUser", null);
			int totalNum = 0;
			String numStr = managerMapper.getTotalNum();
			if(numStr != null){
				totalNum = Integer.parseInt(numStr);
			}
			recordNum = totalNum;
			setManagerPage(totalNum);
			@SuppressWarnings("unchecked")
			List<School> schools = (List<School>) schoolMapper.findAll();
			if(schools == null || schools.size() == 0){
				request.put("emptySchool", "true");
				return SUCCESS;
			}
			try {
				sqlSession = sqlSessionFactory.getObject().openSession();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String orderType = (String) session.get("orderType");
			if(orderType != null && orderType != "" && !orderType.equals("defaultOrder")){
				String orderSeq = (String) session.get("orderSeq");
				if(orderSeq.equals("DESC")){
					orderSeq = "ASC";
				}else if(orderSeq.equals("ASC")){
					orderSeq = "DESC";
				}
				session.put("orderSeq", orderSeq);
				//System.out.println("findAll:" + orderSeq);
				Map<String, String> map = new HashMap<String, String>();
		        map.put("orderType", orderType);
		        map.put("orderSeq", orderSeq);
				managerUsers = sqlSession.selectList("findAllManagers", map, new RowBounds((nowPage-1)*10, 10));
			}else{
				managerUsers = sqlSession.selectList("findAllManagers", null, new RowBounds((nowPage-1)*10, 10));
			}
			if(managerUsers == null || managerUsers.size() == 0){
				request.put("emptyUser", "true");
			}
			String numStr1 = managerMapper.getTotalNum();
			if(numStr1 != null){
				recordNum = Integer.parseInt(numStr1);
			}
			sqlSession.commit();
			sqlSession.clearCache();
			sqlSession.close();
			return SUCCESS;
		}
	//�����ҳ������
	public int getManagerPage(int totalNum){
		/*int totalNum = 0;
		String numStr = managerMapper.getTotalNum();
		if(numStr != null){
			totalNum = Integer.parseInt(numStr);
		}*/
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
	public void setManagerPage(int totalNum){
		allPage = getManagerPage(totalNum);
		if(nowPage < 1){
			nowPage = 1;
		}
		if(nowPage > allPage){
			nowPage = allPage;
		}
	}
	
	//�����û��ķ���
	public String searchUser(){
		showAll = null;
		if(searchUser != null){
			String orderType = (String) session.get("orderType");
			if(orderType != null && !orderType.equals("defaultOrder")){
				searchUser.setOrderType(orderType);
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
				searchUser.setOrderSeq(orderSeq);
				//System.out.println(orderSeq);
			}
			try {
				sqlSession = sqlSessionFactory.getObject().openSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String, String> map = new HashMap<String, String>();
	        map.put("school_manager_account", searchUser.getSchool_manager_account());
	        map.put("school_province", searchUser.getSchool_province());
	        map.put("school_city", searchUser.getSchool_city());
	        map.put("school_name", searchUser.getSchool_name());
	        map.put("school_manager_state", searchUser.getSchool_manager_state());
	        map.put("orderType", searchUser.getOrderType());
	        map.put("orderSeq", searchUser.getOrderSeq());
			managerUsers = sqlSession.selectList("searchManager", map, new RowBounds((nowPage-1)*10, 10));
			sqlSession.commit();
			sqlSession.clearCache();
			sqlSession.close();
			recordNum = managerUsers.size();
			setManagerPage(managerUsers.size());
			//managerUsers = managerMapper.searchManager(searchUser);
			if(managerUsers != null && managerUsers.size() > 0){
				/*int totalNum = managerUsers.size();
				recordNum = totalNum;
				if(totalNum % 10 == 0){
					allPage = totalNum / 10;
				}else{
					allPage = totalNum / 10 + 1;
				}
				if(managerUsers.size() > 10){
					if(nowPage > allPage || nowPage <= 1){
						nowPage = 1;
						List<SchoolManager> temp = new ArrayList<SchoolManager>();
						for(int i=0; i<10; i++){
							temp.add(managerUsers.get(i));
						}
						managerUsers = temp;
					}else{
						List<SchoolManager> temp = new ArrayList<SchoolManager>();
						int maxNum = 10*nowPage;
						if(maxNum > totalNum){
							maxNum = totalNum;
						}
						for(int i=0+10*(nowPage-1); i<maxNum; i++){
							temp.add(managerUsers.get(i));
						}
						managerUsers = temp;
					}
				}else{
					nowPage = 1;
				}*/
				session.put("backSearchUser", searchUser);
				searchErrorMsg = null;
			}else{
				searchErrorMsg = "�ܱ�Ǹ���������ݣ������²�ѯ��";
				showAll = "true";
				return showPage();
			}
		}
		return SUCCESS;
	}
	//�����޸ĵķ���
	public String alterJurs(){
		if(userAccounts != null && userAccounts != ""){
			request.put("userAccounts", userAccounts);
		}
		return "jurisdictionAlterPage";
	}
	//ajax�л���У�ķ���
	public String changeSchool(){
		List<School> schools = schoolMapper.findByProvinceAndCity(ajaxProvince, ajaxCity);
		if(schools != null && schools.size() > 0){
			ArrayList<String> schoolNames = new ArrayList<String>();
			for(School s :schools){
				schoolNames.add(s.getSchool_name());
			}
			Gson gson = new Gson();
			jsonResult = gson.toJson(schoolNames);
		}
		return SUCCESS;
	}
	
	
	//setter and getter
	public List<SchoolManager> getManagerUsers() {
		return managerUsers;
	}
	public void setManagerUsers(List<SchoolManager> managerUsers) {
		this.managerUsers = managerUsers;
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
	public ManagerSearch getSearchUser() {
		return searchUser;
	}
	public void setSearchUser(ManagerSearch searchUser) {
		this.searchUser = searchUser;
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
	public String getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	public SchoolMapper getSchoolMapper() {
		return schoolMapper;
	}
	public void setSchoolMapper(SchoolMapper schoolMapper) {
		this.schoolMapper = schoolMapper;
	}
	public String getAjaxProvince() {
		return ajaxProvince;
	}
	public void setAjaxProvince(String ajaxProvince) {
		this.ajaxProvince = ajaxProvince;
	}
	public String getAjaxCity() {
		return ajaxCity;
	}
	public void setAjaxCity(String ajaxCity) {
		this.ajaxCity = ajaxCity;
	}
	public int getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public SchoolManager getManager() {
		return manager;
	}
	public void setManager(SchoolManager manager) {
		this.manager = manager;
	}
	public List<String> getManagerJurisdictions() {
		return managerJurisdictions;
	}
	public void setManagerJurisdictions(List<String> managerJurisdictions) {
		this.managerJurisdictions = managerJurisdictions;
	}
	public String getJurTwoNames() {
		return jurTwoNames;
	}
	public void setJurTwoNames(String jurTwoNames) {
		this.jurTwoNames = jurTwoNames;
	}
	public String getJurManagerId() {
		return jurManagerId;
	}
	public void setJurManagerId(String jurManagerId) {
		this.jurManagerId = jurManagerId;
	}
	public String getSaveMsg() {
		return saveMsg;
	}
	public void setSaveMsg(String saveMsg) {
		this.saveMsg = saveMsg;
	}
	public String getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(String userAccounts) {
		this.userAccounts = userAccounts;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
	
}
