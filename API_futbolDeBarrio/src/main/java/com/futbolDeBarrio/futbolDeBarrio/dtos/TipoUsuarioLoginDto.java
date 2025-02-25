package com.futbolDeBarrio.futbolDeBarrio.dtos;

public class TipoUsuarioLoginDto {
    private String tipoUsuario;
    private Object usuarioEntidad;  // Puede ser UsuarioEntidad, ClubEntidad o InstalacionEntidad
    private String mensajeError;    // Mensaje de error (opcional)

    // Constructor con parámetros
    public TipoUsuarioLoginDto(String tipoUsuario, Object usuarioEntidad) {
        this.tipoUsuario = tipoUsuario;
        this.usuarioEntidad = usuarioEntidad;
    }

    // Constructor para mensajes de error
    public TipoUsuarioLoginDto(String tipoUsuario, Object usuarioEntidad, String mensajeError) {
        this.tipoUsuario = tipoUsuario;
        this.usuarioEntidad = usuarioEntidad;
        this.mensajeError = mensajeError;
    }

    // Getters y setters
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Object getUsuarioEntidad() {
        return usuarioEntidad;
    }

    public void setUsuarioEntidad(Object usuarioEntidad) {
        this.usuarioEntidad = usuarioEntidad;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}



