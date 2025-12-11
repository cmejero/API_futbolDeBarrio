package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "acta_partido", schema = "sch")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idActaPartido")
public class ActaPartidoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acta_partido")
    private Long idActaPartido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torneo_id")
    private TorneoEntidad torneo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instalacion_id")
    private InstalacionEntidad instalacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_local_id")
    private ClubEntidad clubLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_visitante_id")
    private ClubEntidad clubVisitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local_id")
    private EquipoTorneoEntidad equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visitante_id")
    private EquipoTorneoEntidad equipoVisitante;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "partido_torneo_id")
    private PartidoTorneoEntidad partidoTorneo;

    @Column(name = "goles_local")
    private int golesLocal;

    @Column(name = "goles_visitante")
    private int golesVisitante;

    @Column(name = "goles_penaltis_local")
    private int golesPenaltisLocal;

    @Column(name = "goles_penaltis_visitante")
    private int golesPenaltisVisitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_ganador_id")
    private ClubEntidad clubGanador;

    @Column(name = "fecha_partido")
    private String fechaPartido;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "cerrado")
    private boolean cerrado;

    @OneToMany(mappedBy = "actaPartido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EventoPartidoEntidad> eventoPartido = new ArrayList<>();

    // Getters y setters (copiados/compatibles con lo que tenías)
    public Long getIdActaPartido() {
        return idActaPartido;
    }

    public void setIdActaPartido(Long idActaPartido) {
        this.idActaPartido = idActaPartido;
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

    public PartidoTorneoEntidad getPartidoTorneo() {
        return partidoTorneo;
    }

    public void setPartidoTorneo(PartidoTorneoEntidad partidoTorneo) {
        this.partidoTorneo = partidoTorneo;
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

    public int getGolesPenaltisLocal() {
        return golesPenaltisLocal;
    }

    public void setGolesPenaltisLocal(int golesPenaltisLocal) {
        this.golesPenaltisLocal = golesPenaltisLocal;
    }

    public int getGolesPenaltisVisitante() {
        return golesPenaltisVisitante;
    }

    public void setGolesPenaltisVisitante(int golesPenaltisVisitante) {
        this.golesPenaltisVisitante = golesPenaltisVisitante;
    }

    public ClubEntidad getClubGanador() {
        return clubGanador;
    }

    public void setClubGanador(ClubEntidad clubGanador) {
        this.clubGanador = clubGanador;
    }

    public String getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(String fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean estaCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public List<EventoPartidoEntidad> getEventoPartido() {
        return eventoPartido;
    }

    public void setEventoPartido(List<EventoPartidoEntidad> eventoPartido) {
        this.eventoPartido = eventoPartido;
    }

    public boolean isCerrado() {
        return cerrado;
    }
}
