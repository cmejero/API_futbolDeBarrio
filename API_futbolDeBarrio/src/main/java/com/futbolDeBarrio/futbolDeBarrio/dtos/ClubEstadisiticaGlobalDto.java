package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;

/**
 * Clase que se encarga de los campos de Club estadistica global
 */
public class ClubEstadisiticaGlobalDto {

	private Long idGlobal;
	private Long clubGlobalId;
	private int partidosJugadosGlobal;
	private int ganadosGlobal;
	private int empatadosGlobal;
	private int perdidosGlobal;
	private int golesFavorGlobal;
	private int golesContraGlobal;
	private String nombreClub;
	private String localidad;

	public ClubEstadisiticaGlobalDto(ClubEstadisticaGlobalEntidad clubEstadisticaGlobalEntidad) {
		this.idGlobal = clubEstadisticaGlobalEntidad.getIdClubEstadisticaGlobal();
		this.clubGlobalId = clubEstadisticaGlobalEntidad.getClubGlobal().getIdClub();
		this.partidosJugadosGlobal = clubEstadisticaGlobalEntidad.getPartidosJugadosGlobal();
		this.ganadosGlobal = clubEstadisticaGlobalEntidad.getGanadosGlobal();
		this.empatadosGlobal = clubEstadisticaGlobalEntidad.getEmpatadosGlobal();
		this.perdidosGlobal = clubEstadisticaGlobalEntidad.getPerdidosGlobal();
		this.golesFavorGlobal = clubEstadisticaGlobalEntidad.getGolesFavorGlobal();
		this.golesContraGlobal = clubEstadisticaGlobalEntidad.getGolesContraGlobal();
		this.nombreClub = clubEstadisticaGlobalEntidad.getClubGlobal().getNombreClub();
		this.localidad = clubEstadisticaGlobalEntidad.getClubGlobal().getLocalidadClub();

	}

	public Long getIdGlobal() {
		return idGlobal;
	}

	public void setIdGlobal(Long idGlobal) {
		this.idGlobal = idGlobal;
	}

	public Long getClubGlobalId() {
		return clubGlobalId;
	}

	public void setClubGlobalId(Long clubGlobalId) {
		this.clubGlobalId = clubGlobalId;
	}

	public int getPartidosJugadosGlobal() {
		return partidosJugadosGlobal;
	}

	public void setPartidosJugadosGlobal(int partidosJugadosGlobal) {
		this.partidosJugadosGlobal = partidosJugadosGlobal;
	}

	public int getGanadosGlobal() {
		return ganadosGlobal;
	}

	public void setGanadosGlobal(int ganadosGlobal) {
		this.ganadosGlobal = ganadosGlobal;
	}

	public int getEmpatadosGlobal() {
		return empatadosGlobal;
	}

	public void setEmpatadosGlobal(int empatadosGlobal) {
		this.empatadosGlobal = empatadosGlobal;
	}

	public int getPerdidosGlobal() {
		return perdidosGlobal;
	}

	public void setPerdidosGlobal(int perdidosGlobal) {
		this.perdidosGlobal = perdidosGlobal;
	}

	public int getGolesFavorGlobal() {
		return golesFavorGlobal;
	}

	public void setGolesFavorGlobal(int golesFavorGlobal) {
		this.golesFavorGlobal = golesFavorGlobal;
	}

	public int getGolesContraGlobal() {
		return golesContraGlobal;
	}

	public void setGolesContraGlobal(int golesContraGlobal) {
		this.golesContraGlobal = golesContraGlobal;
	}

	public String getNombreClub() {
		return nombreClub;
	}

	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ClubEstadisiticaGlobalDto() {
		super();
	}

}
