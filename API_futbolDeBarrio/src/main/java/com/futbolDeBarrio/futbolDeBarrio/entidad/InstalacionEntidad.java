package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "instalacion", schema = "sch")
public class InstalacionEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instalacion")
    private long idInstalacion;

    @Column(name = "nombre_instalacion")
    private String nombreInstalacion;

    @Column(name = "direccion_instalacion")
    private String direccionInstalacion;

    @Column(name = "telefono_instalacion")
    private String telefonoInstalacion;

    @Column(name = "email_instalacion")
    private String emailInstalacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_campo1")
    private Modalidad tipoCampo1;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_campo2")
    private Modalidad tipoCampo2;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_campo3")
    private Modalidad tipoCampo3;

    @Column(name = "servicios_instalacion")
    private String serviciosInstalacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_instalacion")
    private Estado estadoInstalacion;

    @Column(name = "password_instalacion")
    private String passwordInstalacion;

    @Column(name = "imagen_instalacion")
    private byte[] imagenInstalacion;

    @Column(name = "tipo_de_campo")
    private String tipoDeCampo;  // Aquí almacenamos la lista como una cadena delimitada por comas

    @OneToMany(mappedBy = "instalacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TorneoEntidad> torneo = new ArrayList<>();

    public List<Long> getTorneoIds() {
        return torneo.stream()
                     .map(TorneoEntidad::getIdTorneo)
                     .collect(Collectors.toList());
    }

    // Getters y Setters
    public long getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(long idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public String getNombreInstalacion() {
        return nombreInstalacion;
    }

    public void setNombreInstalacion(String nombreInstalacion) {
        this.nombreInstalacion = nombreInstalacion;
    }

    public String getDireccionInstalacion() {
        return direccionInstalacion;
    }

    public void setDireccionInstalacion(String direccionInstalacion) {
        this.direccionInstalacion = direccionInstalacion;
    }

    public String getTelefonoInstalacion() {
        return telefonoInstalacion;
    }

    public void setTelefonoInstalacion(String telefonoInstalacion) {
        this.telefonoInstalacion = telefonoInstalacion;
    }

    public String getEmailInstalacion() {
        return emailInstalacion;
    }

    public void setEmailInstalacion(String emailInstalacion) {
        this.emailInstalacion = emailInstalacion;
    }

    public Modalidad getTipoCampo1() {
        return tipoCampo1;
    }

    public void setTipoCampo1(Modalidad tipoCampo1) {
        this.tipoCampo1 = tipoCampo1;
    }

    public Modalidad getTipoCampo2() {
        return tipoCampo2;
    }

    public void setTipoCampo2(Modalidad tipoCampo2) {
        this.tipoCampo2 = tipoCampo2;
    }

    public Modalidad getTipoCampo3() {
        return tipoCampo3;
    }

    public void setTipoCampo3(Modalidad tipoCampo3) {
        this.tipoCampo3 = tipoCampo3;
    }

    public String getServiciosInstalacion() {
        return serviciosInstalacion;
    }

    public void setServiciosInstalacion(String serviciosInstalacion) {
        this.serviciosInstalacion = serviciosInstalacion;
    }

    public Estado getEstadoInstalacion() {
        return estadoInstalacion;
    }

    public void setEstadoInstalacion(Estado estadoInstalacion) {
        this.estadoInstalacion = estadoInstalacion;
    }

    public String getPasswordInstalacion() {
        return passwordInstalacion;
    }

    public void setPasswordInstalacion(String passwordInstalacion) {
        this.passwordInstalacion = passwordInstalacion;
    }

    public byte[] getImagenInstalacion() {
        return imagenInstalacion;
    }

    public void setImagenInstalacion(byte[] imagenInstalacion) {
        this.imagenInstalacion = imagenInstalacion;
    }

    // Convertir la cadena delimitada en una lista
    public List<String> getTipoDeCampo() {
        if (tipoDeCampo != null && !tipoDeCampo.isEmpty()) {
            return List.of(tipoDeCampo.split(","));
        }
        return new ArrayList<>();
    }

    // Convertir la lista a una cadena delimitada por comas
    public void setTipoDeCampo(List<String> tipoDeCampo) {
        if (tipoDeCampo != null) {
            this.tipoDeCampo = String.join(",", tipoDeCampo);
        } else {
            this.tipoDeCampo = "";
        }
    }

    public List<TorneoEntidad> getTorneo() {
        return torneo;
    }

    public void setTorneo(List<TorneoEntidad> torneo) {
        this.torneo = torneo;
    }
}
