package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTorneo")
@Entity
@Table(name = "torneo", schema = "sch")
public class TorneoEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_torneo")
	private long idTorneo;

	@NotNull
	@Column(name = "nombre_torneo")
	private String nombreTorneo;

	@NotNull
	@Column(name = "fecha_inicio_torneo")
	private String fechaInicioTorneo;

	@NotNull
	@Column(name = "fecha_fin_torneo")
	private String fechaFinTorneo;

	@Column(name = "descripcion_torneo")
	private String descripcionTorneo;

	@Column(name = "clubesInscritos")
	private String clubesInscritos;

	@NotNull
	@Column(name = "modalidad")
	@Enumerated(EnumType.STRING)
	private Modalidad modalidad;

	@Column(name = "esta_activo")
	private boolean estaActivo;

	@NotNull
	@ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
	@JoinColumn(name = "instalacion_id", referencedColumnName = "id_instalacion", nullable = false)
	private InstalacionEntidad instalacion;

	@OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EquipoTorneoEntidad> equipoTorneo = new ArrayList<>();

	@OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ActaPartidoEntidad> actasPartidos = new ArrayList<>();

	@OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ClubEstadisticaTorneoEntidad> clubEstadisticaTorneo = new ArrayList<>();

	@OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<JugadorEstadisticaTorneoEntidad> jugadorEstadisticaTorneo = new ArrayList<>();

	public TorneoEntidad() {
	}

	public long getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(long idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	public String getFechaInicioTorneo() {
		return fechaInicioTorneo;
	}

	public void setFechaInicioTorneo(String fechaInicioTorneo) {
		this.fechaInicioTorneo = fechaInicioTorneo;
	}

	public String getFechaFinTorneo() {
		return fechaFinTorneo;
	}

	public void setFechaFinTorneo(String fechaFinTorneo) {
		this.fechaFinTorneo = fechaFinTorneo;
	}

	public String getDescripcionTorneo() {
		return descripcionTorneo;
	}

	public void setDescripcionTorneo(String descripcionTorneo) {
		this.descripcionTorneo = descripcionTorneo;
	}

	public String getClubesInscritos() {
		return clubesInscritos;
	}

	public void setClubesInscritos(String clubesInscritos) {
		this.clubesInscritos = clubesInscritos;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public boolean isEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	public InstalacionEntidad getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(InstalacionEntidad instalacion) {
		this.instalacion = instalacion;
	}

	public List<EquipoTorneoEntidad> getEquipoTorneo() {
		return equipoTorneo;
	}

	public void setEquipoTorneo(List<EquipoTorneoEntidad> equipoTorneo) {
		this.equipoTorneo = equipoTorneo;
	}

	public List<ActaPartidoEntidad> getActasPartidos() {
		return actasPartidos;
	}

	public void setActasPartidos(List<ActaPartidoEntidad> actasPartidos) {
		this.actasPartidos = actasPartidos;
	}

	public List<ClubEstadisticaTorneoEntidad> getClubEstadisticaTorneo() {
		return clubEstadisticaTorneo;
	}

	public void setClubEstadisticaTorneo(List<ClubEstadisticaTorneoEntidad> clubEstadisticaTorneo) {
		this.clubEstadisticaTorneo = clubEstadisticaTorneo;
	}

	public List<JugadorEstadisticaTorneoEntidad> getJugadorEstadisticaTorneo() {
		return jugadorEstadisticaTorneo;
	}

	public void setJugadorEstadisticaTorneo(List<JugadorEstadisticaTorneoEntidad> jugadorEstadisticaTorneo) {
		this.jugadorEstadisticaTorneo = jugadorEstadisticaTorneo;
	}
}
