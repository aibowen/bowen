package fileserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/***
 * 客户端登录类
 * 
 * 本servlet将验证用户登录，并为用户创建session状态
 * 
 * @author chenjye 2014-12-6
 *
 */
public class Login extends HttpServlet {

    /**
     * The doGet method of the servlet.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Error");
        out.flush();
        out.close();
    }

    /**
     * The doPost method of the servlet.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("fileserver:user logining.");
        //客户端登录
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("admin".equals(username) && "123456".equals(password)){
            request.getSession().setAttribute("islogined",true);
            out.print(true);
            System.out.println("fileserver:login success.");
        }else{
            out.print("the username or password error.");
            System.out.println("fileserver:login failure.");
        }
        
        out.flush();
        out.close();
    }

}