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
	//ͨ��ʡ�ݺͳ��в��Ҽ�У
		public List<School> findByProvinceAndCity(@Param("province")String province, @Param("city")String city);
		//ͨ��ʡ�ݡ����к����Ʋ��Ҽ�У
		public School findByProvinceAndCityAndName(@Param("school_province")String school_province, @Param("school_city")String school_city, @Param("school_name")String school_name);
		//ͨ��id����
		public School findById(@Param("school_id")String school_id);
		//�������
		public String getTotalNum();
		//�õ������û�
		public List<School> findAllSchools(Map<String, String> map);
		//ͨ������������ѯ��У
		public List<School> searchSchool(SchoolSearch searchSchool);
		//�������id
		public Integer getMaxId();
		//���������δ��ˣ�
		public String getTotalNumUnverify();
		//�޸ļ�У��״̬�;ܾ�����
		public Integer alterStateAndReason(@Param("school_id")String school_id, @Param("school_state")String school_state, @Param("school_refuse_reason")String school_refuse_reason);
		
		//��ù���Ա������
		public Integer getManagerNum(@Param("school_id")String school_id);
		//��ý���������
		public Integer getTeacherNum(@Param("school_id")String school_id);
		//���ѧԱ������
		public Integer getStudentNum(@Param("school_id")String school_id);
		
		//ͨ��ʡ���ҵ��õ������еļ�У   �����淢���õ�
	   List<School> findSchoolid(@Param("province")String province, @Param("city")String city);

}
