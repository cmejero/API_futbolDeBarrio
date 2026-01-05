package com.futbolDeBarrio.futbolDeBarrio.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpMethod;
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
	 * Configura la cadena de filtros de seguridad para la aplicación web. Establece
	 * reglas de autorización, manejo de sesiones e integración del filtro JWT.
	 * 
	 * @param seguridadHttp configuración de seguridad HTTP.
	 * @return una instancia de SecurityFilterChain con la configuración.
	 * @throws Exception si ocurre un error.
	 */
	@Bean
	public SecurityFilterChain cadenaFiltroSeguridad(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf
						.disable())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth

						// 🔓 ENDPOINTS PÚBLICOS
						.requestMatchers("/api/login", "/api/loginGoogle", "/api/verificarEmail",
								"/api/recuperar-contrasena", "/api/restablecer-contrasena", "/api/guardarUsuario",
								"/api/guardarClub", "/api/guardarInstalacion")
						.permitAll()

						.requestMatchers(HttpMethod.GET, "/api/mostrarClubEstadisticaGlobal",
								"/api/clubEstadisticaGlobal/**", "/api/mostrarJugadorEstadisticaGlobal",
								"/api/jugadorEstadisticaGlobal/**", "/api/mostrarClubEstadisticaTorneo",
								"/api/clubEstadisticaTorneo/**", "/api/mostrarJugadorEstadisticaTorneo",
								"/api/jugadorEstadisticaTorneo/**", "/api/mostrarTorneo", "/api/torneo",
								"/api/mostrarEquipoTorneo", "/api/equipoTorneo/**", "/api/miembroClub/**",
								"/api/jugadores/porClub/**", "/api/mostrarPartidosTorneo", "/api/partidosPorTorneo/**",
								"/api/partidoTorneo/**", "/api/actaPartido/**", "/api/mostrarActasPartidos")
						.permitAll()

						// 🔒 SOLO ADMINISTRADOR
						.requestMatchers(HttpMethod.GET, "/api/mostrarUsuarios", "/api/mostrarClubes", "/api/mostrarInstalaciones")
						.hasRole("ADMINISTRADOR")

						.requestMatchers(HttpMethod.DELETE, "/api/eliminarUsuario/**", "/api/eliminarClub/**",
								"/api/eliminarInstalacion/**")
						.hasRole("ADMINISTRADOR")

						.requestMatchers(HttpMethod.PUT, "/api/modificarUsuario/**", "/api/modificarClub/**",
								"/api/modificarInstalacion/**")
						.hasRole("ADMINISTRADOR")

						// 🔐 SOLO USUARIO LOGUEADO (JUGADOR, CLUB o INSTALACION)
						.requestMatchers(HttpMethod.POST, "/api/guardarMiembroClub").hasAnyRole("USUARIO", "CLUB")
						.requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasRole("USUARIO")
						.requestMatchers(HttpMethod.GET, "/api/club/**").hasRole("CLUB")
						.requestMatchers(HttpMethod.DELETE, "/api/eliminarMiembroClub/**").hasAnyRole("USUARIO", "CLUB")
						.requestMatchers(HttpMethod.PUT, "/api/modificarPremiumUsuario/**").hasRole("USUARIO")
						.requestMatchers(HttpMethod.PUT, "/api/modificarPremiumClub/**").hasRole("CLUB")
						.requestMatchers(HttpMethod.POST, "/api/guardarEquipoTorneo").hasRole("CLUB")
						.requestMatchers(HttpMethod.GET, "/api/instalacion/**").hasRole("INSTALACION")
						.requestMatchers(HttpMethod.POST, "/api/guardarTorneo").hasRole("INSTALACION")
						.requestMatchers(HttpMethod.POST, "/api/modificarPartidoTorneo/**").hasRole("INSTALACION")
						.requestMatchers(HttpMethod.POST, "/api/guardarPartidoTorneo").hasRole("INSTALACION")
						.requestMatchers(HttpMethod.POST, "/api/guardarActaPartido").hasRole("INSTALACION")

						// CUALQUIER OTRA RUTA NECESITA TOKEN
						.anyRequest().authenticated())
				.authenticationProvider(proveedorAutenticacion)
				.addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class).build();
	}

}
