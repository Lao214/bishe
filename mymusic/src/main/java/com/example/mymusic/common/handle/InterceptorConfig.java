package com.example.mymusic.common.handle;

import com.example.mymusic.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")  //拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/app/member/login","/app/member/memberName","/app/member/memberNo","/app/stock/queryAreaList","/app/member/register","/api/**","/app/article/**","/app/warehouse/**","/app/stock/queryStockListByCondition");  //拦截所有请求，通过判断token是否合法来决定是否需要登录
    }

    @Bean
    public  JwtInterceptor jwtInterceptor(){
         return  new JwtInterceptor();
    }
}
