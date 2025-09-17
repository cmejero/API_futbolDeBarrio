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
@Table(name = "club_estadistica_global", schema = "sch")
public class ClubEstadisticaGlobalEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club_estadistica_global")
    private Long idClubEstadisticaGlobal;

    @ManyToOne
    @JoinColumn(name = "club_id_global", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad clubGlobal;

    @Column(name = "partidos_jugados_global")
    private int partidosJugadosGlobal;

    @Column(name = "ganados_global")
    private int ganadosGlobal;

    @Column(name = "empatados_global")
    private int empatadosGlobal;

    @Column(name = "perdidos_global")
    private int perdidosGlobal;

    @Column(name = "goles_favor_global")
    private int golesFavorGlobal;

    @Column(name = "goles_contra_global")
    private int golesContraGlobal;

	public Long getIdClubEstadisticaGlobal() {
		return idClubEstadisticaGlobal;
	}

	public void setIdClubEstadisticaGlobal(Long idClubEstadisticaGlobal) {
		this.idClubEstadisticaGlobal = idClubEstadisticaGlobal;
	}

	public ClubEntidad getClubGlobal() {
		return clubGlobal;
	}

	public void setClubGlobal(ClubEntidad clubGlobal) {
		this.clubGlobal = clubGlobal;
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

   
}

