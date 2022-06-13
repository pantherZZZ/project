package com.ttwl.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author zhang bao
 * @Date 2022/5/25 15:02
 * @Description： 跨域解决
 * @Version 1.0
 */
@SpringBootConfiguration
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer CORSConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        //设置是否容许跨域传cookie
                        .allowCredentials(true)
                        //设置缓存时间，减小重复响应
                        .maxAge(3600);
            }
        };
    }
}
