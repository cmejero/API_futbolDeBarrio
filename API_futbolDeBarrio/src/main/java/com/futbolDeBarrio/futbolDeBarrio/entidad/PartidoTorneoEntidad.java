package com.futbolDeBarrio.futbolDeBarrio.entidad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partido_torneo", schema = "sch")
public class PartidoTorneoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido_torneo")
    private Long idPartidoTorneo;

    @ManyToOne
    @JoinColumn(name = "torneo_id", referencedColumnName = "id_torneo", nullable = false)
    private TorneoEntidad torneo;

    @ManyToOne
    @JoinColumn(name = "instalacion_id", referencedColumnName = "id_instalacion")
    private InstalacionEntidad instalacion;

    @ManyToOne
    @JoinColumn(name = "club_local_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad clubLocal;

    @ManyToOne
    @JoinColumn(name = "club_visitante_id", referencedColumnName = "id_club", nullable = false)
    private ClubEntidad clubVisitante;

    @ManyToOne
    @JoinColumn(name = "equipo_local_id", referencedColumnName = "id_equipo_torneo", nullable = false)
    private EquipoTorneoEntidad equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id", referencedColumnName = "id_equipo_torneo", nullable = false)
    private EquipoTorneoEntidad equipoVisitante;
    
    @OneToOne(mappedBy = "partidoTorneo", cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    private ActaPartidoEntidad actaPartido;


    @Column(name = "goles_local")
    private int golesLocal;

    @Column(name = "goles_visitante")
    private int golesVisitante;

    @Column(name = "fecha_partido")
    private String fechaPartido;

    @Column(name = "ronda", nullable = false)
    private String ronda; // Ej: "OCTAVOS", "CUARTOS", "SEMIFINAL", "FINAL"

    @Column(name = "estado", nullable = false)
    private String estado; // PENDIENTE, JUGADO, CERRADO

    // Getters y setters

    public Long getIdPartidoTorneo() {
        return idPartidoTorneo;
    }

    public void setIdPartidoTorneo(Long idPartidoTorneo) {
        this.idPartidoTorneo = idPartidoTorneo;
    }

    public TorneoEntidad getTorneo() {
        return torneo;
    }

    public void setTorneo(TorneoEntidad torneo) {
        this.torneo = torneo;
    }

    public InstalacionEntidad getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(InstalacionEntidad instalacion) {
        this.instalacion = instalacion;
    }

    public ClubEntidad getClubLocal() {
        return clubLocal;
    }

    public void setClubLocal(ClubEntidad clubLocal) {
        this.clubLocal = clubLocal;
    }

    public ClubEntidad getClubVisitante() {
        return clubVisitante;
    }

    public void setClubVisitante(ClubEntidad clubVisitante) {
        this.clubVisitante = clubVisitante;
    }

    public EquipoTorneoEntidad getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(EquipoTorneoEntidad equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public EquipoTorneoEntidad getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(EquipoTorneoEntidad equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
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

	public ActaPartidoEntidad getActaPartido() {
		return actaPartido;
	}

	public void setActaPartido(ActaPartidoEntidad actaPartido) {
		this.actaPartido = actaPartido;
	}
    
    
}
