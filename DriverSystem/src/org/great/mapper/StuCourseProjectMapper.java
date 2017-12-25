package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StuCourseProject;

@Mapper
public interface StuCourseProjectMapper extends BaseMapper {

	public List<StuCourseProject> findAllByCourseProject(@Param("courseSelect")String courseSelect,@Param("pageMin")int min,@Param("pageMax")int max);


	public List<StuCourseProject> examFindAllByCourseProject(String courseSelect);
}
