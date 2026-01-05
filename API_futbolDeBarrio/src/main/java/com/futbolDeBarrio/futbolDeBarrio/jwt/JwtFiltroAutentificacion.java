package com.futbolDeBarrio.futbolDeBarrio.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;

@Component
/**
 * Clase que se encarga del filtro de autenticación del JWT.
 */
public class JwtFiltroAutentificacion extends OncePerRequestFilter {

    // Clave secreta para firmar y verificar el JWT
	private final String CLAVE_SECRETA = "AltairFutbolDeBarrioSuperSecreto123456";

    /**
     * Método para interceptar y verificar el token JWT en cada solicitud.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest solicitud, HttpServletResponse respuesta, FilterChain cadena)
            throws ServletException, IOException {

        String token = obtenerTokenDeSolicitud(solicitud);  
        if (token == null) {
            cadena.doFilter(solicitud, respuesta); 
            return;
        }
        try {
            Claims datos = Jwts.parserBuilder()  
                    .setSigningKey(obtenerClave()) 
                    .build()
                    .parseClaimsJws(token)  
                    .getBody();  
            String usuario = datos.getSubject();  
            String rol = datos.get("role", String.class);  
            if (usuario != null && rol != null) {  
                SimpleGrantedAuthority autoridad = new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()); 
                UsernamePasswordAuthenticationToken tokenAutenticacion = new UsernamePasswordAuthenticationToken(
                        usuario, null, Collections.singleton(autoridad)  
                );
                SecurityContextHolder.getContext().setAuthentication(tokenAutenticacion);  
            }
        } catch (Exception e) {
            respuesta.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            respuesta.getWriter().write("Token inválido o expirado.");
            return;
        }
        cadena.doFilter(solicitud, respuesta);  
    }

    /**
     * Método que extrae el token JWT del encabezado de la solicitud HTTP.
     * @param solicitud Solicitud HTTP entrante.
     * @return El token JWT o null si no existe.
     */
    private String obtenerTokenDeSolicitud(HttpServletRequest solicitud) {
        String cabeceraAutorizacion = solicitud.getHeader(HttpHeaders.AUTHORIZATION);  
        if (cabeceraAutorizacion != null && cabeceraAutorizacion.startsWith("Bearer ")) {  
            return cabeceraAutorizacion.substring(7);  
        }
        return null; 
    }

    /**
     * Método que genera la clave secreta para la firma del token JWT.
     * @return La clave secreta como un objeto Key.
     */
    private Key obtenerClave() {
        return Keys.hmacShaKeyFor(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8));  
    }
}
