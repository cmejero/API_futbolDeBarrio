package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginGoogleDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.jwt.JwtFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;

@Service
/**
 * Clase que se encarga del login
 */
public class LoginFuncionalidades {

	@Autowired
	private UsuarioInterfaz usuarioInterfaz;

	@Autowired
	private ClubInterfaz clubInterfaz;

	@Autowired
	private InstalacionInterfaz instalacionInterfaz;

	@Autowired
	private JwtFuncionalidades jwtUtil;
	@Autowired
	private JugadorEstadisticaGlobalInterfaz jugadorEstadisticaGlobalInterfaz;
	@Autowired
	private ClubEstadisticaGlobalInterfaz clubEstadisticaGlobalInterfaz;

	/**
	 * Método que verifica las credenciales del usuario, club o instalación.
	 * 
	 * @param loginDto Objeto con el email y la contraseña introducidos
	 * @return Objeto RespuestaLoginDto si las credenciales son válidas, o null si
	 *         no lo son
	 */
	public RespuestaLoginDto verificarCredenciales(LoginDto loginDto) {
		String tipoUsuario = loginDto.getTipoUsuario();

		switch (tipoUsuario.toLowerCase()) {
		case "jugador":
		case "administrador":
			Optional<UsuarioEntidad> usuarioOpt = usuarioInterfaz.findByEmailUsuario(loginDto.getEmail());
			if (usuarioOpt.isPresent()) {
				UsuarioEntidad usuario = usuarioOpt.get();

				/*
				 * if (usuario.getCuenta() != null && !usuario.getCuenta().isEmailVerificado())
				 * { throw new
				 * IllegalArgumentException("El email no ha sido verificado. Revisa tu bandeja de entrada."
				 * ); }
				 */

				if (Utilidades.verificarContrasena(loginDto.getPassword(), usuario.getPasswordUsuario())) {
					Rol rolToken = (usuario.getRolUsuario() == RolUsuario.Administrador) ? Rol.Administrador
							: Rol.Usuario;
					String token = jwtUtil.obtenerToken(loginDto.getEmail(), rolToken);
					String tipo = usuario.getRolUsuario() == RolUsuario.Administrador ? "administrador" : "jugador";
					UsuarioDto usuarioDto = new UsuarioDto(usuario);
					return new RespuestaLoginDto(tipo, token, usuarioDto);
				} else {
					throw new IllegalArgumentException("Contraseña incorrecta.");
				}
			} else {
				throw new IllegalArgumentException("Usuario no encontrado.");
			}

		case "club":
			Optional<ClubEntidad> clubOpt = clubInterfaz.findByEmailClub(loginDto.getEmail());
			if (clubOpt.isPresent()) {
				ClubEntidad club = clubOpt.get();

				/*
				 * if (club.getCuenta() != null && !club.getCuenta().isEmailVerificado()) {
				 * throw new
				 * IllegalArgumentException("El email no ha sido verificado. Revisa tu bandeja de entrada."
				 * ); }
				 */

				if (Utilidades.verificarContrasena(loginDto.getPassword(), club.getPasswordClub())) {
					String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Club);
					return new RespuestaLoginDto("club", token, club);
				} else {
					throw new IllegalArgumentException("Contraseña incorrecta.");
				}
			} else {
				throw new IllegalArgumentException("Club no encontrado.");
			}

		case "instalacion":
			Optional<InstalacionEntidad> instalacionOpt = instalacionInterfaz
					.findByEmailInstalacion(loginDto.getEmail());
			if (instalacionOpt.isPresent()) {
				InstalacionEntidad instalacion = instalacionOpt.get();

				/*
				 * if (instalacion.getCuenta() != null &&
				 * !instalacion.getCuenta().isEmailVerificado()) { throw new
				 * IllegalArgumentException("El email no ha sido verificado. Revisa tu bandeja de entrada."
				 * ); }
				 */

				if (Utilidades.verificarContrasena(loginDto.getPassword(), instalacion.getPasswordInstalacion())) {
					String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Instalacion);
					return new RespuestaLoginDto("instalacion", token, instalacion);
				} else {
					throw new IllegalArgumentException("Contraseña incorrecta.");
				}
			} else {
				throw new IllegalArgumentException("Instalación no encontrada.");
			}

		default:
			throw new IllegalArgumentException("Tipo de usuario desconocido.");
		}
	}

	/**
	 * Verifica si el email ya existe en alguno de los tipos de usuario y genera
	 * token.
	 * 
	 * @param loginGoogleDto contiene email y tipoUsuario
	 * @return RespuestaLoginDto con token y datos del usuario
	 */
	public Map<String, Object> loginConGoogle(LoginGoogleDto loginGoogleDto) {
		Map<String, Object> respuesta = new HashMap<>();
		String tipoUsuario = loginGoogleDto.getTipoUsuario().toLowerCase();

		LoginGoogleDto dtoBase = new LoginGoogleDto();
		dtoBase.setEmail(loginGoogleDto.getEmail());
		dtoBase.setTipoUsuario(tipoUsuario);
		dtoBase.setNombreCompleto(loginGoogleDto.getNombreCompleto());


		switch (tipoUsuario) {
		case "jugador":
			UsuarioDto jugador = loginJugador(loginGoogleDto);
			dtoBase.setIdTipoUsuario(jugador.getIdUsuario());
			dtoBase.setEsPremium(jugador.isEsPremium());
			respuesta.put("login", dtoBase);
			respuesta.put("jugador", jugador);
			break;
		case "club":
			ClubDto club = loginClub(loginGoogleDto);
			dtoBase.setIdTipoUsuario(club.getIdClub());
			dtoBase.setEsPremium(club.isEsPremium());
			respuesta.put("login", dtoBase);
			respuesta.put("club", club);
			break;
		case "instalacion":
			InstalacionDto instalacion = loginInstalacion(loginGoogleDto);
			dtoBase.setIdTipoUsuario(instalacion.getIdInstalacion());
			respuesta.put("login", dtoBase);
			respuesta.put("instalacion", instalacion);
			break;
		default:
			return null;
		}

		return respuesta;
	}

	// ------------------ Jugador ------------------
	private UsuarioDto loginJugador(LoginGoogleDto loginGoogleDto) {
		String email = loginGoogleDto.getEmail();
		String nombreCompleto = loginGoogleDto.getNombreCompleto() != null ? loginGoogleDto.getNombreCompleto()
				: "Desconocido";
		String imagenBase64Usuario = loginGoogleDto.getImagenUsuario();

		UsuarioEntidad usuario = usuarioInterfaz.findByEmailUsuario(email).orElse(null);
		if (usuario == null) {
			usuario = inicializarUsuarioPorDefecto(email, nombreCompleto, imagenBase64Usuario);
			usuario = usuarioInterfaz.save(usuario);
			crearEstadisticasJugador(usuario);
		}

		if (usuario.getImagenUsuario() != null) {
			imagenBase64Usuario = Base64.getEncoder().encodeToString(usuario.getImagenUsuario());
		}

		String token = jwtUtil.obtenerToken(email, Rol.Usuario);

		UsuarioDto dto = new UsuarioDto();
		dto.setIdUsuario(usuario.getIdUsuario());
		dto.setNombreCompletoUsuario(usuario.getNombreCompletoUsuario());
		dto.setAliasUsuario(usuario.getAliasUsuario());
		dto.setFechaNacimientoUsuario(usuario.getFechaNacimientoUsuario());
		dto.setEmailUsuario(usuario.getEmailUsuario());
		dto.setTelefonoUsuario(usuario.getTelefonoUsuario());
		dto.setPasswordUsuario(usuario.getPasswordUsuario());
		dto.setRolUsuario(usuario.getRolUsuario());
		dto.setDescripcionUsuario(usuario.getDescripcionUsuario());
		if (dto.getImagenUsuario() != null) {
			byte[] imagenBytes = Base64.getDecoder().decode(dto.getImagenUsuario());
			usuario.setImagenUsuario(imagenBytes);
		}
		dto.setEstadoUsuario(usuario.getEstadoUsuario());
		dto.setEsPremium(usuario.isEsPremium());

		return dto;

	}

	private UsuarioEntidad inicializarUsuarioPorDefecto(String email, String nombreCompleto, String imagenBase64) {
		UsuarioEntidad usuario = new UsuarioEntidad();
		usuario.setEmailUsuario(email);
		usuario.setNombreCompletoUsuario(nombreCompleto);
		usuario.setAliasUsuario(generarAlias(nombreCompleto));
		usuario.setPasswordUsuario(Utilidades.encriptarContrasenya(UUID.randomUUID().toString()));
		usuario.setRolUsuario(RolUsuario.Jugador);
		usuario.setEstadoUsuario(Estado.Activo);
		usuario.setDescripcionUsuario("");
		usuario.setFechaNacimientoUsuario("1970-01-01");
		usuario.setTelefonoUsuario("");
		usuario.setEsPremium(false);
		usuario.setImagenUsuario(imagenBase64 != null ? Base64.getDecoder().decode(imagenBase64) : new byte[0]);
		return usuario;
	}

	// ------------------ Club ------------------
	private ClubDto loginClub(LoginGoogleDto loginGoogleDto) {
		ClubEntidad club = clubInterfaz.findByEmailClub(loginGoogleDto.getEmail()).orElse(null);

		if (club == null) {
			club = inicializarClubPorDefecto(loginGoogleDto.getEmail(), loginGoogleDto.getNombreCompleto(),
					loginGoogleDto.getImagenUsuario());
			club = clubInterfaz.save(club);
			crearEstadisticasClub(club);
		}

		String token = jwtUtil.obtenerToken(club.getEmailClub(), Rol.Club);

		ClubDto dto = new ClubDto();
		dto.setIdClub(club.getIdClub());
		dto.setNombreClub(club.getNombreClub());
		dto.setAbreviaturaClub(club.getAbreviaturaClub());
		dto.setDescripcionClub(club.getDescripcionClub());
		dto.setFechaCreacionClub(club.getFechaCreacionClub());
		dto.setFechaFundacionClub(club.getFechaFundacionClub());
		dto.setLocalidadClub(club.getLocalidadClub());
		dto.setPaisClub(club.getPaisClub());
		dto.setTelefonoClub(club.getTelefonoClub());
		dto.setEmailClub(club.getEmailClub());
		dto.setPasswordClub(club.getPasswordClub());
		dto.setEsPremium(club.isEsPremium());
		if (dto.getLogoClub() != null) {
			byte[] imagenBytes = Base64.getDecoder().decode(dto.getLogoClub());
			club.setLogoClub(imagenBytes);
		}

		return dto;
	}

	private ClubEntidad inicializarClubPorDefecto(String email, String nombreCompleto, String imagenBase64) {
		ClubEntidad club = new ClubEntidad();
		club.setEmailClub(email);
		club.setNombreClub(nombreCompleto);
		club.setAbreviaturaClub(generarAbreviatura(nombreCompleto));
		club.setDescripcionClub("");
		club.setFechaCreacionClub("1970-01-01");
		club.setFechaFundacionClub("1970-01-01");
		club.setLocalidadClub("");
		club.setPaisClub("");
		club.setTelefonoClub("");
		club.setPasswordClub(Utilidades.encriptarContrasenya(UUID.randomUUID().toString()));
		club.setEsPremium(false);
		club.setLogoClub(imagenBase64 != null ? Base64.getDecoder().decode(imagenBase64) : new byte[0]);
		return club;
	}

	// ------------------ Instalación ------------------
	private InstalacionDto loginInstalacion(LoginGoogleDto loginGoogleDto) {
		String email = loginGoogleDto.getEmail();
		String nombreCompleto = loginGoogleDto.getNombreCompleto() != null ? loginGoogleDto.getNombreCompleto()
				: "Desconocido";
		String imagenBase64 = loginGoogleDto.getImagenUsuario();

		InstalacionEntidad instalacion = instalacionInterfaz.findByEmailInstalacion(email).orElse(null);
		if (instalacion == null) {
			instalacion = inicializarInstalacionPorDefecto(email, nombreCompleto, imagenBase64);
			instalacion = instalacionInterfaz.save(instalacion);
		}

		if (instalacion.getImagenInstalacion() != null) {
			imagenBase64 = Base64.getEncoder().encodeToString(instalacion.getImagenInstalacion());
		}

		String token = jwtUtil.obtenerToken( email, Rol.Instalacion);

		InstalacionDto dto = new InstalacionDto();
		dto.setIdInstalacion(instalacion.getIdInstalacion());
		dto.setNombreInstalacion(instalacion.getNombreInstalacion());
		dto.setDireccionInstalacion(instalacion.getDireccionInstalacion());
		dto.setTelefonoInstalacion(instalacion.getTelefonoInstalacion());
		dto.setEmailInstalacion(instalacion.getEmailInstalacion());
		dto.setTipoCampo1(instalacion.getTipoCampo1());
		dto.setTipoCampo2(instalacion.getTipoCampo2());
		dto.setTipoCampo3(instalacion.getTipoCampo3());
		dto.setServiciosInstalacion(instalacion.getServiciosInstalacion());
		dto.setEstadoInstalacion(instalacion.getEstadoInstalacion());
		dto.setPasswordInstalacion(instalacion.getPasswordInstalacion());
		dto.setTorneoIds(instalacion.getTorneoIds());
		dto.setImagenInstalacion(imagenBase64);

		return dto;
	}

	private InstalacionEntidad inicializarInstalacionPorDefecto(String email, String nombreCompleto,
			String imagenBase64) {
		InstalacionEntidad instalacion = new InstalacionEntidad();
		instalacion.setEmailInstalacion(email);
		instalacion.setNombreInstalacion(nombreCompleto);
		instalacion.setDireccionInstalacion("");
		instalacion.setTelefonoInstalacion("");
		instalacion.setServiciosInstalacion("");
		instalacion.setTipoCampo1(null);
		instalacion.setTipoCampo2(null);
		instalacion.setTipoCampo3(null);
		instalacion.setPasswordInstalacion(Utilidades.encriptarContrasenya(UUID.randomUUID().toString()));
		instalacion.setEstadoInstalacion(Estado.Activo);
		instalacion.setImagenInstalacion(imagenBase64 != null ? Base64.getDecoder().decode(imagenBase64) : new byte[0]);
		return instalacion;
	}

	// ------------------ Métodos auxiliares ------------------
	private String generarAlias(String nombreCompleto) {
		String[] palabras = nombreCompleto.split("\s+");
		StringBuilder alias = new StringBuilder();
		for (String palabra : palabras) {
			alias.append(palabra.length() >= 2 ? palabra.substring(0, 2) : palabra);
		}
		return alias.toString().toLowerCase();
	}

	private String generarAbreviatura(String nombreClub) {
		String[] palabras = nombreClub.split("\s+");
		StringBuilder abreviatura = new StringBuilder();
		for (int i = 0; i < palabras.length && i < 3; i++) {
			abreviatura.append(palabras[i].substring(0, 1));
		}
		return abreviatura.toString().toUpperCase();
	}

	private void crearEstadisticasJugador(UsuarioEntidad usuario) {
		JugadorEstadisticaGlobalEntidad estadistica = new JugadorEstadisticaGlobalEntidad();
		estadistica.setJugadorGlobalId(usuario);
		estadistica.setGolesGlobal(0);
		estadistica.setAsistenciasGlobal(0);
		estadistica.setAmarillasGlobal(0);
		estadistica.setRojasGlobal(0);
		estadistica.setPartidosJugadosGlobal(0);
		estadistica.setPartidosGanadosGlobal(0);
		estadistica.setPartidosPerdidosGlobal(0);
		estadistica.setMinutosJugadosGlobal(0);
		jugadorEstadisticaGlobalInterfaz.save(estadistica);
	}

	private void crearEstadisticasClub(ClubEntidad club) {
		ClubEstadisticaGlobalEntidad estadisticaClub = new ClubEstadisticaGlobalEntidad();
		estadisticaClub.setClubGlobal(club);
		estadisticaClub.setGolesFavorGlobal(0);
		estadisticaClub.setGolesContraGlobal(0);
		estadisticaClub.setGanadosGlobal(0);
		estadisticaClub.setPerdidosGlobal(0);
		estadisticaClub.setPartidosJugadosGlobal(0);
		clubEstadisticaGlobalInterfaz.save(estadisticaClub);
	}

}
