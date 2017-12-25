package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StudentNotice;
@Mapper
public interface StuNoticeMapper extends BaseMapper{
	public void deleteByStudentId(@Param("student_id")String id);

	public List<StudentNotice> findByStudentId(@Param("student_id")String id);
	 //但公告对象会是学员时，插入学员公告信息表
	public void addStudentNotice(StudentNotice studentNotic);
	//根据某条公告id删除学生公告表
	public void delStudentNotic(String transnoticeid);
}
