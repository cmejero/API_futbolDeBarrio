package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;

/**
 * Clase que se encarga de los campos de Jugador estadisitica global
 */
public class JugadorEstadisticaGlobalDto {

	
	private Long idGlobal;
    private Long jugadorGlobalId;
    private int golesGlobal;
    private int asistenciasGlobal;
    private int amarillasGlobal;
    private int rojasGlobal;
    private int partidosJugadosGlobal;
    private int minutosJugadosGlobal;
    
    public JugadorEstadisticaGlobalDto(JugadorEstadisticaGlobalEntidad jugadorEstadisticaGlobalEntidad) {
        this.idGlobal = jugadorEstadisticaGlobalEntidad.getIdJugadorEstadisticaGlobal();
        this.jugadorGlobalId = jugadorEstadisticaGlobalEntidad.getJugadorGlobalId().getIdUsuario();
        this.golesGlobal = jugadorEstadisticaGlobalEntidad.getGolesGlobal();
        this.asistenciasGlobal = jugadorEstadisticaGlobalEntidad.getAsistenciasGlobal();
        this.amarillasGlobal = jugadorEstadisticaGlobalEntidad.getAmarillasGlobal();
        this.rojasGlobal = jugadorEstadisticaGlobalEntidad.getRojasGlobal();
        this.partidosJugadosGlobal = jugadorEstadisticaGlobalEntidad.getPartidosJugadosGlobal();
        this.minutosJugadosGlobal = jugadorEstadisticaGlobalEntidad.getMinutosJugadosGlobal();
    }
    
	public Long getIdGlobal() {
		return idGlobal;
	}
	public void setIdGlobal(Long idGlobal) {
		this.idGlobal = idGlobal;
	}
	public Long getJugadorGlobalId() {
		return jugadorGlobalId;
	}
	public void setJugadorGlobalId(Long jugadorGlobalId) {
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

	public JugadorEstadisticaGlobalDto() {
		super();
	}
    
    
    
}
