package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Base64;

import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Clase que se encarga de los campos de usuario
 */
public class UsuarioDto {

	private long idUsuario;

	@NotBlank(message = "El nombre completo es obligatorio.")
	@Size(max = 50, message = "El nombre completo no puede exceder los 50 caracteres.")
	private String nombreCompletoUsuario;

	@NotBlank(message = "El alias es obligatorio.")
	@Size(max = 30, message = "El alias no puede exceder los 30 caracteres.")
	private String aliasUsuario;

	@NotBlank(message = "La fecha de nacimiento es obligatoria.")
	private String fechaNacimientoUsuario;

	@Email(message = "El correo electrónico debe ser válido.")
	@NotBlank(message = "El correo electrónico es obligatorio.")
	private String emailUsuario;

	@Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "El teléfono debe tener entre 9 y 15 dígitos y puede empezar con +.")
	private String telefonoUsuario;

	@NotBlank(message = "La contraseña es obligatoria.")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
	private String passwordUsuario;

	private RolUsuario rolUsuario;

	@Size(max = 500, message = "La descripción no puede exceder los 500 caracteres.")
	private String descripcionUsuario;

	private String imagenUsuario;

	private Estado estadoUsuario;

	private boolean esPremium = false;

	public UsuarioDto(String nombreCompletoUsuario, String aliasusuario, String emailUsuario) {
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
		this.esPremium = usuarioEntidad.isEsPremium();
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

	public boolean isEsPremium() {
		return esPremium;
	}

	public void setEsPremium(boolean esPremium) {
		this.esPremium = esPremium;
	}

	public UsuarioDto() {
		super();
	}

}
