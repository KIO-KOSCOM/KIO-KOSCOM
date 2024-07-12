package com.byld.koscomloanalgorithm.configurations

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfigurer : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*") // 여기에 클라이언트의 주소를 입력합니다.
            .allowedMethods("*") // 필요한 HTTP 메서드를 지정합니다.
            .allowedHeaders("*") // 필요한 헤더를 지정합니다.
            .allowCredentials(false)
    }
}
