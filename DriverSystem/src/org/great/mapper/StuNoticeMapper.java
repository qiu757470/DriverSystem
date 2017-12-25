package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StudentNotice;
@Mapper
public interface StuNoticeMapper extends BaseMapper{
	public void deleteByStudentId(@Param("student_id")String id);

	public List<StudentNotice> findByStudentId(@Param("student_id")String id);
	 //������������ѧԱʱ������ѧԱ������Ϣ��
	public void addStudentNotice(StudentNotice studentNotic);
	//����ĳ������idɾ��ѧ�������
	public void delStudentNotic(String transnoticeid);
}
