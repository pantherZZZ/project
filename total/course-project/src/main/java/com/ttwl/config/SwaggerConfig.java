package com.ttwl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zhang bao
 * @Date 2022/5/31 15:12
 * @Description： swagger配置类
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.byuan.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot整合Swagger")
                        .description("详细信息")
                        .version("1.0")
                        .contact(new Contact(
                                "mtb",
                                "https://www.cnblogs.com/byuan",
                                "byuan98@outlook.com"))
                        .license("The Apache License")
                        .licenseUrl("https://github.com/byuan98/springboot-integration")
                        .build()
                );
    }
}
