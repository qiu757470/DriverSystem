package org.great.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.great.bean.School;
import org.great.mapper.SchoolManagerMapper;
import org.great.tools.FileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
	  /**
     * 接收拦截器传入的临时文件
     */
    private List<File> some;
    /**
     * 接收拦截器注入的原始文件名
     */
    private List<String> someFileName;
    @Resource
	private SchoolManagerMapper achoolManagerMapper;
	private String group_code;
	private String agree_acount;
	private String sc_name;
	private String cmbProvince;
	private String cmbCity;
	private String address;
	private String telephone;
	private String person;
	private String id;
	private String mobilephone;
	private String picture;
	private String otherdata;
    private String state;
    private String homepage;
    private String reason;
    private String oldaccount;
    private String sc_charge;
    private String str[]=new String[2];
    public String upload() {
//        if (some == null){
//            return "error";
//        }
        
        System.out.println(some.get(0).getName());
        System.out.println(someFileName.get(0));
        // 将文件放于项目部署路径下的upload文件夹下
        for(int i=0;i<some.size();i++){
        String path = "/upload/" + someFileName.get(i);
        System.out.println(path+"1111111=======");
        // 根据相对部署路径计算完整路径
        path = ServletActionContext.getServletContext().getRealPath(path);
        str[i]="upload/" + someFileName.get(i);
        // 将临时文件复制到上述路径下
        FileUtil.copy(some.get(i), new File(path));
        }
        
        System.out.println("未审核！！！！！！！");
		System.out.println("组织代码"+group_code);
		System.out.println(sc_name+"学校名称");
		System.out.println(id+"身份证");
	System.out.println(state);
	 picture=str[0];
	otherdata=str[1];
	School school=new School(null, sc_name, person, id, mobilephone, cmbProvince, cmbCity, address, telephone, agree_acount, group_code, state, picture, homepage, reason, otherdata);
	 achoolManagerMapper.insertSchoolBean(school);
	
		return SUCCESS;	
    }
    
    public String update() {
//      if (some == null){
//          return "error";
//      }
      Map<String, String> map=new HashMap<String, String>();
      System.out.println(some.get(0).getName());
      System.out.println(someFileName.get(0));
      // 将文件放于项目部署路径下的upload文件夹下
      for(int i=0;i<some.size();i++){
      String path = "/upload/" + someFileName.get(i);
      System.out.println(path+"1111111=======");
      
      //判断文件是否存在
      String urls=ServletActionContext.getServletContext().getRealPath("/upload");
      System.out.println("自动创建的路径"+urls);
      File uploadDir =new File(urls);
      if(!uploadDir.exists()){
      	uploadDir.mkdir(); 	
      }
      
      // 根据相对部署路径计算完整路径
      path = ServletActionContext.getServletContext().getRealPath(path);
      str[i]= "upload/" + someFileName.get(i);;
      // 将临时文件复制到上述路径下
      FileUtil.copy(some.get(i), new File(path));
      }
      
        System.out.println("未审核！！！！！！！");
		System.out.println("组织代码"+group_code);
		System.out.println(sc_name+"学校名称");
		System.out.println(id+"身份证");
		System.out.println(homepage+"主页");
	    System.out.println(state);
	    System.out.println(sc_charge);
	picture=str[0];
	otherdata=str[1];
	map.put("school_id", oldaccount);
	map.put("school_name", sc_name);
	map.put("school_representative_name", person);
	map.put("school_representative_id", id);
	map.put("school_representative_phone", mobilephone);
	map.put("school_province", cmbProvince);
	map.put("school_city", cmbCity);
	map.put("school_certificate", agree_acount);
	map.put("school_code", group_code);
	map.put("school_state", state);
	map.put("school_picture_url", picture);
	map.put("school_phone", telephone);
	map.put("school_homepage", homepage);
	map.put("school_file_url", otherdata);
	map.put("school_address", address);
	map.put("school_refuse_reason", reason);
	map.put("school_charge", sc_charge);
	achoolManagerMapper.updateSchool(map);
	
	return SUCCESS;	
  }
    
    
	 public List<File> getSome() {
			return some;
		}

		public void setSome(List<File> some) {
			this.some = some;
		}

		public List<String> getSomeFileName() {
			return someFileName;
		}

		public void setSomeFileName(List<String> someFileName) {
			this.someFileName = someFileName;
		}
		public String getGroup_code() {
			return group_code;
		}
		public void setGroup_code(String group_code) {
			this.group_code = group_code;
		}
		public String getAgree_acount() {
			return agree_acount;
		}
		public void setAgree_acount(String agree_acount) {
			this.agree_acount = agree_acount;
		}
		public String getSc_name() {
			return sc_name;
		}
		public void setSc_name(String sc_name) {
			this.sc_name = sc_name;
		}
		public String getCmbProvince() {
			return cmbProvince;
		}
		public void setCmbProvince(String cmbProvince) {
			this.cmbProvince = cmbProvince;
		}
		public String getCmbCity() {
			return cmbCity;
		}
		public void setCmbCity(String cmbCity) {
			this.cmbCity = cmbCity;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getPerson() {
			return person;
		}
		public void setPerson(String person) {
			this.person = person;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMobilephone() {
			return mobilephone;
		}
		public void setMobilephone(String mobilephone) {
			this.mobilephone = mobilephone;
		}
		public String getPicture() {
			return picture;
		}
		public void setPicture(String picture) {
			this.picture = picture;
		}
		public String getOtherdata() {
			return otherdata;
		}
		public void setOtherdata(String otherdata) {
			this.otherdata = otherdata;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getHomepage() {
			return homepage;
		}
		public void setHomepage(String homepage) {
			this.homepage = homepage;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}

		public String getOldaccount() {
			return oldaccount;
		}

		public void setOldaccount(String oldaccount) {
			this.oldaccount = oldaccount;
		}

		public String getSc_charge() {
			return sc_charge;
		}

		public void setSc_charge(String sc_charge) {
			this.sc_charge = sc_charge;
		}
		
}
