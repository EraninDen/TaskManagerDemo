package com.example.taskmanagerdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Title")
                        .description("TaskManagerDemo")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Den")
                                .email("d.eranin@yandex.ru")
                                )
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                );
    }
}
