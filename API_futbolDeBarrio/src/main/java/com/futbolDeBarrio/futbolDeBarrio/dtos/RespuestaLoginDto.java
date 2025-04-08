package com.futbolDeBarrio.futbolDeBarrio.dtos;

public class RespuestaLoginDto {

    private String token;
    private String tipoUsuario;
    private Object datosUsuario; // Puede ser un Usuario, Club o Instalacion

    // Constructores
    public RespuestaLoginDto(String tipoUsuario, String token, Object datosUsuario) {
        this.token = token;
        this.tipoUsuario = tipoUsuario;
        this.datosUsuario = datosUsuario;
    }

    public RespuestaLoginDto() {
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Object getDatosUsuario() {
        return datosUsuario;
    }

    public void setDatosUsuario(Object datosUsuario) {
        this.datosUsuario = datosUsuario;
    }
}
