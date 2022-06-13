package com.th.system.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;


public class HttpClient {

		protected static int httpClientPost(HttpServletRequest request) {
			String serverUrl = getServerUrl(request);
//			StringBuilder responseBuilder = null;
		    BufferedReader reader = null;
		    OutputStreamWriter wr = null;
		    URL url;
		    try {
		    	//url = new URL("http://www.poorren.com");
		    	//url = new URL("http://119.3.156.168:8085/model/findDetectionTypeAll");
		    	url = new URL(serverUrl);
		    	System.out.println("****serverUrl****"+serverUrl+"*******");
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setDoOutput(true);
		        conn.setConnectTimeout(1000 * 5);
		        wr = new OutputStreamWriter(conn.getOutputStream());
		        wr.write("");
		        wr.flush();
		        // Get the response
		        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		        responseBuilder = new StringBuilder();
//		        String line = null; 
//		        while ((line = reader.readLine()) != null) {
//		            responseBuilder.append(line + "\n");
//		        }
		        conn.connect();
		        int responseCode = conn.getResponseCode();
		        wr.close();
		        reader.close();
//		        System.out.println(responseBuilder.toString());
		        return responseCode;
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return 0000;
		}
		
	    public static String getServerUrl(HttpServletRequest request) {
	        String requestUrl = request.getScheme() //当前链接使用的协议
	        	    +"://" + request.getServerName()//服务器地址 
	        	    + ":" + request.getServerPort() //端口号 
	        	    + request.getContextPath() //应用名称，如果应用名称为
	        	    + request.getServletPath(); //请求的相对url
	        return requestUrl;
	    }
}
