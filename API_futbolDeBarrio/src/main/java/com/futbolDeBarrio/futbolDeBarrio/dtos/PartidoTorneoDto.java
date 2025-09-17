package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;

/**
 * DTO para la gestión de partidos de un torneo.
 */
public class PartidoTorneoDto {

    private Long idPartidoTorneo;
    private Long torneoId;
    private Long instalacionId;
    private Long clubLocalId;
    private Long clubVisitanteId;
    private Long equipoLocalId;
    private Long equipoVisitanteId;
    private Long actaPartidoId;
    private int golesLocal;
    private int golesVisitante;
    private String fechaPartido;
    private String ronda;
    private String estado;

    public PartidoTorneoDto(PartidoTorneoEntidad entidad) {
        this.idPartidoTorneo = entidad.getIdPartidoTorneo();
        this.torneoId = entidad.getTorneo().getIdTorneo();
        this.instalacionId = entidad.getInstalacion() != null ? entidad.getInstalacion().getIdInstalacion() : null;
        this.clubLocalId = entidad.getClubLocal().getIdClub();
        this.clubVisitanteId = entidad.getClubVisitante().getIdClub();
        this.equipoLocalId = entidad.getEquipoLocal().getIdEquipoTorneo();
        this.equipoVisitanteId = entidad.getEquipoVisitante().getIdEquipoTorneo();
        this.actaPartidoId = entidad.getActaPartido().getIdActaPartido();
        this.golesLocal = entidad.getGolesLocal();
        this.golesVisitante = entidad.getGolesVisitante();
        this.fechaPartido = entidad.getFechaPartido();
        this.ronda = entidad.getRonda();
        this.estado = entidad.getEstado();
       
    }

    public PartidoTorneoDto() {
        super();
    }

	public Long getIdPartidoTorneo() {
		return idPartidoTorneo;
	}

	public void setIdPartidoTorneo(Long idPartidoTorneo) {
		this.idPartidoTorneo = idPartidoTorneo;
	}

	public Long getTorneoId() {
		return torneoId;
	}

	public void setTorneoId(Long torneoId) {
		this.torneoId = torneoId;
	}

	public Long getInstalacionId() {
		return instalacionId;
	}

	public void setInstalacionId(Long instalacionId) {
		this.instalacionId = instalacionId;
	}

	public Long getClubLocalId() {
		return clubLocalId;
	}

	public void setClubLocalId(Long clubLocalId) {
		this.clubLocalId = clubLocalId;
	}

	public Long getClubVisitanteId() {
		return clubVisitanteId;
	}

	public void setClubVisitanteId(Long clubVisitanteId) {
		this.clubVisitanteId = clubVisitanteId;
	}

	public Long getActaPartidoId() {
		return actaPartidoId;
	}

	public void setActaPartidoId(Long actaPartidoId) {
		this.actaPartidoId = actaPartidoId;
	}

	public Long getEquipoLocalId() {
		return equipoLocalId;
	}

	public void setEquipoLocalId(Long equipoLocalId) {
		this.equipoLocalId = equipoLocalId;
	}

	public Long getEquipoVisitanteId() {
		return equipoVisitanteId;
	}

	public void setEquipoVisitanteId(Long equipoVisitanteId) {
		this.equipoVisitanteId = equipoVisitanteId;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public String getFechaPartido() {
		return fechaPartido;
	}

	public void setFechaPartido(String fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public String getRonda() {
		return ronda;
	}

	public void setRonda(String ronda) {
		this.ronda = ronda;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

   
}
