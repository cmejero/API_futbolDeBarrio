package com.futbolDeBarrio.futbolDeBarrio.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.futbolDeBarrio.futbolDeBarrio.jwt.JwtFiltroAutentificacion;

@Configuration
@EnableWebSecurity
public class SeguridadConf {
	
	@Autowired
	JwtFiltroAutentificacion jwtFiltroAutentificacion ;
	@Autowired
	AuthenticationProvider authProvider;
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Desactiva la protección CSRF si es necesario
                .authorizeHttpRequests(authRequest ->
                    authRequest
                        .requestMatchers("/**").permitAll()  // Permite el acceso sin autenticación a ciertas rutas
                        .anyRequest().authenticated()      // Requiere autenticación para cualquier otra solicitud
                )
                .formLogin(formLogin ->
                    formLogin
                        .permitAll()  // Permite el acceso al formulario de inicio de sesión sin autenticación
                )
                .sessionManagement( sessionManager ->
                sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtFiltroAutentificacion, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}
