package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.futbolDeBarrio.futbolDeBarrio.enums.EstadoParticipacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEquipoTorneo")
@Entity
@Table(name = "equipo_torneo", schema = "sch")
public class EquipoTorneoEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipo_torneo")
	private long idEquipoTorneo;

	@Column(name = "fecha_inicio_participacion")
	private String fechaInicioParticipacion;

	@Column(name = "fecha_fin_participacion")
	private String fechaFinParticipacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_participacion")
	private EstadoParticipacion estadoParticipacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "torneo_id", referencedColumnName = "id_torneo", nullable = false)
	private TorneoEntidad torneo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id", referencedColumnName = "id_club", nullable = false)
	private ClubEntidad club;

	@OneToMany(mappedBy = "equipoLocal", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ActaPartidoEntidad> actasComoLocal = new ArrayList<>();

	@OneToMany(mappedBy = "equipoVisitante", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ActaPartidoEntidad> actasComoVisitante = new ArrayList<>();

	@OneToMany(mappedBy = "equipoTorneo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EventoPartidoEntidad> eventoPartido = new ArrayList<>();

	public long getIdEquipoTorneo() {
		return idEquipoTorneo;
	}

	public void setIdEquipoTorneo(long idEquipoTorneo) {
		this.idEquipoTorneo = idEquipoTorneo;
	}

	public TorneoEntidad getTorneo() {
		return torneo;
	}

	public void setTorneo(TorneoEntidad torneo) {
		this.torneo = torneo;
	}

	public ClubEntidad getClub() {
		return club;
	}

	public void setClub(ClubEntidad club) {
		this.club = club;
	}

	public String getFechaInicioParticipacion() {
		return fechaInicioParticipacion;
	}

	public void setFechaInicioParticipacion(String fechaInicioParticipacion) {
		this.fechaInicioParticipacion = fechaInicioParticipacion;
	}

	public String getFechaFinParticipacion() {
		return fechaFinParticipacion;
	}

	public void setFechaFinParticipacion(String fechaFinParticipacion) {
		this.fechaFinParticipacion = fechaFinParticipacion;
	}

	public EstadoParticipacion getEstadoParticipacion() {
		return estadoParticipacion;
	}

	public void setEstadoParticipacion(EstadoParticipacion estadoParticipacion) {
		this.estadoParticipacion = estadoParticipacion;
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

	public EquipoTorneoEntidad() {
	}
}
