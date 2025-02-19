package com.futbolDeBarrio.futbolDeBarrio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

import com.futbolDeBarrio.futbolDeBarrio.enums.EstadoParticipacion;

@Entity
@Table(name = "equipo_torneo", schema = "sch")
public class EquipoTorneoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo_torneo")
    private long idEquipoTorneo;

    @Column(name = "fecha_inicio_participacion")
    private Date fechaInicioParticipacion;

    @Column(name = "fecha_fin_participacion")
    private Date fechaFinParticipacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_participacion")
    private EstadoParticipacion estadoParticipacion;
    
    @ManyToOne
    @JoinColumn(name = "torneo_id", referencedColumnName = "id_torneo", nullable = false)
    private TorneoEntidad torneo;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad club;
    
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

	public Date getFechaInicioParticipacion() {
		return fechaInicioParticipacion;
	}

	public void setFechaInicioParticipacion(Date fechaInicioParticipacion) {
		this.fechaInicioParticipacion = fechaInicioParticipacion;
	}

	public Date getFechaFinParticipacion() {
		return fechaFinParticipacion;
	}

	public void setFechaFinParticipacion(Date fechaFinParticipacion) {
		this.fechaFinParticipacion = fechaFinParticipacion;
	}

	public EstadoParticipacion getEstadoParticipacion() {
		return estadoParticipacion;
	}

	public void setEstadoParticipacion(EstadoParticipacion estadoParticipacion) {
		this.estadoParticipacion = estadoParticipacion;
	}

	public EquipoTorneoEntidad() {

	}

    
}
