package org.great.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.Student;
import org.great.bean.StudentBean;
import org.great.bean.StudentCourse;
import org.great.bean.TeacherBean;

@Mapper
public interface StudentMapper extends BaseMapper {
	public List<StudentBean>  findAllUser();
	
	public Student findStudentByAccAndPassword(@Param("user_account")String user_account,@Param("user_password") String user_password);

	//按页查找
	public List<StudentBean>  findByPage(@Param("pagemax")int pagemax,@Param("pagemin")int pagemin);
	
	//查找学员数
	public int studentNum(StudentBean student);
	public int studentallNum();
	
	//查找所有学员
	public StudentBean  findStudentBySid(@Param("student_id")String student_id);
	public StudentBean  findStudentBySidentifiction(@Param("student_identification")String student_identification);
	
	//查找教练
	public TeacherBean  findTeacherBySid(@Param("student_id")String student_id);
	
	//查找所有教练
	//查找其他教练
	public List<TeacherBean>  findAllTeacher(@Param("teacher_name")String teacher_name);
	public TeacherBean  findTeacherByTname(@Param("teacher_name")String teacher_name);
	
	
	//按条件查找
	public List<StudentBean>  searchUser(StudentBean student);
	
	//创建用户
	public void createUser(StudentBean student);
	
	//删除
	public void delectUser(@Param("student_id")String student_id);
	
	//更新
	public void  updateUser(@Param("student_identification")String student_identification,
			@Param("student_name")String student_name,
			
			@Param("student_sex")String student_sex,
			@Param("student_province")String student_province,
			@Param("student_city")String student_city,
			@Param("student_phone")String student_phone,
			@Param("student_address")String student_address,
			@Param("student_id")String student_id,
	@Param("teacher_id")String teacher_id);


	public String searchSchool(String school_id);

	public Student selectStudentNameByStudentId(@Param("student_id")String student_id);

	//修改密码
	public int updatePwd(@Param("newStudentPassword")String newStudentPassword,@Param("student_id")String student_id);
    //判断密码是否正确
	public Object judgPassword(@Param("oldStudentPassword")String newStudentPassword,@Param("student_id")String student_id);


	//查找所有的学员
	//通过驾校查找学员
	public ArrayList<Student> findBySchoolId(@Param("school_id")String schoolId);
	//通过学员id删除
	public void deleteByStudentId(@Param("student_id")String id);
	//通过学员id查询
	public Student findByStudentId(@Param("student_id")String id);
	//根据驾校id找到所有的学生
	List<Student> findAllStudent(String schoolid);

	public void insertStuCourse(StudentCourse s);

	public String selectmax();

	//通过注册时间查找
	public Integer searchByRegisterTimeNum(@Param("time")String time);
	//通过注册时间查找
	public ArrayList<Student> searchByRegisterTime(@Param("time")String time);
	
	public Integer searchByGraduateTimeNum(@Param("time")String time);
	
	public ArrayList<Student> searchByGraduateTime(@Param("time")String time);

}
