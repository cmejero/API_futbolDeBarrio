package com.futbolDeBarrio.futbolDeBarrio.servicios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	            .requestMatchers("/auth/login").permitAll()  // Permitir el acceso a /auth/login sin autenticación
	            .anyRequest().authenticated()               // Cualquier otra solicitud requiere autenticación
	            .and()
	            .httpBasic();                               // Habilitar autenticación básica para pruebas
	        return http.build();
	    }
}