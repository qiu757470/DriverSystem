package org.great.mapper;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.Course;

@Mapper
public interface CourseMapper extends BaseMapper{
	public Course selectCourseState(@Param("courseSelect")String courseSelect);

}
