package com.futbolDeBarrio.futbolDeBarrio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Indica que esta clase es una clase de configuración

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:8080")  // Permitir orígenes específicos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true)  // Permitir el uso de credenciales
                .maxAge(3600);  // Cachear la política CORS durante 1 hora
    }
}