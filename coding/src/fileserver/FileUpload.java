package fileserver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class FileUpload extends HttpServlet implements FileRenamePolicy
{
	private static final long serialVersionUID=1l;
	private static int maxSize=102400*1024;
	private String directory="uploads";
	private String fp="png,gif,jpg,bmp,html,htm,rar,zip,doc,docx,xml,xls,xlsx,txt,tmp";

	public FileUpload(){
		super();
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);	
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		Boolean islogin=(Boolean)request.getSession().getAttribute("islogin");
		if(islogin==null || (islogin!=null && islogin!=true)){
			out.println("error,you are not login");
			return;
		}

		String path=this.getServletContext().getRealPath("/");

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String directory2=sdf.format(new Date());

		String buildPath=path+directory+"/"+directory2+"/";
		File f1=new File(buildPath);
		if(!f1.exists()){
			f1.mkdirs();
		}

		FileUpload fileUpload=new FileUpload();
		try{
			MultipartRequest multi=new MultipartRequest(request,buildPath,maxSize,"UTF-8",fileUpload);
			Enumeration<?> enuma=multi.getFileNames();
			while(enuma.hasMoreElements()){
				String fileName=(String)enuma.nextElement();
				File file=multi.getFile(fileName);
				if(file!=null){
					String name=multi.getFilesystemName(fileName);
					String webroot=request.getScheme()+"://"
											+request.getServerName()+":"
											+request.getServerPort()
											+request.getContextPath();
					String fileurl=webroot+"/"+directory+"/"+directory2+"/"+name;
					out.println(fileurl);
				}
			}
		}catch(Exception e){
			out.println("server exception");
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}

	@Override
    public File rename(File file) {

        int index = file.getName().lastIndexOf("."); //得到文件名中最后一个.的位置
        String postfix = file.getName().substring(index); //得到文件名后缀

        //构建新文件名(使用当前服务器时间戳)
        String newFileName = System.currentTimeMillis() + postfix;

        // 判断文件类型是否符合限定范围
        String[] ps = fp.split(",");
        boolean flag = false;
        for (String p : ps) {
            if (postfix.equals(("." + p))) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return new File(file.getParent(), newFileName);
        } else {
            return null;
        }

    }

}