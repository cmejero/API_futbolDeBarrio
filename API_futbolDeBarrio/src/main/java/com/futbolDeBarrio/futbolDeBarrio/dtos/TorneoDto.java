package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.ArrayList;
import java.util.Date;
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

@Entity
@Table(name = "torneo" ,schema="sch")
public class TorneoDto {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneo")
    private long idTorneo;

    @Column(name = "nombre_torneo")
    private String nombreTorneo;

    @Column(name = "fecha_inicio_torneo")
    private Date fechaInicioTorneo;

    @Column(name = "fecha_fin_torneo")
    private Date fechaFinTorneo;

    @Column(name = "descripcion_torneo")
    private String descripcionTorneo;

    @Column(name = "modalidad")
    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;  // Opción para usar un Enum según tu implementación de modalidad_enum

    @ManyToOne
    @JoinColumn(name = "instalacion_id", referencedColumnName = "id_instalacion", nullable = false)
    private InstalacionDto instalacion;
    
    
   
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoTorneoDto> equipoTorneo = new ArrayList<>();
    
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

	public Date getFechaInicioTorneo() {
		return fechaInicioTorneo;
	}

	public void setFechaInicioTorneo(Date fechaInicioTorneo) {
		this.fechaInicioTorneo = fechaInicioTorneo;
	}

	public Date getFechaFinTorneo() {
		return fechaFinTorneo;
	}

	public void setFechaFinTorneo(Date fechaFinTorneo) {
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

	public InstalacionDto getInstalacion() {
		return instalacion;
	}

	public void setInstalcion(InstalacionDto instalacion) {
		this.instalacion = instalacion;
	}
	

	public TorneoDto() {
	
	}

	
	
	

}
