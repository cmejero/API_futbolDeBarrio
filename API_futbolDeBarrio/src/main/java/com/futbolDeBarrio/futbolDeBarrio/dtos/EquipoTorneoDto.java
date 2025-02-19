package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Date;

import com.futbolDeBarrio.futbolDeBarrio.enums.EstadoParticipacion;

public class EquipoTorneoDto {

    private long idEquipoTorneo;
    private Date fechaInicioParticipacion;
    private Date fechaFinParticipacion;
    private EstadoParticipacion estadoParticipacion;
    private long torneoId;  
    private long clubId;    

    // Getters y Setters
    public long getIdEquipoTorneo() {
        return idEquipoTorneo;
    }

    public void setIdEquipoTorneo(long idEquipoTorneo) {
        this.idEquipoTorneo = idEquipoTorneo;
    }

    public Date getFechaInicioParticipacion() {
        return fechaInicioParticipacion;
    }

    public void setFechaInicioParticipacion(Date fechaInicioParticipacion) {
        this.fechaInicioParticipacion = fechaInicioParticipacion;
    }

    public Date getFechaFinParticipacion() {
        return fechaFinParticipacion;
    }

    public void setFechaFinParticipacion(Date fechaFinParticipacion) {
        this.fechaFinParticipacion = fechaFinParticipacion;
    }

    public EstadoParticipacion getEstadoParticipacion() {
        return estadoParticipacion;
    }

    public void setEstadoParticipacion(EstadoParticipacion estadoParticipacion) {
        this.estadoParticipacion = estadoParticipacion;
    }

    public long getTorneoId() {
        return torneoId;
    }

    public void setTorneoId(long torneoId) {
        this.torneoId = torneoId;
    }

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

}
