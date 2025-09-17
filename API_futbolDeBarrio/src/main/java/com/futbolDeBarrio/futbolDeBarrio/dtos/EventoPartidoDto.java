package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.EventoPartidoEntidad;

/**
 * Clase que se encarga de los campos de Evento partido
 */
public class EventoPartidoDto {


    private Long idEventoPartido;
    private Long actaPartidoId;
    private Long jugadorId;
    private Long clubId;
    private Long equipoTorneoId;
    private String tipoEvento; 
    private int minuto;
    
    
    
    public EventoPartidoDto(EventoPartidoEntidad eventoPartidoEntidad) {
        this.idEventoPartido = eventoPartidoEntidad.getIdEventoPartido();
        this.actaPartidoId = eventoPartidoEntidad.getActaPartido().getIdActaPartido();
        this.jugadorId = eventoPartidoEntidad.getJugador().getIdUsuario();
        this.clubId = eventoPartidoEntidad.getClub().getIdClub();
        this.equipoTorneoId = eventoPartidoEntidad.getEquipoTorneo().getIdEquipoTorneo();
        this.tipoEvento = eventoPartidoEntidad.getTipoEvento();
        this.minuto = eventoPartidoEntidad.getMinuto();
    }

    
    

	public Long getIdEventoPartido() {
		return idEventoPartido;
	}
	public void setIdEventoPartido(Long idEventoPartido) {
		this.idEventoPartido = idEventoPartido;
	}
	public Long getActaPartidoId() {
		return actaPartidoId;
	}
	public void setActaPartidoId(Long actaPartidoId) {
		this.actaPartidoId = actaPartidoId;
	}
	public Long getJugadorId() {
		return jugadorId;
	}
	public void setJugadorId(Long jugadorId) {
		this.jugadorId = jugadorId;
	}
	public Long getClubId() {
		return clubId;
	}
	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}
	public Long getEquipoTorneoId() {
		return equipoTorneoId;
	}
	public void setEquipoTorneoId(Long equipoTorneoId) {
		this.equipoTorneoId = equipoTorneoId;
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
	public EventoPartidoDto() {
		super();
	}
    
    
    
    

}
