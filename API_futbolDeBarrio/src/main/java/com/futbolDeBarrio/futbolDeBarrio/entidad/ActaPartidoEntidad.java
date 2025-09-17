package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.time.LocalDateTime;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "acta_partido", schema = "sch")
public class ActaPartidoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acta_partido")
    private Long idActaPartido;

    @ManyToOne
    @JoinColumn(name = "torneo_id", referencedColumnName = "id_torneo", nullable = false)
    private TorneoEntidad torneo;
    
    @ManyToOne
    @JoinColumn(name = "instalacion_id", referencedColumnName = "id_instalacion", nullable = false)
    private InstalacionEntidad instalacion;

    @ManyToOne
    @JoinColumn(name = "club_local_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad clubLocal;

    @ManyToOne
    @JoinColumn(name = "club_visitante_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad clubVisitante;

  
    @ManyToOne
    @JoinColumn(name = "equipo_local_id", referencedColumnName = "id_equipo_torneo", nullable = false)
    private EquipoTorneoEntidad equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id", referencedColumnName = "id_equipo_torneo", nullable = false)
    private EquipoTorneoEntidad equipoVisitante;
    
    @OneToOne
    @JoinColumn(name = "partido_torneo_id", nullable = true)
    private PartidoTorneoEntidad partidoTorneo;


    @Column(name = "goles_local")
    private int golesLocal;

    @Column(name = "goles_visitante")
    private int golesVisitante;

    @Column(name = "fecha_partido")
    private LocalDateTime fechaPartido;
    
    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "cerrado")
    private boolean cerrado;


    @OneToMany(mappedBy = "actaPartido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoPartidoEntidad> eventoPartido = new ArrayList<>();

	public Long getIdActaPartido() {
		return idActaPartido;
	}

	public void setIdActaPartido(Long idActaPartido) {
		this.idActaPartido = idActaPartido;
	}

	public TorneoEntidad getTorneo() {
		return torneo;
	}

	public void setTorneo(TorneoEntidad torneo) {
		this.torneo = torneo;
	}
	
	public InstalacionEntidad getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(InstalacionEntidad instalacion) {
		this.instalacion = instalacion;
	}


	public ClubEntidad getClubLocal() {
		return clubLocal;
	}

	public void setClubLocal(ClubEntidad clubLocal) {
		this.clubLocal = clubLocal;
	}

	public ClubEntidad getClubVisitante() {
		return clubVisitante;
	}

	public void setClubVisitante(ClubEntidad clubVisitante) {
		this.clubVisitante = clubVisitante;
	}
	

	public PartidoTorneoEntidad getPartidoTorneo() {
		return partidoTorneo;
	}

	public void setPartidoTorneo(PartidoTorneoEntidad partidoTorneo) {
		this.partidoTorneo = partidoTorneo;
	}

	public EquipoTorneoEntidad getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(EquipoTorneoEntidad equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public EquipoTorneoEntidad getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(EquipoTorneoEntidad equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public LocalDateTime getFechaPartido() {
		return fechaPartido;
	}

	public void setFechaPartido(LocalDateTime fechaPartido) {
		this.fechaPartido = fechaPartido;
	}
	

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean estaCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}


	public List<EventoPartidoEntidad> getEventoPartido() {
		return eventoPartido;
	}

	public void setEventoPartido(List<EventoPartidoEntidad> eventoPartido) {
		this.eventoPartido = eventoPartido;
	}

	public boolean isCerrado() {
		return cerrado;
	}
    
    

	    
	}



