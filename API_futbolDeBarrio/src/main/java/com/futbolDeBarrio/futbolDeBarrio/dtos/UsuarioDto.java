package com.futbolDeBarrio.futbolDeBarrio.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolJugador;

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
public class UsuarioDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    @Column(name = "nombreCompleto_usuario")
    private String nombreCompletoUsuario;

    @Column(name = "fecha_nacimiento_usuario")
    private Date fechaNacimientoUsuario;

    @Size(min = 3, max = 50, message = "El correo debe tener entre 3 y 50 caracteres")
    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @Column(name = "password_usuario")
    private String passwordUsuario;

    @Enumerated(EnumType.STRING) // Indica que se almacenará como texto en la base de datos
    @Column(name = "rol_jugador")
    private RolJugador rolJugador;

    @Column(name = "descripcion_usuario")
    private String descripcionUsuario;

    @Column(name = "imagen_usuario")
    private String imagenUsuario;

    @Enumerated(EnumType.STRING) // Enum mapeado como STRING
    @Column(name = "estado_usuario")
    private Estado estadoUsuario;

    @Enumerated(EnumType.STRING) // Enum mapeado como STRING
    @Column(name = "rol_usuario")
    private Rol rolUsuario;
    
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MiembroClubDto> miembroClub = new ArrayList<>();

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


	public Date getFechaNacimientoUsuario() {
		return fechaNacimientoUsuario;
	}

	public void setFechaNacimientoUsuario(Date fechaNacimientoUsuario) {
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

	public RolJugador getRolJugador() {
		return rolJugador;
	}

	public void setRolJugador(RolJugador rolJugador) {
		this.rolJugador = rolJugador;
	}

	public String getDescripcionUsuario() {
		return descripcionUsuario;
	}

	public void setDescripcionUsuario(String descripcionUsuario) {
		this.descripcionUsuario = descripcionUsuario;
	}

	public String getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(String imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}

	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Rol getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(Rol rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public UsuarioDto() {
	}

    
}
