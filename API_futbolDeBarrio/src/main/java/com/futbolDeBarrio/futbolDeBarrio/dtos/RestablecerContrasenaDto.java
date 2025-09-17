package com.futbolDeBarrio.futbolDeBarrio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Clase que se encarga de los campos de restablecer contrasena
 */
public class RestablecerContrasenaDto {

    @NotBlank(message = "El token no puede estar vacío")
    private String token;

    @NotBlank(message = "La nueva contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String nuevaContrasena;

    @NotBlank(message = "Debe repetir la nueva contraseña")
    private String repetirContrasena;

    // Getters y setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }

    public String getRepetirContrasena() {
        return repetirContrasena;
    }

    public void setRepetirContrasena(String repetirContrasena) {
        this.repetirContrasena = repetirContrasena;
    }

}
