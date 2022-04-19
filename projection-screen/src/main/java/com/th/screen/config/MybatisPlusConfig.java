package com.th.screen.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhang bao
 * @Date 2022/3/10 9:59
 * @Description
 * @Version 1.0
 */
@Configuration
@MapperScan("com.th.screen.mapper*")
public class MybatisPlusConfig {

//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        // 旧版
//            PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//            // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
//            // paginationInterceptor.setOverflow(false);
//            // 设置最大单页限制数量，默认 500 条，-1 不受限制
//            // paginationInterceptor.setLimit(500);
//            // 开启 count 的 join 优化,只针对部分 left join
//            paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
//            return paginationInterceptor;
//        }

    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
