package fileserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test
{
	public static void main(String[] args) throws Exception{
		//虚拟模板，并将内容填充进模板
		String title = "动态内容静态化测试";
		String content ="测试";

		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		sb.append("<html>\r\n");
		sb.append("    <head>\r\n");
		sb.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
		 sb.append("        <title>"+title+"</title>\r\n");
		 sb.append("    </head>\r\n");
		 sb.append("    <body>\r\n");
		 sb.append(content+"\r\n");
		 sb.append("    </body>\r\n");
		 sb.append("</html>\r\n");
		 
		 //创建文件传输对象，将内容发送给文件服务器并得到返回的url
		 UploadFileToFileServer uftfs = new UploadFileToFileServer();
		 Map<String,String> parMap = new HashMap<String,String>();
		 parMap.put("username", "admin");
		 parMap.put("password", "123456");
		 String loginFlag = uftfs.login(parMap);//登录
		 if("true".equals(loginFlag)){
			 byte[] bytes = sb.toString().getBytes("UTF-8");
			 String url = uftfs.fileUpload(bytes);//上传
			 System.out.println("文件URL："+url);
		 }
		 
		 
		 //直接传二进制文件
		 byte[] buffer = null;  
		 try {  
			 File file = new File("E:\\cat.jpg");  
			 FileInputStream fis = new FileInputStream(file);  
			 ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
			 byte[] b = new byte[1000];  
			 int n;  
			 while ((n = fis.read(b)) != -1) {  
				 bos.write(b, 0, n);  
			 }  
			 fis.close();  
			 bos.close();  
			 buffer = bos.toByteArray();  
		 } catch (FileNotFoundException e) {  
			 e.printStackTrace();  
		 } catch (IOException e) {  
			 e.printStackTrace();  
		 }  
		 
		 String url = uftfs.fileUpload(buffer);//上传
		 System.out.println("文件URL："+url);
	}
}
