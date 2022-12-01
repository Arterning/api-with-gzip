package com.jinchange.gzip.apidocument;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName: SwaggerConfig
 * @Author zhangjin
 * @Date 2022/1/28 17:15
 * @Description: 接口文档配置类
 * 访问地址：http://localhost:8888/swagger-ui/index.html
 */
@Slf4j
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * 用于读取配置文件 application.yml 中 swagger 属性是否开启
     */
    @Value("${swagger.enabled}")
    private Boolean swaggerEnabled;

    /**
     * 读取配置的IP地址属性
     */
    @Value("${server.address}")
    private String address;

    /**
     * 读取配置的端口属性
     */
    @Value("${server.port}")
    private String port;

    /**
     * 业务分组
     */
    @Bean
    public Docket docket() {
        // 打印访问地址
        log.info("API Document Address: {}", "http://"+address+":"+port+"/swagger-ui/index.html");
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启swagger
                .enable(swaggerEnabled)
                .select()
                // 过滤条件，扫描指定路径下的文件
                .apis(RequestHandlerSelectors.basePackage("com.jinchange.gzip.controller"))
                .paths(PathSelectors.any()) // 请求路径匹配，匹配所有
                .build().groupName("业务分组");
    }


    private ApiInfo apiInfo() {
        /*作者信息*/
        Contact contact = new Contact("Jinchange", "", "475636591@qq.com");
        return new ApiInfo(
                "写作平台",
                "写作平台接口文档",
                "v1.0",
                "",
                contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

}