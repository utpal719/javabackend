package com.techvariable.traxpense.core.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author utpal
 * com.techvariable.traxpense.core.common.config
 */

@Configuration
@EnableSwagger2
public class SwaggerLoaderConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addViewControllers(ViewControllerRegistry registry){
        registry.addRedirectViewController("/docs","/swagger-ui.html#/");
        registry.addRedirectViewController("/docs/", "/swagger-ui.html#/");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket appApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("traxpense")
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.techvariable.traxpense.core.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Xpense API documentation")
                .description("API documentation for Xpense REST api")
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }
}
