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
@Table(name = "evento_partido", schema = "sch")
public class EventoPartidoEntidad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_partido")
    private Long idEventoPartido;

    @ManyToOne
    @JoinColumn(name = "acta_partido_id", referencedColumnName = "id_acta_partido", nullable = false)
    private ActaPartidoEntidad actaPartido;

    @ManyToOne
    @JoinColumn(name = "jugador_id", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioEntidad jugador;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad club;
  
    @ManyToOne
    @JoinColumn(name = "equipo_torneo_id", referencedColumnName = "id_equipo_torneo", nullable = false)
    private EquipoTorneoEntidad equipoTorneo;

    @Column(name = "tipo_evento") // GOL, ASISTENCIA, AMARILLA, ROJA
    private String tipoEvento;

    @Column(name = "minuto")
    private int minuto;

	public Long getIdEventoPartido() {
		return idEventoPartido;
	}

	public void setIdEventoPartido(Long idEventoPartido) {
		this.idEventoPartido = idEventoPartido;
	}

	public ActaPartidoEntidad getActaPartido() {
		return actaPartido;
	}

	public void setActaPartido(ActaPartidoEntidad actaPartido) {
		this.actaPartido = actaPartido;
	}

	public UsuarioEntidad getJugador() {
		return jugador;
	}

	public void setJugador(UsuarioEntidad jugador) {
		this.jugador = jugador;
	}

	public ClubEntidad getClub() {
		return club;
	}

	public void setClub(ClubEntidad club) {
		this.club = club;
	}

	public EquipoTorneoEntidad getEquipoTorneo() {
		return equipoTorneo;
	}

	public void setEquipoTorneo(EquipoTorneoEntidad equipoTorneo) {
		this.equipoTorneo = equipoTorneo;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

    
}

