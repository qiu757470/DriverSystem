package org.great.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.great.tools.FileUtil;

import com.opensymphony.xwork2.ActionContext;

import org.great.bean.TeacherBean;
import org.great.mapper.TeacherMapper;
import org.great.tools.ExcelReader;

public class TeacherUploadAction {
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
	private  TeacherMapper teacherMapper;
    
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
				System.out.println("0"+s1[0]);
				System.out.println("1"+s1[1]);
				System.out.println("2"+s1[2]);
				System.out.println("3"+s1[3]);
				System.out.println("4"+s1[4]);
				System.out.println("5"+s1[5]);
				System.out.println("6"+s1[6]);
				System.out.println("7"+s1[7]);
				
				
				
				System.out.println("                    ///");
				TeacherBean teacher=new TeacherBean(null, s1[1], s1[1], s1[1].substring(6, s1[1].length()-6), s1[0], s1[2], s1[3], s1[4], s1[5], s1[6], "����", "5", s1[7], school_id);
				
				teacherMapper.createTeacher(teacher);
				
				System.out.println(map.size()+" size");
			}
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			
			
			
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
	public TeacherMapper getTeacherMapper() {
		return teacherMapper;
	}
	public void setTeacherMapper(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}
	
}
