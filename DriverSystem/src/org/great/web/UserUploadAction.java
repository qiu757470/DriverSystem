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
     * 接收拦截器传入的临时文件
     */
    private File some;
    /**
     * 接收拦截器注入的原始文件名
     */
    private String someFileName;
    private String[] s1;//表1
    
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
        // 将文件放于项目部署路径下的upload文件夹下
        String path = "/upload/" + someFileName;
        // 根据相对部署路径计算完整路径
        path = ServletActionContext.getServletContext().getRealPath(path);
       
        // 将临时文件复制到上述路径下
        FileUtil.copy(some, new File(path));
        
        
       
        try {
        	String school_id=(String) ActionContext.getContext().getSession().get("school_id");
			
        	InputStream is = new FileInputStream(path);
			ExcelReader excelReader = new ExcelReader();
			String[] title = excelReader.readExcelTitle(is);
			System.out.println("获得Excel表格的标题:");
			for (String s : title) {
				System.out.print(s + " ");

			}
			 System.out.println("路径                               "+path);
			Map<Integer, String> map = excelReader.readExcelContent(is);
			for (int i = 1; i <= map.size(); i++) {
				s1=(map.get(i)).split("#");
				System.out.println("身份证"+s1[0]);
				System.out.println("姓名"+s1[1]);
				System.out.println("性别"+s1[2]);
				System.out.println("电话"+s1[3]);
				System.out.println("省份"+s1[4]);
				System.out.println("市区"+s1[5]);
				System.out.println("地址"+s1[6]);
				System.out.println("时间"+s1[7]);
				System.out.println("教练"+s1[8]);
				teacher =studentMapper.findTeacherByTname(s1[8]);
				
				
				StudentBean student=new StudentBean(null, s1[0], s1[0].substring(6, s1[0].length()-6), s1[1], s1[2], s1[4], s1[5], "1", s1[3], s1[6], "审核通过", school_id, teacher.getTeacher_id(),s1[7]);
				//StudentBean student=new StudentBean(null, s1[0], s1[0].substring(6, s1[0].length()-6), s1[1], s1[2], "科目一", s1[3], s1[4], "可用", "2", "2");
				
				//StudentBean student1=new StudentBean(student_id, student_identification, student_password, student_name, student_sex, student_province, student_city, course_id, student_phone, student_address, student_state, school_id, teacher_id)
				studentMapper.createUser(student);
				
				String student_id=studentMapper.selectmax();
				StudentCourse s=new StudentCourse(null, student_id, "1", teacher.getTeacher_id(), "", "","未考试");
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
