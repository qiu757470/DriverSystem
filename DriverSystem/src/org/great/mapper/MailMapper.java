package org.great.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.Mail;
import org.great.bean.StudentSearch;

@Mapper
public interface MailMapper extends BaseMapper{
	public List getMailInfoByStudentId(@Param("student_id")String student_id,@Param("pageMin")int pageMin,@Param("pageMax")int pageMax);
	public int delRreplyInfoById(@Param("mail_id")String mail_id);
	public Mail selectClickMailById(@Param("delRreplyInfoId")String delRreplyInfoId);
	public int insertMail(Mail mail);
	//ȫѡɾ��
	public int deleteAll(List delList);
	public String getTotalNum();
	//�õ����еļ�¼����
	public List<Mail> findAllMail(Map<String, String> map);
	//ͨ������������ѯ�����ʼ�
	public List<Mail> searchMail(StudentSearch searchUser);
	//ͨ��id����һ��
	public Mail findOneById(@Param("mail_id")String mailId);
	//ͨ��idƴ�ӳɵ��ַ���ɾ��
	public void deleteByIds(@Param("ids")String[] mailIds);
	//ͨ��ʱ���ѧԱid����һ��
	public Object getOneByTimeAndStuId(@Param("mail_time")String time, @Param("student_id")String student_id);
	//ͨ��ѧԱidɾ��
	public void deleteByStudentId(@Param("student_id")String id);
	//ͨ��ѧԱid����
	public List<Mail> findByStudentId(@Param("student_id")String id);
	//ͨ����������һ��
	public void addOneMail(@Param("mail_theme")String mail_theme, @Param("mail_content")String replyContent, @Param("mail_time")String time, 
			@Param("mail_type")String string, @Param("student_id")String student_id);
}
