package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StudentNotice;
/** 
* @author ���ߣ���ΰ��
* @version ����ʱ�䣺2017��8��10��
* ��˵�� ��ѧԱ�����Mapper�ӿ�
*/
@Mapper
public interface StudentNoticeMapper extends BaseMapper{
	
	public void deleteByStudentId(@Param("student_id")String id);

	public List<StudentNotice> findByStudentId(@Param("student_id")String id);
	 //������������ѧԱʱ������ѧԱ������Ϣ��
	public void addStudentNotice(StudentNotice studentNotic);
	//����ĳ������idɾ��ѧ�������
	public void delStudentNotic(String transnoticeid);
}
