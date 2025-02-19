package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Date;

import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

public class TorneoDto {

    private long idTorneo;
    private String nombreTorneo;
    private Date fechaInicioTorneo;
    private Date fechaFinTorneo;
    private String descripcionTorneo;
    private Modalidad modalidad;
    private long instalacionId;  // Solo guardamos el ID de la instalación asociada

    // Getters y Setters
    public long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(long idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public Date getFechaInicioTorneo() {
        return fechaInicioTorneo;
    }

    public void setFechaInicioTorneo(Date fechaInicioTorneo) {
        this.fechaInicioTorneo = fechaInicioTorneo;
    }

    public Date getFechaFinTorneo() {
        return fechaFinTorneo;
    }

    public void setFechaFinTorneo(Date fechaFinTorneo) {
        this.fechaFinTorneo = fechaFinTorneo;
    }

    public String getDescripcionTorneo() {
        return descripcionTorneo;
    }

    public void setDescripcionTorneo(String descripcionTorneo) {
        this.descripcionTorneo = descripcionTorneo;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public long getInstalacionId() {
        return instalacionId;
    }

    public void setInstalacionId(long instalacionId) {
        this.instalacionId = instalacionId;
    }
}
