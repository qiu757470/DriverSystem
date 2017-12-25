package org.great.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.great.bean.Mail;
import org.great.bean.ManagerSearch;
import org.great.bean.SchoolManager;
import org.great.bean.StudentSearch;
import org.great.mapper.MailMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��16�� ����10:26:08 
* ��˵�� ���û���Ӫƽ̨�����Action
*/
@Controller
public class MailAction extends BaseAction {
	//mail���ݿ�����ӿ�
	@Resource
	private MailMapper mailMapper;
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
	//ȫ��mail�ļ���
	private List<Mail> mailList;
	//һҳ�ж�����
	private static final int PAGENUM = 10;
	//ǰ�˴�������������
	private StudentSearch searchUser;
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
	//ǰ�˴�����mail_id
	private String mailId;
	//ǰ�˴����Ķ��mail_idƴ�ӵ��ַ���
	private String mailIds;
	//Mailxiangq
	private Mail detailMail;
	//�ظ�����
	private String replyContent;
	//���������
	private String orderType;
		
	//�����ҳ������
	public int getMailPage(int totalNum){
		/*String numStr = mailMapper.getTotalNum();
		if(numStr != null){
			recordNum = Integer.parseInt(numStr);
		}*/
		recordNum = totalNum;
		int page = 0;
		if(recordNum == 0){
			return page;
		}
		if(recordNum % PAGENUM == 0){
			page = recordNum / PAGENUM;
		}else{
			page = recordNum / PAGENUM + 1;
		}
		return page;
	}
	//������ҳ������
	public void setMailPage(int totalNum){
		allPage = getMailPage(totalNum);
		//System.out.println("allPage" + allPage);
		if(nowPage < 1){
			nowPage = 1;
		}
		if(nowPage > allPage){
			nowPage = allPage;
		}
	}
	//ɾ���˺ŵķ���
	public String deleteMail(){
		if(mailId != null && mailId != ""){
			mailMapper.deleteOneById(mailId);
		}
		if(mailMapper.findOneById(mailId) == null){
			request.put("deleteSuccess", "true");
		}
		return checkMailPage();
	}
	//����ɾ���˺ŵķ���
	public String deleteMails(){
		if(mailIds != null && mailIds != ""){
			String[] ids = mailIds.split(",");
			mailMapper.deleteByIds(ids);
			Mail mail = mailMapper.findOneById(ids[0]);
			if(mail == null){
				request.put("deleteSuccess", "true");
			}
		}
		return checkMailPage();
	}
	//�����ʼ��ķ���
	public String searchMail(){
		showAll = null;
		if(searchUser != null){
			String mailOrderType = (String) session.get("mailOrderType");
			if(orderType != null){
				searchUser.setMailOrderType(mailOrderType);
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
	        map.put("student_identification", searchUser.getStudent_identification());
	        map.put("student_name", searchUser.getStudent_name());
	        map.put("school_province", searchUser.getSchool_province());
	        map.put("school_city", searchUser.getSchool_city());
	        map.put("school_name", searchUser.getSchool_name());
	        map.put("mail_type", searchUser.getMail_type());
	        map.put("mail_theme", searchUser.getMail_theme());
	        map.put("mailOrderType", searchUser.getMailOrderType());
	        map.put("orderSeq", searchUser.getOrderSeq());
	        mailList = sqlSession.selectList("searchMail", map, new RowBounds((nowPage-1)*10, 10));
	        setMailPage(mailList.size());
			//mailList = mailMapper.searchMail(searchUser);
			if(mailList != null && mailList.size() > 0){
				/*int totalNum = mailList.size();
				recordNum = totalNum;
				if(totalNum % 10 == 0){
					allPage = totalNum / PAGENUM;
				}else{
					allPage = totalNum / PAGENUM + 1;
				}
				if(mailList.size() > PAGENUM){
					if(nowPage > allPage || nowPage <= 1){
						nowPage = 1;
						List<Mail> temp = new ArrayList<Mail>();
						for(int i=0; i<PAGENUM; i++){
							temp.add(mailList.get(i));
						}
						mailList = temp;
					}else{
						List<Mail> temp = new ArrayList<Mail>();
						int maxNum = PAGENUM * nowPage;
						if(maxNum > totalNum){
							maxNum = totalNum;
						}
						for(int i=0+PAGENUM*(nowPage-1); i<maxNum; i++){
							temp.add(mailList.get(i));
						}
						mailList = temp;
					}
				}else{
					nowPage = 1;
				}*/
				session.put("backSearchStudent", searchUser);
				searchErrorMsg = null;
				sqlSession.commit();
				sqlSession.clearCache();
				sqlSession.close();
			}else{
				searchErrorMsg = "�ܱ�Ǹ���������ݣ������²�ѯ��";
				showAll= "true";
				return checkMailPage();
			}
		}
		return "checkMailPage";
	}
	//�鿴�ʼ�����
	public String mailDetail(){
		if(mailId != null){
			detailMail = mailMapper.findOneById(mailId);
			detailMail.setMail_time(setTimeFormat(detailMail.getMail_time()));
		}
		return "mailDetailPage";
	}
	//�ظ�ҳ��
	public String replyMailPage(){
		if(mailId != null){
			detailMail = mailMapper.findOneById(mailId);
			detailMail.setMail_time(setTimeFormat(detailMail.getMail_time()));
		}
		return "mailReplyPage";
	}
	//�ظ���Ϣ
	public String replyMail(){
		if(mailId != null){
			detailMail = mailMapper.findOneById(mailId);
			System.out.println(detailMail);
			if(replyContent != null && replyContent != ""){
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyMMddHHmmss");//�������ڸ�ʽ
				String time = df.format(date);
				mailMapper.addOneMail(detailMail.getMail_theme(), replyContent, time, "�ظ�", detailMail.getStudent_id());
				if(mailMapper.getOneByTimeAndStuId(time,detailMail.getStudent_id()) != null){
					request.put("sendSuccess", "true");
				}
			}
		}
		return "mailReplyPage";
	}
	//�鿴�ʼ���ҳ��ķ����������������
	public String checkMailPage(){
		session.put("detailMail", null);
		if(orderType != null && orderType != ""){
			session.put("mailOrderType", orderType);
		}else if(orderType != null && orderType != ""){
			session.put("mailOrderType", null);	
		}
		if(session.get("orderSeq") == null){
			session.put("orderSeq", "DESC");
		}
		//���showAll�����£������û���Ϊ��
		if(showAll != null && showAll.equals("true")){
			searchUser = null;
			session.put("backSearchStudent", null);
		}
		//���session�д�ŵ������û���Ϊ�գ���ת������
		StudentSearch backSearchUser = (StudentSearch) session.get("backSearchStudent");
		if(backSearchUser != null){
			searchUser = backSearchUser;
			return searchMail();
		}
		//���������û�Ϊ��
		searchUser = null;
		session.put("backSearchStudent", null);
		String numStr = mailMapper.getTotalNum();
		int totalNum = 0;
		if(numStr != null){
			totalNum = Integer.parseInt(numStr);
		}
		setMailPage(totalNum);
		//�������
		try {
			sqlSession = sqlSessionFactory.getObject().openSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String orderType = (String) session.get("mailOrderType");
		if(orderType != null && orderType != ""){
			String orderSeq = (String) session.get("orderSeq");
			if(orderSeq.equals("DESC")){
				orderSeq = "ASC";
			}else if(orderSeq.equals("ASC")){
				orderSeq = "DESC";
			}
			session.put("orderSeq", orderSeq);
			Map<String, String> map = new HashMap<String, String>();
	        map.put("mailOrderType", orderType);
	        map.put("orderSeq", orderSeq);
	        mailList = sqlSession.selectList("findAllMail", map, new RowBounds((nowPage - 1) * PAGENUM, PAGENUM));
		}else{
			mailList = sqlSession.selectList("findAllMail", null, new RowBounds((nowPage - 1) * PAGENUM, PAGENUM));
		}
		//����ҳ���õ�����
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
		//Ϊ�շ���
		if(mailList == null || mailList.size() == 0){
			request.put("emptyMail", true);
		}
		//�������ڸ�ʽ
		for(int i=0; i<mailList.size(); i++){
			mailList.get(i).setMail_time(setTimeFormat(mailList.get(i).getMail_time()));
		}
		return "checkMailPage";
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
	//�����ʼ�����
	public String sendMailPage(){
		return "sendMailPage";
	}
	
	
	
	
	//setter and getter
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
	public List<Mail> getMailList() {
		return mailList;
	}
	public void setMailList(List<Mail> mailList) {
		this.mailList = mailList;
	}
	public StudentSearch getSearchUser() {
		return searchUser;
	}
	public void setSearchUser(StudentSearch searchUser) {
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
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMailIds() {
		return mailIds;
	}
	public void setMailIds(String mailIds) {
		this.mailIds = mailIds;
	}
	public Mail getDetailMail() {
		return detailMail;
	}
	public void setDetailMail(Mail detailMail) {
		this.detailMail = detailMail;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
}
