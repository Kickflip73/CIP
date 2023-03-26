package com.uchain.cip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 定义一个API分组，可有多个分组，分别扫描不同的API
     * */
    @Bean
    public Docket docket(Environment environment) {
        //判断是否属于dev、test环境
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("CIP")
                .apiInfo(this.apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uchain.cip.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 定义API主界面的信息
     * */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CIP项目API")
                .description("CPI项目SwaggerAPI管理")
                .contact(new Contact("kickflip", "个人主页", "3065242502@qq.com"))
                .version("v1.0")
                .build();
    }
}
