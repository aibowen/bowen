/**
 * 
 */
package org;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ProgressMonitor;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 * @author bowen
 *
 */
public class MySftp {
	
	private static final String host="djrcfeed.dowjones.com";
	private static final String username="bankningbocp";
	private static final String password="Ban1040";
	private static final int port=22;
	
	public static long getBytes(String src,String dst) throws Exception{
		byte[] buf=new byte[1024*10*2];
		FileInputStream fis=new FileInputStream(new File(src));
		FileOutputStream fos=new FileOutputStream(new File(dst));
		long fileLength=new File(src).length();
		System.out.println();
		int i=0;
		int off=0;
		int len=(int)fileLength;
		while(len>=0){
			i=fis.read(buf, off, len);
			if(i<=0)
				throw new IOException("inputstream is closed");
			off+=i;
			len-=i;
		}
		
		int j=0;
		int off_=0;
		while(len>=0){
			fos.write(buf, off_, len);
			
		}
		
		return 0;
	}
	/**
	 * 官方示例
	 * @param args
	 * @throws JSchException 
	 * @throws SftpException 
	 */
	public static void office_demo() throws JSchException,SftpException{
		JSch jsch=new JSch();
		Session session=jsch.getSession(username, host, port);
		UserInfo myUserInfo=new MyUserInfo();
		session.setUserInfo(myUserInfo);
		session.connect();
		Channel channel=session.openChannel("sftp");
		channel.connect();
		ChannelSftp channelSftp=(ChannelSftp)channel;
		String src="/DJRC_Cities_and_Ports_XML_201806122359_S.zip";
		String dst="C:\\Users\\13552\\Desktop\\DJRC_Cities_and_Ports_XML_201806122359_S.zip";
		
		String log_src="/Feedlogs/DJRC_Cities_and_Ports_XML_STATS_201808192359.txt";
		String log_dst="C:\\Users\\13552\\Desktop\\DJRC_Cities_and_Ports_XML_STATS_201808192359.txt";
		MyProgressMonitor monitor=new MyProgressMonitor();
		channelSftp.get(log_src, log_dst,monitor,ChannelSftp.OVERWRITE);
	}
	
	public static void main(String[] args) throws JSchException, SftpException {
		office_demo();
//		try {
//			getBytes("C:\\Users\\13552\\Desktop\\DJRC_Cities_and_Ports_XML_201806122359_S.zip");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static class MyUserInfo implements UserInfo{
		String passwd;
		public String getPassword() {
			return passwd;
		}

		@Override
		public String getPassphrase() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean promptPassword(String message) {
			passwd=MySftp.password;
			return true;
		}

		@Override
		public boolean promptPassphrase(String message) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean promptYesNo(String message) {
			return true;
		}

		@Override
		public void showMessage(String message) {
			// TODO Auto-generated method stub
			
		}
	}
	/*
	 * java.swing.ProgressMonitor实现进度条提示实时下载进度
	 */
	public static class MyProgressMonitor implements SftpProgressMonitor{
		ProgressMonitor progressMonitor;
		long count=0;
		long max=0;
		long percent=-1;

		@Override
		public void init(int op, String src, String dst, long max) {
			this.max=max;
			progressMonitor=new ProgressMonitor(null,((op==SftpProgressMonitor.PUT)?"put":"get")+":"+src,"",0,(int)max);
			progressMonitor.setProgress((int)count);
			progressMonitor.setMillisToDecideToPopup(1000);
		}
		
		@Override
		public boolean count(long count) {
			this.count+=count;
			if(percent>=this.count*100/max){
				return true;
			}
			percent=this.count*100/max;
			progressMonitor.setNote("已完成："+this.count+"("+percent+"%) out of"+max+".");
			progressMonitor.setProgress((int)this.count);
			return !(progressMonitor.isCanceled());
		}
		
		@Override
		public void end() {
			progressMonitor.close();
		}
		
	}
}
