package com.th.system.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.util.TextUtils;
import com.th.system.service.SysDataService;
import com.th.system.utils.AnalysisUtil;

//@Component
public class ServerThread{

	  private Socket socket ;

	  public static final char END_CHAR = '#';

	  AnalysisUtil util = new AnalysisUtil();

	  public ServerThread(Socket socket){
	    this.socket=socket;
	  }

	  public void runData(SysDataService sysDataService,Socket socket) {
		  System.out.println("**********");
		  while(true){
			  new Thread(()->{
			    Date date = new Date();
				SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("时间:"+time.format(date));
				String text = "";
				    try {
						//向客户端发送消息
					    OutputStream outputStream = socket.getOutputStream();
				        String cmdInfor = "0A0301F400080579";
						//将十六进制的字符串转换成字节数组
						byte[] cmdInfor2 = hexStrToBinaryStr(cmdInfor);
//						System.out.println("***16***"+cmdInfor2);
				        //接收客户端的消息并打印
				        System.out.println("*59*"+socket);
				    	InputStream inputStream=socket.getInputStream();
				        byte[] bytes = new byte[1024];
				        inputStream.read(bytes);
//				        int len = inputStream.read(bytes);
//				        text = new String(bytes,0,len);
			            System.out.println("数据*:"+text);
						System.out.println("**********bytes2HexString****"+ServerThread.bytes2HexString(bytes));
						System.out.println("********byteArrayToHexStr****"+ServerThread.byteArrayToHexStr(bytes));
						sysDataService.insertTextData(text,time.format(date));
						sysDataService.insertTextData(ServerThread.bytes2HexString(bytes),time.format(date));
					    Thread.sleep(10000);
					    outputStream.write(cmdInfor2);
					    socket.close();
					} catch (Exception e) {
				      System.out.println("客户端主动断开连接了");
				      e.printStackTrace();
				    }
//				    try {
//						socket.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//
//		       }
		  }).start();
		  }
	  }

	  public static String byteArrayToHexStr(byte[] byteArray) {
		    if (byteArray == null){
		        return null;
		    }
		    char[] hexArray = "0123456789ABCDEF".toCharArray();
		    char[] hexChars = new char[byteArray.length * 2];
		    for (int j = 0; j < byteArray.length; j++) {
		        int v = byteArray[j] & 0xFF;
		        hexChars[j * 2] = hexArray[v >>> 4];
		        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		    }
		    return new String(hexChars);
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


	/**
     * @Title:bytes2HexString
     * @Description:字节数组转16进制字符串
     * @param b
     * 字节数组
     * @return 16进制字符串
     * @throws
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(String.format("%02X",b[i]));
        }
        return result.toString();
    }

}
