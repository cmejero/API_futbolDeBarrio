package com.futbolDeBarrio.futbolDeBarrio.dtos;

/**
 * DTO para manejar el login con Google.
 * Contiene únicamente la información mínima necesaria.
 */
public class LoginGoogleDto {

    private String email;
    private String tipoUsuario;

    public LoginGoogleDto() {
        // Constructor vacío necesario para el mapeo JSON
    }

    public LoginGoogleDto(String email, String tipoUsuario) {
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}