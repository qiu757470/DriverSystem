package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.ExamPlan;

@Mapper
public interface ExamPlanMapper extends BaseMapper{
	public void deleteByTeacherId(@Param("teacher_id")String id);

	public List<ExamPlan> findByTeacherId(@Param("teacher_id")String id);

	public void deleteBySchoolId(@Param("school_id")String schoolId);

	public List<ExamPlan> findBySchoolId(@Param("school_id")String schoolId);
}
