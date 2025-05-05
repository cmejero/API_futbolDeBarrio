package com.futbolDeBarrio.futbolDeBarrio.dtos;

import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;

public class MiembroClubDto {

    private long idMiembroClub;
    private String fechaAltaUsuario;
    private String fechaBajaUsuario;
    private long clubId;  
    private long usuarioId;  

    

	public MiembroClubDto(long idMiembroClub, String fechaAltaUsuario, String fechaBajaUsuario, long clubId,
			long usuarioId) {
		super();
		this.idMiembroClub = idMiembroClub;
		this.fechaAltaUsuario = fechaAltaUsuario;
		this.fechaBajaUsuario = fechaBajaUsuario;
		this.clubId = clubId;
		this.usuarioId = usuarioId;
	}

    
    public MiembroClubDto(MiembroClubEntidad miembroClubEntidad) {
        this.idMiembroClub = miembroClubEntidad.getIdMiembroClub();
        this.fechaAltaUsuario = miembroClubEntidad.getFechaAltaUsuario();
        this.fechaBajaUsuario = miembroClubEntidad.getFechaBajaUsuario();
        if (miembroClubEntidad.getClub() != null) {
            this.clubId = miembroClubEntidad.getClub().getIdClub();
        }
        if (miembroClubEntidad.getUsuario() != null) {
            this.usuarioId = miembroClubEntidad.getUsuario().getIdUsuario();
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





	public MiembroClubDto() {
		super();
	}
    
    
    
}
