package com.futbolDeBarrio.futbolDeBarrio.entidad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;

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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario", schema = "sch")
public class UsuarioEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    @Column(name = "nombreCompleto_usuario")
    private String nombreCompletoUsuario;
    
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 1 y 50 caracteres")
    @Column(name = "alias_usuario")
    private String aliasUsuario;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento_usuario")
    private String fechaNacimientoUsuario;

    @Size(min = 3, max = 50, message = "El correo debe tener entre 3 y 50 caracteres")
    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @Column(name = "password_usuario")
    private String passwordUsuario;

  
    @Column(name = "descripcion_usuario")
    private String descripcionUsuario;

    @Column(name = "imagen_usuario")
    private  byte[] imagenUsuario;

    @Enumerated(EnumType.STRING) 
    @Column(name = "estado_usuario")
    private Estado estadoUsuario;

    @Enumerated(EnumType.STRING) 
    @Column(name = "rolUsuario")
    private RolUsuario rolUsuario;
    
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiembroClubEntidad> miembroClub = new ArrayList<>();
    
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoPartidoEntidad> eventoPartido = new ArrayList<>();
    
    @OneToMany(mappedBy = "jugadorGlobalId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JugadorEstadisticaGlobalEntidad> jugadorEstadisticaGlobal = new ArrayList<>();
    
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JugadorEstadisticaTorneoEntidad> jugadorEstadisticaTorneo = new ArrayList<>();

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}

	public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}

	

	public String getAliasUsuario() {
		return aliasUsuario;
	}

	public void setAliasUsuario(String aliasUsuario) {
		this.aliasUsuario = aliasUsuario;
	}

	public String getFechaNacimientoUsuario() {
		return fechaNacimientoUsuario;
	}

	public void setFechaNacimientoUsuario(String fechaNacimientoUsuario) {
		this.fechaNacimientoUsuario = fechaNacimientoUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}



	public String getDescripcionUsuario() {
		return descripcionUsuario;
	}

	public void setDescripcionUsuario(String descripcionUsuario) {
		this.descripcionUsuario = descripcionUsuario;
	}


	public byte[] getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(byte[] imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}
	

	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	
	public RolUsuario getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(RolUsuario rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	

	public UsuarioEntidad() {
	}

	public List<MiembroClubEntidad> getMiembroClub() {
		return miembroClub;
	}

	public void setMiembroClub(List<MiembroClubEntidad> miembroClub) {
		this.miembroClub = miembroClub;
	}

	public List<EventoPartidoEntidad> getEventoPartido() {
		return eventoPartido;
	}

	public void setEventoPartido(List<EventoPartidoEntidad> eventoPartido) {
		this.eventoPartido = eventoPartido;
	}

	public List<JugadorEstadisticaGlobalEntidad> getJugadorEstadisticaGlobal() {
		return jugadorEstadisticaGlobal;
	}

	public void setJugadorEstadisticaGlobal(List<JugadorEstadisticaGlobalEntidad> jugadorEstadisticaGlobal) {
		this.jugadorEstadisticaGlobal = jugadorEstadisticaGlobal;
	}

	public List<JugadorEstadisticaTorneoEntidad> getJugadorEstadisticaTorneo() {
		return jugadorEstadisticaTorneo;
	}

	public void setJugadorEstadisticaTorneo(List<JugadorEstadisticaTorneoEntidad> jugadorEstadisticaTorneo) {
		this.jugadorEstadisticaTorneo = jugadorEstadisticaTorneo;
	}

	
    
}
