package com.futbolDeBarrio.futbolDeBarrio.utilidades;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletContext;

/**
 * Clase con metodos que usaremos varias veces en la aplicación
 */
public class Utilidades {

	/**
	 * Método que devuelve el nombre del archivo de log basado en la fecha actual.
	 * 
	 * @return el nombre del archivo de log
	 */
	public static final String nombreArchivoLog() {
		try {
			LocalDate fechaActual = LocalDate.now();
			String fechaStr = fechaActual.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
			return "log-" + fechaStr + ".txt";
		} catch (Exception e) {
			return "log-error.txt";
		}
	}

	/**
	 * Método que devuelve el nombre de la carpeta basada en la fecha actual.
	 * 
	 * @return el nombre de la carpeta basada en la fecha
	 */
	public static final String nombreCarpetaFecha() {
		try {
			LocalDate fechaActual = LocalDate.now();
			return fechaActual.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
		} catch (Exception e) {
			return "errorFecha";
		}
	}

	/**
	 * Método que encripta una contraseña antes de guardarla en la base de datos.
	 * 
	 * @param contraseña la contraseña a encriptar
	 * @return el hash de la contraseña
	 * @throws IllegalArgumentException si la contraseña es nula o vacía
	 */
	public static String encriptarContrasenya(String contraseña) {
		if (contraseña == null || contraseña.isEmpty()) {
			throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
		}
		return BCrypt.hashpw(contraseña, BCrypt.gensalt());
	}

	/**
	 * Método que verifica si la contraseña ingresada coincide con el hash
	 * almacenado.
	 * 
	 * @param contraseñaIngresada la contraseña que el usuario ingresa
	 * @param hashAlmacenado      el hash de la contraseña almacenado
	 * @return true si la contraseña ingresada coincide con el hash almacenado,
	 *         false en caso contrario
	 */
	public static boolean verificarContrasena(String contraseñaIngresada, String hashAlmacenado) {
		return BCrypt.checkpw(contraseñaIngresada, hashAlmacenado);
	}

	
}
