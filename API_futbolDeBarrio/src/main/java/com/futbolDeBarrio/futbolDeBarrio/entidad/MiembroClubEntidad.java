package com.futbolDeBarrio.futbolDeBarrio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "miembro_club", schema = "sch")
public class MiembroClubEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro_club")
    private long idMiembroClub;

    @NotNull
    @Column(name = "fecha_alta_usuario", nullable = false)
    private String fechaAltaUsuario; 

    @Column(name = "fecha_baja_usuario")
    private String fechaBajaUsuario; 

    @NotNull
    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad club;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioEntidad usuario;

    
    public MiembroClubEntidad() {
    }

   

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

    public ClubEntidad getClub() {
        return club;
    }

    public void setClub(ClubEntidad club) {
        this.club = club;
    }

    public UsuarioEntidad getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntidad usuario) {
        this.usuario = usuario;
    }
}
