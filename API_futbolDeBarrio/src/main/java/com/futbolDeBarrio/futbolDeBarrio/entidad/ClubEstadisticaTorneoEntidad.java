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
@Table(name = "club_estadistica_torneo", schema = "sch")

public class ClubEstadisticaTorneoEntidad {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_club_estadistica_torneo")
	    private Long idClubEstadisticaTorneo;

	    @ManyToOne
	    @JoinColumn(name = "club_id", referencedColumnName = "id_club", nullable = false)
	    private ClubEntidad club;

	    @ManyToOne
	    @JoinColumn(name = "torneo_id", referencedColumnName = "id_torneo", nullable = false)
	    private TorneoEntidad torneo;

	    @Column(name = "partidos_jugados")
	    private int partidosJugados;

	    @Column(name = "ganados")
	    private int ganados;

	    @Column(name = "empatados")
	    private int empatados;

	    @Column(name = "perdidos")
	    private int perdidos;

	    @Column(name = "goles_favor")
	    private int golesFavor;

	    @Column(name = "goles_contra")
	    private int golesContra;


	    // Getters y setters

	    public Long getIdClubEstadisticaTorneo() {
	        return idClubEstadisticaTorneo;
	    }

	    public void setIdClubEstadisticaTorneo(Long idClubEstadisticaTorneo) {
	        this.idClubEstadisticaTorneo = idClubEstadisticaTorneo;
	    }

	    public ClubEntidad getClub() {
	        return club;
	    }

	    public void setClub(ClubEntidad club) {
	        this.club = club;
	    }

	    public TorneoEntidad getTorneo() {
	        return torneo;
	    }

	    public void setTorneo(TorneoEntidad torneo) {
	        this.torneo = torneo;
	    }

	    public int getPartidosJugados() {
	        return partidosJugados;
	    }

	    public void setPartidosJugados(int partidosJugados) {
	        this.partidosJugados = partidosJugados;
	    }

	    public int getGanados() {
	        return ganados;
	    }

	    public void setGanados(int ganados) {
	        this.ganados = ganados;
	    }

	    public int getEmpatados() {
	        return empatados;
	    }

	    public void setEmpatados(int empatados) {
	        this.empatados = empatados;
	    }

	    public int getPerdidos() {
	        return perdidos;
	    }

	    public void setPerdidos(int perdidos) {
	        this.perdidos = perdidos;
	    }

	    public int getGolesFavor() {
	        return golesFavor;
	    }

	    public void setGolesFavor(int golesFavor) {
	        this.golesFavor = golesFavor;
	    }

	    public int getGolesContra() {
	        return golesContra;
	    }

	    public void setGolesContra(int golesContra) {
	        this.golesContra = golesContra;
	    }

		public ClubEstadisticaTorneoEntidad() {
			super();
		}

	
	}

