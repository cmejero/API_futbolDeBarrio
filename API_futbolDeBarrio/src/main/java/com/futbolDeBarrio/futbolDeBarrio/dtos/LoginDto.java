package com.futbolDeBarrio.futbolDeBarrio.dtos;

/**
 * Clase que se encarga de los campos del login
 */
public class LoginDto {
	String email;
	String password;
	String tipoUsuario;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
