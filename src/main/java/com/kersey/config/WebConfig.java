package com.kersey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/pages/user/index.html","/pages/user/login.html","/pages/user/register.html","/pages/order/index.html","/users/**","/css/**","/imgs/**","/js/**","/plugins/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("file: D:/桌面/shcool-express/src/main/resources/static");
//        registry.addResourceHandler("/pages/**").addResourceLocations("file: D:/桌面/shcool-express/src/main/resources/static/pages");
//        registry.addResourceHandler("/css/**").addResourceLocations("file: D:/桌面/shcool-express/src/main/resources/static/css");
//        registry.addResourceHandler("/imgs/**").addResourceLocations("file: D:/桌面/shcool-express/src/main/resources/static/imgs");
//        registry.addResourceHandler("/js/**").addResourceLocations("file: D:/桌面/shcool-express/src/main/resources/static/js");
//        registry.addResourceHandler("/plugins/**").addResourceLocations("file: D:/桌面/shcool-express/src/main/resources/static/plugins");
    }
}
