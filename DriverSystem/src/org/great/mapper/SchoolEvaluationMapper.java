package org.great.mapper;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.SchoolEvaluation;

@Mapper
public interface SchoolEvaluationMapper extends BaseMapper {
	public int updateStudentToSchoolEvaluatton(SchoolEvaluation schoolEvaluatton);
	public void deleteByStudentId(@Param("student_id")String id);

	public SchoolEvaluation findByStudentId(@Param("student_id")String id);
}
