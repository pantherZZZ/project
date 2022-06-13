package com.th.system.tcp;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.th.system.service.SysDataService;
import com.th.system.service.SysUserService;
import com.th.system.service.impl.SysDataServiceImpl;
@Component
public class Server {

	 private static final int PORT = 1950;
//	 private static final String IP = "119.3.156.168";


	 public void goServer(SysDataService sysDataService){
		 while(true){
		    try {
		    	 ServerSocket serverSocket = new ServerSocket(PORT);
		         //建立服务器的Socket，并设定一个监听的端口PORT
		         //由于需要进行循环监听，因此获取消息的操作应放在一个while大循环中
			     //建立跟客户端的连接
				Socket socket = serverSocket.accept();// 接收其客户端的接口
//			     socket.setReuseAddress(true);
			     System.out.println("*******连*接*地*址*******"+socket.getInetAddress());
				 OutputStream outputStream = socket.getOutputStream();
//				 System.out.println("AA 75 10 00 0E 00 00 00 00 00 00 00 00 00 19 05 10 11 21 45 B8".getBytes("gbk"));
//				 outputStream.write("AA 75 10 00 0E 00 00 00 00 00 00 00 00 00 19 05 10 11 21 45 B8".getBytes("GBK"));
//				 outputStream.write("b8".getBytes("GBK"));
//				 AA 75 10 00 0E 00 00 00 00 00 00 00 00 00 19 05 10 11 21 45 B8
//				 AA7510000E000000000000000000190510112145B8
//				 String cmdInfor1 = "FF03000100100018";
				 String cmdInfor1 = "0A0301F400080579";
//				 String cmdInfor1 = "AA7510000E000000000000000000190510112145B8";
//				 将十六进制的字符串转换成字节数组
				 byte[] cmdInfor2 = hexStrToBinaryStr(cmdInfor1);

				 outputStream.write(cmdInfor2);
				Thread.sleep(10000);
//				 Thread.sleep(1000);
//				 outputStream.write(cmdInfor2);
//				 new Thread(()->{
				 ServerThread thread = new ServerThread(socket);
				 thread.runData(sysDataService,socket);
//				 }).start();
//				 outputStream.write(cmdInfor2);
			     serverSocket.close();
		    } catch (Exception e) {
		      System.out.println("端口被占用");
		      e.printStackTrace();
		    }
		 }
	 }
	 /**
	    * 将十六进制的字符串转换成字节数组
	  *
	  * @param hexString
	  * @return
	  */
	public static byte[] hexStrToBinaryStr(String hexString) {
		if (TextUtils.isEmpty(hexString)) {
			return null;
		}
		hexString = hexString.replaceAll(" ", "");
		int len = hexString.length();
		int index = 0;
		byte[] bytes = new byte[len / 2];
		while (index < len) {
			String sub = hexString.substring(index, index + 2);
			bytes[index/2] = (byte)Integer.parseInt(sub,16);
			index += 2;
		}
		return bytes;
	}
}
