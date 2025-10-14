package com.futbolDeBarrio.futbolDeBarrio.dtos;

/**
 * DTO para manejar el login con Google.
 * Contiene únicamente la información mínima necesaria.
 */
public class LoginGoogleDto {

	private String token;
    private String email;
    private String tipoUsuario;
    private String nombreCompleto;
    private Long idTipoUsuario;
    private boolean esPremium;

    public LoginGoogleDto() {
       
    }

    public LoginGoogleDto(String token, String email, String tipoUsuario, String nombreCompleto, Long idTipoUsuario, boolean esPremium) {
        this.token = token;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.nombreCompleto = nombreCompleto;
        this.idTipoUsuario = idTipoUsuario;
        this.esPremium = esPremium;
    }

    
    

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public boolean isEsPremium() {
		return esPremium;
	}

	public void setEsPremium(boolean esPremium) {
		this.esPremium = esPremium;
	}
    
    
}
