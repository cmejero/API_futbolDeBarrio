package com.futbolDeBarrio.futbolDeBarrio.dtos;

public class RespuestaLoginDto {
    private String tipoUsuario;
    private String token; 

    public RespuestaLoginDto(String tipoUsuario, String token) {
        this.tipoUsuario = tipoUsuario;
        this.token = token;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
