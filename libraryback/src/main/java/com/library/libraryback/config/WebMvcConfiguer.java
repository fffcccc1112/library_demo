package com.library.libraryback.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguer implements WebMvcConfigurer {
    /**
     * 配置跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")        //允许的路径，即我们那些接口可以跨域
                .allowedOrigins("*")        //允许的源
                .allowCredentials(true)    //是否允许发送凭证
                .allowedMethods("GET", "POST", "PUT", "DELETE")  //指定允许的 HTTP 方法
                .maxAge(3600 * 24);        //预检请求有效期
    }
}
