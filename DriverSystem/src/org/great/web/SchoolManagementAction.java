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
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��21�� ����1:31:19 
* ��˵�� 
*/
@Controller
public class SchoolManagementAction extends BaseAction{

	//ÿҳ�ж�����
	private final int PAGENUM = 10;
	//��У���Mapper�ӿ�
	@Resource
	private SchoolMapper schoolMapper;
	//��УȨ�ޱ��Mapper�ӿ�
	@Resource
	private ManagerJurMapper managerJurMapper;
	//������Mapper�ӿ�
	@Resource
	private TransNoticeMapper transNoticeMapper;
	//ѧԱ���Mapper�ӿ�
	@Resource
	private StudentMapper studentMapper;
	//�ʼ����Mapper�ӿ�
	@Resource
	private MailMapper mailMapper;
	//��У���۱��Mapper�ӿ�
	@Resource
	private SchoolEvaluationMapper schoolEvaluationMapper;
	//ѧԱ��Ŀ���Mapper�ӿ�
	@Resource
	private StudentCourseMapper studentCourseMapper;
	//�������Mapper�ӿ�
	@Resource
	private TeacherMapper teacherMapper;
	//ѧԱ���Ա��Mapper�ӿ�
	@Resource
	private StuExamPlanMapper stuExamPlanMapper;
	//�������۱��Mapper�ӿ�
	@Resource
	private TeacherEvaluationMapper teacherEvaluationMapper;
	//��У������Mapper�ӿ�
	@Resource
	private SchoolNoticeMapper schoolNoticeMapper;
	//��У����Ա���Mapper�ӿ�
	@Resource
	private SchoolManagerMapper managerMapper;
	//��У����Ա���Mapper�ӿ�
	@Resource
	private ExamPlanMapper examPlanMapper;
	//ѧԱ������Mapper�ӿ�
	@Resource
	private StudentNoticeMapper studentNoticeMapper;
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
	//�޸ĺ�������ʾ�ļ�У
	private School school;
	//ǰ�˴������޸ļ�У������
	private School alterSchool;
	//ǰ�˴����Ĵ�����У������
	private School createSchool;
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
	private String createIds;
	//ѹ���ļ�
	private ZipFile zipFile;
	//���������û������˺�
	private String createId;
	
	
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
	//����idɾ��һ����У����Ա�ķ���
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
	//����idɾ��һ�������ķ���
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
	//����idɾ��һ��ѧԱ�ķ���
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
	//ɾ��һ����У
	public boolean deleteOneSchool(String schoolId){
		if(schoolId != null && schoolId != ""){
			//ɾ�����й���Ա
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
			//ɾ������ѧԱ
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
			//ɾ�����н���
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
	//ɾ����У�ķ���
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
	
	
	//����ɾ���û��ķ���
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
				System.out.println("����ɾ���ɹ���");
				request.put("deleteSuccess", "true");
			}else{
				System.out.println("����ɾ��ʧ�ܣ�");
				request.put("deleteSuccess", "false");
			}
		}
		return showPage();
	}
	//�޸ļ�У��ʾ����ķ���
	public String alterSchoolPage(){
		if(schoolId != null && schoolId != ""){
			school = schoolMapper.findById(schoolId);
			return "alterSchoolPage";
		}else{
			return showPage();
		}
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
		if(school.getSchool_file_url().contains("\\") || school.getSchool_file_url().contains("/")){
			school.setSchool_file_url(school.getSchool_file_url().replace("\\", ","));
		}
		if(school.getSchool_charge() == null){
			school.setSchool_charge("");
		}
		return school;
	}
	//�޸ļ�У�ķ���
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
	//��У�������
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
	//������У����
	public String createSchoolPage(){
		return "createSchoolPage";
	}
	//����������У
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
	//����������У
	public String createBatchPage(){
		return "createSchoolBatchPage";
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
				ArrayList<School> createSchools = new ArrayList<School>();
				session.put("createSchools", createSchools);
				session.put("createId", 0);
				//����excel�ļ�
				readExcelFile(decomFile);
				session.put("zipFile", null);
				session.put("uploadFile", null);
				if(setNewSchoolData()){
					request.put("uploadSuccess", "true");
				}else{
					request.put("uploadSuccess", "false");
				}
			}else{
				System.out.println("����ʧ�ܣ�");
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
			//System.out.println("�ϴ��ɹ����ļ�·����" + uploadPath);
			if(filePath.endsWith("zip")){
				try {
					zipFile = new ZipFile(uploadFile);
					//����ļ��Ǽ��ܵ�
					if (zipFile .isEncrypted()) {
						//���û��������룬��ת�����Թ������
						request.put("inputFilePwd", "true");
						return "createSchoolBatchPage";
					}else{
						//��ѹ�ļ����ļ�
						File decomFile = new File(decomPath);
						//��ѹȫ��
						zipFile.extractAll(decomPath);
						//�������϶���
						ArrayList<School> createSchools = new ArrayList<School>();
						session.put("createSchools", createSchools);
						session.put("createId", 0);
						//����excel�ļ�
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
				//�������϶���
				ArrayList<School> createSchools = new ArrayList<School>();
				session.put("createSchools", createSchools);
				session.put("createId", 0);
				//����excel�ļ�
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
			System.out.println("�ϴ�ʧ�ܣ�");
			request.put("uploadSuccess", "false");
		}
		return "createSchoolBatchPage";
	}
	//�������������û�������
	public boolean setNewSchoolData(){
		@SuppressWarnings("unchecked")
		ArrayList<School> createSchools = (ArrayList<School>) session.get("createSchools");
		if(createSchools.size() > 0){
			int createSchoolNowPage = 1;//��ǰҳ��
			int totalNum = createSchools.size();
			int createSchoolTotalNum = totalNum;//���û���
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
			int createPageNum = pageNum;//��ҳ��
			session.put("createSchoolNowPage", createSchoolNowPage);
			session.put("createSchoolTotalNum", createSchoolTotalNum);
			session.put("createSchoolPageNum", createPageNum);
			return true;
		}else{
			System.out.println("�ϴ�ʧ�ܣ�");
			return false;
		}
	}
	//���������ķ�ҳ��ʾ����
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
	//����excel�ļ�
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
			//����ѧ��������application����
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
	//ȫ�������ķ���
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
	//ѡ���˺����������ķ���
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
				System.out.println("�����ɹ���");
				//���õ�����ɾ��
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
				System.out.println("����ʧ�ܣ�");
				request.put("createSuccess", "false");
			}
		}
		return "createSchoolBatchPage";
	}
	
	//����������У������
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
