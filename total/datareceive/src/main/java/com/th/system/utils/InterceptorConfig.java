package com.th.system.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.th.system.service.SysDataService;
import com.th.system.tcp.Server;
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	
	 @Autowired
	 private SysDataService sysDataService;
	
	@Resource
	private TokenInterceptor tokenInterceptor;
	
	AnalysisUtil util = new AnalysisUtil();
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/login/**");
        excludePath.add("**/login/**");
        excludePath.add("**/upload/**");
        excludePath.add("**/xm/upload/**");
        System.out.println("=======拦截器=======");
        registry.addInterceptor(tokenInterceptor).excludePathPatterns(excludePath);
//        String time = util.getTimeAll();
//		String hour = util.getHour();
//        sysDataService.insertData("83f4f2cb995b4c8bb60dc0b0f4d02114", "78d99eb1f27d4a3f8679c6f5072cbf12", "18.8", "28E47EEC010000D4", time, hour);
    }
    
   
    
}
