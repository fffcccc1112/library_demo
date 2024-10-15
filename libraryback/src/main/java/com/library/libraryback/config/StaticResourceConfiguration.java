package com.library.libraryback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    //这是配置属性中的指向文件夹的注解
    @Value("${upload.path}")
    private String uploadPath;

    /**
     * 实际路径映射到虚拟路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加静态资源映射路径
        registry.addResourceHandler("/localimage/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}