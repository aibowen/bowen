package fileserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/***
 * 上传文件到文件服务器类
 * 上传步骤：1.使用login方法登录文件服务器，2.使用fileUpload方法传输文件
 * 说明：必须先登录，再传输，登录后需要得到服务器分配的session，上传文件时需要将session发给服务器，以便服务器确认身份。
 * @author Admin
 *
 */
public class UploadFileToFileServer {
    

    //登录文件服务器的地址
    private String loginUrl = "http://localhost/fileserver/servlet/Login";
    
    //接收文件的服务器地址
    private String serverUrl = "http://localhost/fileserver/servlet/FileUpload";
    
    private String[] session = null;

    /**
     * 模拟浏览器POST提交数据方法
     * 将装进HashMap的参数及值组合成URL并POST提交到web，
     * @param action
     * @param parMap
     * @return
     * @throws Exception
     */
    public String login(Map<String,String> parMap) throws Exception{
        String resultSet = null;
        
        try{
            HttpURLConnection huc = (HttpURLConnection)  new URL(loginUrl).openConnection();
            //指定HTTP内容类型及URL格式为form表单格式
            //huc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            
            
            // 设置允许output
            huc.setDoOutput(true);
            // 设置提交方式为post方式
            huc.setRequestMethod("POST");
            String parameter="";
            for(String key:parMap.keySet()){
                //组建参数URL并指定URL及参数编码格式
                parameter+=key+"="+  java.net.URLEncoder.encode(parMap.get(key),"utf-8")+"&";
            }
            parameter=parameter.substring(0, parameter.length()-1);
            OutputStream os = huc.getOutputStream();
            os.write(parameter.getBytes("utf-8"));//指定URL及参数编码格式
            os.flush();
            os.close();
            //执行提交后获取执行结果
            BufferedReader br = new BufferedReader(new InputStreamReader(huc .getInputStream()));
            huc.connect();
            String line=null ;
            resultSet = br.readLine();
          //循环按行读取文本流
            while ((line = br.readLine()) != null) {
                resultSet += line;//此处未加上\r\n
            }
            br.close();
            resultSet = resultSet.trim();
            
            //得到本次会话session，以便传文件时服务器确认身份
            session = huc.getHeaderField("Set-Cookie").split(";");
            System.out.println("sessionId:"+session[0]);
            
            huc.disconnect();
        }catch(Exception e){
            throw e;
        }
        
        return resultSet;
    }
    
    

    /***
     * 上传文件到文件服务器，得到返回的文件的网络地址并返回给调用程序
     * chenjye 2014-12-6
     * 参考：http://314858770.iteye.com/blog/720456
     * 
     * @param f
     * @param url
     * @return
     * @throws Exception
     */
    public String fileUpload(byte[] bytes) throws Exception{
        String resultSet = null;
        

        
        
        try{
            HttpURLConnection huc = (HttpURLConnection)  new URL(serverUrl).openConnection();

            
            huc.setRequestMethod("POST");// 设置提交方式为post方式
            huc.setDoInput(true);
            huc.setDoOutput(true);//设置允许output
            huc.setUseCaches(false);//POST不能使用缓存
            
            //同步会话session
            if(session!=null && session.length>0){
                huc.setRequestProperty("Cookie", session[0]);
            }else{
                return "Session Error";
            }
            
            //设置请求头信息
            huc.setRequestProperty("Connection", "Keep-Alive");
            huc.setRequestProperty("Charset", "UTF-8");
            
            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            huc.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ boundary);
            
            
            // 头部：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // ////////必须多两道线
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"uploaded_file.html\"\r\n");
            sb.append("Content-Type: application/octet-stream\r\n\r\n");

            // 获得输出流
            OutputStream out = new DataOutputStream(huc.getOutputStream());
            out.write(sb.toString().getBytes("utf-8"));//写入header
            // 文件数据部分
            out.write(bytes, 0, bytes.length);//写入文件数据
            
            // 结尾部分
            byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);//写入尾信息
            
            out.flush();
            out.close();

            
            //执行提交后获取执行结果
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            huc.connect();
            String line=null ;
            resultSet = br.readLine();
            
            //循环按行读取文本流
            while ((line = br.readLine()) != null) {
                resultSet += line+"\r\n";//此处未加上\r\n
            }
            br.close();
            resultSet = resultSet.trim();
            huc.disconnect();
        }catch(Exception e){
            throw e;
        }
        
        return resultSet;
        
    }

}