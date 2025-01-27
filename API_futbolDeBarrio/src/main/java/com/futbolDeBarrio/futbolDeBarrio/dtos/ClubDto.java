package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "club", schema = "sch")
public class ClubDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club")
    private long idClub;

    @Column(name = "nombre_club")
    private String nombreClub;
    
    @Column(name = "abreviatura_club")
    private String abreviaturaClub;

    @Column(name = "descripcion_club")
    private String descripcionClub;

    @Column(name = "fecha_creacion_club")
    private Date fechaCreacionClub;

    @Column(name = "fecha_fundacion_club")
    private Date fechaFundacionClub;

    @Column(name = "localidad_club")
    private String localidadClub;

    @Column(name = "pais_club")
    private String paisClub;

    @Column(name = "logo_club")
    private String logoClub;

    @Column(name = "email_club")
    private String emailClub;

    @Column(name = "password_club")
    private String passwordClub;

    @Column(name = "telefono_club")
    private String telefonoClub;

    // Relación con InstalacionDto
    @ManyToOne
    @JoinColumn(name = "instalacion_id", referencedColumnName = "id_instalacion", nullable = false)
    private InstalacionDto instalacion;
    

    // Relación con los clubes
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoTorneoDto> equipoTorneo = new ArrayList<>();
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiembroClubDto> miembroClub = new ArrayList<>();

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

    public Date getFechaCreacionClub() {
        return fechaCreacionClub;
    }

    public void setFechaCreacionClub(Date fechaCreacionClub) {
        this.fechaCreacionClub = fechaCreacionClub;
    }

    public Date getFechaFundacionClub() {
        return fechaFundacionClub;
    }

    public void setFechaFundacionClub(Date fechaFundacionClub) {
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

    public InstalacionDto getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(InstalacionDto instalacion) {
        this.instalacion = instalacion;
    }
}
