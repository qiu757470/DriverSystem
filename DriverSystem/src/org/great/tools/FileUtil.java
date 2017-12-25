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
 * 文件处理工具
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

      // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
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

//解密压缩文件
public static boolean decodeFile(File file, ZipFile zipFile, String pwd, String decomPath) {
	try {
		//设置密码
		zipFile.setPassword(pwd);
		//解压全部
		zipFile.extractAll(decomPath);
		//读取Excel文件
		return true;
	} catch (ZipException e) {
		try {
			System.out.println("密码错误！");
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

//上传文件
@Deprecated
public static boolean uploadFile(String uploadPath, String tempPath, HttpServletRequest request, File ioFile){
	//获得磁盘文件条目工厂, 第三方jar包（fileupload），上传文件
	DiskFileItemFactory factory = new DiskFileItemFactory();  
	File file = new File(uploadPath);
	file.mkdirs();
	/*System.out.println(path);
	原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
	按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
	然后再将其真正写到 对应目录的硬盘上 */
	//设置路径
	factory.setRepository(new File(tempPath));
	//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
	factory.setSizeThreshold(1024*1024*5);
	//高水平的API文件上传处理  
	ServletFileUpload upload = new ServletFileUpload(factory); 
	//最大上传文件为50M
	upload.setFileSizeMax(1024*1024*50);
	//最大请求文件为50M
	upload.setSizeMax(1024*1024*50);
	//文件名
	String filename = "";
	try {  
		//可以上传多个文件  
		@SuppressWarnings("unchecked")
		List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		//遍历FileItem对象
		for(FileItem item : list){  
			//获取表单的属性名字  
			String name = item.getFieldName();  
			//如果获取的 表单信息是普通的 文本 信息  
			if(item.isFormField()){                     
				//获取用户具体输入的字符串 
				String value = item.getString();  
				request.setAttribute(name, value);  
			} else{  
				/*以下三步，主要获取 上传文件的名字 */
				//获取路径名  
				String value = item.getName();  
				//索引到最后一个反斜杠  
				int start = value.lastIndexOf("\\");  
				//截取上传文件的 文件名，加1是 去掉反斜杠，  
				filename = value.substring(start+1); //获得文件名
				request.setAttribute(name, filename); //设置属性name，和值文件名
				ioFile = new File(uploadPath,filename);
				//真正写到磁盘上  
				//它抛出的异常 用exception 捕捉  

				//item.write( new File(path,filename) );//第三方提供的  

				//输出流，写入到上传文件的路径中  
				OutputStream out = new FileOutputStream(ioFile);  
				//输入流
				InputStream in = item.getInputStream() ;  
				int length = 0 ;  
				byte [] buf = new byte[1024] ;  
				//System.out.println("获取上传文件的总共的容量："+item.getSize()); //文件的容量 
				// in.read(buf) 每次读到的数据存放在   buf 数组中  
				while((length = in.read(buf)) != -1){  
					//在   buf 数组中 取出数据 写到输出流中  
					out.write(buf, 0, length);  
				} 
				//关闭流操作
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



