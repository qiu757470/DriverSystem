package org.great.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.School;
import org.great.bean.SchoolSearch;
import org.great.bean.Student;
import org.great.bean.Teacher;

@Mapper
public interface SchoolMapper extends BaseMapper{

	public List<Teacher> selectTeacherBySchool(String school_name);

   public List<School> selectSchoolByProcity(@Param("school_province")String cmbProvince,@Param("school_city") String cmbCity);

   public int insertStudent(Student student);
   
   
   public List<?> selectAllFromSchool();

	public School selectStudentAndSchoolByStudentId(@Param("student_id")String student_id);
	//通过省份和城市查找驾校
		public List<School> findByProvinceAndCity(@Param("province")String province, @Param("city")String city);
		//通过省份、城市和名称查找驾校
		public School findByProvinceAndCityAndName(@Param("school_province")String school_province, @Param("school_city")String school_city, @Param("school_name")String school_name);
		//通过id查找
		public School findById(@Param("school_id")String school_id);
		//获得总数
		public String getTotalNum();
		//得到所有用户
		public List<School> findAllSchools(Map<String, String> map);
		//通过搜索条件查询驾校
		public List<School> searchSchool(SchoolSearch searchSchool);
		//获得最大的id
		public Integer getMaxId();
		//获得总数（未审核）
		public String getTotalNumUnverify();
		//修改驾校的状态和拒绝理由
		public Integer alterStateAndReason(@Param("school_id")String school_id, @Param("school_state")String school_state, @Param("school_refuse_reason")String school_refuse_reason);
		
		//获得管理员总人数
		public Integer getManagerNum(@Param("school_id")String school_id);
		//获得教练总人数
		public Integer getTeacherNum(@Param("school_id")String school_id);
		//获得学员总人数
		public Integer getStudentNum(@Param("school_id")String school_id);
		
		//通过省市找到该地区所有的驾校   做公告发布用的
	   List<School> findSchoolid(@Param("province")String province, @Param("city")String city);

}
