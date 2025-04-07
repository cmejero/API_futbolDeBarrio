package com.futbolDeBarrio.futbolDeBarrio.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;  // Importación correcta de HttpHeaders
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFiltroAutentificacion extends OncePerRequestFilter {

    // Realiza todos filtros relacionados al token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Aquí agregarías la lógica de validación del token
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        // Obtener el token de la cabecera 'Authorization'
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Validar que la cabecera no sea nula o vacía y extraer el token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);  // Extrae el token, después de "Bearer "
        }
        return null;
    }
}
