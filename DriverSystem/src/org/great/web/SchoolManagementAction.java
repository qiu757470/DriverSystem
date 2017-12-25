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
import org.great.bean.ExamPlan;
import org.great.bean.Mail;
import org.great.bean.ManagerJur;
import org.great.bean.School;
import org.great.bean.SchoolEvaluation;
import org.great.bean.SchoolManager;
import org.great.bean.SchoolNotice;
import org.great.bean.SchoolSearch;
import org.great.bean.StuExamPlan;
import org.great.bean.Student;
import org.great.bean.StudentCourse;
import org.great.bean.StudentNotice;
import org.great.bean.Teacher;
import org.great.bean.TeacherEvaluation;
import org.great.bean.TransNotice;
import org.great.bean.TransUser;
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
import org.great.tools.FileUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月21日 下午1:31:19 
* 类说明 
*/
@Controller
public class SchoolManagementAction extends BaseAction{

	//每页有多少条
	private final int PAGENUM = 10;
	//驾校表的Mapper接口
	@Resource
	private SchoolMapper schoolMapper;
	//驾校权限表的Mapper接口
	@Resource
	private ManagerJurMapper managerJurMapper;
	//公告表的Mapper接口
	@Resource
	private TransNoticeMapper transNoticeMapper;
	//学员表的Mapper接口
	@Resource
	private StudentMapper studentMapper;
	//邮件表的Mapper接口
	@Resource
	private MailMapper mailMapper;
	//驾校评价表的Mapper接口
	@Resource
	private SchoolEvaluationMapper schoolEvaluationMapper;
	//学员科目表的Mapper接口
	@Resource
	private StudentCourseMapper studentCourseMapper;
	//教练表的Mapper接口
	@Resource
	private TeacherMapper teacherMapper;
	//学员考试表的Mapper接口
	@Resource
	private StuExamPlanMapper stuExamPlanMapper;
	//教练评价表的Mapper接口
	@Resource
	private TeacherEvaluationMapper teacherEvaluationMapper;
	//驾校公告表的Mapper接口
	@Resource
	private SchoolNoticeMapper schoolNoticeMapper;
	//驾校管理员表的Mapper接口
	@Resource
	private SchoolManagerMapper managerMapper;
	//驾校管理员表的Mapper接口
	@Resource
	private ExamPlanMapper examPlanMapper;
	//学员公告表的Mapper接口
	@Resource
	private StudentNoticeMapper studentNoticeMapper;
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
	//修改和详情显示的驾校
	private School school;
	//前端传来的修改驾校的数据
	private School alterSchool;
	//前端传来的创建驾校的数据
	private School createSchool;
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
	private String createIds;
	//压缩文件
	private ZipFile zipFile;
	//批量创建用户详情账号
	private String createId;
	
	
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
		String numStr = schoolMapper.getTotalNum();
		if(numStr != null){
			totalNum = Integer.parseInt(numStr);
		}
		setSchoolPage(totalNum);
		recordNum = totalNum;
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
			schoolList = sqlSession.selectList("findAllSchools", map, new RowBounds((nowPage-1)*10, 10));
		}else{
			schoolList = sqlSession.selectList("findAllSchools", null, new RowBounds((nowPage-1)*10, 10));
		}
		String numStr1 = schoolMapper.getTotalNum();
		if(numStr1 != null){
			recordNum = Integer.parseInt(numStr1);
		}
		sqlSession.commit();
		sqlSession.clearCache();
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
			schoolList = sqlSession.selectList("searchSchool", map, new RowBounds((nowPage-1)*10, 10));
			sqlSession.commit();
			sqlSession.clearCache();
			sqlSession.close();
			setSchoolPage(schoolList.size());
			recordNum = schoolList.size();
			//schoolList = schoolMapper.searchSchool(searchSchool);
			if(schoolList != null && schoolList.size() > 0){
				/*int totalNum = schoolList.size();
				recordNum = totalNum;
				if(totalNum % 10 == 0){
					allPage = totalNum / 10;
				}else{
					allPage = totalNum / 10 + 1;
				}
				if(schoolList.size() > 10){
					if(nowPage > allPage || nowPage <= 1){
						nowPage = 1;
						List<School> temp = new ArrayList<School>();
						for(int i=0; i<10; i++){
							temp.add(schoolList.get(i));
						}
						schoolList = temp;
					}else{
						List<School> temp = new ArrayList<School>();
						int maxNum = 10*nowPage;
						if(maxNum > totalNum){
							maxNum = totalNum;
						}
						for(int i=0+10*(nowPage-1); i<maxNum; i++){
							temp.add(schoolList.get(i));
						}
						schoolList = temp;
					}
				}else{
					nowPage = 1;
				}*/
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
	//根据id删除一个驾校管理员的方法
	public boolean deleteOneManager(String id){
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
	//根据id删除一个教练的方法
	public boolean deleteOneTeacher(String id){
		if(id != null && id != ""){
			teacherEvaluationMapper.deleteByTeacherId(id);
			examPlanMapper.deleteByTeacherId(id);
			teacherMapper.deleteOneById(id);
		}
		TeacherEvaluation te = teacherEvaluationMapper.findByStudentId(id);
		List<ExamPlan> exams = examPlanMapper.findByTeacherId(id);
		Teacher teacher = teacherMapper.findById(id);
		if(te == null && exams == null && teacher == null){
			return true;
		}else{
			return false;
		}
	}
	//根据id删除一个学员的方法
	public boolean deleteOneStudent(String id) {
		if(id != null && id != ""){
			studentNoticeMapper.deleteByStudentId(id);
			mailMapper.deleteByStudentId(id);
			schoolEvaluationMapper.deleteByStudentId(id);
			teacherEvaluationMapper.deleteByStudentId(id);
			studentCourseMapper.deleteByStudentId(id);
			stuExamPlanMapper.deleteByStudentId(id);
			studentMapper.deleteByStudentId(id);
		}
		List<StudentNotice> notices = studentNoticeMapper.findByStudentId(id);
		List<Mail> mails = mailMapper.findByStudentId(id);
		SchoolEvaluation se = schoolEvaluationMapper.findByStudentId(id);
		TeacherEvaluation te = teacherEvaluationMapper.findByStudentId(id);
		List<StudentCourse> stuCourses = studentCourseMapper.findByStudentId(id);
		List<StuExamPlan> examPlans = stuExamPlanMapper.findByStudentId(id);
		Student student = studentMapper.findByStudentId(id);
		if((notices == null || notices.size() == 0) && (mails == null || mails.size() == 0) && se == null && te == null
				&& (stuCourses == null || stuCourses.size() == 0) && (examPlans == null || examPlans.size() == 0) && student == null){
			return true;
		}else{
			return false;
		}
	}
	//删除一个驾校
	public boolean deleteOneSchool(String schoolId){
		if(schoolId != null && schoolId != ""){
			//删除所有管理员
			boolean delteManagerCheck =false;
			ArrayList<SchoolManager> managerList = managerMapper.findBySchoolId(schoolId);
			if(managerList != null && managerList.size() > 0){
				int deleteManagerCount = 0;
				for(SchoolManager s : managerList){
					if(deleteOneManager(s.getSchool_manager_id())){
						deleteManagerCount++;
					}
				}
				if(deleteManagerCount == managerList.size()){
					delteManagerCheck = true;
				}
			}else{
				delteManagerCheck = true;
			}
			//删除所有学员
			boolean deleteStudentCheck =false;
			ArrayList<Student> studentList = studentMapper.findBySchoolId(schoolId);
			if(studentList != null && studentList.size() > 0){
				int deleteStudentCount = 0;
				for(Student t : studentList){
					if(deleteOneStudent(t.getStudent_id())){
						deleteStudentCount++;
					}
				}
				if(deleteStudentCount == studentList.size()){
					deleteStudentCheck = true;
				}
			}else{
				deleteStudentCheck = true;
			}
			//删除所有教练
			boolean deleteTeacherCheck =false;
			ArrayList<Teacher> teacherList = teacherMapper.findBySchoolId(schoolId);
			if(teacherList != null && teacherList.size() > 0){
				int deleteTeacherCount = 0;
				for(Teacher t : teacherList){
					if(deleteOneTeacher(t.getTeacher_id())){
						deleteTeacherCount++;
					}
				}
				if(deleteTeacherCount == teacherList.size()){
					deleteTeacherCheck = true;
				}
			}else{
				deleteTeacherCheck = true;
			}
			boolean deleteNoticeCheck = false;
			schoolNoticeMapper.deleteBySchoolId(schoolId);
			List<SchoolNotice>notices = schoolNoticeMapper.findBySchoolId(schoolId);
			if(notices == null || notices.size() == 0){
				deleteNoticeCheck = true;
			}
			boolean deleteExamCheck = false;
			examPlanMapper.deleteBySchoolId(schoolId);
			List<ExamPlan> exams = examPlanMapper.findBySchoolId(schoolId);
			if(exams == null || exams.size() == 0){
				deleteExamCheck = true;
			}
			boolean deleteSchoolCheck = false;
			schoolMapper.deleteOneById(schoolId);
			School school = schoolMapper.findById(schoolId);
			if(school == null){
				deleteSchoolCheck = true;
			}
			if(delteManagerCheck && deleteStudentCheck && deleteTeacherCheck && deleteNoticeCheck && deleteExamCheck && deleteSchoolCheck){
				return true;
			}
		}
		return false;
		
	}
	//删除驾校的方法
	public String deleteSchool(){
		if(schoolId != null && schoolId != ""){
			if(deleteOneSchool(schoolId)){
				request.put("deleteSuccess", "true");
			}else{
				request.put("deleteSuccess", "false");
			}
		}
		return showPage();
	}
	
	
	//批量删除用户的方法
	public String deleteSelectSchool(){
		if(schoolIds != null && schoolIds != ""){
			//System.out.println(schoolIds);
			String[] ids = schoolIds.split(",");
			int count = 0;
			int count1 = 0;
			for(String id : ids){
				if(deleteOneSchool(id)){
					count++;
				}
				if(schoolMapper.findById(id) == null){
					count1++;
				}
			}
			//System.out.println(count);
			
			if(count == ids.length || count1 == ids.length){
				System.out.println("批量删除成功！");
				request.put("deleteSuccess", "true");
			}else{
				System.out.println("批量删除失败！");
				request.put("deleteSuccess", "false");
			}
		}
		return showPage();
	}
	//修改驾校显示界面的方法
	public String alterSchoolPage(){
		if(schoolId != null && schoolId != ""){
			school = schoolMapper.findById(schoolId);
			return "alterSchoolPage";
		}else{
			return showPage();
		}
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
		if(school.getSchool_file_url().contains("\\") || school.getSchool_file_url().contains("/")){
			school.setSchool_file_url(school.getSchool_file_url().replace("\\", ","));
		}
		if(school.getSchool_charge() == null){
			school.setSchool_charge("");
		}
		return school;
	}
	//修改驾校的方法
	public String alterSchool(){
		if(alterSchool != null){
			//System.out.println(alterSchool);
			School s = schoolMapper.findById(alterSchool.getSchool_id());
			alterSchool.setSchool_picture_url(s.getSchool_picture_url());
			alterSchool.setSchool_refuse_reason(s.getSchool_refuse_reason());
			alterSchool.setSchool_file_url(s.getSchool_file_url());
			alterSchool = setSchoolData(alterSchool);
			int count = schoolMapper.alterOneByBean(alterSchool);
			//System.out.println("count:" + count);
			if(count > 0){
				request.put("alterSuccess", "true");
			}else{
				request.put("alterSuccess", "false");
			}
		}
		schoolId = alterSchool.getSchool_id();
		return alterSchoolPage();
	}
	//驾校详情界面
	public String schoolDetailPage(){
		if(schoolId != null && schoolId != ""){
			school = schoolMapper.findById(schoolId);
			school.setManagerNum(schoolMapper.getManagerNum(schoolId) != null ? schoolMapper.getManagerNum(schoolId) : 0);
			school.setTeacherNum(schoolMapper.getTeacherNum(schoolId) != null ? schoolMapper.getTeacherNum(schoolId) : 0);
			school.setStudentNum(schoolMapper.getStudentNum(schoolId) != null ? schoolMapper.getStudentNum(schoolId) : 0);
			school = setSchoolData(school);
			return "schoolDetailPage";
		}else{
			return showPage();
		}
	}
	//创建驾校界面
	public String createSchoolPage(){
		return "createSchoolPage";
	}
	//创建单个驾校
	public String createSchool(){
		
		if(createSchool != null){
			try{
				createSchool = setSchoolData(createSchool);
				schoolMapper.addOneByBean(createSchool);
				request.put("createSuccess", "true");
				return "createSchoolPage";
			}catch(Exception e){
				e.printStackTrace();
				request.put("createSuccess", "false");
				return "createSchoolPage";
			}
		}
		return "createSchoolPage";
	}
	//批量创建驾校
	public String createBatchPage(){
		return "createSchoolBatchPage";
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
			return "createSchoolBatchPage";
		}
		
		userPath = webPath + "\\transUsers\\" + acc;
		uploadPath = userPath + "\\upload\\school";
		decomPath = userPath + "\\decompression\\school";
		tempPath = userPath + "\\temp\\school";
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
				ArrayList<School> createSchools = new ArrayList<School>();
				session.put("createSchools", createSchools);
				session.put("createId", 0);
				//解析excel文件
				readExcelFile(decomFile);
				session.put("zipFile", null);
				session.put("uploadFile", null);
				if(setNewSchoolData()){
					request.put("uploadSuccess", "true");
				}else{
					request.put("uploadSuccess", "false");
				}
			}else{
				System.out.println("解密失败！");
				request.put("uploadSuccess", "false");
			}
			return "createSchoolBatchPage";
		}
		/*System.out.println(filePath);
		System.out.println(uploadPath);
		System.out.println(uploadFile);*/
		File uploadSuccessFile = new File(uploadPath, filePath);
		boolean isUpload = FileUtil.upload(filePath, uploadFile, uploadSuccessFile);
		if(isUpload){
			session.put("uploadSuccessFile", uploadSuccessFile);
			//System.out.println("上传成功！文件路径：" + uploadPath);
			if(filePath.endsWith("zip")){
				try {
					zipFile = new ZipFile(uploadFile);
					//如果文件是加密的
					if (zipFile .isEncrypted()) {
						//让用户输入密码，跳转到考试管理界面
						request.put("inputFilePwd", "true");
						return "createSchoolBatchPage";
					}else{
						//解压文件夹文件
						File decomFile = new File(decomPath);
						//解压全部
						zipFile.extractAll(decomPath);
						//创建集合对象
						ArrayList<School> createSchools = new ArrayList<School>();
						session.put("createSchools", createSchools);
						session.put("createId", 0);
						//解析excel文件
						readExcelFile(decomFile);
						if(setNewSchoolData()){
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
				ArrayList<School> createSchools = new ArrayList<School>();
				session.put("createSchools", createSchools);
				session.put("createId", 0);
				//解析excel文件
				readExcelFile(new File(uploadPath));
				if(setNewSchoolData()){
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
		return "createSchoolBatchPage";
	}
	//设置批量创建用户的数据
	public boolean setNewSchoolData(){
		@SuppressWarnings("unchecked")
		ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
		if(createSchools.size() > 0){
			int createSchoolNowPage = 1;//当前页数
			int totalNum = createSchools.size();
			int createSchoolTotalNum = totalNum;//总用户数
			int pageNum = 1;
			if(createSchoolTotalNum > PAGENUM){
				if(createSchoolTotalNum % PAGENUM == 0){
					pageNum = createSchoolTotalNum / PAGENUM;
				}else{
					pageNum = createSchoolTotalNum / PAGENUM + 1;
				}
				ArrayList<School> newSchools = new ArrayList<School>();
				for(int i=0; i<PAGENUM; i++){
					newSchools.add(createSchools.get(i));
				}
				session.put("newSchools", newSchools);
			}
			int createPageNum = pageNum;//总页数
			session.put("createSchoolNowPage", createSchoolNowPage);
			session.put("createSchoolTotalNum", createSchoolTotalNum);
			session.put("createSchoolPageNum", createPageNum);
			return true;
		}else{
			System.out.println("上传失败！");
			return false;
		}
	}
	//批量创建的分页显示方法
	public String showCreatePage(){
		@SuppressWarnings("unchecked")
		ArrayList<School> newSchools = (ArrayList<School>) session.get("newSchools");
		@SuppressWarnings("unchecked")
		ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
		int createTotalNum = (Integer) session.get("createSchoolTotalNum");
		if(createSchools != null){
			createTotalNum = createSchools.size();
			int maxSize = batchNowPage * PAGENUM;
			if(maxSize > createTotalNum){
				maxSize = createTotalNum;
			}
			newSchools = new ArrayList<School>();
			for(int i=0 + (batchNowPage - 1)* PAGENUM; i < maxSize; i++){
				newSchools.add(createSchools.get(i));
			}
			session.put("newSchools", newSchools);
			session.put("createSchoolNowPage", batchNowPage);
			
		}
		return "createSchoolBatchPage";
		
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
	//解析excel文件
	public void readExcel(Workbook readwb, File file){
		@SuppressWarnings("unchecked")
		ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
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
			ArrayList<School> schools = null;
			if(rsRows > 1){
				schools = new ArrayList<School>();
				for(int i=1; i<rsRows; i++){
					School school = new School();
					for(int j=0; j<rsColumns; j++){
						Cell cell = readsheet.getCell(j, i);
						String content = cell.getContents();
						//System.out.println(content);
						if(j == 0){
							school.setSchool_name(content);
						}else if(j == 1){
							school.setSchool_representative_name(content);
						}else if(j == 2){
							school.setSchool_representative_id(content);
						}else if(j == 3){
							school.setSchool_representative_phone(content);
						}else if(j == 4){
							school.setSchool_province(content);
						}else if(j == 5){
							school.setSchool_city(content);
						}else if(j == 6){
							school.setSchool_address(content);
						}else if(j == 7){
							school.setSchool_phone(content);
						}else if(j == 8){
							school.setSchool_certificate(content);
						}else if(j == 9){
							school.setSchool_code(content);
						}else if(j == 10){
							school.setSchool_state(content);
						}else if(j == 11){
							school.setSchool_homepage(content);
						}else if(j == 12){
							school.setSchool_charge(content);
						}
					}
					//System.out.println("school:" + school);
					if(school != null){
						int id = (Integer) session.get("createId");
						id++;
						school = setSchoolData(school);
						school.setCreateId(id);
						session.put("createId", id);
						schools.add(school);
					}
				}
			}
			//System.out.println(managers.size());
			if(schools != null && schools.size() > 0){
				for(School s : schools){
					//System.out.println(s);
					createSchools.add(s);
				}
				session.put("createSchools", createSchools);
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
	//全部创建的方法
	public String createAll(){
		int maxId = schoolMapper.getMaxId();
		@SuppressWarnings("unchecked")
		ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
		for(School s : createSchools){
			schoolMapper.addOneByBean(s);
		}
		if(schoolMapper.getMaxId() != maxId){
			session.put("createSchools", null);
			session.put("newSchools", null);
			session.put("createSchoolNowPage", null);
			session.put("createSchoolPageNum", null);
			session.put("createSchoolTotalNum", null);
			request.put("createSuccess", "true");
		}else{
			request.put("createSuccess", "false");
		}
		return "createSchoolBatchPage";
	}
	//选择账号批量创建的方法
	public String createSelect(){
		if(createIds != null && createIds != ""){
//			System.out.println("createAccounts" + createAccounts);
			String[] ids = createIds.split(",");
			@SuppressWarnings("unchecked")
			ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
			ArrayList<School> temp = new ArrayList<School>();
			int i=0;
			while(i < ids.length){
				for(School s : createSchools){
					System.out.println(s.getCreateId());
					System.out.println(Integer.parseInt(ids[i]));
					if(Integer.parseInt(ids[i]) == s.getCreateId()){
						temp.add(s);
						break;
					}
				}
				i++;
			}
			int maxId = schoolMapper.getMaxId();
			for(School s : temp){
				schoolMapper.addOneByBean(s);
			}
			if(schoolMapper.getMaxId() != maxId){
				System.out.println("创建成功！");
				//采用迭代器删除
				Iterator<School> it = createSchools.iterator();
				int j = 0;
		        while(it.hasNext()){  
		        	School item = it.next();  
		            if (item.getCreateId() == temp.get(j).getCreateId()){  
		                it.remove();
		            }
		            j++;
		            if(j == temp.size()){
		            	break;
		            }
		            session.put("createSchools", createSchools);
		            setNewSchoolData();
		        } 
				request.put("createSuccess", "true");
			}else{
				System.out.println("创建失败！");
				request.put("createSuccess", "false");
			}
		}
		return "createSchoolBatchPage";
	}
	
	//批量创建驾校的详情
	public String createSchoolDetail(){
		if(createId != null && createId != ""){
			@SuppressWarnings("unchecked")
			ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
			School createSchool = null;
			for(School s : createSchools){
				if(s.getCreateId() == Integer.parseInt(createId)){
					createSchool = s;
					break;
				}
			}
			if(createSchool != null){
				request.put("createSchool", createSchool);
				return "createSchoolDetailPage";
			}
		}
		request.put("noDetail", "true");
		return "createManagerBatchPage";
		
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
	public School getCreateSchool() {
		return createSchool;
	}
	public void setCreateSchool(School createSchool) {
		this.createSchool = createSchool;
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
	public String getCreateIds() {
		return createIds;
	}
	public void setCreateIds(String createIds) {
		this.createIds = createIds;
	}
	public ZipFile getZipFile() {
		return zipFile;
	}
	public void setZipFile(ZipFile zipFile) {
		this.zipFile = zipFile;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
}
