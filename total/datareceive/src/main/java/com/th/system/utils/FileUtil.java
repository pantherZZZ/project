package com.th.system.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileUtil {
	
	private static final String FTP_ADDRESS = "119.3.156.168";
    private static final int FTP_PORT = 21;
    private static final String FTP_USERNAME = "java";
    private static final String FTP_PASSWORD = "YrKt5EBHYGpDFzzx";
    private static final String FTP_BASEPATH = "/dudao";
	
    public static boolean uploadFile(String fileName,InputStream input) {
    	boolean success = false;
    	FTPClient ftp = new FTPClient();    	
    	try {
    		int reply;
			ftp.connect(FTP_ADDRESS, FTP_PORT);
			ftp.login(FTP_USERNAME, FTP_PASSWORD);
			reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.makeDirectory(FTP_BASEPATH);
			ftp.changeWorkingDirectory(FTP_BASEPATH);
			ftp.enterLocalPassiveMode();
			ftp.storeFile(fileName,input);
			
			input.close();
			ftp.logout();
			success = true;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return success;
    }
    
}
