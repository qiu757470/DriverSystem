package org.great.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.great.tools.FileUtil;

import com.opensymphony.xwork2.ActionContext;

import org.great.bean.StudentBean;
import org.great.bean.StudentCourse;
import org.great.bean.TeacherBean;
import org.great.mapper.StudentMapper;
import org.great.tools.ExcelReader;

public class UserUploadAction {
	  /**
     * �����������������ʱ�ļ�
     */
    private File some;
    /**
     * ����������ע���ԭʼ�ļ���
     */
    private String someFileName;
    private String[] s1;//��1
    
    @Resource
	private  StudentMapper studentMapper;
    private TeacherBean teacher;
    
    public String execute() {
    	
    	 System.out.println("aaaaa"+some);
        if (some == null){
        	 System.out.println("aaaaaa1");
            return "error";
        }
        
        System.out.println(some);
        System.out.println(someFileName);
        // ���ļ�������Ŀ����·���µ�upload�ļ�����
        String path = "/upload/" + someFileName;
        // ������Բ���·����������·��
        path = ServletActionContext.getServletContext().getRealPath(path);
       
        // ����ʱ�ļ����Ƶ�����·����
        FileUtil.copy(some, new File(path));
        
        
       
        try {
        	String school_id=(String) ActionContext.getContext().getSession().get("school_id");
			
        	InputStream is = new FileInputStream(path);
			ExcelReader excelReader = new ExcelReader();
			String[] title = excelReader.readExcelTitle(is);
			System.out.println("���Excel���ı���:");
			for (String s : title) {
				System.out.print(s + " ");

			}
			 System.out.println("·��                               "+path);
			Map<Integer, String> map = excelReader.readExcelContent(is);
			for (int i = 1; i <= map.size(); i++) {
				s1=(map.get(i)).split("#");
				System.out.println("���֤"+s1[0]);
				System.out.println("����"+s1[1]);
				System.out.println("�Ա�"+s1[2]);
				System.out.println("�绰"+s1[3]);
				System.out.println("ʡ��"+s1[4]);
				System.out.println("����"+s1[5]);
				System.out.println("��ַ"+s1[6]);
				System.out.println("ʱ��"+s1[7]);
				System.out.println("����"+s1[8]);
				teacher =studentMapper.findTeacherByTname(s1[8]);
				
				
				StudentBean student=new StudentBean(null, s1[0], s1[0].substring(6, s1[0].length()-6), s1[1], s1[2], s1[4], s1[5], "1", s1[3], s1[6], "���ͨ��", school_id, teacher.getTeacher_id(),s1[7]);
				//StudentBean student=new StudentBean(null, s1[0], s1[0].substring(6, s1[0].length()-6), s1[1], s1[2], "��Ŀһ", s1[3], s1[4], "����", "2", "2");
				
				//StudentBean student1=new StudentBean(student_id, student_identification, student_password, student_name, student_sex, student_province, student_city, course_id, student_phone, student_address, student_state, school_id, teacher_id)
				studentMapper.createUser(student);
				
				String student_id=studentMapper.selectmax();
				StudentCourse s=new StudentCourse(null, student_id, "1", teacher.getTeacher_id(), "", "","δ����");
				studentMapper.insertStuCourse(s);
				
				System.out.println(map.size()+" size");
			}
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
        
        
        
        //<param name="nameSpace">/driveSchool</param>
       // <param name="actionName">DriveSchoolUserAction</param>
        
        
        
        
        
        return "success";
    }
    public File getSome() {
        return some;
    }
    public void setSome(File some) {
        this.some = some;
    }
    public String getSomeFileName() {
        return someFileName;
    }
    public void setSomeFileName(String someFileName) {
        this.someFileName = someFileName;
    }
	public StudentMapper getStudentMapper() {
		return studentMapper;
	}
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

}
