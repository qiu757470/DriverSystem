package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.School;
import org.great.bean.SchoolNotice;
import org.great.bean.StudentBean;
import org.great.bean.TransNotice;
@Mapper
public interface SchoolNoticeMapper extends BaseMapper{

	public List<TransNotice> findNotice(@Param("trans_notice_province")String trans_notice_province,@Param("trans_notice_city")String trans_notice_city, @Param("school_id")String school_id);
	public List<TransNotice> findhasNotice(@Param("trans_notice_province")String trans_notice_province,@Param("trans_notice_city")String trans_notice_city,@Param("school_id")String school_id);

	public TransNotice checkNotice(String trans_notice_id);

	public School schoolCity(@Param("school_id")String school_id);

	public List<StudentBean> student(String school_id);


	public void insertTnotice(TransNotice transNotice);
	public String selectmax();

	public void insertsNote(@Param("max")String max, @Param("student_id")String student_id);
	public void updatestu(String trans_notice_id);
	public List<TransNotice> NoticehasSend(String school_id);
	 //���������Ϊ��Уʱ�������У������Ϣ��
		public void addSchoolNotice(SchoolNotice schoolNotice);
		//���ݹ���idɾ��ĳ����У����������
		public void delSchoolNotice(@Param("trans_notice_id")String transnoticeid);
		//ͨ����Уidɾ��
		public void deleteBySchoolId(@Param("school_id")String schoolId);
		//ͨ����Уid����
		public List<SchoolNotice> findBySchoolId(@Param("school_id")String schoolId);

}
