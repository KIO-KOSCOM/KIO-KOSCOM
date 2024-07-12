package com.byld.koscomloanalgorithm.configurations

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@OpenAPIDefinition(
    info =
        Info(
            title = "My API",
            version = "1.0",
            description = "API documentation",
        ),
)
class SwaggerConfig : WebMvcConfigurer
