package com.futbolDeBarrio.futbolDeBarrio.dtos;

public class RespuestaLoginDto {
    
    private String token;
    private String mensaje;
    
    // Constructor con parámetros
    public RespuestaLoginDto(String token, String mensaje) {
        this.token = token;
        this.mensaje = mensaje;
    }

    // Constructor vacío (opcional, si lo necesitas)
    public RespuestaLoginDto() {
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
