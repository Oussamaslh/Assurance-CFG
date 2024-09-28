package com.example.Gestion.Assurence.Bancaire.config;

// src/main/java/com/example/assurancebank/config/WebConfig.java


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;
import static org.hibernate.CacheMode.PUT;
import static org.hibernate.sql.model.MutationType.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

