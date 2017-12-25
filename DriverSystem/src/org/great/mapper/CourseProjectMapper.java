package org.great.mapper;

import java.util.List;

import org.great.annotation.Mapper;
import org.great.bean.CourseProject;

@Mapper
public interface CourseProjectMapper extends BaseMapper{
	List<CourseProject> findAllByCourseProject();
}
