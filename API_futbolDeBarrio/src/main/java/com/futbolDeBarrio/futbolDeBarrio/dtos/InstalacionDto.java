package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.Base64;
import java.util.List;

import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.Modalidad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Clase que se encarga de los campos de instalacion
 */
public class InstalacionDto {

	 private long idInstalacion;

	    @NotBlank(message = "El nombre de la instalación es obligatorio")
	    private String nombreInstalacion;

	    @NotBlank(message = "La dirección de la instalación es obligatoria")
	    private String direccionInstalacion;

	    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos")
	    private String telefonoInstalacion;

	    @Email(message = "El email debe tener un formato válido")
	    @NotBlank(message = "El email de la instalación es obligatorio")
	    private String emailInstalacion;

	    private Modalidad tipoCampo1;
	    private Modalidad tipoCampo2;
	    private Modalidad tipoCampo3;

	    @NotBlank(message = "Los servicios de la instalación son obligatorios")
	    private String serviciosInstalacion;

	    private Estado estadoInstalacion;

	    @NotBlank(message = "La contraseña es obligatoria")
	    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	    private String passwordInstalacion;

	    private String imagenInstalacion;

	   
	    private List<Long> torneoIds;

   

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
        if (instalacionEntidad.getImagenInstalacion() != null) {
            this.imagenInstalacion = Base64.getEncoder().encodeToString(instalacionEntidad.getImagenInstalacion());
        }
        this.torneoIds = instalacionEntidad.getTorneoIds();

       
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

    public String getImagenInstalacion() {
        return imagenInstalacion;
    }

    public void setImagenInstalacion(String imagenInstalacion) {
        this.imagenInstalacion = imagenInstalacion;
    }

    public List<Long> getTorneoIds() {
        return torneoIds;
    }

    public void setTorneoIds(List<Long> torneoIds) {
        this.torneoIds = torneoIds;
    }

  

	public InstalacionDto() {
		super();
	}
    
}
