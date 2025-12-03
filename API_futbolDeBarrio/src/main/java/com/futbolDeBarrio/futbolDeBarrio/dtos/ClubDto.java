package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Base64;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Clase que se encarga de los campos de club
 */
public class ClubDto {

	private long idClub;

	@NotBlank(message = "El nombre del club es obligatorio") // Validación de campo no vacío
	private String nombreClub;

	@NotBlank(message = "La abreviatura del club es obligatoria") // Validación de campo no vacío
	private String abreviaturaClub;


	private String descripcionClub;

	private String fechaCreacionClub;

	private String fechaFundacionClub;

	@NotBlank(message = "La localidad del club es obligatoria")
	private String localidadClub;

	@NotBlank(message = "El país del club es obligatorio")
	private String paisClub;

	private String logoClub;

	@Email(message = "El email debe tener un formato válido")
	@NotBlank(message = "El email del club es obligatorio")
	private String emailClub;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	private String passwordClub;

	@Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos")
	private String telefonoClub;

	private boolean esPremium = false;

	public ClubDto(ClubEntidad clubEntidad) {
	    this.idClub = clubEntidad.getIdClub();
	    this.nombreClub = clubEntidad.getNombreClub() != null ? clubEntidad.getNombreClub() : "";
	    this.abreviaturaClub = clubEntidad.getAbreviaturaClub() != null ? clubEntidad.getAbreviaturaClub() : "";
	    this.descripcionClub = clubEntidad.getDescripcionClub() != null ? clubEntidad.getDescripcionClub() : "";
	    this.fechaCreacionClub = clubEntidad.getFechaCreacionClub() != null ? clubEntidad.getFechaCreacionClub() : "";
	    this.fechaFundacionClub = clubEntidad.getFechaFundacionClub() != null ? clubEntidad.getFechaFundacionClub() : "";
	    this.localidadClub = clubEntidad.getLocalidadClub() != null ? clubEntidad.getLocalidadClub() : "";
	    this.paisClub = clubEntidad.getPaisClub() != null ? clubEntidad.getPaisClub() : "";
	    if (clubEntidad.getLogoClub() != null) {
	        this.logoClub = Base64.getEncoder().encodeToString(clubEntidad.getLogoClub());
	    } else {
	        this.logoClub = "";
	    }
	    this.emailClub = clubEntidad.getEmailClub() != null ? clubEntidad.getEmailClub() : "";
	    this.passwordClub = clubEntidad.getPasswordClub() != null ? clubEntidad.getPasswordClub() : "";
	    this.telefonoClub = clubEntidad.getTelefonoClub() != null ? clubEntidad.getTelefonoClub() : "";
	    this.esPremium = clubEntidad.isEsPremium();
	}


	public long getIdClub() {
		return idClub;
	}

	public void setIdClub(long idClub) {
		this.idClub = idClub;
	}

	public String getNombreClub() {
		return nombreClub;
	}

	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public String getAbreviaturaClub() {
		return abreviaturaClub;
	}

	public void setAbreviaturaClub(String abreviaturaClub) {
		this.abreviaturaClub = abreviaturaClub;
	}

	public String getDescripcionClub() {
		return descripcionClub;
	}

	public void setDescripcionClub(String descripcionClub) {
		this.descripcionClub = descripcionClub;
	}

	public String getFechaCreacionClub() {
		return fechaCreacionClub;
	}

	public void setFechaCreacionClub(String fechaCreacionClub) {
		this.fechaCreacionClub = fechaCreacionClub;
	}

	public String getFechaFundacionClub() {
		return fechaFundacionClub;
	}

	public void setFechaFundacionClub(String fechaFundacionClub) {
		this.fechaFundacionClub = fechaFundacionClub;
	}

	public String getLocalidadClub() {
		return localidadClub;
	}

	public void setLocalidadClub(String localidadClub) {
		this.localidadClub = localidadClub;
	}

	public String getPaisClub() {
		return paisClub;
	}

	public void setPaisClub(String paisClub) {
		this.paisClub = paisClub;
	}

	public String getLogoClub() {
		return logoClub;
	}

	public void setLogoClub(String logoClub) {
		this.logoClub = logoClub;
	}

	public String getEmailClub() {
		return emailClub;
	}

	public void setEmailClub(String emailClub) {
		this.emailClub = emailClub;
	}

	public String getPasswordClub() {
		return passwordClub;
	}

	public void setPasswordClub(String passwordClub) {
		this.passwordClub = passwordClub;
	}

	public String getTelefonoClub() {
		return telefonoClub;
	}

	public void setTelefonoClub(String telefonoClub) {
		this.telefonoClub = telefonoClub;
	}

	public boolean isEsPremium() {
		return esPremium;
	}

	public void setEsPremium(boolean esPremium) {
		this.esPremium = esPremium;
	}

	public ClubDto() {
		super();

	}

}
