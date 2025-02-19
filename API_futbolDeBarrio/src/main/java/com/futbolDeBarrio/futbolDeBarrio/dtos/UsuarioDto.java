package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;

public class UsuarioDto {

	long idUsuario;
	String nombreCompletoUsuario ="aaaaa";
	String aliasUsuario = "aaaaa";
	String fechaNacimientoUsuario;
	String emailUsuario = "aaaaa";
	String telefonoUsuario ="aaaaa";
	String passwordUsuario = "aaaaa";
	RolUsuario rolUsuario;
	String descripcionUsuario ="aaaaaa";
	byte[] imagenUsuario;
	Estado estadoUsuario;
	
	
	
	
	
	public UsuarioDto(long idUsuario, String nombreCompletoUsuario, String aliasusuario, String fechaNacimientoUsuario,
			String emailUsuario, String telefonoUsuario, String passwordUsuario, RolUsuario rolUsuario,
			String descripcionUsuario, byte[] imagenUsuario, Estado estadoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
		this.aliasUsuario = aliasusuario;
		this.fechaNacimientoUsuario = fechaNacimientoUsuario;
		this.emailUsuario = emailUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.passwordUsuario = passwordUsuario;
		this.rolUsuario = rolUsuario;
		this.descripcionUsuario = descripcionUsuario;
		this.imagenUsuario = imagenUsuario;
		this.estadoUsuario = estadoUsuario;
	}
	
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}
	public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
	
	public String getAliasUsuario() {
		return aliasUsuario;
	}


	public void setAliasUsuario(String aliasUsuario) {
		this.aliasUsuario = aliasUsuario;
	}


	public String getFechaNacimientoUsuario() {
		return fechaNacimientoUsuario;
	}
	public void setFechaNacimientoUsuario(String fechaNacimientoUsuario) {
		this.fechaNacimientoUsuario = fechaNacimientoUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}
	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}
	public String getPasswordUsuario() {
		return passwordUsuario;
	}
	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}
	public RolUsuario getRolUsuario() {
		return rolUsuario;
	}
	public void setRolUsuario(RolUsuario rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	public String getDescripcionUsuario() {
		return descripcionUsuario;
	}
	public void setDescripcionUsuario(String descripcionUsuario) {
		this.descripcionUsuario = descripcionUsuario;
	}
	public byte[] getImagenUsuario() {
		return imagenUsuario;
	}
	public void setImagenUsuario(byte[] imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}
	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}


	public UsuarioDto() {
		super();
	}
	
	
	
	
	
}
