package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.StuExamPlan;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月21日 下午5:09:28 
* 类说明 
*/
@Mapper
public interface StuExamPlanMapper extends BaseMapper {

	public void deleteByStudentId(@Param("student_id")String id);

	public List<StuExamPlan> findByStudentId(@Param("student_id")String id);

}
