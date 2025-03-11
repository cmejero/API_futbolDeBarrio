package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.List;

import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

public class InstalacionDto {

	InstalacionEntidad instalacionEntidad = new InstalacionEntidad();

    private long idInstalacion;
    private String nombreInstalacion;
    private String direccionInstalacion;
    private String telefonoInstalacion;
    private String emailInstalacion;
    private Modalidad tipoCampo1;
    private Modalidad tipoCampo2;
    private Modalidad tipoCampo3;
    private String serviciosInstalacion;
    private Estado estadoInstalacion;
    private String passwordInstalacion;
    private String imagenInstalacion;
    List<Long> torneoIds = instalacionEntidad.getTorneoIds(); // Esto es correcto

    


    // Constructor que recibe una entidad de Instalacion
    public InstalacionDto(InstalacionEntidad instalacionEntidad) {
        this.idInstalacion = instalacionEntidad.getIdInstalacion();
        this.nombreInstalacion = instalacionEntidad.getNombreInstalacion();
        this.direccionInstalacion = instalacionEntidad.getDireccionInstalacion();
        this.telefonoInstalacion = instalacionEntidad.getTelefonoInstalacion();
        this.emailInstalacion = instalacionEntidad.getEmailInstalacion();
        this.tipoCampo1 = instalacionEntidad.getTipoCampo1();
        this.tipoCampo2 = instalacionEntidad.getTipoCampo2();
        this.tipoCampo3 = instalacionEntidad.getTipoCampo3();
        this.serviciosInstalacion = instalacionEntidad.getServiciosInstalacion();
        this.estadoInstalacion = instalacionEntidad.getEstadoInstalacion();
        this.passwordInstalacion = instalacionEntidad.getPasswordInstalacion();
        this.imagenInstalacion = instalacionEntidad.getImagenInstalacion();
        this.torneoIds = instalacionEntidad.getTorneoIds(); 
    }

    // Getters and Setters
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

    public String getImagenInstalacion() {
        return imagenInstalacion;
    }

    public void setImagenInstalacion(String imagenInstalacion) {
        this.imagenInstalacion = imagenInstalacion;
    }

    public List<Long> getTorneoIds() {
        return torneoIds;
    }

    public void setTorneoIds(List<Long> torneoId) {
        this.torneoIds = torneoId;
    }

	public InstalacionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
