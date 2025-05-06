package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;

public class MiembroClubDto {

    private long idMiembroClub;
    private String fechaAltaUsuario;
    private String fechaBajaUsuario;
    private long idClub;
    private long usuarioId;
    private UsuarioDto usuario;

    // Constructor sin parámetros
    public MiembroClubDto() {
        super();
    }

    // Constructor con parámetros
    public MiembroClubDto(long idMiembroClub, String fechaAltaUsuario, String fechaBajaUsuario, long idClub, long usuarioId) {
        super();
        this.idMiembroClub = idMiembroClub;
        this.fechaAltaUsuario = fechaAltaUsuario;
        this.fechaBajaUsuario = fechaBajaUsuario;
        this.idClub = idClub;
        this.usuarioId = usuarioId;
    }

    // Constructor a partir de la entidad
    public MiembroClubDto(MiembroClubEntidad miembroClubEntidad) {
        this.idMiembroClub = miembroClubEntidad.getIdMiembroClub();
        this.fechaAltaUsuario = miembroClubEntidad.getFechaAltaUsuario();
        this.fechaBajaUsuario = miembroClubEntidad.getFechaBajaUsuario();
        
        if (miembroClubEntidad.getClub() != null) {
            this.idClub = miembroClubEntidad.getClub().getIdClub();
        }
        
        if (miembroClubEntidad.getUsuario() != null) {
            this.usuarioId = miembroClubEntidad.getUsuario().getIdUsuario();
            // Asegúrate de convertir la entidad de usuario a DTO
            this.usuario = new UsuarioDto(miembroClubEntidad.getUsuario());
        }
    }

    // Getters and Setters
    public long getIdMiembroClub() {
        return idMiembroClub;
    }

    public void setIdMiembroClub(long idMiembroClub) {
        this.idMiembroClub = idMiembroClub;
    }

    public String getFechaAltaUsuario() {
        return fechaAltaUsuario;
    }

    public void setFechaAltaUsuario(String fechaAltaUsuario) {
        this.fechaAltaUsuario = fechaAltaUsuario;
    }

    public String getFechaBajaUsuario() {
        return fechaBajaUsuario;
    }

    public void setFechaBajaUsuario(String fechaBajaUsuario) {
        this.fechaBajaUsuario = fechaBajaUsuario;
    }

    public long getIdClub() {
        return idClub;
    }

    public void setIdClub(long idClub) {
        this.idClub = idClub;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    // Métodos adicionales: toString, hashCode y equals
    @Override
    public String toString() {
        return "MiembroClubDto{" +
                "idMiembroClub=" + idMiembroClub +
                ", fechaAltaUsuario='" + fechaAltaUsuario + '\'' +
                ", fechaBajaUsuario='" + fechaBajaUsuario + '\'' +
                ", idClub=" + idClub +
                ", usuarioId=" + usuarioId +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiembroClubDto that = (MiembroClubDto) o;
        return idMiembroClub == that.idMiembroClub;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idMiembroClub);
    }
}
