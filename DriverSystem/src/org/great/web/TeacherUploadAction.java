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
     * 接收拦截器传入的临时文件
     */
    private File some;
    /**
     * 接收拦截器注入的原始文件名
     */
    private String someFileName;
    private String[] s1;//表1
    
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
				System.out.println("0"+s1[0]);
				System.out.println("1"+s1[1]);
				System.out.println("2"+s1[2]);
				System.out.println("3"+s1[3]);
				System.out.println("4"+s1[4]);
				System.out.println("5"+s1[5]);
				System.out.println("6"+s1[6]);
				System.out.println("7"+s1[7]);
				
				
				
				System.out.println("                    ///");
				TeacherBean teacher=new TeacherBean(null, s1[1], s1[1], s1[1].substring(6, s1[1].length()-6), s1[0], s1[2], s1[3], s1[4], s1[5], s1[6], "正常", "5", s1[7], school_id);
				
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
