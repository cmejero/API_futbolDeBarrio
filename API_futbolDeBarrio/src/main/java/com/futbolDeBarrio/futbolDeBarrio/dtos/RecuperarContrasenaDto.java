package com.futbolDeBarrio.futbolDeBarrio.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Clase que se encarga de los campos de recuperar contrasena
 */
public class RecuperarContrasenaDto {


    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    private String email;

    // Getter y setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
