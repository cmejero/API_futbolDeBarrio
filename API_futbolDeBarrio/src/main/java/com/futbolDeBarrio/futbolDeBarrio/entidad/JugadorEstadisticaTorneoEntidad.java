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
@Table(name = "jugador_estadistica_torneo", schema = "sch")
public class JugadorEstadisticaTorneoEntidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_jugador_estadistica_torneo")
	private Long idJugadorEstadisticaTorneo;

	@ManyToOne
	@JoinColumn(name = "jugador_id", referencedColumnName = "id_usuario", nullable = false)
	private UsuarioEntidad jugador;

	@ManyToOne
	@JoinColumn(name = "torneo_id", referencedColumnName = "id_torneo", nullable = false)
	private TorneoEntidad torneo;

	@Column(name = "goles_torneo")
	private int golesTorneo;

	@Column(name = "asistencias_torneo")
	private int asistenciasTorneo;

	@Column(name = "amarillas_torneo")
	private int amarillasTorneo;

	@Column(name = "rojas_torneo")
	private int rojasTorneo;

	@Column(name = "partidos__jugados_torneo")
	private int partidosJugadosTorneo;
	
	  
    @Column(name = "partidos_ganados_torneo")
    private int partidosGanadosTorneo;
    
    @Column(name = "partidos_perdidos_torneo")
    private int partidosPerdidosTorneo;
    

	@Column(name = "minutos_jugados_torneo")
	private int minutosJugadosTorneo;

	// Getters y setters

	public Long getIdJugadorEstadisticaTorneo() {
		return idJugadorEstadisticaTorneo;
	}

	public void setIdJugadorEstadisticaTorneo(Long idJugadorEstadisticaTorneo) {
		this.idJugadorEstadisticaTorneo = idJugadorEstadisticaTorneo;
	}

	public UsuarioEntidad getJugador() {
		return jugador;
	}

	public void setJugador(UsuarioEntidad jugador) {
		this.jugador = jugador;
	}

	public TorneoEntidad getTorneo() {
		return torneo;
	}

	public void setTorneo(TorneoEntidad torneo) {
		this.torneo = torneo;
	}

	public int getGolesTorneo() {
		return golesTorneo;
	}

	public void setGolesTorneo(int golesTorneo) {
		this.golesTorneo = golesTorneo;
	}

	public int getAsistenciasTorneo() {
		return asistenciasTorneo;
	}

	public void setAsistenciasTorneo(int asistenciasTorneo) {
		this.asistenciasTorneo = asistenciasTorneo;
	}

	public int getAmarillasTorneo() {
		return amarillasTorneo;
	}

	public void setAmarillasTorneo(int amarillasTorneo) {
		this.amarillasTorneo = amarillasTorneo;
	}

	public int getRojasTorneo() {
		return rojasTorneo;
	}

	public void setRojasTorneo(int rojasTorneo) {
		this.rojasTorneo = rojasTorneo;
	}

	public int getPartidosJugadosTorneo() {
		return partidosJugadosTorneo;
	}

	public void setPartidosJugadosTorneo(int partidosJugadosTorneo) {
		this.partidosJugadosTorneo = partidosJugadosTorneo;
	}

	public int getMinutosJugadosTorneo() {
		return minutosJugadosTorneo;
	}

	public void setMinutosJugadosTorneo(int minutosJugadosTorneo) {
		this.minutosJugadosTorneo = minutosJugadosTorneo;
	}
	
	

	public int getPartidosGanadosTorneo() {
		return partidosGanadosTorneo;
	}

	public void setPartidosGanadosTorneo(int partidosGanadosTorneo) {
		this.partidosGanadosTorneo = partidosGanadosTorneo;
	}

	public int getPartidosPerdidosTorneo() {
		return partidosPerdidosTorneo;
	}

	public void setPartidosPerdidosTorneo(int partidosPerdidosTorneo) {
		this.partidosPerdidosTorneo = partidosPerdidosTorneo;
	}

	public JugadorEstadisticaTorneoEntidad() {
		super();
	}
	
	
	
}
