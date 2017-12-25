package org.great.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.great.bean.ManagerJur;
import org.great.bean.ManagerSearch;
import org.great.bean.School;
import org.great.bean.SchoolManager;
import org.great.bean.TransNotice;
import org.great.bean.TransUser;
import org.great.mapper.ManagerJurMapper;
import org.great.mapper.SchoolManagerMapper;
import org.great.mapper.SchoolMapper;
import org.great.mapper.TransNoticeMapper;
import org.great.tools.FileUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��19�� ����12:45:57 
* ��˵�� 
*/
public class ManagerManagementAction extends BaseAction {

	//ÿҳ�ж�����
	private final int PAGENUM = 10;
	//��У����Ա���Mapper�ӿ�
	@Resource
	private SchoolManagerMapper managerMapper;
	//��У���Mapper�ӿ�
	@Resource
	private SchoolMapper schoolMapper;
	//��УȨ�ޱ��Mapper�ӿ�
	@Resource
	private ManagerJurMapper managerJurMapper;
	//������Mapper�ӿ�
	@Resource
	private TransNoticeMapper transNoticeMapper;
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
	//ǰ�˴����Ĺ���Աid��һ����
	private String managerId;
	//ǰ�˴����Ĺ���Աid�������
	private String managerIds;
	//ajax����˺��Ƿ���ڵ��˺�
	private String managerAccount;
	//�����û�������
	private SchoolManager createUser;
	//�޸��û���������ʾ������
	private SchoolManager manager;
	
	//�ϴ����ļ�����������ʱ�ļ���
	private File uploadFile;
    //ǰ�˴������ļ���
    private String filePath;
    //�ϴ��ļ���ŵ�·��
    private String uploadPath;
    //�û��ļ���
    private String userPath;
    //��ѹ�ļ���ŵ�·��
    private String decomPath;
    //web·��
    private String webPath;
    //��ʱ·��
	private String tempPath;
	//�ļ�����
	private String filePwd;
	//���������ĵ�ǰҳ
	private int batchNowPage;
	//ѡ�����������������˺�
	private String createAccounts;
	//ѹ���ļ�
	private ZipFile zipFile;
	//���������û������˺�
	private String createAccount;
	//���������
	private String orderType;

	//��ʾ�û�����ҳ�淽��
	public String showPage(){
		if(orderType != null && orderType != ""){
			session.put("orderType", orderType);
		}else if(orderType != null && orderType != ""){
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
		if(orderType != null && orderType != ""){
			String orderSeq = (String) session.get("orderSeq");
			if(orderSeq.equals("DESC")){
				orderSeq = "ASC";
			}else if(orderSeq.equals("ASC")){
				orderSeq = "DESC";
			}
			session.put("orderSeq", orderSeq);
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
	//ajax����û��˺��Ƿ��Ѵ���
	public String checkManagerAccout(){
		if(managerAccount != null && managerAccount != ""){
			SchoolManager manager = managerMapper.findByAccount(managerAccount);
			String data = "";
			if(manager != null){
				data = "exist";
			}else{
				data = "notExist";
			}
			Gson gson = new Gson();
			jsonResult = gson.toJson(data);
		}
		return SUCCESS;
		
	}
	//��ʾ�����û��Ľ���
	public String createManagerPage(){
		return "createManagerPage";
	}
	//�����û��ķ���
	public String createManager(){
		if(createUser != null){
			School school = schoolMapper.findByProvinceAndCityAndName(createUser.getSchool().getSchool_province(), createUser.getSchool().getSchool_city(), createUser.getSchool().getSchool_name());
			if(school != null){
				createUser.setSchool_id(school.getSchool_id());
			}else{
				request.put("createSuccess", "false");
				return "createManagerPage";
			}
			managerMapper.addOneByBean(createUser);
			if(managerMapper.findByAccount(createUser.getSchool_manager_account()) != null){
				request.put("createSuccess", "true");
			}else{
				request.put("createSuccess", "false");
			}
		}
		return "createManagerPage";
	}
	//���������û�������
	public String createManagerDetail(){
		if(createAccount != null && createAccount != ""){
			@SuppressWarnings("unchecked")
			ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
			SchoolManager createManager = null;
			for(SchoolManager m : createManagers){
				if(m.getSchool_manager_account().equals(createAccount)){
					createManager = m;
					break;
				}
			}
			if(createManager != null){
				request.put("createManager", createManager);
				return "createManagerDetailPage";
			}
		}
		request.put("noDetail", "true");
		return "createManagerBatchPage";
		
	}
	//��ʾ���������û��Ľ���
	public String createBatchPage(){
		return "createManagerBatchPage";
	}
	//�ϴ��ļ����ύ�ķ���
	public String uploadFile(){
		/*System.out.println(uploadFile);
		System.out.println(filePath);*/
		//���request����
		HttpServletRequest req = ServletActionContext.getRequest();
		webPath = req.getServletContext().getRealPath("/");
		String acc = session.get("loginTransUser") != null ? ((TransUser)session.get("loginTransUser")).getTrans_user_account() : null;
		if(acc == null){
			request.put("wrongLogin", "true");
			return "createManagerBatchPage";
		}
		
		userPath = webPath + "\\transUsers\\" + acc;
		uploadPath = userPath + "\\upload\\manager";
		decomPath = userPath + "\\decompression\\manager";
		tempPath = userPath + "\\temp\\manager";
		File uploadDir = new File(uploadPath);
		File decomDir = new File(decomPath);
		File tempDir = new File(tempPath);
		uploadDir.mkdirs();
		decomDir.mkdirs();
		tempDir.mkdirs();
		//���ù������ϴ��ļ����ϴ��ļ���·��
		/*System.out.println(webPath);
		System.out.println(uploadPath);
		System.out.println(uploadFile);*/
		if(filePwd != null && filePwd != ""){
			System.out.println("filePwd" + filePwd);
			File uploadSuccessFile = (File) session.get("uploadSuccessFile");
			ZipFile zFile = null;
			try {
				zFile = new ZipFile(uploadSuccessFile);
			} catch (ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(FileUtil.decodeFile(uploadSuccessFile, zFile, filePwd, decomPath)){
				//��ѹ�ļ����ļ�
				File decomFile = new File(decomPath);
				//�������϶���
				ArrayList<SchoolManager> createManagers = new ArrayList<SchoolManager>();
				session.put("createManagers", createManagers);
				//����excel�ļ�
				readExcelFile(decomFile);
				session.put("zipFile", null);
				session.put("uploadFile", null);
				if(setNewManagerData()){
					request.put("uploadSuccess", "true");
				}else{
					request.put("uploadSuccess", "false");
				}
			}else{
				System.out.println("����ʧ�ܣ�");
				request.put("uploadSuccess", "false");
			}
			return "createManagerBatchPage";
		}
		File uploadSuccessFile = new File(uploadPath, filePath);
		boolean isUpload = FileUtil.upload(filePath, uploadFile, uploadSuccessFile);
		if(isUpload){
			session.put("uploadSuccessFile", uploadSuccessFile);
			System.out.println("�ϴ��ɹ����ļ�·����" + uploadPath);
			if(filePath.endsWith("zip")){
				try {
					zipFile = new ZipFile(uploadFile);
					//����ļ��Ǽ��ܵ�
					if (zipFile .isEncrypted()) {
						//���û��������룬��ת�����Թ������
						request.put("inputFilePwd", "true");
						return "createManagerBatchPage";
					}else{
						//��ѹ�ļ����ļ�
						File decomFile = new File(decomPath);
						//��ѹȫ��
						zipFile.extractAll(decomPath);
						//�������϶���
						ArrayList<SchoolManager> createManagers = new ArrayList<SchoolManager>();
						session.put("createManagers", createManagers);
						//����excel�ļ�
						readExcelFile(decomFile);
						if(setNewManagerData()){
							request.put("uploadSuccess", "true");
						}else{
							request.put("uploadSuccess", "false");
						}
					}
				} catch (ZipException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(filePath.endsWith("xls") || filePath.endsWith("xlsx")){
				//�������϶���
				ArrayList<SchoolManager> createManagers = new ArrayList<SchoolManager>();
				session.put("createManagers", createManagers);
				//����excel�ļ�
				readExcelFile(new File(uploadPath));
				if(setNewManagerData()){
					request.put("uploadSuccess", "true");
				}else{
					request.put("uploadSuccess", "false");
				}
			}else{	
				System.out.println("wrong file type!");
				request.put("uploadSuccess", "false");
			}
		}else{
			System.out.println("�ϴ�ʧ�ܣ�");
			request.put("uploadSuccess", "false");
		}
		return "createManagerBatchPage";
	}
	//�������������û�������
	public boolean setNewManagerData(){
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
		if(createManagers.size() > 0){
			int createNowPage = 1;//��ǰҳ��
			int totalNum = createManagers.size();
			int createTotalNum = totalNum;//���û���
			int pageNum = 1;
			if(createTotalNum > PAGENUM){
				if(createTotalNum % PAGENUM == 0){
					pageNum = createTotalNum / PAGENUM;
				}else{
					pageNum = createTotalNum / PAGENUM + 1;
				}
				ArrayList<SchoolManager> newManagers = new ArrayList<SchoolManager>();
				for(int i=0; i<PAGENUM; i++){
					newManagers.add(createManagers.get(i));
				}
				session.put("newManagers", newManagers);
			}
			int createPageNum = pageNum;//��ҳ��
			session.put("createNowPage", createNowPage);
			session.put("createTotalNum", createTotalNum);
			session.put("createPageNum", createPageNum);
			return true;
		}else{
			System.out.println("�ϴ�ʧ�ܣ�");
			return false;
		}
	}
	//���������ķ�ҳ��ʾ����
	public String showCreatePage(){
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> newManagers = (ArrayList<SchoolManager>) session.get("newManagers");
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
		int createTotalNum = (Integer) session.get("createTotalNum");
		if(createManagers != null){
			createTotalNum = createManagers.size();
			int maxSize = batchNowPage * PAGENUM;
			if(maxSize > createTotalNum){
				maxSize = createTotalNum;
			}
			newManagers = new ArrayList<SchoolManager>();
			for(int i=0 + (batchNowPage - 1)* PAGENUM; i < maxSize; i++){
				newManagers.add(createManagers.get(i));
			}
			session.put("newManagers", newManagers);
			session.put("createNowPage", batchNowPage);
			
		}
		return "createManagerBatchPage";
		
	}
	//ȫ�������ķ���
	public String createAll(){
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
		for(SchoolManager m : createManagers){
			managerMapper.addOneByBean(m);
		}
		if(managerMapper.findByAccount(createManagers.get(0).getSchool_manager_account()) != null){
			//���õ�����ɾ��
			session.put("createManagers", null);
			session.put("newManagers", null);
			session.put("createNowPage", null);
			session.put("createPageNum", null);
			session.put("createTotalNum", null);
			request.put("createSuccess", "true");
		}else{
			request.put("createSuccess", "false");
		}
		return "createManagerBatchPage";
	}
	//ѡ���˺����������ķ���
	public String createSelect(){
		System.out.println("createAccounts" + createAccounts);
		if(createAccounts != null && createAccounts != ""){
//			System.out.println("createAccounts" + createAccounts);
			String[] accs = createAccounts.split(",");
			@SuppressWarnings("unchecked")
			ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
			ArrayList<SchoolManager> temp = new ArrayList<SchoolManager>();
			int i=0;
			for(SchoolManager m : createManagers){
				if(m.getSchool_manager_account().equals(accs[i])){
					temp.add(m);
				}
				i++;
				if(i == accs.length){
					break;
				}
			}
			for(SchoolManager m : temp){
				managerMapper.addOneByBean(m);
			}
			if(managerMapper.findByAccount(accs[0]) != null){
				System.out.println("�����ɹ���");
				//���õ�����ɾ��
				Iterator<SchoolManager> it = createManagers.iterator();
				int j = 0;
		        while(it.hasNext()){  
		        	SchoolManager item = it.next();  
		            if (item.getSchool_manager_account().equals(temp.get(j).getSchool_manager_account())){  
		                it.remove();
		            }
		            j++;
		            if(j == temp.size()){
		            	break;
		            }
		            session.put("createManagers", createManagers);
		            setNewManagerData();
		        } 
				request.put("createSuccess", "true");
			}else{
				System.out.println("����ʧ�ܣ�");
				request.put("createSuccess", "false");
			}
		}
		return "createManagerBatchPage";
		
	}
	//����excel�ļ�����
	public void readExcelFile(File file) {
		Workbook readwb = null;
		//������ļ��У��������ļ��е��µ������ļ������ø÷���
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f: files){
				readExcelFile(f);
			}
		}else{
			//�����excel���
			if(file.getName().endsWith("xls") || file.getName().endsWith("xlsx")){
				readExcel(readwb, file);
			}
		}
		return;
	}
	public void readExcel(Workbook readwb, File file){
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
		InputStream instream = null;
		try {
			instream = new FileInputStream(file);
			readwb = Workbook.getWorkbook(instream);
			Sheet readsheet = readwb.getSheet(0);
			int rsColumns = readsheet.getColumns();
			int rsRows = readsheet.getRows();
			/*System.out.println("col:"+rsColumns);
			System.out.println("row:" + rsRows);*/
			//����ѧ��������application����
			application.put("recordNum", rsRows-1);
			ArrayList<SchoolManager> managers = null;
			if(rsRows > 1){
				managers = new ArrayList<SchoolManager>();
				for(int i=1; i<rsRows; i++){
					SchoolManager bean = new SchoolManager();
					School school = new School();
					for(int j=0; j<rsColumns; j++){
						Cell cell = readsheet.getCell(j, i);
						String content = cell.getContents();
						//System.out.println(content);
						if(j == 0){
							bean.setSchool_manager_account(content);
						}else if(j == 1){
							bean.setSchool_manager_password(content);
						}else if(j == 2){
							bean.setSchool_manager_name(content);
						}else if(j == 3){
							bean.setSchool_manager_identification(content);
						}else if(j == 4){
							bean.setSchool_manager_sex(content);
						}else if(j == 5){
							bean.setSchool_manager_province(content);
						}else if(j == 6){
							bean.setSchool_manager_city(content);
						}else if(j == 7){
							bean.setSchool_manager_address(content);
						}else if(j == 8){
							bean.setSchool_manager_phone(content);
						}else if(j == 9){
							bean.setSchool_manager_state(content);
						}else if(j == 10){
							school.setSchool_province(content);
						}else if(j == 11){
							school.setSchool_city(content);
						}else if(j == 12){
							school.setSchool_name(content);
						}
					}
					//System.out.println("bean:" + bean);
					//���·����Ƿ�ֹ���ݿⲻ���ڵļ�У���Ѵ��ڵ��˺ż������
					School s  = schoolMapper.findByProvinceAndCityAndName(school.getSchool_province(), school.getSchool_city(), school.getSchool_name());	
					SchoolManager m = managerMapper.findByAccount(bean.getSchool_manager_account());
					if(m != null){
						bean.setSchool_manager_account(null);
					}
					if(s != null){
						bean.setSchool(s);
						bean.setSchool_id(s.getSchool_id());
					}else{
						bean.setSchool(null);
					}
					if(bean.getSchool() != null && bean.getSchool_manager_account() != null){
						managers.add(bean);
					}
				}
			}
			//System.out.println(managers.size());
			if(managers != null && managers.size() > 0){
				for(SchoolManager s : managers){
					//System.out.println(s);
					createManagers.add(s);
				}
				session.put("createManagers", createManagers);
				session.put("uploadSuccessFile", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				instream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//����idɾ��һ���û��ķ���
	public boolean deleteOne(String id){
		if(id != null && id != ""){
			transNoticeMapper.deleteByManagerId(id);
			managerJurMapper.deleteByManagerId(id);
			managerMapper.deleteOneById(id);
		}
		List<ManagerJur> jurs = managerJurMapper.findByManagerId(id);
		List<TransNotice> notices = transNoticeMapper.findByManagerId(id);
		SchoolManager manager = managerMapper.findById(id);
		if((jurs == null || jurs.size() == 0) && (notices == null || notices.size() == 0) && manager == null){
			return true;
		}else{
			return false;
		}
	}
	//ɾ���û��ķ���
	public String deleteManager(){
		if(managerId != null && managerId != ""){
			if(deleteOne(managerId)){
				request.put("deleteSuccess", "true");
			}else{
				request.put("deleteSuccess", "false");
			}
		}
		return showPage();
	}
	
	//����ɾ���û��ķ���
	public String deleteSelectManager(){
		if(managerIds != null && managerIds != ""){
			String[] ids = managerIds.split(",");
			int count = 0;
			for(String id : ids){
				if(deleteOne(id)){
					count++;
				}
			}
			if(count == ids.length){
				request.put("deleteSuccess", "true");
			}else{
				request.put("deleteSuccess", "false");
			}
		}
		return showPage();
	}
	//�޸��û�����ķ���
	public String alterManagerPage(){
		if(managerId != null && managerId != ""){
			manager = managerMapper.findById(managerId);
			School school = schoolMapper.findById(manager.getSchool_id());
			if(school == null){
				return showPage();
			}else{
				manager.setSchool(school);
			}
			return "alterManagerPage";
		}else{
			return showPage();
		}
	}
	//�޸��û��ķ���
	public String alterManager(){
		if(createUser != null){
			//System.out.println(createUser);
			School school = schoolMapper.findByProvinceAndCityAndName(createUser.getSchool().getSchool_province(), createUser.getSchool().getSchool_city(), createUser.getSchool().getSchool_name());
			if(school != null){
				//System.out.println(school);
				createUser.setSchool_id(school.getSchool_id());
			}else{
				request.put("alterSuccess", "false");
				return "alterManagerPage";
			}
			int count = managerMapper.alterOneByBean(createUser);
			//System.out.println("count:" + count);
			if(count > 0){
				request.put("alterSuccess", "true");
			}else{
				request.put("alterSuccess", "false");
			}
		}
		managerId = createUser.getSchool_manager_id();
		return alterManagerPage();
	}
	
	//��ʾ�û�����Ľ���
	public String managerDetailPage(){
		if(managerId != null && managerId != ""){
			manager = managerMapper.findById(managerId);
			School school = schoolMapper.findById(manager.getSchool_id());
			if(school == null){
				return showPage();
			}else{
				manager.setSchool(school);
			}
			return "managerDetailPage";
		}else{
			return showPage();
		}
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
			if(orderType != null){
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
			setManagerPage(managerUsers.size());
			recordNum = managerUsers.size();
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
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerIds() {
		return managerIds;
	}
	public void setManagerIds(String managerIds) {
		this.managerIds = managerIds;
	}
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public SchoolManager getCreateUser() {
		return createUser;
	}
	public void setCreateUser(SchoolManager createUser) {
		this.createUser = createUser;
	}
	public SchoolManager getManager() {
		return manager;
	}
	public void setManager(SchoolManager manager) {
		this.manager = manager;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUserPath() {
		return userPath;
	}
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
	public String getDecomPath() {
		return decomPath;
	}
	public void setDecomPath(String decomPath) {
		this.decomPath = decomPath;
	}
	public String getWebPath() {
		return webPath;
	}
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}
	public String getTempPath() {
		return tempPath;
	}
	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}
	public String getFilePwd() {
		return filePwd;
	}
	public void setFilePwd(String filePwd) {
		this.filePwd = filePwd;
	}
	public int getBatchNowPage() {
		return batchNowPage;
	}
	public void setBatchNowPage(int batchNowPage) {
		this.batchNowPage = batchNowPage;
	}
	public String getCreateAccounts() {
		return createAccounts;
	}
	public void setCreateAccounts(String createAccounts) {
		this.createAccounts = createAccounts;
	}
	public String getCreateAccount() {
		return createAccount;
	}
	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
