package com.futbolDeBarrio.futbolDeBarrio.jwt;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
/**
 * Clase que proporciona funcionalidades relacionadas con la generación de JWT.
 */
public class JwtFuncionalidades {

	  private static final String CLAVE_SECRETA = "AltairFutbolDeBarrioSuperSecreto123456"; 

	

    /**
     * Genera un token JWT utilizando el email del usuario y su rol.
     *
     * @param email Email del usuario (se usará como "subject" del token)
     * @param rol   Rol del usuario, que se añade como "claim" adicional
     * @return Token JWT como cadena
     */
	    public String obtenerToken(String email, Rol rol) {
	        Map<String, Object> extraClaims = new HashMap<>();
	        extraClaims.put("role", rol.name());

	        return Jwts.builder()
	                .setClaims(extraClaims)
	                .setSubject(email)
	                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
	                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)) 
	                .signWith(Keys.hmacShaKeyFor(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
	                .compact();
	    }
  

    
}
