package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Base64;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

public class TorneoDto {

    private long idTorneo;
    private String nombreTorneo;
    private String fechaInicioTorneo;
    private String fechaFinTorneo;
    private String descripcionTorneo;
    private Modalidad modalidad;
    private long instalacionId;

    public TorneoDto(TorneoEntidad torneoEntidad) {
        this.idTorneo = torneoEntidad.getIdTorneo();
        this.nombreTorneo = torneoEntidad.getNombreTorneo();
        this.fechaInicioTorneo = torneoEntidad.getFechaInicioTorneo();
        this.fechaFinTorneo = torneoEntidad.getFechaFinTorneo();
        this.descripcionTorneo = torneoEntidad.getDescripcionTorneo();
        this.modalidad = torneoEntidad.getModalidad();
        if (torneoEntidad.getInstalacion() != null) {
            this.instalacionId = torneoEntidad.getInstalacion().getIdInstalacion();
        }
  
    }
    
    
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

    public String getFechaInicioTorneo() {
        return fechaInicioTorneo;
    }

    public void setFechaInicioTorneo(String fechaInicioTorneo) {
        this.fechaInicioTorneo = fechaInicioTorneo;
    }

    public String getFechaFinTorneo() {
        return fechaFinTorneo;
    }

    public void setFechaFinTorneo(String fechaFinTorneo) {
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


	public TorneoDto() {
		super();
	}
    
    
}
