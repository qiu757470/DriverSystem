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
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月16日 上午10:26:08 
* 类说明 ：用户运营平台邮箱的Action
*/
@Controller
public class MailAction extends BaseAction {
	//mail数据库操作接口
	@Resource
	private MailMapper mailMapper;
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
	//全部mail的集合
	private List<Mail> mailList;
	//一页有多少条
	private static final int PAGENUM = 10;
	//前端传来的搜索数据
	private StudentSearch searchUser;
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
	//前端传来的mail_id
	private String mailId;
	//前端传来的多个mail_id拼接的字符串
	private String mailIds;
	//Mailxiangq
	private Mail detailMail;
	//回复内容
	private String replyContent;
	//排序的类型
	private String orderType;
		
	//获得总页数方法
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
	//设置总页数方法
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
	//删除账号的方法
	public String deleteMail(){
		if(mailId != null && mailId != ""){
			mailMapper.deleteOneById(mailId);
		}
		if(mailMapper.findOneById(mailId) == null){
			request.put("deleteSuccess", "true");
		}
		return checkMailPage();
	}
	//批量删除账号的方法
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
	//搜索邮件的方法
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
				searchErrorMsg = "很抱歉，查无数据，请重新查询。";
				showAll= "true";
				return checkMailPage();
			}
		}
		return "checkMailPage";
	}
	//查看邮件详情
	public String mailDetail(){
		if(mailId != null){
			detailMail = mailMapper.findOneById(mailId);
			detailMail.setMail_time(setTimeFormat(detailMail.getMail_time()));
		}
		return "mailDetailPage";
	}
	//回复页面
	public String replyMailPage(){
		if(mailId != null){
			detailMail = mailMapper.findOneById(mailId);
			detailMail.setMail_time(setTimeFormat(detailMail.getMail_time()));
		}
		return "mailReplyPage";
	}
	//回复消息
	public String replyMail(){
		if(mailId != null){
			detailMail = mailMapper.findOneById(mailId);
			System.out.println(detailMail);
			if(replyContent != null && replyContent != ""){
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyMMddHHmmss");//设置日期格式
				String time = df.format(date);
				mailMapper.addOneMail(detailMail.getMail_theme(), replyContent, time, "回复", detailMail.getStudent_id());
				if(mailMapper.getOneByTimeAndStuId(time,detailMail.getStudent_id()) != null){
					request.put("sendSuccess", "true");
				}
			}
		}
		return "mailReplyPage";
	}
	//查看邮件的页面的方法，不搜索的情况
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
		//如果showAll被按下，搜索用户设为空
		if(showAll != null && showAll.equals("true")){
			searchUser = null;
			session.put("backSearchStudent", null);
		}
		//如果session中存放的搜索用户不为空，跳转至搜索
		StudentSearch backSearchUser = (StudentSearch) session.get("backSearchStudent");
		if(backSearchUser != null){
			searchUser = backSearchUser;
			return searchMail();
		}
		//设置搜索用户为空
		searchUser = null;
		session.put("backSearchStudent", null);
		String numStr = mailMapper.getTotalNum();
		int totalNum = 0;
		if(numStr != null){
			totalNum = Integer.parseInt(numStr);
		}
		setMailPage(totalNum);
		//获得连接
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
		//根据页数得到集合
		sqlSession.commit();
		sqlSession.clearCache();
		sqlSession.close();
		//为空返回
		if(mailList == null || mailList.size() == 0){
			request.put("emptyMail", true);
		}
		//更改日期格式
		for(int i=0; i<mailList.size(); i++){
			mailList.get(i).setMail_time(setTimeFormat(mailList.get(i).getMail_time()));
		}
		return "checkMailPage";
	}
	//转化时间格式
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
	//发送邮件界面
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
