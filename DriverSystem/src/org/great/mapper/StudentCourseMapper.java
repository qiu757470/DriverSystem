package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StudentCourse;

@Mapper
public interface StudentCourseMapper extends BaseMapper{
	public StudentCourse findAllById(@Param("course_id")String course_id,@Param("student_id")String student_id);

	public StudentCourse selectAStudentCourseAndStudent(@Param("course_id")String course_id, @Param("student_id")String student_id);



	public int updateStudentCourse(StudentCourse studentCourse);
	public void deleteByStudentId(@Param("student_id")String id);

	public List<StudentCourse> findByStudentId(@Param("student_id")String id);

}
