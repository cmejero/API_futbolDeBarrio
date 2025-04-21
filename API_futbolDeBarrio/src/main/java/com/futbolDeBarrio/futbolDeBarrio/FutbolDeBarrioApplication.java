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
	            registry.addMapping("/api/**")
	                    .allowedOrigins("http://localhost:4200", "http://localhost:8080")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE")
	                    .allowedHeaders("Content-Type", "Authorization");
	        }
	    };
	}

}
