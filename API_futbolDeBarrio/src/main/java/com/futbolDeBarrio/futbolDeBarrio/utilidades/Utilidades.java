package com.futbolDeBarrio.futbolDeBarrio.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;

public class Utilidades {

	public static final String nombreArchivoLog() {
		try {
			LocalDate fechaActual = LocalDate.now();
			String fechaStr = fechaActual.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
			return "log-" + fechaStr + ".txt";
		} catch(Exception e) {
			System.out.println("Se ha producido un error [1003], intentelo más tarde");
			return "log-error.txt";
		}
	}

	public static final String nombreCarpetaFecha() {
		try {
			LocalDate fechaActual = LocalDate.now();
			return fechaActual.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
		} catch(Exception e) {
			System.out.println("Se ha producido un error [1004], intentelo más tarde");
			return "errorFecha";
		}
	}
	   /**
     * Método que encripta una contraseña antes de guardarla
     */
	public static String encriptarContrasenya(String contraseña) {
	    if (contraseña == null || contraseña.isEmpty()) {
	        throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
	    }
	    // Genera el hash de la contraseña usando BCrypt
	    return BCrypt.hashpw(contraseña, BCrypt.gensalt());
	}
    
	public static boolean verificarContrasena(String contraseñaIngresada, String hashAlmacenado) {
	    // Verifica que la contraseña ingresada coincide con el hash almacenado
	    return BCrypt.checkpw(contraseñaIngresada, hashAlmacenado);
	}
}
