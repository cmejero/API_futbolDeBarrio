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

import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Configuration
public class AplicacionConf {

	@Autowired
	UsuarioInterfaz usuarioInterfaz;
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passWordEncoder());
		return authenticationProvider;
		}

	@Bean
	public PasswordEncoder passWordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	 @Bean
	    public UserDetailsService userDetailsService() {
	        return email -> usuarioInterfaz.findByEmailUsuario(email)
	                .map(usuario -> new org.springframework.security.core.userdetails.User(
	                        usuario.getEmailUsuario(), 
	                        usuario.getPasswordUsuario(), 
	                        new ArrayList<>())) // Aquí puedes agregar roles si es necesario
	                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	    }
}
