package com.futbolDeBarrio.futbolDeBarrio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FutbolDeBarrioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolDeBarrioApplication.class, args);
	}

	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/api/**") // Se aplica a los endpoints que comienzan con /api/
	                        .allowedOrigins("http://localhost:4200") // Permitir solicitudes desde tu frontend (Angular en este caso)
	                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
	                        .allowedHeaders("Content-Type", "Authorization"); // Encabezados permitidos
	            }
	        };
	    }
}
