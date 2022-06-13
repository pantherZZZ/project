package com.ttwl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author zhang bao
 * @Date 2022/5/19 17:27
 * @Description： 启动类
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement //开启声明式事务
@MapperScan("com.ttwl.dao")
@EnableScheduling   //开启定时任务
public class CourseProjectApplication {
    public static void main(String[] args)   {
        SpringApplication.run(CourseProjectApplication.class, args);
    }
}
