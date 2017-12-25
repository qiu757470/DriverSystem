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
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月19日 下午12:45:57 
* 类说明 
*/
public class ManagerManagementAction extends BaseAction {

	//每页有多少条
	private final int PAGENUM = 10;
	//驾校管理员表的Mapper接口
	@Resource
	private SchoolManagerMapper managerMapper;
	//驾校表的Mapper接口
	@Resource
	private SchoolMapper schoolMapper;
	//驾校权限表的Mapper接口
	@Resource
	private ManagerJurMapper managerJurMapper;
	//公告表的Mapper接口
	@Resource
	private TransNoticeMapper transNoticeMapper;
	//前端界面显示的驾校管理员列表
	private List<SchoolManager> managerUsers;
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
	private ManagerSearch searchUser;
	//判断是否显示全部去
	private String showAll;
	//搜索错误信息
	private String searchErrorMsg;
	//json返回数据
	private String jsonResult;
	//ajax省份
	private String ajaxProvince;
	//ajax城市
	private String ajaxCity;
	//前端传来的管理员id（一个）
	private String managerId;
	//前端传来的管理员id（多个）
	private String managerIds;
	//ajax检查账号是否存在的账号
	private String managerAccount;
	//创建用户的数据
	private SchoolManager createUser;
	//修改用户、详情显示的数据
	private SchoolManager manager;
	
	//上传的文件，存在于临时文件夹
	private File uploadFile;
    //前端传来的文件名
    private String filePath;
    //上传文件存放的路径
    private String uploadPath;
    //用户文件夹
    private String userPath;
    //解压文件存放的路径
    private String decomPath;
    //web路径
    private String webPath;
    //临时路径
	private String tempPath;
	//文件密码
	private String filePwd;
	//批量创建的当前页
	private int batchNowPage;
	//选择批量创建传来的账号
	private String createAccounts;
	//压缩文件
	private ZipFile zipFile;
	//批量创建用户详情账号
	private String createAccount;
	//排序的类型
	private String orderType;

	//显示用户管理页面方法
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
	//ajax检查用户账号是否已存在
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
	//显示创建用户的界面
	public String createManagerPage(){
		return "createManagerPage";
	}
	//创建用户的方法
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
	//批量创建用户的详情
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
	//显示批量创建用户的界面
	public String createBatchPage(){
		return "createManagerBatchPage";
	}
	//上传文件表单提交的方法
	public String uploadFile(){
		/*System.out.println(uploadFile);
		System.out.println(filePath);*/
		//获得request对象
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
		//调用工具类上传文件到上传文件的路径
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
				//解压文件夹文件
				File decomFile = new File(decomPath);
				//创建集合对象
				ArrayList<SchoolManager> createManagers = new ArrayList<SchoolManager>();
				session.put("createManagers", createManagers);
				//解析excel文件
				readExcelFile(decomFile);
				session.put("zipFile", null);
				session.put("uploadFile", null);
				if(setNewManagerData()){
					request.put("uploadSuccess", "true");
				}else{
					request.put("uploadSuccess", "false");
				}
			}else{
				System.out.println("解密失败！");
				request.put("uploadSuccess", "false");
			}
			return "createManagerBatchPage";
		}
		File uploadSuccessFile = new File(uploadPath, filePath);
		boolean isUpload = FileUtil.upload(filePath, uploadFile, uploadSuccessFile);
		if(isUpload){
			session.put("uploadSuccessFile", uploadSuccessFile);
			System.out.println("上传成功！文件路径：" + uploadPath);
			if(filePath.endsWith("zip")){
				try {
					zipFile = new ZipFile(uploadFile);
					//如果文件是加密的
					if (zipFile .isEncrypted()) {
						//让用户输入密码，跳转到考试管理界面
						request.put("inputFilePwd", "true");
						return "createManagerBatchPage";
					}else{
						//解压文件夹文件
						File decomFile = new File(decomPath);
						//解压全部
						zipFile.extractAll(decomPath);
						//创建集合对象
						ArrayList<SchoolManager> createManagers = new ArrayList<SchoolManager>();
						session.put("createManagers", createManagers);
						//解析excel文件
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
				//创建集合对象
				ArrayList<SchoolManager> createManagers = new ArrayList<SchoolManager>();
				session.put("createManagers", createManagers);
				//解析excel文件
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
			System.out.println("上传失败！");
			request.put("uploadSuccess", "false");
		}
		return "createManagerBatchPage";
	}
	//设置批量创建用户的数据
	public boolean setNewManagerData(){
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
		if(createManagers.size() > 0){
			int createNowPage = 1;//当前页数
			int totalNum = createManagers.size();
			int createTotalNum = totalNum;//总用户数
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
			int createPageNum = pageNum;//总页数
			session.put("createNowPage", createNowPage);
			session.put("createTotalNum", createTotalNum);
			session.put("createPageNum", createPageNum);
			return true;
		}else{
			System.out.println("上传失败！");
			return false;
		}
	}
	//批量创建的分页显示方法
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
	//全部创建的方法
	public String createAll(){
		@SuppressWarnings("unchecked")
		ArrayList<SchoolManager> createManagers = (ArrayList<SchoolManager>) session.get("createManagers");
		for(SchoolManager m : createManagers){
			managerMapper.addOneByBean(m);
		}
		if(managerMapper.findByAccount(createManagers.get(0).getSchool_manager_account()) != null){
			//采用迭代器删除
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
	//选择账号批量创建的方法
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
				System.out.println("创建成功！");
				//采用迭代器删除
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
				System.out.println("创建失败！");
				request.put("createSuccess", "false");
			}
		}
		return "createManagerBatchPage";
		
	}
	//解析excel文件方法
	public void readExcelFile(File file) {
		Workbook readwb = null;
		//如果是文件夹，遍历该文件夹底下的所有文件，调用该方法
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f: files){
				readExcelFile(f);
			}
		}else{
			//如果是excel表格
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
			//设置学生人数的application属性
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
					//以下方法是防止数据库不存在的驾校和已存在的账号加入进来
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
	
	//根据id删除一个用户的方法
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
	//删除用户的方法
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
	
	//批量删除用户的方法
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
	//修改用户界面的方法
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
	//修改用户的方法
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
	
	//显示用户详情的界面
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
	
	//获得总页数方法
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
	
	//设置总页数方法
	public void setManagerPage(int totalNum){
		allPage = getManagerPage(totalNum);
		if(nowPage < 1){
			nowPage = 1;
		}
		if(nowPage > allPage){
			nowPage = allPage;
		}
	}
	
	//搜索用户的方法
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
				searchErrorMsg = "很抱歉，查无数据，请重新查询。";
				showAll = "true";
				return showPage();
			}
		}
		return SUCCESS;
	}

	//ajax切换驾校的方法
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
