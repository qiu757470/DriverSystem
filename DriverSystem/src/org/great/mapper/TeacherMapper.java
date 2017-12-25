package org.great.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.Course;
import org.great.bean.StudentBean;
import org.great.bean.Teacher;
import org.great.bean.TeacherBean;

@Mapper
public interface TeacherMapper extends BaseMapper {
	public List<TeacherBean>  findTeacher();
	public TeacherBean  selectByTeacherId(@Param("teacher_id")String teacher_id);
	
	//查找教练总数
	public int teacherNum(TeacherBean teacher);
	
	//按条件查找
		public List<TeacherBean>  searchTeacher(TeacherBean teacher);
		public int teacherallNum();
		public void createTeacher(TeacherBean teacher);
		public List<StudentBean> selectStudent(String teacher_id);
		public void teacherDelete(String teacher_id);
		public Course searchCourseId(String course_name);
		public void upadateTeacher(TeacherBean teacher);
		
		public List<Teacher> selectFromTeacher(@Param("course_id")String course_id);
		
		public Teacher selectTeacherName(@Param("teacher_id")String teacher_id);
		//通过驾校id查找所有教练
		public ArrayList<Teacher> findBySchoolId(@Param("school_id")String schoolId);

		public Teacher findById(@Param("teacher_id")String id);
}
