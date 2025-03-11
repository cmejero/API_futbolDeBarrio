package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class Utilidades {
	
    /**
     * Método que encripta una contraseña antes de guardarla
     */
	public String encriptarContrasenya(String contraseña) {
	    if (contraseña == null || contraseña.isEmpty()) {
	        throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
	    }
	    // Genera el hash de la contraseña usando BCrypt
	    return BCrypt.hashpw(contraseña, BCrypt.gensalt());
	}
    
	public boolean verificarContrasena(String contraseñaIngresada, String hashAlmacenado) {
	    // Verifica que la contraseña ingresada coincide con el hash almacenado
	    return BCrypt.checkpw(contraseñaIngresada, hashAlmacenado);
	}
}
