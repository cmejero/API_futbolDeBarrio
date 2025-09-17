package com.futbolDeBarrio.futbolDeBarrio.configuracion;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Configuration
/**
 * Clase de configuración de seguridad para la aplicación.
 */
public class AplicacionConf {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;

    /**
     * Define un {@link AuthenticationManager} para manejar la autenticación en la aplicación. 
     * @param config Configuración de autenticación de Spring Security.
     * @return una instancia de {@link AuthenticationManager} para manejar la autenticación de usuarios.
     * @throws Exception si ocurre un error.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * metodo que se encarga de autenticar a los usuarios mediante una consulta a la base de datos y la validación de contraseñas.
     * @return un {@link AuthenticationProvider} configurado con el {@link UserDetailsService} y el {@link PasswordEncoder}.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passWordEncoder());
        return authenticationProvider;
    }

    /**
     * Metodo para  codificar y verificar las contraseñas de los usuarios.
     * @return un {@link PasswordEncoder} configurado con {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passWordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Metodo que carga el usuario desde la base de datos a través de {@link UsuarioInterfaz}.
     * @return un {@link UserDetailsService} que consulta el repositorio {@link UsuarioInterfaz} para cargar los detalles del usuario.
     * @throws UsernameNotFoundException si no se encuentra un usuario con el email proporcionado.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> usuarioInterfaz.findByEmailUsuario(email)
                .map(usuario -> new org.springframework.security.core.userdetails.User(
                        usuario.getEmailUsuario(), 
                        usuario.getPasswordUsuario(), 
                        new ArrayList<>())) // Aquí puedes agregar roles si es necesario
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
    /**
     * Configura las políticas CORS para las rutas de la API.
     * 
     * @return configuración personalizada de CORS.
     */
    @Bean
    public WebMvcConfigurer configuracionCors() {
        return new WebMvcConfigurer() {
            /**
             * Define las reglas CORS.
             * 
             * @param registroCors configuración del registro CORS.
             */
            @Override
            public void addCorsMappings(CorsRegistry registroCors) {
                registroCors.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200", "http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("Content-Type", "Authorization");
            }
        };
    }
}
