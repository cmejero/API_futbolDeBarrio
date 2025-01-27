package com.futbolDeBarrio.futbolDeBarrio.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "miembro_club", schema = "sch")
public class MiembroClubDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_miembro_club")
	private long idMiembroClub;

	@Column(name = "fecha_alta_usuario")
	private Date fechaAltaUsuario; // Fecha en que el usuario se unió al club

	@Column(name = "fecha_baja_usuario")
	private Date fechaBajaUsuario; // Fecha en que el usuario dejó de ser parte del club (si aplica)

	   @ManyToOne
	    @JoinColumn(name = "club_id", referencedColumnName = "id_club", nullable = false)
	    private ClubDto club;

	    @ManyToOne
	    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
	    private UsuarioDto usuario;
	
	
	// Getters y Setters
	public long getIdMiembroClub() {
		return idMiembroClub;
	}

	public void setIdMiembroClub(long idMiembroClub) {
		this.idMiembroClub = idMiembroClub;
	}


	public ClubDto getClub() {
		return club;
	}

	public void setClub(ClubDto club) {
		this.club = club;
	}


	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Date getFechaAltaUsuario() {
		return fechaAltaUsuario;
	}

	public void setFechaAltaUsuario(Date fechaAltaUsuario) {
		this.fechaAltaUsuario = fechaAltaUsuario;
	}

	public Date getFechaBajaUsuario() {
		return fechaBajaUsuario;
	}

	public void setFechaBajaUsuario(Date fechaBajaUsuario) {
		this.fechaBajaUsuario = fechaBajaUsuario;
	}

	public MiembroClubDto() {
	}
}
