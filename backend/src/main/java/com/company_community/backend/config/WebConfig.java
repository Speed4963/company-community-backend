package com.company_community.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // /api/로 시작하는 모든 요청에 대해 CORS 허용
        registry.addMapping("/api/**")
                // 리액트 개발 서버 주소 명시
                .allowedOrigins("http://localhost:5173")
                // 허용할 HTTP 메서드 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}