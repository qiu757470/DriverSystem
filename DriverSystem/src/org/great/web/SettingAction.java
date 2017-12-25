package org.great.web;

import javax.annotation.Resource;

import org.great.bean.TransUser;
import org.great.mapper.TransUserMapper;
import org.springframework.stereotype.Controller;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��15�� ����9:22:29 
* ��˵�� 
*/
@Controller
public class SettingAction extends BaseAction {

	//������
	private String oldPwd;
	//������
	private String newPwd;
	//ȷ������
	private String confirmPwd;
	//������Ϣ
	private String message;
	//mapper�ӿ�
	@Resource
	private TransUserMapper transUserMapper;
	//�޸ĵ��û���
	private String alterName;
	//�޸ĵ�ʡ��
	private String alterProvince;
	//�޸ĵĳ���
	private String alterCity;
	//�޸ĵĵ�ַ
	private String alterAddress;
	//�޸ĵ��Ա�
	private String alterSex;
	//�޸ĵ����֤
	private String alterID;
	//�޸ĵĵ绰
	private String alterPhone;
	
	//��ʾ�޸��������ķ���
	public String revisePassword(){
		return "revisePasswordPage";
	}
	//ȷ���޸�����ķ���
	public String confirmRevisePwd(){
		TransUser loginUser = (TransUser) session.get("loginTransUser");
		if(oldPwd != null && newPwd != null && confirmPwd != null && oldPwd != "" && newPwd != "" && confirmPwd != ""){
			if(!oldPwd.equals(loginUser.getTrans_user_password())){
				message = "���������";
				return "revisePasswordPage";
			}
			if(!newPwd.equals(confirmPwd)){
				message = "�������벻һ�£�";
				return "revisePasswordPage";
			}
			transUserMapper.revisePwdByAccount(loginUser.getTrans_user_account(), newPwd);
			TransUser newUser = transUserMapper.findTransUser(loginUser.getTrans_user_account(), newPwd);
			if(newUser != null){
				message = "�޸ĳɹ���,�����˳��������µ�¼��";
				session.put("loginTransUser", newUser);
				return "revisePasswordPage";
			}
		}
		message = "�޸�ʧ�ܣ�";
		return "revisePasswordPage";
	}
	//��ʾ�޸ĸ�����Ϣ�Ľ���
	public String revisePersonInfo(){
		return "revisePersonInfoPage";
	}
	//�޸ĸ������ϵĽ���
	public String confirmReviseData(){
		if(alterName != null && alterProvince != null && alterCity != null){
			TransUser loginUser = (TransUser) session.get("loginTransUser");
			transUserMapper.alterOneByNameAndPlace(loginUser.getTrans_user_account(), alterName, alterProvince, alterCity,alterAddress, alterSex, alterID, alterPhone);
			TransUser newUser = transUserMapper.findTransUser(loginUser.getTrans_user_account(), loginUser.getTrans_user_password());
			if(newUser.getTrans_user_name().equals(alterName) && newUser.getTrans_user_province().equals(alterProvince) && newUser.getTrans_user_city().equals(alterCity)){
				message = "�޸ĳɹ���";
				session.put("loginTransUser", newUser);
			}else{
				message = "�޸�ʧ�ܣ�";
			}
		}
		return "revisePersonInfoPage";
		
	}
	
	//���ڵķ���
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
