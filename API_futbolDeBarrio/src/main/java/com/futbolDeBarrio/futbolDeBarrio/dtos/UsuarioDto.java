package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Base64;

import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
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
	String imagenUsuario;
	Estado estadoUsuario;
	
	
	
	
	
	public UsuarioDto(String nombreCompletoUsuario, String aliasusuario,
			String emailUsuario) {
		super();
	
		this.nombreCompletoUsuario = nombreCompletoUsuario;
		this.aliasUsuario = aliasusuario;
		this.emailUsuario = emailUsuario;
		
	}
	
	
	public UsuarioDto(UsuarioEntidad usuarioEntidad) {
        this.idUsuario = usuarioEntidad.getIdUsuario();
        this.nombreCompletoUsuario = usuarioEntidad.getNombreCompletoUsuario();
        this.aliasUsuario = usuarioEntidad.getAliasUsuario();
        this.fechaNacimientoUsuario = usuarioEntidad.getFechaNacimientoUsuario();
        this.emailUsuario = usuarioEntidad.getEmailUsuario();
        this.telefonoUsuario = usuarioEntidad.getTelefonoUsuario();
        this.passwordUsuario = usuarioEntidad.getPasswordUsuario();
        this.rolUsuario = usuarioEntidad.getRolUsuario();
        this.descripcionUsuario = usuarioEntidad.getDescripcionUsuario();
	        if (usuarioEntidad.getImagenUsuario() != null) {
	            this.imagenUsuario = Base64.getEncoder().encodeToString(usuarioEntidad.getImagenUsuario());
	        }
        this.estadoUsuario = usuarioEntidad.getEstadoUsuario();
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
	public String getImagenUsuario() {
		return imagenUsuario;
	}
	public void setImagenUsuario(String imagenUsuario) {
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
