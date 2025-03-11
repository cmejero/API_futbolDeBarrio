package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;

public class ClubDto {

    private long idClub;
    private String nombreClub;
    private String abreviaturaClub;
    private String descripcionClub;
    private String fechaCreacionClub;
    private String fechaFundacionClub;
    private String localidadClub;
    private String paisClub;
    private String logoClub;
    private String emailClub;
    private String passwordClub;
    private String telefonoClub;

    // Constructor que recibe una entidad de Club
    public ClubDto(ClubEntidad clubEntidad) {
        this.idClub = clubEntidad.getIdClub();
        this.nombreClub = clubEntidad.getNombreClub();
        this.abreviaturaClub = clubEntidad.getAbreviaturaClub();
        this.descripcionClub = clubEntidad.getDescripcionClub();
        this.fechaCreacionClub = clubEntidad.getFechaCreacionClub();
        this.fechaFundacionClub = clubEntidad.getFechaFundacionClub();
        this.localidadClub = clubEntidad.getLocalidadClub();
        this.paisClub = clubEntidad.getPaisClub();
        this.logoClub = clubEntidad.getLogoClub();
        this.emailClub = clubEntidad.getEmailClub();
        this.passwordClub = clubEntidad.getPasswordClub();
        this.telefonoClub = clubEntidad.getTelefonoClub();
    }

    // Getters y Setters
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

	public ClubDto() {
		super();
		
	}
    
    
}
