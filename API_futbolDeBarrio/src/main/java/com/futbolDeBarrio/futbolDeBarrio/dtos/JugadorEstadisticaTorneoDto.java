package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaTorneoEntidad;

/**
 * Clase que se encarga de los campos de Jugador estadisitica torneo
 */
public class JugadorEstadisticaTorneoDto {

	private Long idJugadorEstadisticaTorneo;
	private Long jugadorId;
	private Long torneoId;
	private int golesTorneo;
	private int asistenciasTorneo;
	private int amarillasTorneo;
	private int rojasTorneo;
	private int partidosJugadosTorneo;
	private int partidosGanadosTorneo;
	private int partidosPerdidosTorneo;
	private int minutosJugadosTorneo;

	public JugadorEstadisticaTorneoDto(JugadorEstadisticaTorneoEntidad jugadorEstadisticaTorneoEntidad) {
		this.idJugadorEstadisticaTorneo = jugadorEstadisticaTorneoEntidad.getIdJugadorEstadisticaTorneo();
		this.jugadorId = jugadorEstadisticaTorneoEntidad.getJugador().getIdUsuario();
		this.torneoId = jugadorEstadisticaTorneoEntidad.getTorneo().getIdTorneo();
		this.golesTorneo = jugadorEstadisticaTorneoEntidad.getGolesTorneo();
		this.asistenciasTorneo = jugadorEstadisticaTorneoEntidad.getAsistenciasTorneo();
		this.amarillasTorneo = jugadorEstadisticaTorneoEntidad.getAmarillasTorneo();
		this.rojasTorneo = jugadorEstadisticaTorneoEntidad.getRojasTorneo();
		this.partidosJugadosTorneo = jugadorEstadisticaTorneoEntidad.getPartidosJugadosTorneo();
		this.partidosGanadosTorneo = jugadorEstadisticaTorneoEntidad.getPartidosGanadosTorneo();
		this.partidosPerdidosTorneo = jugadorEstadisticaTorneoEntidad.getPartidosPerdidosTorneo();

		this.minutosJugadosTorneo = jugadorEstadisticaTorneoEntidad.getMinutosJugadosTorneo();
	}

	public Long getIdJugadorEstadisticaTorneo() {
		return idJugadorEstadisticaTorneo;
	}

	public void setIdJugadorEstadisticaTorneo(Long id) {
		this.idJugadorEstadisticaTorneo = id;
	}

	public Long getJugadorId() {
		return jugadorId;
	}

	public void setJugadorId(Long jugadorId) {
		this.jugadorId = jugadorId;
	}

	public Long getTorneoId() {
		return torneoId;
	}

	public void setTorneoId(Long torneoId) {
		this.torneoId = torneoId;
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

	public int getMinutosJugadosTorneo() {
		return minutosJugadosTorneo;
	}

	public void setMinutosJugadosTorneo(int minutosJugadosTorneo) {
		this.minutosJugadosTorneo = minutosJugadosTorneo;
	}

	public JugadorEstadisticaTorneoDto() {
		super();
	}

}
