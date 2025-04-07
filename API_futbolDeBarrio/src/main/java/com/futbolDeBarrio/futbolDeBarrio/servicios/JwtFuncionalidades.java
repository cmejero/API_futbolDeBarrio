package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtFuncionalidades {

    private static final String SECRET_KEY = "altair";

    // Método modificado para aceptar un String (email) y un Rol
    public String obtenerToken(String email, Rol rol) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", rol.name()); // Se agrega el rol en los claims del JWT

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email) // Establece el email como subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Fecha de expiración
                .signWith(getKey(), SignatureAlgorithm.HS256) // Firmar el JWT
                .compact(); // Genera el token JWT
    }

   
    	private Key getKey() {
    	    return Keys.secretKeyFor(SignatureAlgorithm.HS256); 
    	}

    
}
