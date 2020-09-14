package com.ygx.ms.config;

import com.ygx.ms.interceptor.AntiBrushInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AntiBrushInterceptor antiBrushInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(antiBrushInterceptor).addPathPatterns("/**");
    }
}
