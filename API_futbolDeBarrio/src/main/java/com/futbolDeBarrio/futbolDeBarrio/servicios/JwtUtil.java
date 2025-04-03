package com.futbolDeBarrio.futbolDeBarrio.servicios;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    
    private final String SECRET_KEY = "mySecretKey"; // Debes cambiar esta clave por una más segura
    
    public String generarToken(String username, Rol rol) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", rol.name())  
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Expiración: 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Método para validar el token
    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY))
               .build()
               .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método para extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                  .build()
                  .verify(token)
                  .getSubject();
    }
}

