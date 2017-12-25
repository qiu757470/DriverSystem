package org.great.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload extends HttpServlet {
      
	/**
		 * Constructor of the object.
		 */
	private String path=null;
	private  String filename;
	private  String value;
	public Upload() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 
		 *  
		 * @author Administrator 
		 * �ļ��ϴ� 
		 * ���岽�裺 
		 * 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ���� 
		 * 2�� ���� request ��ȡ ��ʵ·�� ������ʱ�ļ��洢���� �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ 
		 * 3���� DiskFileItemFactory ��������һЩ ���� 
		 * 4����ˮƽ��API�ļ��ϴ�����  ServletFileUpload upload = new ServletFileUpload(factory); 
		 * Ŀ���ǵ��� parseRequest��request������  ��� FileItem ����list �� 
		 *      
		 * 5���� FileItem ������ ��ȡ��Ϣ��   ������ �ж� ���ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ  �������� 
		 * 6�� 
		 *    ��һ��. �õ����� �ṩ��  item.write( new File(path,filename) );  ֱ��д�������� 
		 *    �ڶ���. �ֶ�����   
		 * 
		 */  

		/* if(!ServletFileUpload.isMultipartContent(request)){
			PrintWriter writer=response.getWriter();
			writer.println("Error:��������� enctype=multipart/form-data");
			writer.flush();
			return;
			 
		 }*/
		
		          System.out.println(request+"========");
		        request.setCharacterEncoding("utf-8");  //���ñ���  
		          
		        //��ô����ļ���Ŀ����  
		        DiskFileItemFactory factory = new DiskFileItemFactory();  
		        //��ȡ�ļ���Ҫ�ϴ�����·��  
		        path = request.getRealPath("/upload");  
		        System.out.println("�ϴ�·��============"+path);
		        //���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬  
		        //������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ  
		        /** 
		         * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ�  
		         * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ��  
		         * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ���� 
		         */  
		        System.out.println("2222");
		        factory.setRepository(new File(path));  
		        //���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��  
		        factory.setSizeThreshold(1024*1024) ;  
		          
		        //��ˮƽ��API�ļ��ϴ�����  
		        System.out.println("333333");
		        ServletFileUpload upload = new ServletFileUpload(factory);  
		          
		        //Ŀ¼�������򴴽�
		        System.out.println("444444");
		        File uploadDir =new File(path);
		        if(!uploadDir.exists()){
		        	uploadDir.mkdir(); 	
		        }
		        
		        System.out.println("5555");
		        try {  
		            //�����ϴ�����ļ�  
		        	System.out.println("59999");
					List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		              System.out.println(list.get(0).getFieldName()+"list========");
		            for(FileItem item : list)  
		            {  
		                //��ȡ������������  
		                String name = item.getFieldName();  
		                System.out.println("��������="+name);
		                  
		                //�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
		                if(item.isFormField())  
		                {                     
		                    //��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�  
		                    String value = item.getString() ;  
		                      
		                    request.setAttribute(name, value);  
		                }  
		                //�Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ  
		                else  
		                {  
		                    /** 
		                     * ������������Ҫ��ȡ �ϴ��ļ������� 
		                     */  
		                    //��ȡ·����  
		                     value = item.getName() ;  
		                    System.out.println("·����==========="+value);
		                    //���������һ����б��  
		                    int start = value.lastIndexOf("\\");  
		                    //��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�  
		                    filename = value.substring(start+1);  
		                    System.out.println("�ļ���==========="+filename);  
		                    
		                    request.setAttribute(name, filename);  
		                      
		                    //����д��������  
		                    //���׳����쳣 ��exception ��׽  
		                      
		                    //item.write( new File(path,filename) );//�������ṩ��  
		                      
		                    //�ֶ�д��  
		                    OutputStream out = new FileOutputStream(new File(path,filename));  
		                      
		                    InputStream in = item.getInputStream() ;  
		                      
		                    int length = 0 ;  
		                    byte [] buf = new byte[1024] ;  
		                      
		                    System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize());  
		  
		                    // in.read(buf) ÿ�ζ��������ݴ����   buf ������  
		                    while( (length = in.read(buf) ) != -1)  
		                    {  
		                        //��   buf ������ ȡ������ д�� ���������������  
		                        out.write(buf, 0, length);  
		                          
		                    }  
		                      
		                    in.close();  
		                    out.close();  
		                }  
		            }  
		              
		              
		              
		        } catch (FileUploadException e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
		        catch (Exception e) {  
		            // TODO Auto-generated catch block  
		              
		            //e.printStackTrace();  
		        }  
		          
		          
		          
		          
		  
		    }  
		  
	

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

}
