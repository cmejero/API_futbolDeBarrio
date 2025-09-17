package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "club", schema = "sch")
public class ClubEntidad {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club")
    private long idClub;

    @Column(name = "nombre_club")
    private String nombreClub;
    
    @Column(name = "abreviatura_club")
    private String abreviaturaClub;

    @Column(name = "descripcion_club")
    private String descripcionClub;

    @Column(name = "fecha_creacion_club")
    private String fechaCreacionClub;

    @Column(name = "fecha_fundacion_club")
    private String fechaFundacionClub;

    @Column(name = "localidad_club")
    private String localidadClub;

    @Column(name = "pais_club")
    private String paisClub;

    @Column(name = "logo_club")
    private byte[] logoClub;

    @Column(name = "email_club")
    private String emailClub;

    @Column(name = "password_club")
    private String passwordClub;

    @Column(name = "telefono_club")
    private String telefonoClub;

    // Relación con los clubes
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoTorneoEntidad> equipoTorneo = new ArrayList<>();
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiembroClubEntidad> miembroClub = new ArrayList<>();
    
    @OneToMany(mappedBy = "clubLocal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActaPartidoEntidad> actasComoLocal = new ArrayList<>();

    @OneToMany(mappedBy = "clubVisitante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActaPartidoEntidad> actasComoVisitante = new ArrayList<>();
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoPartidoEntidad> eventoPartido = new ArrayList<>();
    
    @OneToMany(mappedBy = "clubGlobal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubEstadisticaGlobalEntidad> clubEstadisticaGlobal = new ArrayList<>();
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubEstadisticaTorneoEntidad> clubEstadisticaTorneo = new ArrayList<>();

    // Getters y Setters
    public long getIdClub() {
        return idClub;
    }

    public void setIdClub(long idClub) {
        this.idClub = idClub;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    public String getAbreviaturaClub() {
        return abreviaturaClub;
    }

    public void setAbreviaturaClub(String abreviaturaClub) {
        this.abreviaturaClub = abreviaturaClub;
    }
    
    public String getDescripcionClub() {
        return descripcionClub;
    }
    

    public void setDescripcionClub(String descripcionClub) {
        this.descripcionClub = descripcionClub;
    }

    public String getFechaCreacionClub() {
        return fechaCreacionClub;
    }

    public void setFechaCreacionClub(String fechaCreacionClub) {
        this.fechaCreacionClub = fechaCreacionClub;
    }

    public String getFechaFundacionClub() {
        return fechaFundacionClub;
    }

    public void setFechaFundacionClub(String fechaFundacionClub) {
        this.fechaFundacionClub = fechaFundacionClub;
    }

    public String getLocalidadClub() {
        return localidadClub;
    }

    public void setLocalidadClub(String localidadClub) {
        this.localidadClub = localidadClub;
    }

    public String getPaisClub() {
        return paisClub;
    }

    public void setPaisClub(String paisClub) {
        this.paisClub = paisClub;
    }

    public byte[] getLogoClub() {
        return logoClub;
    }

    public void setLogoClub(byte[] logoClub) {
        this.logoClub = logoClub;
    }

    public String getEmailClub() {
        return emailClub;
    }

    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    public String getPasswordClub() {
        return passwordClub;
    }

    public void setPasswordClub(String passwordClub) {
        this.passwordClub = passwordClub;
    }

    public String getTelefonoClub() {
        return telefonoClub;
    }

    public void setTelefonoClub(String telefonoClub) {
        this.telefonoClub = telefonoClub;
    }

	public List<EquipoTorneoEntidad> getEquipoTorneo() {
		return equipoTorneo;
	}

	public void setEquipoTorneo(List<EquipoTorneoEntidad> equipoTorneo) {
		this.equipoTorneo = equipoTorneo;
	}

	public List<MiembroClubEntidad> getMiembroClub() {
		return miembroClub;
	}

	public void setMiembroClub(List<MiembroClubEntidad> miembroClub) {
		this.miembroClub = miembroClub;
	}

	public List<ActaPartidoEntidad> getActasComoLocal() {
		return actasComoLocal;
	}

	public void setActasComoLocal(List<ActaPartidoEntidad> actasComoLocal) {
		this.actasComoLocal = actasComoLocal;
	}

	public List<ActaPartidoEntidad> getActasComoVisitante() {
		return actasComoVisitante;
	}

	public void setActasComoVisitante(List<ActaPartidoEntidad> actasComoVisitante) {
		this.actasComoVisitante = actasComoVisitante;
	}

	public List<EventoPartidoEntidad> getEventoPartido() {
		return eventoPartido;
	}

	public void setEventoPartido(List<EventoPartidoEntidad> eventoPartido) {
		this.eventoPartido = eventoPartido;
	}

	public List<ClubEstadisticaGlobalEntidad> getClubEstadisticaGlobal() {
		return clubEstadisticaGlobal;
	}

	public void setClubEstadisticaGlobal(List<ClubEstadisticaGlobalEntidad> clubEstadisticaGlobal) {
		this.clubEstadisticaGlobal = clubEstadisticaGlobal;
	}

	public List<ClubEstadisticaTorneoEntidad> getClubEstadisticaTorneo() {
		return clubEstadisticaTorneo;
	}

	public void setClubEstadisticaTorneo(List<ClubEstadisticaTorneoEntidad> clubEstadisticaTorneo) {
		this.clubEstadisticaTorneo = clubEstadisticaTorneo;
	}

   
}
