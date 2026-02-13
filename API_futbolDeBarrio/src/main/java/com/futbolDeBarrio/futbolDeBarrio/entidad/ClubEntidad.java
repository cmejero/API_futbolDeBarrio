																																																																																																																																																																																																																																																																														package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "club", schema = "sch")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idClub")
public class ClubEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_club")
	private long idClub;

	@Size(min = 3, max = 50, message = "El nombre del club debe tener entre 3 y 100 caracteres")
	@Column(name = "nombre_club")
	private String nombreClub;

	@Size(min = 2, max = 3, message = "La abreviatura del club debe tener entre 2 y 20 caracteres")
	@Column(name = "abreviatura_club")
	private String abreviaturaClub;

	@Size(max = 300, message = "La descripción del club puede tener hasta 500 caracteres")
	@Column(name = "descripcion_club")
	private String descripcionClub;

	@Column(name = "fecha_creacion_club")
	private String fechaCreacionClub;

	@Column(name = "fecha_fundacion_club")
	private String fechaFundacionClub;

	@Size(min = 2, max = 50, message = "La localidad debe tener entre 2 y 50 caracteres")
	@Column(name = "localidad_club")
	private String localidadClub;

	@Size(min = 2, max = 30, message = "El país debe tener entre 2 y 50 caracteres")
	@Column(name = "pais_club")
	private String paisClub;

	@Column(name = "logo_club")
	private byte[] logoClub;

	@Size(min = 5, max = 40, message = "El correo debe tener entre 5 y 50 caracteres")
	@Column(name = "email_club")
	private String emailClub;

	@Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
	@Column(name = "password_club")
	private String passwordClub;

	@Size(min = 9, max = 15, message = "El teléfono debe tener entre 9 y 15 dígitos")
	@Column(name = "telefono_club")
	private String telefonoClub;

	@Column(name = "es_premium")
	private boolean esPremium;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_cuenta")
    private CuentaEntidad cuenta;

    // Relación con los clubes
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EquipoTorneoEntidad> equipoTorneo = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MiembroClubEntidad> miembroClub = new ArrayList<>();

    @OneToMany(mappedBy = "clubLocal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ActaPartidoEntidad> actasComoLocal = new ArrayList<>();

    @OneToMany(mappedBy = "clubVisitante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ActaPartidoEntidad> actasComoVisitante = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EventoPartidoEntidad> eventoPartido = new ArrayList<>();

    @OneToMany(mappedBy = "clubGlobal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClubEstadisticaGlobalEntidad> clubEstadisticaGlobal = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClubEstadisticaTorneoEntidad> clubEstadisticaTorneo = new ArrayList<>();

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

    public byte[] getLogoClub() {
        return logoClub;
    }

    public void setLogoClub(byte[] logoClub) {
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
    
    

    public CuentaEntidad getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaEntidad cuenta) {
		this.cuenta = cuenta;
	}

	public List<EquipoTorneoEntidad> getEquipoTorneo() {
        return equipoTorneo;
    }

    public void setEquipoTorneo(List<EquipoTorneoEntidad> equipoTorneo) {
        this.equipoTorneo = equipoTorneo;
    }

    public List<MiembroClubEntidad> getMiembroClub() {
        return miembroClub;
    }

    public void setMiembroClub(List<MiembroClubEntidad> miembroClub) {
        this.miembroClub = miembroClub;
    }

    public List<ActaPartidoEntidad> getActasComoLocal() {
        return actasComoLocal;
    }

    public void setActasComoLocal(List<ActaPartidoEntidad> actasComoLocal) {
        this.actasComoLocal = actasComoLocal;
    }

    public List<ActaPartidoEntidad> getActasComoVisitante() {
        return actasComoVisitante;
    }

    public void setActasComoVisitante(List<ActaPartidoEntidad> actasComoVisitante) {
        this.actasComoVisitante = actasComoVisitante;
    }

    public List<EventoPartidoEntidad> getEventoPartido() {
        return eventoPartido;
    }

    public void setEventoPartido(List<EventoPartidoEntidad> eventoPartido) {
        this.eventoPartido = eventoPartido;
    }

    public List<ClubEstadisticaGlobalEntidad> getClubEstadisticaGlobal() {
        return clubEstadisticaGlobal;
    }

    public void setClubEstadisticaGlobal(List<ClubEstadisticaGlobalEntidad> clubEstadisticaGlobal) {
        this.clubEstadisticaGlobal = clubEstadisticaGlobal;
    }

    public List<ClubEstadisticaTorneoEntidad> getClubEstadisticaTorneo() {
        return clubEstadisticaTorneo;
    }

    public void setClubEstadisticaTorneo(List<ClubEstadisticaTorneoEntidad> clubEstadisticaTorneo) {
        this.clubEstadisticaTorneo = clubEstadisticaTorneo;
    }

    public boolean isEsPremium() {
        return esPremium;
    }

    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }
}
