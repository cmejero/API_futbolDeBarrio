package com.futbolDeBarrio.futbolDeBarrio.dtos;

public class RespuestaLoginDto {
    private String tipoUsuario; 
    private Object tipoUsuarioDto;  // Contendrá el DTO correspondiente al tipo de usuario

    // Constructor
    public RespuestaLoginDto(String tipoUsuario, Object usuarioDto) {
        this.tipoUsuario = tipoUsuario;
        this.tipoUsuarioDto = usuarioDto;
    }

    // Getters y setters
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Object getUsuarioDto() {
        return tipoUsuarioDto;
    }

    public void setUsuarioDto(Object usuarioDto) {
        this.tipoUsuarioDto = usuarioDto;
    }
}
