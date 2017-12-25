package org.great.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.Course;
import org.great.bean.ExamPlan;
import org.great.bean.ManagerJurOne;
import org.great.bean.ManagerSearch;
import org.great.bean.School;
import org.great.bean.SchoolManager;
import org.great.bean.StuExamPlan;
import org.great.bean.Student;
import org.great.bean.Teacher;

@Mapper
public interface SchoolManagerMapper extends BaseMapper {

	public SchoolManager findUser(@Param("school_manager_account")String school_manager_account, @Param("school_manager_password")String school_manager_password);
	public List<ManagerJurOne> findMenuByAccount(@Param("school_manager_account")String school_manager_account);
	public School findSchoolInfoByAccount(@Param("school_manager_id")String school_manager_id);
	public void insertSchoolBean(School school);
	public void updateSchool(Map map);
	//��ҳ���ҿ��԰�����Ϣ
	public List<ExamPlan> findExamPlan(@Param("school_id")String school_id,@Param("exam_plan_state")String exam_plan_state,
			@Param("minnum")String minnum,@Param("maxnum")String maxnum );
	public void deleteExam(@Param("school_id")String school_id,@Param("epid")String epid);
	public ExamPlan updateExam(@Param("epid")String epid);
	public List<Teacher> selectTeacher(@Param("teacher_name")String teacher_name,@Param("school_id")String school_id);
	public List<Course> selectCourse(@Param("course_name")String course_name);
	public Course selectcourseIdByname(@Param("course_name")String course_name);
	public Teacher selectteacherIdByname(@Param("teacher_name")String teacher_name);
	public void updateExamPlan(ExamPlan examplan);
	//��ҳ���ҿ��԰����ڵ�ѧ��
	public ExamPlan studentExamInfo(@Param("epid")String epid,@Param("minnum")String minnum,@Param("maxnum")String maxnum);
	public Course selectcourseTimeById(@Param("course_id")String course_id);
    public List<Student> selectExamStudent(@Param("student_course_time")String student_course_time,@Param("course_name")String course_name,@Param("school_id")String school_id);
    public List<Teacher> selectexamTeacher(@Param("school_id")String school_id);
    public void insertNewExam();
    public void insertNewExamPlan(ExamPlan examplan);
    public String selectExam_planId();
    public void insertNewStu_exam_plan(StuExamPlan stuexamplan);
    public void deletestu_examplan(@Param("epid")String epid);
    public SchoolManager selectSchoolManager(@Param("school_manager_id")String school_manager_id);
    
    public void updateSchoolManager(@Param("school_manager_id")String school_manager_id,
    		@Param("school_manager_identification")String school_manager_identification,@Param("school_manager_name")String school_manager_name,
    		@Param("school_manager_sex")String school_manager_sex,@Param("school_manager_province")String school_manager_province
    		,@Param("school_manager_city")String school_manager_city,@Param("school_manager_address")String school_manager_address,
    		@Param("school_manager_phone")String school_manager_phone);
    
    
    public void updatePwd(@Param("school_manager_id")String school_manager_id,@Param("school_manager_password")String school_manager_password);
    //�����ɼ�¼��
   public void updateStuscore(@Param("student_id")String student_id,@Param("student_course_score")String student_course_score);
   //���԰���¼��״̬�ı�
   public void updateExamPlanInput(@Param("exam_plan_id")String exam_plan_id,@Param("exam_plan_input")String exam_plan_input);
   //��������δ��˵�ѧ��
   public List<Student> selectStudents(@Param("school_id")String school_id,@Param("student_state")String student_state,
		   @Param("minnum")String minnum,@Param("maxnum")String maxnum);
   //ѧ�����״̬�ı�
   public void updateStudentState(@Param("student_id")String student_id,@Param("student_state")String student_state);
	
   public List<SchoolManager> findAllManagers(Map<String, String> map);
	//�õ������ķ���
	public String getTotalNum();
	//�õ������û��ķ���
	public List<SchoolManager> searchManager(ManagerSearch searchUser);
	//ͨ���˺Ų����û��ķ���
	public SchoolManager findByAccount(@Param("account")String managerAccount);
	//ͨ��id�����û��ķ���
	public SchoolManager findById(@Param("school_manager_id")String managerId);
	//ͨ����Уid����
	public ArrayList<SchoolManager> findBySchoolId(@Param("school_id")String schoolId);
   
}
