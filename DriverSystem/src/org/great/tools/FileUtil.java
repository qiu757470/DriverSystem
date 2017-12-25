package org.great.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * �ļ�������
 */
public class FileUtil {
	public static boolean copy(File src, File dest) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		File parent = new File(src.getParent());
		File destFile = new File(dest.getParent());
		parent.mkdirs();
		destFile.mkdirs();
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(dest));
			byte[] bts = new byte[1024];
			int len = -1;
			while ((len = bis.read(bts)) != -1) {
				bos.write(bts, 0, len);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	
	}
	
	public static boolean upload(String fileName, File uploadFile, File uploadSuccessFile){
        InputStream is = null;
        OutputStream os = null;
		try {
			is = new FileInputStream(uploadFile);
			os = new FileOutputStream(uploadSuccessFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
       /* 
        System.out.println("fileFileName: " + fileName);

      // ��Ϊfile�Ǵ������ʱ�ļ��е��ļ������ǿ��Խ����ļ������ļ�·����ӡ����������֮ǰ��fileFileName�Ƿ���ͬ
        System.out.println("file: " + uploadFile.getName());
        System.out.println("file: " + uploadFile.getPath());*/
        
        byte[] buffer = new byte[500];
        int len = 0;
        try {
			while(-1 != (len = is.read(buffer, 0, buffer.length))){
			    os.write(buffer);
			}
			 os.close();
		    is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(uploadSuccessFile.exists()){
        	return true;
        }else{
        	return false;
        }
}

//����ѹ���ļ�
public static boolean decodeFile(File file, ZipFile zipFile, String pwd, String decomPath) {
	try {
		//��������
		zipFile.setPassword(pwd);
		//��ѹȫ��
		zipFile.extractAll(decomPath);
		//��ȡExcel�ļ�
		return true;
	} catch (ZipException e) {
		try {
			System.out.println("�������");
			return false;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

//�ϴ��ļ�
@Deprecated
public static boolean uploadFile(String uploadPath, String tempPath, HttpServletRequest request, File ioFile){
	//��ô����ļ���Ŀ����, ������jar����fileupload�����ϴ��ļ�
	DiskFileItemFactory factory = new DiskFileItemFactory();  
	File file = new File(uploadPath);
	file.mkdirs();
	/*System.out.println(path);
	ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ�  
	������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ��  
	Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ���� */
	//����·��
	factory.setRepository(new File(tempPath));
	//���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��  
	factory.setSizeThreshold(1024*1024*5);
	//��ˮƽ��API�ļ��ϴ�����  
	ServletFileUpload upload = new ServletFileUpload(factory); 
	//����ϴ��ļ�Ϊ50M
	upload.setFileSizeMax(1024*1024*50);
	//��������ļ�Ϊ50M
	upload.setSizeMax(1024*1024*50);
	//�ļ���
	String filename = "";
	try {  
		//�����ϴ�����ļ�  
		@SuppressWarnings("unchecked")
		List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		//����FileItem����
		for(FileItem item : list){  
			//��ȡ������������  
			String name = item.getFieldName();  
			//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
			if(item.isFormField()){                     
				//��ȡ�û�����������ַ��� 
				String value = item.getString();  
				request.setAttribute(name, value);  
			} else{  
				/*������������Ҫ��ȡ �ϴ��ļ������� */
				//��ȡ·����  
				String value = item.getName();  
				//���������һ����б��  
				int start = value.lastIndexOf("\\");  
				//��ȡ�ϴ��ļ��� �ļ�������1�� ȥ����б�ܣ�  
				filename = value.substring(start+1); //����ļ���
				request.setAttribute(name, filename); //��������name����ֵ�ļ���
				ioFile = new File(uploadPath,filename);
				//����д��������  
				//���׳����쳣 ��exception ��׽  

				//item.write( new File(path,filename) );//�������ṩ��  

				//�������д�뵽�ϴ��ļ���·����  
				OutputStream out = new FileOutputStream(ioFile);  
				//������
				InputStream in = item.getInputStream() ;  
				int length = 0 ;  
				byte [] buf = new byte[1024] ;  
				//System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize()); //�ļ������� 
				// in.read(buf) ÿ�ζ��������ݴ����   buf ������  
				while((length = in.read(buf)) != -1){  
					//��   buf ������ ȡ������ д���������  
					out.write(buf, 0, length);  
				} 
				//�ر�������
				in.close();  
				out.close();
				if(ioFile.exists()){
					return true;
				}
			}  
		}  
	} 
	catch (Exception e) {  
		// TODO Auto-generated catch block  
		e.printStackTrace();  
	} 
	return false;
}
	
	
}



