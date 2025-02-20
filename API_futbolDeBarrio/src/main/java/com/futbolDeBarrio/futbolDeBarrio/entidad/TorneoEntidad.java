package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "torneo", schema = "sch")
public class TorneoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneo")
    private long idTorneo;

    @NotNull
    @Column(name = "nombre_torneo")
    private String nombreTorneo;

    @NotNull
    @Column(name = "fecha_inicio_torneo")
    private LocalDate fechaInicioTorneo;

    @NotNull
    @Column(name = "fecha_fin_torneo")
    private LocalDate fechaFinTorneo;

    @Column(name = "descripcion_torneo")
    private String descripcionTorneo;

    @NotNull
    @Column(name = "modalidad")
    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;  // Usando el Enum para modalidad

    @NotNull
    @ManyToOne
    @JoinColumn(name = "instalacion_id", referencedColumnName = "id_instalacion", nullable = false)
    private InstalacionEntidad instalacion;
    
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoTorneoEntidad> equipoTorneo = new ArrayList<>();

    // Constructor por defecto
    public TorneoEntidad() {
    }

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

    public InstalacionEntidad getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(InstalacionEntidad instalacion) {
        this.instalacion = instalacion;
    }
    
    public List<EquipoTorneoEntidad> getEquipoTorneo() {
        return equipoTorneo;
    }

    public void setEquipoTorneo(List<EquipoTorneoEntidad> equipoTorneo) {
        this.equipoTorneo = equipoTorneo;
    }
}
