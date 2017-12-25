package org.great.web;

import javax.annotation.Resource;

import org.great.bean.TransUser;
import org.great.mapper.TransUserMapper;
import org.springframework.stereotype.Controller;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月15日 上午9:22:29 
* 类说明 
*/
@Controller
public class SettingAction extends BaseAction {

	//旧密码
	private String oldPwd;
	//新密码
	private String newPwd;
	//确认密码
	private String confirmPwd;
	//返回消息
	private String message;
	//mapper接口
	@Resource
	private TransUserMapper transUserMapper;
	//修改的用户名
	private String alterName;
	//修改的省份
	private String alterProvince;
	//修改的城市
	private String alterCity;
	//修改的地址
	private String alterAddress;
	//修改的性别
	private String alterSex;
	//修改的身份证
	private String alterID;
	//修改的电话
	private String alterPhone;
	
	//显示修改密码界面的方法
	public String revisePassword(){
		return "revisePasswordPage";
	}
	//确认修改密码的方法
	public String confirmRevisePwd(){
		TransUser loginUser = (TransUser) session.get("loginTransUser");
		if(oldPwd != null && newPwd != null && confirmPwd != null && oldPwd != "" && newPwd != "" && confirmPwd != ""){
			if(!oldPwd.equals(loginUser.getTrans_user_password())){
				message = "旧密码错误！";
				return "revisePasswordPage";
			}
			if(!newPwd.equals(confirmPwd)){
				message = "两次密码不一致！";
				return "revisePasswordPage";
			}
			transUserMapper.revisePwdByAccount(loginUser.getTrans_user_account(), newPwd);
			TransUser newUser = transUserMapper.findTransUser(loginUser.getTrans_user_account(), newPwd);
			if(newUser != null){
				message = "修改成功！,即将退出，请重新登录！";
				session.put("loginTransUser", newUser);
				return "revisePasswordPage";
			}
		}
		message = "修改失败！";
		return "revisePasswordPage";
	}
	//显示修改个人信息的界面
	public String revisePersonInfo(){
		return "revisePersonInfoPage";
	}
	//修改个人资料的界面
	public String confirmReviseData(){
		if(alterName != null && alterProvince != null && alterCity != null){
			TransUser loginUser = (TransUser) session.get("loginTransUser");
			transUserMapper.alterOneByNameAndPlace(loginUser.getTrans_user_account(), alterName, alterProvince, alterCity,alterAddress, alterSex, alterID, alterPhone);
			TransUser newUser = transUserMapper.findTransUser(loginUser.getTrans_user_account(), loginUser.getTrans_user_password());
			if(newUser.getTrans_user_name().equals(alterName) && newUser.getTrans_user_province().equals(alterProvince) && newUser.getTrans_user_city().equals(alterCity)){
				message = "修改成功！";
				session.put("loginTransUser", newUser);
			}else{
				message = "修改失败！";
			}
		}
		return "revisePersonInfoPage";
		
	}
	
	//关于的方法
	public String about(){
		return "aboutPage";
	}
	
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAlterName() {
		return alterName;
	}
	public void setAlterName(String alterName) {
		this.alterName = alterName;
	}
	public String getAlterProvince() {
		return alterProvince;
	}
	public void setAlterProvince(String alterProvince) {
		this.alterProvince = alterProvince;
	}
	public String getAlterCity() {
		return alterCity;
	}
	public void setAlterCity(String alterCity) {
		this.alterCity = alterCity;
	}
	public String getAlterAddress() {
		return alterAddress;
	}
	public void setAlterAddress(String alterAddress) {
		this.alterAddress = alterAddress;
	}
	public String getAlterSex() {
		return alterSex;
	}
	public void setAlterSex(String alterSex) {
		this.alterSex = alterSex;
	}
	public String getAlterID() {
		return alterID;
	}
	public void setAlterID(String alterID) {
		this.alterID = alterID;
	}
	public String getAlterPhone() {
		return alterPhone;
	}
	public void setAlterPhone(String alterPhone) {
		this.alterPhone = alterPhone;
	}
	
}
