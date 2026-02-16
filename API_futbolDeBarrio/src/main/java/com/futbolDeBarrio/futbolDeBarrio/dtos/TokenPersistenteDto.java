package com.futbolDeBarrio.futbolDeBarrio.dtos;

/**
 * Clase que se encarga de los campos para el token de recordar sesión
 */
public class TokenPersistenteDto {

    private String token;
    private String tipoUsuario;
    private Object datosUsuario; // Puede ser UsuarioDto, ClubDto o InstalacionDto

    public TokenPersistenteDto() {}

    public TokenPersistenteDto(String token, String tipoUsuario, Object datosUsuario) {
        this.token = token;
        this.tipoUsuario = tipoUsuario;
        this.datosUsuario = datosUsuario;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Object getDatosUsuario() { return datosUsuario; }
    public void setDatosUsuario(Object datosUsuario) { this.datosUsuario = datosUsuario; }
}
