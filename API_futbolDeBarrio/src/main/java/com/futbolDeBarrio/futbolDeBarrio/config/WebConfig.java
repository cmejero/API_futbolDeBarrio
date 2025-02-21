package com.futbolDeBarrio.futbolDeBarrio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Indica que esta clase es una clase de configuración
public class WebConfig implements WebMvcConfigurer {

    // Sobreescribimos el método 'addCorsMappings' para habilitar CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aquí configuras los orígenes permitidos
        registry.addMapping("/**")  // Permitir CORS para todas las rutas
                .allowedOrigins("http://localhost:4200", "http://localhost:8080")  // Permitir solo solicitudes desde tu frontend (Angular)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true);  // Permitir credenciales, si es necesario
    }
}
