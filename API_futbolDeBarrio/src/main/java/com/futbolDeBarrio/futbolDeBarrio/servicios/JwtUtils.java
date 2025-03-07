package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private  String secretKey = "altair"; // Esta clave debe ser secreta y segura

    // Método para generar el token
    public  String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Aquí puedes agregar el email del usuario como el "subject"
                .setIssuedAt(new Date()) // Fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expiración en 24 horas
                .signWith(SignatureAlgorithm.HS256, secretKey) // Firma con el algoritmo HS256
                .compact();
    }
}
