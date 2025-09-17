package com.futbolDeBarrio.futbolDeBarrio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador_estadistica_global", schema = "sch")
public class JugadorEstadisticaGlobalEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador_estadistica_global")
    private Long idJugadorEstadisticaGlobal;

    @ManyToOne
    @JoinColumn(name = "jugador_id_global", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioEntidad jugadorGlobalId;

    @Column(name = "goles_global")
    private int golesGlobal;

    @Column(name = "asistencias_global")
    private int asistenciasGlobal;

    @Column(name = "amarillas_global")
    private int amarillasGlobal;

    @Column(name = "rojas_global")
    private int rojasGlobal;

    @Column(name = "partidos_jugados_global")
    private int partidosJugadosGlobal;

    @Column(name = "minutos_jugados_global")
    private int minutosJugadosGlobal;

	public Long getIdJugadorEstadisticaGlobal() {
		return idJugadorEstadisticaGlobal;
	}

	public void setIdJugadorEstadisticaGlobal(Long idJugadorEstadisticaGlobal) {
		this.idJugadorEstadisticaGlobal = idJugadorEstadisticaGlobal;
	}

	public UsuarioEntidad getJugadorGlobalId() {
		return jugadorGlobalId;
	}

	public void setJugadorGlobalId(UsuarioEntidad jugadorGlobalId) {
		this.jugadorGlobalId = jugadorGlobalId;
	}

	public int getGolesGlobal() {
		return golesGlobal;
	}

	public void setGolesGlobal(int golesGlobal) {
		this.golesGlobal = golesGlobal;
	}

	public int getAsistenciasGlobal() {
		return asistenciasGlobal;
	}

	public void setAsistenciasGlobal(int asistenciasGlobal) {
		this.asistenciasGlobal = asistenciasGlobal;
	}

	public int getAmarillasGlobal() {
		return amarillasGlobal;
	}

	public void setAmarillasGlobal(int amarillasGlobal) {
		this.amarillasGlobal = amarillasGlobal;
	}

	public int getRojasGlobal() {
		return rojasGlobal;
	}

	public void setRojasGlobal(int rojasGlobal) {
		this.rojasGlobal = rojasGlobal;
	}

	public int getPartidosJugadosGlobal() {
		return partidosJugadosGlobal;
	}

	public void setPartidosJugadosGlobal(int partidosJugadosGlobal) {
		this.partidosJugadosGlobal = partidosJugadosGlobal;
	}

	public int getMinutosJugadosGlobal() {
		return minutosJugadosGlobal;
	}

	public void setMinutosJugadosGlobal(int minutosJugadosGlobal) {
		this.minutosJugadosGlobal = minutosJugadosGlobal;
	}

    
}
