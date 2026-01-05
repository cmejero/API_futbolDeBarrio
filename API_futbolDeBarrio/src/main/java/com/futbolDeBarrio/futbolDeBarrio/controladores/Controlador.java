package com.futbolDeBarrio.futbolDeBarrio.controladores;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ActaPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisticaGlobalDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.EquipoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.EventoPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.JugadorEstadisticaGlobalDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.JugadorEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginGoogleDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.PartidoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RecuperarContrasenaDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RestablecerContrasenaDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.TorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.EquipoTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ActaPartidoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubEstadisticaGlobalFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubEstadisticaTorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.EquipoTorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.EventoPartidoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.InstalacionFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.JugadorEstadisticaGlobalFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.JugadorEstadisticaTorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.LoginFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.MiembroClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.PartidoTorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.RecuperarContrasenaFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.TorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.UsuarioFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.verificacion.VerificacionEmailFuncionalidad;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*
 * Clase que se encarga de los metodos CRUD de la API
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controlador {

	@Autowired
	ClubFuncionalidades clubFuncionalidades;
	@Autowired
	EquipoTorneoFuncionalidades equipoTorneoFuncionalidades;
	@Autowired
	InstalacionFuncionalidades instalacionFuncionalidades;
	@Autowired
	MiembroClubFuncionalidades miembroClubFuncionalidades;
	@Autowired
	TorneoFuncionalidades torneoFuncionalidades;
	@Autowired
	UsuarioFuncionalidades usuarioFuncionalidades;
	@Autowired
	LoginFuncionalidades loginFuncionalidades;
	@Autowired
	RecuperarContrasenaFuncionalidades recuperarContrasenaFuncionalidades;
	@Autowired
	ActaPartidoFuncionalidades actaPartidoFuncionalidades;
	@Autowired
	EventoPartidoFuncionalidades eventoPartidoFuncionalidades;
	@Autowired
	ClubEstadisticaTorneoFuncionalidades clubEstadisticaTorneoFuncionalidades;
	@Autowired
	ClubEstadisticaGlobalFuncionalidades clubEstadisticaGlobalFuncionalidades;
	@Autowired
	JugadorEstadisticaTorneoFuncionalidades jugadorEstadisticaTorneoFuncionalidades;
	@Autowired
	JugadorEstadisticaGlobalFuncionalidades jugadorEstadisticaGlobalFuncionalidades;
	@Autowired
	PartidoTorneoFuncionalidades partidoTorneoFuncionalidades;
	@Autowired
	VerificacionEmailFuncionalidad verificacionEmailFuncionalidad;
	@Autowired
	EquipoTorneoInterfaz equipoTorneoInterfaz;
	@Autowired
	JugadorEstadisticaGlobalInterfaz jugadorEstadisticaGlobalInterfaz;

	@CrossOrigin(origins = "http://localhost:8080")

	@PostMapping("/login")
	/**
	 * Metodo para realizar el login del usuario.
	 * 
	 * @param loginDto Contiene el email y contraseña del usuario.
	 * @return ResponseEntity con el resultado del login.
	 */
	public ResponseEntity<RespuestaLoginDto> login(@RequestBody LoginDto loginDto) {
		Logs.ficheroLog("Intento de login con email: " + loginDto.getEmail() + " tipo: " + loginDto.getTipoUsuario());
		RespuestaLoginDto respuestaLogin = loginFuncionalidades.verificarCredenciales(loginDto);
		if (respuestaLogin == null) {
			Logs.ficheroLog("Login fallido para email: " + loginDto.getEmail());
			return ResponseEntity.status(401).body(null);
		}
		Logs.ficheroLog("Login exitoso para email: " + loginDto.getEmail());
		return ResponseEntity.ok(respuestaLogin);
	}

	@PostMapping("/loginGoogle")
	public ResponseEntity<Map<String, Object>> loginGoogle(@RequestBody LoginGoogleDto dto) {
	    Map<String, Object> respuesta = loginFuncionalidades.loginConGoogle(dto);

	    if (respuesta != null) {
	        LoginGoogleDto login = (LoginGoogleDto) respuesta.get("login");

	        // Log del login básico
	        Logs.ficheroLog("Login exitoso Google para email: " + login.getEmail() +
	                         ", tipo: " + login.getTipoUsuario() +
	                         ", ID: " + login.getIdTipoUsuario());

	        return ResponseEntity.ok(respuesta);
	    } else {
	        return ResponseEntity.status(401).body(null);
	    }
	}

	
	  /**
     * Endpoint que verifica el email a partir del token recibido.
     */
	@GetMapping("/verificarEmail")
	public ResponseEntity<String> verificarEmail(@RequestParam String token) {
	    Logs.ficheroLog("Intento de verificación de email con token: " + token);
	    try {
	        String resultado = verificacionEmailFuncionalidad.verificarToken(token);
	        Logs.ficheroLog("Verificación de email exitosa para token: " + token);
	        return ResponseEntity.ok(resultado); // 200 OK
	    } catch (RuntimeException e) {
	        Logs.ficheroLog("Error verificando token: " + token + ". Error: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("Error al verificar el email: " + e.getMessage());
	    } catch (Exception e) {
	        Logs.ficheroLog("Error interno verificando token: " + token + ". Error: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error interno del servidor");
	    }
	}


	@PostMapping("/recuperar-contrasena")
	/**
	 * Solicita el envío del enlace para recuperar contraseña.
	 * 
	 * @param request DTO con el email del usuario.
	 * @return Respuesta indicando éxito o error.
	 */
	public ResponseEntity<?> solicitarRecuperacion(@RequestBody @Valid RecuperarContrasenaDto request) {
		Logs.ficheroLog("Solicitud de recuperación de contraseña para email: " + request.getEmail());
		try {
			recuperarContrasenaFuncionalidades.enviarEnlaceRecuperacion(request.getEmail());
			Logs.ficheroLog("Correo de recuperación enviado correctamente para email: " + request.getEmail());
			return ResponseEntity.ok("Correo enviado con instrucciones");
		} catch (RuntimeException e) {
			Logs.ficheroLog("Error al enviar correo de recuperación para email: " + request.getEmail() + ". Error: "
					+ e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/restablecer-contrasena")
	/**
	 * Actualiza la contraseña usando token válido.
	 * 
	 * @param request DTO con token y nuevas contraseñas.
	 * @return Respuesta indicando éxito o error.
	 */
	public ResponseEntity<?> restablecerContrasena(@RequestBody @Valid RestablecerContrasenaDto request) {
		Logs.ficheroLog("Intento de restablecer contraseña con token: " + request.getToken());
		try {
			recuperarContrasenaFuncionalidades.actualizarContrasena(request.getToken(), request.getNuevaContrasena(),
					request.getRepetirContrasena());
			Logs.ficheroLog("Contraseña restablecida exitosamente con token: " + request.getToken());
			return ResponseEntity.ok("Contraseña actualizada correctamente");
		} catch (RuntimeException e) {
			Logs.ficheroLog(
					"Error al restablecer contraseña con token: " + request.getToken() + ". Error: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/* METODOS CRUD DE LA TABLA CLUB */

	@GetMapping("/club/{id_club}")
	/**
	 * Metodo para obtener los detalles de un club por su ID.
	 * Solo puede acceder el club logueado.
	 * 
	 * @param idClub ID del club a buscar.
	 * @param principal Email del usuario logueado (del token JWT).
	 * @return ResponseEntity con el club encontrado o un error 403/404.
	 */
	public ResponseEntity<ClubDto> obtenerClubPorId(
	        @PathVariable("id_club") Long idClub,
	        Principal principal) {

	    Logs.ficheroLog("Buscando club con ID: " + idClub);

	    // 1️⃣ Obtener el email del club logueado
	    String emailLogueado = principal.getName();

	    // 2️⃣ Obtener el club desde el servicio
	    ClubDto clubDto = clubFuncionalidades.obtenerClubDtoPorId(idClub);

	    if (clubDto == null) {
	        Logs.ficheroLog("No se encontró club con ID: " + idClub);
	        return ResponseEntity.notFound().build();
	    }

	    // 3️⃣ Validar que solo el club logueado pueda ver su info
	    if (!emailLogueado.equals(clubDto.getEmailClub())) {
	        Logs.ficheroLog("Intento no autorizado de ver club ID " + idClub + " por: " + emailLogueado);
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }

	    Logs.ficheroLog("Club encontrado con ID: " + idClub + " y autorizado para: " + emailLogueado);
	    return ResponseEntity.ok(clubDto);
	}

	
	
	@GetMapping("/mostrarClubes")
	/**
	 * Metodo para mostrar todos los clubes disponibles.
	 * 
	 * @return Lista de objetos ClubDto.
	 */

	public ResponseEntity<?> mostrarClubes(Principal principal) {
	    String emailAdmin = principal.getName();  // Usuario logueado
	    Logs.ficheroLog("Admin logueado: " + emailAdmin + " solicita lista de clubes");

	    try {
	        List<ClubDto> clubes = clubFuncionalidades.obtenerClubesDto();
	        return ResponseEntity.ok(clubes);

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al obtener clubes por admin: " + emailAdmin);
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error al obtener clubes");
	    }
	}


	@PostMapping("/guardarClub")
	/**
	 * Metodo para guardar un nuevo club.
	 * 
	 * @param clubDto Datos del club a guardar.
	 * @return ResponseEntity con el club guardado o un error si no se pudo guardar.
	 */
	public ResponseEntity<?> guardarClub(@RequestBody ClubDto clubDto) {
		Logs.ficheroLog("Solicitud para guardar club con email: " + clubDto.getEmailClub());
		try {
			ClubEntidad clubEntidad = clubFuncionalidades.guardarClub(clubDto);
			Logs.ficheroLog("Club guardado exitosamente con ID: " + clubEntidad.getIdClub());
			return ResponseEntity.ok(clubFuncionalidades.mapearAClubDto(clubEntidad));
		} catch (IllegalArgumentException e) {
			Logs.ficheroLog("Error al guardar club: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			Logs.ficheroLog("Error inesperado al guardar club: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
		}
	}

	@DeleteMapping("/eliminarClub/{id_club}")
	/**
	 * Método para eliminar un club por su ID.
	 *
	 * @param id_club ID del club a eliminar.
	 * @param principal Usuario logueado (admin)
	 * @return Resultado de la eliminación
	 */
	public ResponseEntity<?> borrarClub(@PathVariable("id_club") String id_club, Principal principal) {
	    String emailAdmin = principal.getName();
	    Logs.ficheroLog("Admin " + emailAdmin + " solicita eliminar club con ID: " + id_club);

	    boolean resultado = this.clubFuncionalidades.borrarClub(id_club);

	    Logs.ficheroLog("Resultado de eliminación del club con ID " + id_club + ": " + resultado +
	                     " (realizado por " + emailAdmin + ")");

	    if (resultado) {
	        return ResponseEntity.ok("Club eliminado correctamente.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("No se pudo eliminar el club. Verifica el ID.");
	    }
	}


	@PutMapping("/modificarClub/{id_club}")
	/**
	 * Método para modificar los detalles de un club.
	 *
	 * @param idClub  ID del club a modificar.
	 * @param clubDto Datos actualizados del club.
	 * @param principal Usuario logueado (admin)
	 * @return Resultado de la modificación
	 */
	public ResponseEntity<?> modificarClub(
	        @PathVariable("id_club") String idClub,
	        @RequestBody ClubDto clubDto,
	        Principal principal) {

	    String emailAdmin = principal.getName();  // 🔑 Email del admin logueado
	    Logs.ficheroLog("Admin " + emailAdmin + " solicita modificar club con ID: " + idClub);

	    boolean resultado = clubFuncionalidades.modificarClub(idClub, clubDto);

	    Logs.ficheroLog("Resultado de modificación del club con ID " + idClub + ": " + resultado +
	                     " (realizado por " + emailAdmin + ")");

	    if (resultado) {
	        return ResponseEntity.ok("Club modificado correctamente.");
	    } else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                             .body("No se pudo modificar el club. Verifica los datos o permisos.");
	    }
	}


	/**
	 * Método PUT para actualizar el campo esPremium de un club.
	 * 
	 * @param idClub ID del club a actualizar.
	 * @return Mensaje de confirmación.
	 */
	@PutMapping("/modificarPremiumClub/{id_club}")
	public ResponseEntity<?> modificarPremiumClub(@PathVariable("id_club") Long idClub, Principal principal) {
	    try {
	        // 1️⃣ Email del club logueado desde el token
	        String emailClubLogueado = principal.getName();

	        // 2️⃣ Llamada al servicio para actualizar a Premium solo si el club coincide
	        boolean resultado = clubFuncionalidades.marcarClubPremium(idClub, emailClubLogueado);

	        if (resultado) {
	            return ResponseEntity.ok("Club actualizado a Premium correctamente.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado o no autorizado.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al actualizar el estado Premium del club.");
	    }
	}


	/* METODOS CRUD DE LA TABLA EQUIPO_TORNEO */

	@GetMapping("/equipoTorneo/{id_equipoTorneo}")
	/**
	 * Metodo para obtener los detalles de un equipo en el torneo por su ID.
	 * 
	 * @param idEquipoTorneo ID del equipo torneo a buscar.
	 * @return ResponseEntity con el equipo torneo encontrado o un error 404 si no
	 *         se encuentra.
	 */
	public ResponseEntity<EquipoTorneoDto> obtenerEquipoTorneoPorId(
			@PathVariable("id_equipoTorneo") Long idEquipoTorneo) {
		Logs.ficheroLog("Buscando equipo torneo con ID: " + idEquipoTorneo);
		EquipoTorneoDto equipoTorneoDto = equipoTorneoFuncionalidades.obtenerEquipoTorneoDtoPorId(idEquipoTorneo);
		if (equipoTorneoDto != null) {
			Logs.ficheroLog("Equipo torneo encontrado con ID: " + idEquipoTorneo);
			return ResponseEntity.ok(equipoTorneoDto);
		} else {
			Logs.ficheroLog("No se encontró equipo torneo con ID: " + idEquipoTorneo);
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/mostrarEquipoTorneo")
	/**
	 * Metodo para mostrar todos los equipos torneo.
	 * 
	 * @return Lista de objetos EquipoTorneoDto.
	 */
	public List<EquipoTorneoDto> mostrarEquipoTorneo() {
		Logs.ficheroLog("Mostrando todos los equipos torneo");
		return equipoTorneoFuncionalidades.obtenerEquiposTorneoDto();
	}
	
    @GetMapping("/equipoTorneo/club/{clubId}")
    /**
     * Metodo para obtener todos los equipos de un club específico.
     */
    public List<EquipoTorneoDto> obtenerEquiposPorClub(@PathVariable Long clubId) {
        Logs.ficheroLog("Obteniendo equipos del club con ID: " + clubId);
        return equipoTorneoFuncionalidades.obtenerEquiposPorClub(clubId);
    }

    
    @GetMapping("/equipoTorneo/usuario/{usuarioId}")
    /**
     * Obtiene todos los torneos de todos los clubes de un usuario.
     */
    public List<TorneoDto> obtenerTorneosPorUsuario(@PathVariable Long usuarioId) {
        Logs.ficheroLog("Obteniendo torneos de todos los clubes del usuario con ID: " + usuarioId);
        return equipoTorneoFuncionalidades.obtenerTorneosPorUsuario(usuarioId);
    }

    
	@PostMapping("/guardarEquipoTorneo")
	/**
	 * Metodo para guardar un nuevo equipo en el torneo.
	 * 
	 * @param equipoTorneoDto Datos del equipo torneo a guardar.
	 * @return El objeto EquipoTorneoDto del equipo guardado.
	 */
	public ResponseEntity<String> guardarEquipoTorneo(
	        @RequestBody EquipoTorneoDto equipoTorneoDto,
	        Principal principal) {

	    try {
	        Logs.ficheroLog("Solicitud para inscribir club en torneo");

	        // 1️⃣ Email del club logueado
	        String emailClubLogueado = principal.getName();

	        // 2️⃣ Guardar inscripción a través del servicio
	        EquipoTorneoEntidad equipoGuardado =
	                equipoTorneoFuncionalidades.guardarEquipoTorneo(
	                        equipoTorneoDto,
	                        emailClubLogueado);

	        Logs.ficheroLog("Club inscrito correctamente en torneo con ID: " + equipoTorneoDto.getTorneoId());

	        return ResponseEntity.ok("Te has unido al torneo correctamente.");

	    } catch (IllegalStateException e) {
	        Logs.ficheroLog("Error de autorización: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

	    } catch (RuntimeException e) {
	        Logs.ficheroLog("Intento de inscripción duplicada: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al inscribirse en torneo: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al intentar unirse al torneo.");
	    }
	}


	@DeleteMapping("/eliminarEquipoTorneo/{id_equipoTorneo}")
	/**
	 * Metodo para eliminar un equipo del torneo por su ID.
	 * 
	 * @param id_equipoTorneo ID del equipo torneo a eliminar.
	 * @return Resultado de la eliminación.
	 */
	public boolean borrarEquipoTorneo(@PathVariable("id_equipoTorneo") String id_equipoTorneo) {
		Logs.ficheroLog("Solicitud para eliminar equipo torneo con ID: " + id_equipoTorneo);
		boolean resultado = this.equipoTorneoFuncionalidades.borrarEquipoTorneo(id_equipoTorneo);
		Logs.ficheroLog("Resultado de eliminación del equipo torneo con ID " + id_equipoTorneo + ": " + resultado);
		return resultado;
	}

	@PutMapping("/modificarEquipoTorneo/{id_equipoTorneo}")
	/**
	 * Metodo para modificar los detalles de un equipo en el torneo.
	 * 
	 * @param idEquipoTorneo  ID del equipo torneo a modificar.
	 * @param equipoTorneoDto Datos actualizados del equipo torneo.
	 * @return Resultado de la modificación.
	 */
	public boolean modificarEquipoTorneo(@PathVariable("id_equipoTorneo") String idEquipoTorneo,
			@RequestBody EquipoTorneoDto equipoTorneoDto) {
		Logs.ficheroLog("Solicitud para modificar equipo torneo con ID: " + idEquipoTorneo);
		boolean resultado = this.equipoTorneoFuncionalidades.modificarEquipoTorneo(idEquipoTorneo, equipoTorneoDto);
		Logs.ficheroLog("Resultado de modificación del equipo torneo con ID " + idEquipoTorneo + ": " + resultado);
		return resultado;
	}

	/* METODOS CRUD DE LA TABLA INSTALACION */

	/**
	 * Metodo para obtener los detalles de una instalación por su ID.
	 * 
	 * @param idInstalacion ID de la instalación a buscar.
	 * @return ResponseEntity con los detalles de la instalación o un error 404 si
	 *         no se encuentra.
	 */
	@GetMapping("/instalacion/{id_instalacion}")
	public ResponseEntity<InstalacionDto> obtenerInstalacionPorId(
	        @PathVariable("id_instalacion") Long idInstalacion,
	        Principal principal) {

	    String emailLogueado = principal.getName();

	    InstalacionDto instalacionDto =
	            instalacionFuncionalidades.obtenerInstalacionDtoPorId(
	                    idInstalacion, emailLogueado);

	    return ResponseEntity.ok(instalacionDto);
	}


	@GetMapping("/mostrarInstalaciones")
	/**
	 * Método para obtener todas las instalaciones.
	 *
	 * @param principal Usuario logueado (admin)
	 * @return Lista de instalaciones
	 */
	public ResponseEntity<?> mostrarInstalaciones(Principal principal) {
	    String emailAdmin = principal.getName();  // 🔑 Para logs
	    Logs.ficheroLog("Admin " + emailAdmin + " solicita mostrar todas las instalaciones");

	    try {
	        List<InstalacionDto> instalaciones = instalacionFuncionalidades.obtenerInstalacionesDto();
	        return ResponseEntity.ok(instalaciones);

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al obtener instalaciones por admin: " + emailAdmin);
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error al obtener instalaciones");
	    }
	}


	/**
	 * Metodo para guardar una nueva instalación.
	 * 
	 * @param instalacionDto Datos de la instalación a guardar.
	 * @return ResponseEntity con la instalación guardada o un error si ocurre una
	 *         excepción.
	 */
	@PostMapping("/guardarInstalacion")
	public ResponseEntity<?> guardarInstalacion(@RequestBody InstalacionDto instalacionDto) {
		try {
			Logs.ficheroLog("Solicitud para guardar instalación con datos: " + instalacionDto.toString());
			InstalacionEntidad instalacionEntidad = instalacionFuncionalidades.guardarInstalacion(instalacionDto);
			Logs.ficheroLog("Instalación guardada exitosamente con ID: " + instalacionEntidad.getIdInstalacion());
			return ResponseEntity.ok(instalacionFuncionalidades.mapearAInstalacionDto(instalacionEntidad));
		} catch (IllegalArgumentException e) {
			Logs.ficheroLog("Error al guardar instalación: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			Logs.ficheroLog("Error inesperado al guardar instalación: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
		}
	}

	@DeleteMapping("/eliminarInstalacion/{id_instalacion}")
	/**
	 * Método para eliminar una instalación.
	 *
	 * @param id_instalacion ID de la instalación a eliminar.
	 * @param principal Usuario logueado (admin)
	 * @return Resultado de la eliminación
	 */
	public ResponseEntity<?> eliminarInstalacion(
	        @PathVariable("id_instalacion") String id_instalacion,
	        Principal principal) {

	    String emailAdmin = principal.getName();  // 🔑 Para auditoría
	    Logs.ficheroLog("Admin " + emailAdmin + " solicita eliminar instalación con ID: " + id_instalacion);

	    boolean resultado = this.instalacionFuncionalidades.borrarInstalacion(id_instalacion);

	    Logs.ficheroLog("Resultado de eliminación de instalación con ID " + id_instalacion +
	                     ": " + resultado + " (realizado por " + emailAdmin + ")");

	    if (resultado) {
	        return ResponseEntity.ok("Instalación eliminada correctamente.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("No se pudo eliminar la instalación. Verifica el ID.");
	    }
	}



	@PutMapping("/modificarInstalacion/{id_instalacion}")
	/**
	 * Método para modificar una instalación.
	 *
	 * @param id_instalacion ID de la instalación a modificar.
	 * @param instalacionDto Datos actualizados de la instalación.
	 * @param principal Usuario logueado (admin)
	 * @return Resultado de la modificación
	 */
	public ResponseEntity<?> modificarInstalacion(
	        @PathVariable("id_instalacion") String id_instalacion,
	        @RequestBody InstalacionDto instalacionDto,
	        Principal principal) {

	    String emailAdmin = principal.getName();  // 🔑 Para logs
	    Logs.ficheroLog("Admin " + emailAdmin + " solicita modificar instalación con ID: " + id_instalacion);

	    boolean resultado = this.instalacionFuncionalidades.modificarInstalacion(id_instalacion, instalacionDto);

	    Logs.ficheroLog("Resultado de modificación de instalación con ID " + id_instalacion +
	                     ": " + resultado + " (realizado por " + emailAdmin + ")");

	    if (resultado) {
	        return ResponseEntity.ok("Instalación modificada correctamente.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("No se pudo modificar la instalación. Verifica el ID.");
	    }
	}


	/* METODOS CRUD DE LA TABLA MIEMBRO_CLUB */

	/**
	 * Metodo para obtener los detalles de un miembro del club por su ID.
	 * 
	 * @param idMiembroClub ID del miembro del club a buscar.
	 * @return ResponseEntity con los detalles del miembro o un error 404 si no se
	 *         encuentra.
	 */
	@GetMapping("/miembroClub/{id_miembroClub}")
	public ResponseEntity<MiembroClubDto> obtenerMiembroClubPorId(@PathVariable("id_miembroClub") Long idMiembroClub) {
		Logs.ficheroLog("Buscando miembro del club con ID: " + idMiembroClub);
		MiembroClubDto miembroClubDto = miembroClubFuncionalidades.obtenerMiembroClubDtoPorId(idMiembroClub);
		if (miembroClubDto != null) {
			Logs.ficheroLog("Miembro del club encontrado con ID: " + idMiembroClub);
			return ResponseEntity.ok(miembroClubDto);
		} else {
			Logs.ficheroLog("No se encontró miembro del club con ID: " + idMiembroClub);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Metodo para obtener todos los miembros de un club, filtrados por clubId.
	 * 
	 * @param clubId ID del club del cual obtener los miembros.
	 * @return Lista de miembros del club o un error 404 si no se encuentran
	 *         miembros.
	 */
	@GetMapping("/miembroClub/porClub/{clubId}")
	public ResponseEntity<List<MiembroClubDto>> obtenerMiembrosPorClub(@PathVariable("clubId") Long clubId) {
		Logs.ficheroLog("Buscando miembros del club con ID: " + clubId);
		List<MiembroClubDto> miembrosClub = miembroClubFuncionalidades.obtenerMiembrosPorClub(clubId);
		if (!miembrosClub.isEmpty()) {
			Logs.ficheroLog("Miembros del club encontrados con ID: " + clubId);
			return ResponseEntity.ok(miembrosClub);
		} else {
			Logs.ficheroLog("No se encontraron miembros del club con ID: " + clubId);
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/miembroClub/porUsuario/{usuarioId}")
	public ResponseEntity<List<MiembroClubDto>> obtenerMisClubesPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
	    Logs.ficheroLog("Buscando clubes del usuario con ID: " + usuarioId);
	    List<MiembroClubDto> misClubes = miembroClubFuncionalidades.obtenerMisClubesPorUsuario(usuarioId);
	    
	    if (!misClubes.isEmpty()) {
	        Logs.ficheroLog("Clubes encontrados para el usuario con ID: " + usuarioId);
	        return ResponseEntity.ok(misClubes);
	    } else {
	        Logs.ficheroLog("No se encontraron clubes para el usuario con ID: " + usuarioId);
	        return ResponseEntity.notFound().build();
	    }
	}

    /**
     * Devuelve todos los jugadores de un club con estadísticas
     */
	  @GetMapping("/jugadores/porClub/{clubId}")
	    public List<JugadorEstadisticaGlobalDto> listarJugadoresPorClub(@PathVariable Long clubId) {
	        return jugadorEstadisticaGlobalFuncionalidades.listarJugadoresPorClub(clubId);
	    }

	/**
	 * Metodo para guardar un nuevo miembro en el club.
	 * 
	 * @param miembroClubDto Datos del miembro a guardar.
	 * @return ResponseEntity con el miembro guardado o un error 409 si ya existe.
	 */
	  @PostMapping("/guardarMiembroClub")
	  public ResponseEntity<MiembroClubDto> guardarMiembroClub(
	          @RequestBody MiembroClubDto miembroClubDto,
	          Principal principal) {

	      Logs.ficheroLog("Solicitud para guardar miembro del club con datos: " + miembroClubDto.toString());

	      try {
	          // Delegar toda la lógica al servicio
	          MiembroClubDto miembroGuardado = miembroClubFuncionalidades.guardarMiembroClub(miembroClubDto, principal.getName());
	          Logs.ficheroLog("Miembro del club guardado exitosamente con ID: " + miembroGuardado.getIdMiembroClub());
	          return ResponseEntity.status(HttpStatus.CREATED).body(miembroGuardado);

	      } catch (RuntimeException e) {
	          Logs.ficheroLog("Error al guardar miembro del club: " + e.getMessage());
	          return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	      }
	  }



	/**
	 * Método para eliminar un miembro del club por su ID.
	 * Puede ser invocado por un usuario (para salir del club) o por un club (para eliminar a un miembro).
	 *
	 * @param idMiembroClub ID de la relación miembro-club a eliminar.
	 * @param usuarioId     (Opcional) ID del usuario que solicita la eliminación.
	 * @param clubId        (Opcional) ID del club que solicita la eliminación.
	 * @return true si la eliminación fue exitosa, false en caso contrario.
	 */
	  @DeleteMapping("/eliminarMiembroClub/{idMiembroClub}")
	  public boolean eliminarMiembroClub(
	          @PathVariable Long idMiembroClub,
	          @RequestParam(required = false) Long usuarioId,
	          @RequestParam(required = false) Long clubId,
	          Principal principal) {

	      Logs.ficheroLog("Solicitud para eliminar miembro del club con ID: " + idMiembroClub +
	                       ", usuarioId: " + usuarioId + ", clubId: " + clubId);

	      return miembroClubFuncionalidades.eliminarMiembroClub(idMiembroClub, usuarioId, clubId, principal.getName());
	  }




	/* METODOS CRUD DE LA TABLA TORNEO */

	/**
	 * Metodo para obtener todos los torneos o los torneos filtrados por instalación
	 * ID.
	 * 
	 * @param instalacionId (opcional) ID de la instalación para filtrar los
	 *                      torneos.
	 * @return Lista de torneos, filtrada si se proporciona instalacionId.
	 */
	@GetMapping("/torneo")
	public List<TorneoDto> obtenerTorneos(@RequestParam(value = "instalacionId", required = false) Long instalacionId) {
		if (instalacionId != null) {
			Logs.ficheroLog("Mostrando torneos para instalación ID: " + instalacionId);
			return torneoFuncionalidades.obtenerTorneosPorInstalacion(instalacionId);
		} else {
			Logs.ficheroLog("Mostrando todos los torneos");
			return torneoFuncionalidades.obtenerTodosLosTorneos();
		}
	}

	/**
	 * Metodo para obtener todos los torneos sin filtrado.
	 * 
	 * @return Lista de todos los torneos.
	 */
	@GetMapping("/mostrarTorneo")
	public List<TorneoDto> mostrarTorneos() {
		Logs.ficheroLog("Mostrando todos los torneos");
		return torneoFuncionalidades.obtenerTodosLosTorneos();
	}

	/**
	 * Metodo para guardar un nuevo torneo.
	 * 
	 * @param torneoDto Datos del torneo a guardar.
	 * @return El torneo guardado con su ID.
	 */
	@PostMapping("/guardarTorneo")
	public ResponseEntity<TorneoDto> guardarTorneo(
	        @RequestBody TorneoDto torneoDto,
	        Principal principal) {

	    try {
	        Logs.ficheroLog("Solicitud para guardar torneo");

	        String emailInstalacionLogueada = principal.getName();

	        TorneoDto torneoGuardado =
	                torneoFuncionalidades.guardarTorneo(
	                        torneoDto,
	                        emailInstalacionLogueada);

	        Logs.ficheroLog("Torneo guardado con ID: " + torneoGuardado.getIdTorneo());

	        return ResponseEntity.ok(torneoGuardado);

	    } catch (IllegalStateException e) {
	        Logs.ficheroLog("Error de autorización: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al guardar torneo: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	/**
	 * Metodo para eliminar un torneo por su ID.
	 * 
	 * @param idTorneo ID del torneo a eliminar.
	 * @return Resultado de la operación de eliminación.
	 */
	@DeleteMapping("/eliminarTorneo/{id_torneo}")
	public ResponseEntity<Boolean> borrarTorneo(@PathVariable("id_torneo") String idTorneo, Principal principal) {
	    Logs.ficheroLog("Solicitud para eliminar torneo con ID: " + idTorneo);

	    try {
	        // opcional: validar que el torneo pertenece a la instalación logueada usando principal.getName()
	        boolean resultado = torneoFuncionalidades.borrarTorneo(idTorneo);

	        Logs.ficheroLog("Resultado de eliminación del torneo con ID " + idTorneo + ": " + resultado);
	        return ResponseEntity.ok(resultado);

	    } catch (RuntimeException e) {
	        Logs.ficheroLog("Error al eliminar torneo con ID " + idTorneo + ": " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}


	/**
	 * Metodo para modificar los detalles de un torneo por su ID.
	 * 
	 * @param idTorneo  ID del torneo a modificar.
	 * @param torneoDto Datos actualizados del torneo.
	 * @return Resultado de la modificación.
	 */
	@PutMapping("/modificarTorneo/{id_torneo}")
	public ResponseEntity<?> modificarTorneo(
	        @PathVariable("id_torneo") String idTorneo,
	        @RequestBody TorneoDto torneoDto,
	        Principal principal) {  // Email de la instalación logueada

	    Logs.ficheroLog("Solicitud para modificar torneo con ID: " + idTorneo);

	    try {
	        String emailLogueado = principal.getName();
	        boolean resultado = torneoFuncionalidades.modificarTorneo(idTorneo, torneoDto, emailLogueado);

	        if (resultado) {
	            Logs.ficheroLog("Torneo modificado exitosamente con ID: " + idTorneo);
	            return ResponseEntity.ok(true);
	        } else {
	            Logs.ficheroLog("No se pudo modificar el torneo con ID: " + idTorneo);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
	        }
	    } catch (Exception e) {
	        Logs.ficheroLog("Error al modificar torneo: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}


	/* METODOS CRUD DE LA TABLA USUARIO */

	/**
	 * Método GET para obtener un usuario por su ID.
	 * 
	 * @param idUsuario ID del usuario a buscar.
	 * @return El usuario correspondiente al ID proporcionado, o un error 404 si no
	 *         se encuentra.
	 */
	@GetMapping("/usuarios/{id_usuario}")
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable("id_usuario") Long idUsuario) {
	    Logs.ficheroLog("Buscando usuario con ID: " + idUsuario);

	    UsuarioDto usuarioDto = usuarioFuncionalidades.obtenerUsuarioDtoPorId(idUsuario);
	    if (usuarioDto == null) {
	        Logs.ficheroLog("No se encontró usuario con ID: " + idUsuario);
	        return ResponseEntity.notFound().build();
	    }

	    // Obtener email del usuario logueado desde el JWT
	    String emailUsuarioLogueado = SecurityContextHolder.getContext().getAuthentication().getName();

	    // Validar que el usuario logueado solo acceda a su propio usuario
	    if (!usuarioDto.getEmailUsuario().equals(emailUsuarioLogueado)) {
	        Logs.ficheroLog("Intento de acceso denegado a usuario ID: " + idUsuario + " por usuario: " + emailUsuarioLogueado);
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }

	    Logs.ficheroLog("Usuario encontrado con ID: " + idUsuario);
	    return ResponseEntity.ok(usuarioDto);
	}



	/**
	 * Método GET para obtener todos los usuarios como una lista de DTOs.
	 * 
	 * @return Lista de todos los usuarios.
	 */
	@GetMapping("/mostrarUsuarios")
	public ResponseEntity<ArrayList<UsuarioDto>> mostrarUsuarios(Principal principal) {
	    try {
	        String emailLogueado = principal.getName();

	        ArrayList<UsuarioDto> usuarios = usuarioFuncionalidades.obtenerUsuariosDto(emailLogueado);

	        return ResponseEntity.ok(usuarios);

	    } catch (IllegalStateException e) {
	        Logs.ficheroLog("Intento no autorizado de mostrar usuarios: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al mostrar usuarios: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	/**
	 * Método POST para crear un nuevo usuario.
	 * 
	 * @param usuarioDto Datos del usuario a guardar.
	 * @return El usuario guardado con su ID.
	 */
	@PostMapping("/guardarUsuario")
	public ResponseEntity<?> guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
		Logs.ficheroLog("Solicitud para guardar usuario con datos: " + usuarioDto.toString());
		try {
			UsuarioEntidad usuarioEntidad = usuarioFuncionalidades.guardarUsuario(usuarioDto);
			Logs.ficheroLog("Usuario guardado exitosamente con ID: " + usuarioEntidad.getIdUsuario());
			return ResponseEntity.ok(usuarioFuncionalidades.mapearAUsuarioDto(usuarioEntidad));
		} catch (IllegalArgumentException e) {
			Logs.ficheroLog("Error al guardar usuario: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			Logs.ficheroLog("Error inesperado al guardar usuario: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
		}
	}

	/**
	 * Método DELETE para eliminar un usuario por su ID.
	 * 
	 * @param idUsuario ID del usuario a eliminar.
	 * @return Resultado de la operación de eliminación.
	 */
	@DeleteMapping("/eliminarUsuario/{id_usuario}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id_usuario") String idUsuario) {
	    Logs.ficheroLog("Solicitud para eliminar usuario con ID: " + idUsuario);

	    try {
	        boolean resultado = usuarioFuncionalidades.borrarUsuario(idUsuario);

	        if (resultado) {
	            Logs.ficheroLog("Usuario eliminado correctamente con ID: " + idUsuario);
	            return ResponseEntity.ok("Usuario eliminado correctamente.");
	        } else {
	            Logs.ficheroLog("No se pudo eliminar el usuario con ID: " + idUsuario);
	            return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                                 .body("No se puede eliminar este usuario o no existe.");
	        }

	    } catch (IllegalArgumentException e) {
	        Logs.ficheroLog("Error al eliminar usuario con ID " + idUsuario + ": " + e.getMessage());
	        return ResponseEntity.badRequest().body(e.getMessage());

	    } catch (Exception e) {
	        Logs.ficheroLog("Error inesperado al eliminar usuario con ID " + idUsuario);
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error interno del servidor");
	    }
	}


	/**
	 * Método PUT para actualizar un usuario por su ID.
	 * 
	 * @param idUsuario  ID del usuario a modificar.
	 * @param usuarioDto Datos actualizados del usuario.
	 * @return Resultado de la modificación.
	 */
	@PutMapping("/modificarUsuario/{id_Usuario}")
	public ResponseEntity<?> modificarUsuario(
	        @PathVariable("id_Usuario") String idUsuario,
	        @RequestBody UsuarioDto usuarioDto,
	        Principal principal) {

	    try {
	        String emailAdminLogueado = principal.getName();

	        boolean resultado = usuarioFuncionalidades.modificarUsuario(
	                idUsuario, usuarioDto, emailAdminLogueado);

	        if (resultado) {
	            return ResponseEntity.ok("Usuario modificado exitosamente");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
	        }

	    } catch (IllegalStateException e) {
	        Logs.ficheroLog("Error de permisos: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
	    } catch (Exception e) {
	        Logs.ficheroLog("Error al modificar usuario: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al modificar usuario");
	    }
	}

	/**
	 * Método PUT para actualizar el campo esPremium de un usuario.
	 * 
	 * @param idUsuario ID del usuario a actualizar.
	 * @return Mensaje de confirmación.
	 */
	@PutMapping("/modificarPremiumUsuario/{id_usuario}")
	public ResponseEntity<?> modificarPremiumUsuario(
	        @PathVariable("id_usuario") Long idUsuario,
	        Principal principal) {

	    String emailLogueado = principal.getName(); // email del jugador logueado
	    System.out.println("API llamada para modificarPremiumUsuario, id=" + idUsuario +
	                       ", email logueado=" + emailLogueado);

	    try {
	        // Llamamos al servicio, que hace toda la validación
	        boolean resultado = usuarioFuncionalidades.actualizarPremiumJugador(idUsuario, emailLogueado);

	        if (resultado) {
	            return ResponseEntity.ok("Usuario actualizado a Premium correctamente.");
	        } else {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                    .body("No tienes permisos para modificar este usuario o usuario no encontrado.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al actualizar el estado Premium del usuario.");
	    }
	}


	/* METODOS CRUD DE LA TABLA ACTA PARTIDO */

	/**
	 * Método GET para obtener un acta por su ID.
	 * 
	 * @param idActaPartido ID del acta a buscar.
	 * @return El acta correspondiente al ID proporcionado, o un error 404 si no se
	 *         encuentra.
	 */
	@GetMapping("/actaPartido/{id_ActaPartido}")
	public ResponseEntity<ActaPartidoDto> obtenerActaPartidoPorId(@PathVariable("id_ActaPartido") Long idActaPartido) {
		try {
			Logs.ficheroLog("Buscando acta del partido con ID: " + idActaPartido);
			ActaPartidoDto actaPartidoDto = actaPartidoFuncionalidades.obtenerActaPartidoDtoPorId(idActaPartido);
			if (actaPartidoDto != null) {
				Logs.ficheroLog("Acta encontrado con ID: " + idActaPartido);
				return ResponseEntity.ok(actaPartidoDto);
			} else {
				Logs.ficheroLog("No se encontró Acta con ID: " + idActaPartido);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener acta con ID " + idActaPartido + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Método GET para obtener todos los actas de partidos como una lista de DTOs.
	 * 
	 * @return Lista de todos los actas de los partidos.
	 */
	@GetMapping("/mostrarActasPartidos")
	public ArrayList<ActaPartidoDto> mostrarActasPartidos() {
		Logs.ficheroLog("Mostrando todos los actas de partidos.");
		return actaPartidoFuncionalidades.obtenerActasPartidosDto();
	}

	/**
	 * Metodo para guardar un nuevo acta de partido
	 * 
	 * @param actaPartidoDto Datos del acta del partido a guardar
	 * @return El acta de partido con su ID
	 */
	@PostMapping("/guardarActaPartido")
	public ResponseEntity<?> guardarActaPartido(@RequestBody ActaPartidoDto actaPartidoDto, Principal principal) {
	    Logs.ficheroLog("Solicitud para guardar Acta de partido con datos: " + actaPartidoDto.toString());

	    try {
	        // Llamamos al servicio y le pasamos la instalación logueada
	        ActaPartidoEntidad actaPartidoEntidad = actaPartidoFuncionalidades.guardarActaPartido(actaPartidoDto, principal);

	        Logs.ficheroLog("Acta del partido guardado exitosamente con ID: " + actaPartidoEntidad.getIdActaPartido());
	        return ResponseEntity.ok(actaPartidoFuncionalidades.mapearAActaPartidoDto(actaPartidoEntidad));

	    } catch (IllegalArgumentException | IllegalStateException e) {
	        Logs.ficheroLog("Error al guardar acta de partido: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        Logs.ficheroLog("Error inesperado al guardar acta de partido: " + e.toString());
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
	    }
	}


	

	/* METODOS CRUD DE LA TABLA EVENTO PARTIDO */

	/**
	 * Método GET para obtener un evento de partido por su ID.
	 * 
	 * @param idEventoPartido ID del evento a buscar.
	 * @return El evento correspondiente al ID proporcionado, o un error 404 si no
	 *         se encuentra.
	 */
	@GetMapping("/eventoPartido/{id_EventoPartido}")
	public ResponseEntity<?> obtenerEventoPartidoPorId(@PathVariable("id_EventoPartido") Long idEventoPartido) {
		try {
			Logs.ficheroLog("Buscando evento de partido con Id " + idEventoPartido);
			EventoPartidoDto eventoPartidoDto = eventoPartidoFuncionalidades.obtenerEventoPartidoPorId(idEventoPartido);
			if (eventoPartidoDto != null) {
				Logs.ficheroLog("Evento partido encontrado " + idEventoPartido);
				return ResponseEntity.ok(eventoPartidoDto);
			} else {
				Logs.ficheroLog("El evento de partido con ID " + idEventoPartido + " no se ha encontrado");
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener evento partido con ID " + idEventoPartido + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Método GET para obtener todos los eventos de partidos como una lista de DTOs.
	 * 
	 * @return Lista de todos los eventos de los partidos.
	 */
	@GetMapping("/mostrarEventosPartidos")
	public ResponseEntity<ArrayList<EventoPartidoDto>> mostrarEventosPartidos() {

		ArrayList<EventoPartidoDto> listaEventoPartido = eventoPartidoFuncionalidades.mostrarEventosPartidos();

		if (listaEventoPartido != null) {
			Logs.ficheroLog("Mostrando todos los actas de partidos.");
			return ResponseEntity.ok(listaEventoPartido);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/* METODOS CRUD DE LA TABLA JUGADOR ESTADISTICAS GLOBAL */

	/**
	 * Método GET para obtener un jugador estadística global por su ID.
	 * 
	 * @param id ID del jugador.
	 * @return DTO {@link JugadorEstadisticaGlobalDto} correspondiente, o 404 si no
	 *         existe.
	 */
	@GetMapping("/jugadorEstadisticaGlobal/{id}")
	public ResponseEntity<JugadorEstadisticaGlobalDto> obtenerJugadorEstadisticaGlobal(@PathVariable Long id) {
		try {
			Logs.ficheroLog("Buscando estadística global del jugador con ID " + id);
			JugadorEstadisticaGlobalDto dto = jugadorEstadisticaGlobalFuncionalidades
					.obtenerJugadorEstadisticaGlobalPorId(id);
			if (dto != null) {
				Logs.ficheroLog("Estadística global encontrada para jugador ID " + id);
				return ResponseEntity.ok(dto);
			} else {
				Logs.ficheroLog("No se encontró estadística global para jugador ID " + id);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener estadística global del jugador con ID " + id + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	 @GetMapping("/jugadorEstadisticaIndividualGlobal/{jugadorId}")
	    public ResponseEntity<JugadorEstadisticaGlobalDto> obtenerPorJugadorId(@PathVariable Long jugadorId) {
	        JugadorEstadisticaGlobalDto dto = jugadorEstadisticaGlobalFuncionalidades
	                .obtenerPorJugadorId(jugadorId);

	        if (dto != null) {
	            return ResponseEntity.ok(dto);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }


	/**
	 * Método GET para obtener todas las estadísticas globales de jugadores como una
	 * lista de DTOs.
	 * 
	 * @return Lista de todos los jugadores con estadísticas globales, o 404 si no
	 *         hay datos.
	 */
	@GetMapping("/mostrarJugadorEstadisticaGlobal")
	public ResponseEntity<ArrayList<JugadorEstadisticaGlobalDto>> mostrarJugadorEstadisticaGlobal() {
		try {
			Logs.ficheroLog("Solicitando todas las estadísticas globales de jugadores");
			ArrayList<JugadorEstadisticaGlobalDto> lista = jugadorEstadisticaGlobalFuncionalidades
					.mostrarJugadorEstadisticaGlobal();
			if (lista != null && !lista.isEmpty()) {
				Logs.ficheroLog("Mostrando todas las estadísticas globales de jugadores");
				return ResponseEntity.ok(lista);
			} else {
				Logs.ficheroLog("No existen estadísticas globales de jugadores");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error mostrando estadísticas globales de jugadores: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/jugadorEstadisticaGlobal/jugador/{idUsuario}")
	public ResponseEntity<JugadorEstadisticaGlobalDto> obtenerJugadorEstadisiticaGlobalPorJugadorId(@PathVariable Long idUsuario) {
	    try {
	        Logs.ficheroLog("Buscando estadística global por ID de jugador: " + idUsuario);

	        JugadorEstadisticaGlobalDto dto = jugadorEstadisticaGlobalFuncionalidades.obtenerPorJugadorId(idUsuario);

	        if (dto != null) {
	            Logs.ficheroLog("Estadística encontrada para jugador ID " + idUsuario);
	            return ResponseEntity.ok(dto);
	        } else {
	            Logs.ficheroLog("No se encontró estadística global para jugador ID " + idUsuario);
	            return ResponseEntity.notFound().build();
	        }

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al obtener estadística por ID de jugador " + idUsuario + ": " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	/* METODOS CRUD DE LA TABLA JUGADOR ESTADISTICAS TORNEO */

	/**
	 * Método GET para obtener un jugador estadística torneo por su ID.
	 * 
	 * @param id ID de la estadística del jugador en el torneo.
	 * @return DTO {@link JugadorEstadisticaTorneoDto} correspondiente, o 404 si no
	 *         existe.
	 */
	@GetMapping("/jugadorEstadisticaTorneo/{id}")
	public ResponseEntity<JugadorEstadisticaTorneoDto> obtenerJugadorEstadisticaTorneo(@PathVariable Long id) {
		try {
			Logs.ficheroLog("Buscando estadística de jugador en torneo con ID " + id);
			JugadorEstadisticaTorneoDto dto = jugadorEstadisticaTorneoFuncionalidades
					.obtenerJugadorEstadisticaTorneoPorId(id);
			if (dto != null) {
				Logs.ficheroLog("Estadística encontrada para jugador en torneo ID " + id);
				return ResponseEntity.ok(dto);
			} else {
				Logs.ficheroLog("No se encontró estadística para jugador en torneo con ID " + id);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener estadística de jugador en torneo con ID " + id + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Método GET para obtener todas las estadísticas de jugadores en torneos como
	 * una lista de DTOs.
	 * 
	 * @return Lista de todos los jugadores con estadísticas en torneos, o 404 si no
	 *         hay datos.
	 */
	@GetMapping("/mostrarJugadorEstadisticaTorneo")
	public ResponseEntity<ArrayList<JugadorEstadisticaTorneoDto>> mostrarJugadorEstadisticaTorneo() {
		try {
			Logs.ficheroLog("Solicitando todas las estadísticas de jugadores en torneos");
			ArrayList<JugadorEstadisticaTorneoDto> lista = jugadorEstadisticaTorneoFuncionalidades
					.mostrarJugadorEstadisticaTorneo();
			if (lista != null && !lista.isEmpty()) {
				Logs.ficheroLog("Mostrando todas las estadísticas de jugadores en torneos");
				return ResponseEntity.ok(lista);
			} else {
				Logs.ficheroLog("No existen estadísticas de jugadores en torneos");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error mostrando estadísticas de jugadores en torneos: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	 
	 
	 @GetMapping("/jugadorEstadisticaTorneo/jugador/{usuarioId}")
	 public ArrayList<JugadorEstadisticaTorneoDto> obtenerEstadisticasPorjugador(@PathVariable Long usuarioId) {
	     return jugadorEstadisticaTorneoFuncionalidades.obtenerEstadisticasDeTodosLosTorneos(usuarioId);
	 }
	

	/* METODOS CRUD DE LA TABLA CLUB ESTADISTICAS GLOBAL */

	/**
	 * Método GET para obtener la estadística global de un club por su ID.
	 *
	 * @param id ID del club estadística global.
	 * @return DTO {@link ClubEstadisticaGlobalDto} correspondiente, o 404 si no
	 *         existe.
	 */
	@GetMapping("/clubEstadisticaGlobal/{id}")
	public ResponseEntity<ClubEstadisticaGlobalDto> obtenerClubEstadisticaGlobal(@PathVariable Long id) {
		try {
			Logs.ficheroLog("Buscando estadística global del club con ID " + id);
			ClubEstadisticaGlobalDto dto = clubEstadisticaGlobalFuncionalidades.obtenerClubEstadisticaGlobalPorId(id);
			if (dto != null) {
				Logs.ficheroLog("Estadística global encontrada para el club ID " + id);
				return ResponseEntity.ok(dto);
			} else {
				Logs.ficheroLog("No se encontró estadística global para el club con ID " + id);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener estadística global del club con ID " + id + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/clubEstadisticaGlobal/club/{idClub}")
	public ResponseEntity<ClubEstadisticaGlobalDto> obtenerClubEstadisiticaGlobalPorClubId(@PathVariable Long idClub) {
	    try {
	        Logs.ficheroLog("Buscando estadística global por ID de club: " + idClub);

	        ClubEstadisticaGlobalDto dto = clubEstadisticaGlobalFuncionalidades.obtenerPorClubId(idClub);

	        if (dto != null) {
	            Logs.ficheroLog("Estadística encontrada para club ID " + idClub);
	            return ResponseEntity.ok(dto);
	        } else {
	            Logs.ficheroLog("No se encontró estadística global para club ID " + idClub);
	            return ResponseEntity.notFound().build();
	        }

	    } catch (Exception e) {
	        Logs.ficheroLog("Error al obtener estadística por ID de club " + idClub + ": " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	/**
	 * Método GET para obtener todas las estadísticas globales de los clubes.
	 *
	 * @return Lista de DTOs {@link ClubEstadisticaGlobalDto}, o 404 si no hay
	 *         datos.
	 */
	@GetMapping("/mostrarClubEstadisticaGlobal")
	public ResponseEntity<ArrayList<ClubEstadisticaGlobalDto>> mostrarClubEstadisticaGlobal() {
		try {
			Logs.ficheroLog("Solicitando todas las estadísticas globales de clubes");
			ArrayList<ClubEstadisticaGlobalDto> lista = clubEstadisticaGlobalFuncionalidades
					.mostrarClubEstadisticaGlobal();
			if (lista != null && !lista.isEmpty()) {
				Logs.ficheroLog("Mostrando todas las estadísticas globales de clubes");
				return ResponseEntity.ok(lista);
			} else {
				Logs.ficheroLog("No existen estadísticas globales de clubes");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error mostrando estadísticas globales de clubes: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/clubEstadisticaIndividualGlobal/{clubId}")
    public ResponseEntity<ClubEstadisticaGlobalDto> obtenerPorClubId(@PathVariable Long clubId) {
        ClubEstadisticaGlobalDto dto = clubEstadisticaGlobalFuncionalidades
                .obtenerPorClubId(clubId);

        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


	/* METODOS CRUD DE LA TABLA CLUB ESTADISTICAS TORNEO */

	/**
	 * Método GET para obtener la estadística de un club en un torneo por su ID.
	 * 
	 * @param idEstadistica ID de la estadística a buscar.
	 * @return DTO correspondiente o 404 si no existe.
	 */
	@GetMapping("/clubEstadisticaTorneo/{id}")
	public ResponseEntity<ClubEstadisticaTorneoDto> obtenerClubEstadisticaTorneo(@PathVariable Long id) {
		try {
			Logs.ficheroLog("Buscando estadística de club torneo con ID " + id);
			ClubEstadisticaTorneoDto dto = clubEstadisticaTorneoFuncionalidades.obtenerClubEstadisticaTorneoPorId(id);
			if (dto != null) {
				Logs.ficheroLog("Estadística encontrada para club torneo ID " + id);
				return ResponseEntity.ok(dto);
			} else {
				Logs.ficheroLog("No se encontró estadística de club torneo con ID " + id);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error obteniendo estadística de club torneo con ID " + id + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Método GET para obtener todas las estadísticas de clubes en torneos.
	 * 
	 * @return Lista de DTOs o 404 si no hay datos.
	 */
	@GetMapping("/mostrarClubEstadisticaTorneo")
	public ResponseEntity<ArrayList<ClubEstadisticaTorneoDto>> mostrarClubEstadisticaTorneo() {
		try {
			Logs.ficheroLog("Solicitando todas las estadísticas de clubes en torneos");
			ArrayList<ClubEstadisticaTorneoDto> lista = clubEstadisticaTorneoFuncionalidades
					.mostrarClubEstadisticaTorneo();
			if (lista != null && !lista.isEmpty()) {
				Logs.ficheroLog("Mostrando todas las estadísticas de clubes en torneos.");
				return ResponseEntity.ok(lista);
			} else {
				Logs.ficheroLog("No existen estadísticas de clubes en torneos.");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error mostrando todas las estadísticas de clubes en torneos: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

	 @GetMapping("/clubEstadisticaTorneo/club/{clubId}")
	 public ArrayList<ClubEstadisticaTorneoDto> obtenerEstadisticasPorClub(@PathVariable Long clubId) {
	     return clubEstadisticaTorneoFuncionalidades.obtenerEstadisticasDeTodosLosTorneos(clubId);
	 }

	/* METODOS CRUD DE LA TABLA PARTIDO TORNEO */

	/**
	 * Método GET para obtener un partido por su ID.
	 *
	 * @param idPartidoTorneo ID del partido a buscar.
	 * @return El partido correspondiente al ID proporcionado, o 404 si no se
	 *         encuentra.
	 */
	@GetMapping("/partidoTorneo/{idPartidoTorneo}")
	public ResponseEntity<PartidoTorneoDto> obtenerPartidoTorneoPorId(
			@PathVariable("idPartidoTorneo") Long idPartidoTorneo) {
		try {
			Logs.ficheroLog("Buscando partido con ID: " + idPartidoTorneo);
			PartidoTorneoDto dto = partidoTorneoFuncionalidades.obtenerPartidoTorneoDtoPorId(idPartidoTorneo);
			if (dto != null) {
				Logs.ficheroLog("Partido encontrado con ID: " + idPartidoTorneo);
				return ResponseEntity.ok(dto);
			} else {
				Logs.ficheroLog("No se encontró partido con ID: " + idPartidoTorneo);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener partido con ID " + idPartidoTorneo + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Método GET para obtener todos los partidos del torneo.
	 *
	 * @return Lista de DTOs {@link PartidoTorneoDto}, o 404 si no hay datos.
	 */
	@GetMapping("/mostrarPartidosTorneo")
	public ResponseEntity<ArrayList<PartidoTorneoDto>> mostrarPartidosTorneo() {
		Logs.ficheroLog("Mostrando todos los partidos del torneo.");
		ArrayList<PartidoTorneoDto> lista = partidoTorneoFuncionalidades.obtenerPartidosTorneoDto();
		return (lista != null && !lista.isEmpty()) ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
	}

	/**
	 * Método GET para obtener todos los partidos de un torneo específico.
	 *
	 * @param idTorneo ID del torneo.
	 * @return Lista de DTOs {@link PartidoTorneoDto}, o 404 si no hay datos.
	 */
	@GetMapping("/partidosPorTorneo/{idTorneo}")
	public ResponseEntity<ArrayList<PartidoTorneoDto>> obtenerPartidosPorTorneo(
			@PathVariable("idTorneo") Long idTorneo) {
		try {
			Logs.ficheroLog("Buscando partidos del torneo con ID: " + idTorneo);
			ArrayList<PartidoTorneoDto> lista = partidoTorneoFuncionalidades.obtenerPartidosPorTorneoDto(idTorneo);
			return (lista != null && !lista.isEmpty()) ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
		} catch (Exception e) {
			Logs.ficheroLog("Error al obtener partidos del torneo con ID " + idTorneo + ": " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Método POST para guardar un nuevo partido.
	 *
	 * @param partidoTorneoDto Datos del partido a guardar.
	 * @return El partido guardado en formato DTO.
	 */
	@PostMapping("/guardarPartidoTorneo")
	public ResponseEntity<?> guardarPartidoTorneo(@RequestBody PartidoTorneoDto partidoTorneoDto,
	                                              Principal principal) {
	    String emailInstalacionLogueada = principal.getName(); // email de la instalación logueada
	    Logs.ficheroLog("Solicitud para guardar partido: " + partidoTorneoDto.toString() +
	                     ", realizada por instalación: " + emailInstalacionLogueada);
	    try {
	        PartidoTorneoEntidad partidoEntidad = 
	                partidoTorneoFuncionalidades.guardarPartidoTorneo(partidoTorneoDto, emailInstalacionLogueada);
	        Logs.ficheroLog("Partido guardado exitosamente con ID: " + partidoEntidad.getIdPartidoTorneo());
	        return ResponseEntity.ok(partidoTorneoFuncionalidades.mapearAPartidoTorneoDto(partidoEntidad));
	    } catch (IllegalArgumentException e) {
	        Logs.ficheroLog("Error al guardar partido: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        Logs.ficheroLog("Error inesperado al guardar partido: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
	    }
	}


	/**
	 * Método PUT para actualizar los datos de un partido.
	 *
	 * @param idPartidoTorneo  ID del partido a modificar.
	 * @param partidoTorneoDto Datos actualizados.
	 * @return Resultado de la actualización.
	 */
	@PutMapping("/modificarPartidoTorneo/{idPartidoTorneo}")
	public ResponseEntity<?> modificarPartidoTorneo(
	        @PathVariable("idPartidoTorneo") Long idPartidoTorneo,
	        @RequestBody PartidoTorneoDto partidoTorneoDto,
	        Principal principal) { 

	    Logs.ficheroLog("Solicitud para modificar partido con ID: " + idPartidoTorneo);

	    try {
	        String emailLogueado = principal.getName(); // email de la instalación logueada
	        boolean resultado = partidoTorneoFuncionalidades.modificarPartidoTorneo(idPartidoTorneo, partidoTorneoDto, emailLogueado);

	        if (resultado) {
	            return ResponseEntity.ok("Partido modificado exitosamente");
	        } else {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                    .body("No tienes permisos para modificar este partido");
	        }
	    } catch (IllegalStateException e) {
	        Logs.ficheroLog("Error al modificar partido con ID " + idPartidoTorneo + ": " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        Logs.ficheroLog("Error al modificar partido con ID " + idPartidoTorneo + ": " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar partido");
	    }
	}




}
