package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.time.LocalDate;

import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

public class TorneoDto {

    private long idTorneo;
    private String nombreTorneo;
    private LocalDate fechaInicioTorneo;
    private LocalDate fechaFinTorneo;
    private String descripcionTorneo;
    private Modalidad modalidad;
    private long instalacionId;

    // Getters and Setters
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

    public LocalDate getFechaInicioTorneo() {
        return fechaInicioTorneo;
    }

    public void setFechaInicioTorneo(LocalDate fechaInicioTorneo) {
        this.fechaInicioTorneo = fechaInicioTorneo;
    }

    public LocalDate getFechaFinTorneo() {
        return fechaFinTorneo;
    }

    public void setFechaFinTorneo(LocalDate fechaFinTorneo) {
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
