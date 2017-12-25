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
	//全选删除
	public int deleteAll(List delList);
	public String getTotalNum();
	//得到所有的记录集合
	public List<Mail> findAllMail(Map<String, String> map);
	//通过搜索条件查询搜索邮件
	public List<Mail> searchMail(StudentSearch searchUser);
	//通过id查找一条
	public Mail findOneById(@Param("mail_id")String mailId);
	//通过id拼接成的字符串删除
	public void deleteByIds(@Param("ids")String[] mailIds);
	//通过时间和学员id查找一条
	public Object getOneByTimeAndStuId(@Param("mail_time")String time, @Param("student_id")String student_id);
	//通过学员id删除
	public void deleteByStudentId(@Param("student_id")String id);
	//通过学员id查找
	public List<Mail> findByStudentId(@Param("student_id")String id);
	//通过参数插入一条
	public void addOneMail(@Param("mail_theme")String mail_theme, @Param("mail_content")String replyContent, @Param("mail_time")String time, 
			@Param("mail_type")String string, @Param("student_id")String student_id);
}
