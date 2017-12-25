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

	//��ҳ����
	public List<StudentBean>  findByPage(@Param("pagemax")int pagemax,@Param("pagemin")int pagemin);
	
	//����ѧԱ��
	public int studentNum(StudentBean student);
	public int studentallNum();
	
	//��������ѧԱ
	public StudentBean  findStudentBySid(@Param("student_id")String student_id);
	public StudentBean  findStudentBySidentifiction(@Param("student_identification")String student_identification);
	
	//���ҽ���
	public TeacherBean  findTeacherBySid(@Param("student_id")String student_id);
	
	//�������н���
	//������������
	public List<TeacherBean>  findAllTeacher(@Param("teacher_name")String teacher_name);
	public TeacherBean  findTeacherByTname(@Param("teacher_name")String teacher_name);
	
	
	//����������
	public List<StudentBean>  searchUser(StudentBean student);
	
	//�����û�
	public void createUser(StudentBean student);
	
	//ɾ��
	public void delectUser(@Param("student_id")String student_id);
	
	//����
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

	//�޸�����
	public int updatePwd(@Param("newStudentPassword")String newStudentPassword,@Param("student_id")String student_id);
    //�ж������Ƿ���ȷ
	public Object judgPassword(@Param("oldStudentPassword")String newStudentPassword,@Param("student_id")String student_id);


	//�������е�ѧԱ
	//ͨ����У����ѧԱ
	public ArrayList<Student> findBySchoolId(@Param("school_id")String schoolId);
	//ͨ��ѧԱidɾ��
	public void deleteByStudentId(@Param("student_id")String id);
	//ͨ��ѧԱid��ѯ
	public Student findByStudentId(@Param("student_id")String id);
	//���ݼ�Уid�ҵ����е�ѧ��
	List<Student> findAllStudent(String schoolid);

	public void insertStuCourse(StudentCourse s);

	public String selectmax();

	//ͨ��ע��ʱ�����
	public Integer searchByRegisterTimeNum(@Param("time")String time);
	//ͨ��ע��ʱ�����
	public ArrayList<Student> searchByRegisterTime(@Param("time")String time);
	
	public Integer searchByGraduateTimeNum(@Param("time")String time);
	
	public ArrayList<Student> searchByGraduateTime(@Param("time")String time);

}
