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
/**
 * Clase de configuración de seguridad para la aplicación.
 */
public class SeguridadConf {

    @Autowired
    JwtFiltroAutentificacion filtroJwt;

    @Autowired
    AuthenticationProvider proveedorAutenticacion;

    /**
     * Configura la cadena de filtros de seguridad para la aplicación web.
     * Establece reglas de autorización, manejo de sesiones e integración del filtro JWT.
     * 
     * @param seguridadHttp configuración de seguridad HTTP.
     * @return una instancia de SecurityFilterChain con la configuración.
     * @throws Exception si ocurre un error.
     */
    @Bean
    public SecurityFilterChain cadenaFiltroSeguridad(HttpSecurity seguridadHttp) throws Exception {
        return seguridadHttp
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(proveedorAutenticacion)
                .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
