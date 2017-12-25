package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StuExamPlan;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��21�� ����5:09:28 
* ��˵�� 
*/
@Mapper
public interface StuExamPlanMapper extends BaseMapper {

	public void deleteByStudentId(@Param("student_id")String id);

	public List<StuExamPlan> findByStudentId(@Param("student_id")String id);

}
