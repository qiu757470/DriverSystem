package org.great.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.great.bean.NoticeAndTransuser;
import org.great.bean.School;
import org.great.bean.SchoolManager;
import org.great.bean.SchoolNotice;
import org.great.bean.Student;
import org.great.bean.StudentNotice;
import org.great.bean.TransNotice;
import org.great.bean.TransUser;
import org.great.mapper.SchoolMapper;
import org.great.mapper.SchoolNoticeMapper;
import org.great.mapper.StudentMapper;
import org.great.mapper.StudentNoticeMapper;
import org.great.mapper.TransNoticeMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

/*�˹ܶ˵Ĺ���ҵ�����action
*/
@Controller
public class TransNoticeAction extends BaseAction{
	
	
	@Resource
	private TransNoticeMapper transNoticeMapper;//�����mapper�ӿ�
    @Resource
	private StudentNoticeMapper  studentNoticeMapper;//ѧ�������mapper�ӿ�
	@Resource
	private StudentMapper  studentMapper;//ѧ����mapper�ӿ�
	@Resource
	private SchoolNoticeMapper  schoolNoticeMapper;//��У�����mapper�ӿ�
	@Resource
	private SchoolMapper schoolmapper;//��У��mapper�ӿ�
	private  TransNotice  transNotice;//��������
	private List<TransNotice> noticeList;//action��Ӧʵ��ļ���
	//json��������
	private String jsonResult;
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;//�������ݿ����ӵĹ���
	private SqlSession sqlSession;//�õ�ʵ����������
	private int changePage = 1;//��ǰҳ  Ϊ�˵õ�ǰ̨�ı��ҳ����
	private String province;//Ϊ�˵õ�ǰ̨�����ʡ��
	private String city;//Ϊ�˵õ�ǰ̨����ĳ���
	//�ж��Ƿ���ʾȫ��
	private String showAll;
	//id�ַ�������
	private String noticeIds;
	//������ʾ�Ĺ���
	private NoticeAndTransuser oneNotice;
	//������������
	private String noticeOrderType;
	
	
	
	
	
	
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	//��ʾ���еĹ�����Ϣ  ����ʵ�����еĹ���
	public String noticePreview(){
		String orderSeq = (String) session.get("orderSeq");
		if(orderSeq != null && orderSeq.equals("ASC")){
			orderSeq = "DESC";
			session.put("orderSeq", orderSeq);
		}else if(orderSeq != null && orderSeq.equals("DESC")){
			orderSeq = "ASC";
			session.put("orderSeq", orderSeq);
		}
		if(orderSeq == null || orderSeq == ""){
			session.put("orderSeq", "ASC");
			orderSeq = (String) session.get("orderSeq");
		}
		
		if(noticeOrderType != null && noticeOrderType != "" && !noticeOrderType.equals("defaultOrder")){
			session.put("noticeOrderType", noticeOrderType);
		}
		//System.out.println(noticeOrderType);
		int nowPage = changePage;//��ǰҳ��
		request.put("NowPage", nowPage);
		String noticeNum = transNoticeMapper.getAllNotice();
		if(showAll != null && showAll.equals("true")){
			session.put("transNoticeSearch", null);
		}
		if(session.get("transNoticeSearch") != null){
			return noticeSearch();
		}
		if(noticeNum==null){
			request.put("noNotice","true");
			return SUCCESS;
		}
		int allPage = 1;//��ҳ��
		int rowNum = Integer.parseInt(noticeNum);
		if(rowNum % 10 == 0){
			allPage = rowNum / 10;
		}else{
			allPage = rowNum / 10 + 1;
		}
		request.put("AllPage", allPage);
		request.put("RowNum",rowNum);//������
		try {
			sqlSession = sqlSessionFactory.getObject().openSession();
			if(noticeOrderType == null || noticeOrderType == ""){
				noticeOrderType = (String) session.get("noticeOrderType");
			}
			if(noticeOrderType != null && noticeOrderType != "" && noticeOrderType != "defaultOrder"){
				Map<String, String> map = new HashMap<String, String>();
				map.put("noticeOrderType", noticeOrderType);
				map.put("orderSeq", orderSeq);
				noticeList = sqlSession.selectList("findAllNotice", map, new RowBounds((nowPage-1)*10, 10));
			}else{
				noticeList = sqlSession.selectList("findAllNotice", null, new RowBounds((nowPage-1)*10, 10));
			}
			
			ArrayList<TransNotice> list = new ArrayList<TransNotice>();
			for(TransNotice n : noticeList){
				n.setTrans_notice_time(setTimeFormat(n.getTrans_notice_time()));
				list.add(n);
			}
			noticeList = list;
			sqlSession.commit();
			sqlSession.clearCache();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;

	}
	//��������Ĳ���
	public String noticeSearch(){
		if(noticeOrderType != null && noticeOrderType != "" && !noticeOrderType.equals("defaultOrder")){
			session.put("noticeOrderType", noticeOrderType);
		}
		//System.out.println(noticeOrderType);
		String orderSeq = (String) session.get("orderSeq");
		if(orderSeq != null && orderSeq.equals("ASC")){
			orderSeq = "DESC";
			session.put("orderSeq", orderSeq);
		}else if(orderSeq != null && orderSeq.equals("DESC")){
			orderSeq = "ASC";
			session.put("orderSeq", orderSeq);
		}
		if(orderSeq == null || orderSeq == ""){
			session.put("orderSeq", "ASC");
			orderSeq = (String) session.get("orderSeq");
		}
		int nowPage = changePage;//��ǰҳ��
		request.put("NowPage", nowPage);
		String time = null;
		if(transNotice == null){
			transNotice = (TransNotice) session.get("transNoticeSearch");
		}
		//System.out.println(transNotice);
		if(transNotice.getTrans_notice_time() != null && transNotice.getTrans_notice_time().length() > 0){
			time = transNotice.getTrans_notice_time().substring(0,4) + transNotice.getTrans_notice_time().substring(5,7) + transNotice.getTrans_notice_time().substring(8,10);
		}
		Map<String,String> map = new HashMap<String,String>();
		if(noticeOrderType == null || noticeOrderType == ""){
			noticeOrderType = (String) session.get("noticeOrderType");
		}
		if(noticeOrderType != null && noticeOrderType != "" && !noticeOrderType.equals("defaultOrder")){
			map.put("noticeOrderType", noticeOrderType);
			map.put("orderSeq", orderSeq);
		}
		map.put("theme",transNotice.getTrans_notice_theme());
		map.put("time", time);
		map.put("province",transNotice.getTrans_notice_province());
		map.put("city",transNotice.getTrans_notice_city());
		map.put("target",transNotice.getTrans_notice_target());
		session.put("transNoticeSearch", transNotice);
		int noticeNum = transNoticeMapper.searchNotice(map).size();
		/*if(noticeNum==null){
			String message = "Ŀǰ���޹������";
			request.put("Message","true");
			return "fail";
		}*/
		int allPage = 1;//��ҳ��
		int rowNum = noticeNum;
		if(rowNum % 10 == 0){
			allPage = rowNum / 10;
		}else{
			allPage = rowNum / 10 + 1;
		}
		request.put("AllPage", allPage);
		request.put("RowNum",rowNum);//������
		try {
			sqlSession = sqlSessionFactory.getObject().openSession();
			noticeList = sqlSession.selectList("searchNotice", map, new RowBounds((nowPage-1)*10, 10));
			ArrayList<TransNotice> list = new ArrayList<TransNotice>();
			for(TransNotice n : noticeList){
				n.setTrans_notice_time(setTimeFormat(n.getTrans_notice_time()));
				list.add(n);
			}
			noticeList = list;
			sqlSession.commit();
			sqlSession.clearCache();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	//ת��ʱ���ʽ
	public String setTimeFormat(String oldTime){
		String year = oldTime.substring(0,4);
		String month = oldTime.substring(4,6);
		String day = oldTime.substring(6,8);
		String hour = oldTime.substring(8,10);
		String min = oldTime.substring(10,12);
		String sec = oldTime.substring(12,14);
		String newTime = year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec;
		return newTime;
	}
	
	//ɾ��ĳ������Ĳ���
	 public String noticeDelete(){
		 if(noticeIds != null && noticeIds != ""){
			 return deleteSelect();
		 }
			try {
				String loc=new String(transNotice.getTrans_notice_target().getBytes("iso-8859-1"),"utf-8");
				boolean isSuccess = deleteOneNotice(transNotice.getTrans_notice_id(), loc);
				if(isSuccess){
					request.put("deleteMsg", "true");
				}else{
					request.put("deleteMsg", "false");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		return noticePreview();
	 }
	 public String deleteSelect() {
		String[] ids = noticeIds.split(",");
		int count = 0;
		for(String id : ids){
			NoticeAndTransuser notice = transNoticeMapper.findById(id);
			if(deleteOneNotice(id, notice.getTrans_notice_target())){
				count++;
			}
		}
		if(count == ids.length){
			request.put("deleteMsg", "true");
		}else{
			request.put("deleteMsg", "false");
		}
		return noticePreview();
	}

	//ɾ����������
	 public boolean deleteOneNotice(String noticeId, String noticeTarget){
		 if(noticeTarget.equals("ѧԱ")){//�����ɾ���Ķ�����ѧԱ����ɾ��ѧԱ�����
			studentNoticeMapper.delStudentNotic(noticeId);
		}else if(noticeTarget.equals("��У")){//�����ɾ���Ķ�����ѧԱ����ɾ����У�����
			schoolNoticeMapper.delSchoolNotice(noticeId);
		}else if(noticeTarget.equals("ȫ��")){//�����ɾ���Ķ�����ѧԱ����ɾ����У�����
			schoolNoticeMapper.delSchoolNotice(noticeId);
			studentNoticeMapper.delStudentNotic(noticeId);
		}
	    transNoticeMapper.deleteNoticeById(noticeId);
	    if(transNoticeMapper.findById(noticeId) == null){
//	    	request.put("deleteSuccess", "true");
	    	return true;
	    }else{
//	    	request.put("deleteSuccess", "false");
	    	return false;
	    }
		
	 }
	 
	//�����������Ĳ���
	 public String Particulars(){
		 oneNotice = transNoticeMapper.findById(transNotice.getTrans_notice_id());
		 oneNotice.setTrans_notice_time(setTimeFormat(oneNotice.getTrans_notice_time()));
		 return SUCCESS;
	 }
	 //��������Ĳ���
	 public String noticeAnno(){
		
		 TransNotice noticeInfo = transNoticeMapper.oneNotice(transNotice.getTrans_notice_theme());
		 if(noticeInfo==null){
			 Date date = new Date();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			 TransUser loginUser = (TransUser)session.get("loginTransUser");
			 if(loginUser == null){
				 request.put("wrongLogin", "true");
				 return SUCCESS;
			 }
			 TransNotice addNotice = new TransNotice(null,transNotice.getTrans_notice_theme(),transNotice.getTrans_notice_content(),
		    		sdf.format(date),province,city,transNotice.getTrans_notice_target(),loginUser.getTrans_user_id(),null);
			 transNoticeMapper.addNoticeByBean(addNotice);
			 if(transNoticeMapper.findByTime(addNotice.getTrans_notice_time()) != null){
				 request.put("sendSuccess", "true");
			 }else{
				 request.put("sendSuccess", "false");
			 }
			 String noticeId = transNoticeMapper.noticeId();
			 //�������Ϊѧ��ʱ
			 if(transNotice.getTrans_notice_target().equals("ѧԱ")){
				 //����ʡ���ҵ��õ������еļ�У
				 List<School> schoolid = schoolmapper.findSchoolid(province, city);
				 for(int i=0;i<schoolid.size();i++){
					 String id = schoolid.get(i).getSchool_id();
					 //�ٸ��ݱ����õ��ļ�Уid�ҵ�  ���м�У�µ�ѧ��
					 List<Student> studentid = studentMapper.findAllStudent(id);
					 for(int j=0;j<studentid.size();j++){
						 String stuid = studentid.get(j).getStudent_id(); 
						 StudentNotice stunotice = new StudentNotice(noticeId,stuid,"δ��");
						 studentNoticeMapper.addStudentNotice(stunotice);
					 }
				 }
				 
			 }else if(transNotice.getTrans_notice_target().equals("��У")){
				 //���ҵ���Ӧ�����µ����м�У
				 List<School> schoolid = schoolmapper.findSchoolid(province, city);
				 //�ٱ���ѭ������  ��У�����
				 for(int i=0;i<schoolid.size();i++){
					 String id = schoolid.get(i).getSchool_id();
					 SchoolNotice schoolNotice = new SchoolNotice(noticeId,id,"δ��");
					 schoolNoticeMapper.addSchoolNotice(schoolNotice);
				 }
			 }else if(transNotice.getTrans_notice_target().equals("ȫ��")){
				 List<School> schoolid = schoolmapper.findSchoolid(province, city);
				 for(int i=0;i<schoolid.size();i++){
					 String id = schoolid.get(i).getSchool_id();
					 SchoolNotice schoolNotice = new SchoolNotice(noticeId,id,"δ��");
					 schoolNoticeMapper.addSchoolNotice(schoolNotice);
					 //�ٸ��ݱ����õ��ļ�Уid�ҵ�  ���м�У�µ�ѧ��
					 List<Student> studentid = studentMapper.findAllStudent(id);
					 for(int j=0;j<studentid.size();j++){
						 String stuid = studentid.get(j).getStudent_id(); 
						 StudentNotice stunotice = new StudentNotice(noticeId,stuid,"δ��");
						 studentNoticeMapper.addStudentNotice(stunotice);
					 }
				 }
			 }
		 }
		 return SUCCESS;
	 }
	
	
	public TransNotice getTransNotice() {
		return transNotice;
	}
	public void setTransNotice(TransNotice transNotice) {
		this.transNotice = transNotice;
	}
	public List<TransNotice> getNoticeList() {
		return noticeList;
	}
	public void setNoticeList(List<TransNotice> noticeList) {
		this.noticeList = noticeList;
	}
	public int getChangePage() {
		return changePage;
	}
	public void setChangePage(int changePage) {
		this.changePage = changePage;
	}
	public String getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	public String getShowAll() {
		return showAll;
	}
	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNoticeIds() {
		return noticeIds;
	}
	public void setNoticeIds(String noticeIds) {
		this.noticeIds = noticeIds;
	}
	public NoticeAndTransuser getOneNotice() {
		return oneNotice;
	}
	public void setOneNotice(NoticeAndTransuser oneNotice) {
		this.oneNotice = oneNotice;
	}
	public String getNoticeOrderType() {
		return noticeOrderType;
	}
	public void setNoticeOrderType(String noticeOrderType) {
		this.noticeOrderType = noticeOrderType;
	}
	
	
	

}
