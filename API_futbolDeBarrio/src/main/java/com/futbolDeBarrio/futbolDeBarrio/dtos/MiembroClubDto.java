package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.time.LocalDate;

public class MiembroClubDto {

    private long idMiembroClub;
    private LocalDate fechaAltaUsuario;
    private LocalDate fechaBajaUsuario;
    private long clubId;  
    private long usuarioId;  

    // Getters and Setters
    public long getIdMiembroClub() {
        return idMiembroClub;
    }

    public void setIdMiembroClub(long idMiembroClub) {
        this.idMiembroClub = idMiembroClub;
    }

    public LocalDate getFechaAltaUsuario() {
        return fechaAltaUsuario;
    }

    public void setFechaAltaUsuario(LocalDate fechaAltaUsuario) {
        this.fechaAltaUsuario = fechaAltaUsuario;
    }

    public LocalDate getFechaBajaUsuario() {
        return fechaBajaUsuario;
    }

    public void setFechaBajaUsuario(LocalDate fechaBajaUsuario) {
        this.fechaBajaUsuario = fechaBajaUsuario;
    }

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
