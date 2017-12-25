package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.TeacherEvaluation;

@Mapper
public interface TeacherEvaluationMapper extends BaseMapper {
public List selectTeacherEvaluationInfoByStudentId(@Param("student_id")String student_id);
	
	public int insertStudentEvaTeacher(TeacherEvaluation teacherEvaluatton);
	public void deleteByStudentId(@Param("student_id")String id);

	public TeacherEvaluation findByStudentId(@Param("student_id")String id);

	public void deleteByTeacherId(@Param("teacher_id")String id);

}
