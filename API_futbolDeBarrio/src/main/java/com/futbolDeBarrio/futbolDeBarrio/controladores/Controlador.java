package com.futbolDeBarrio.futbolDeBarrio.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.EquipoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.TorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import com.futbolDeBarrio.futbolDeBarrio.servicios.AuthFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.EquipoTorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.InstalacionFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.LoginFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.MiembroClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.TorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.UsuarioFuncionalidades;

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
	AuthFuncionalidades authService;
	

	@CrossOrigin(origins = "http://localhost:8080")
	

	@PostMapping("/login")
	public ResponseEntity<RespuestaLoginDto> login(@RequestBody LoginDto loginDto) {
	    Logs.ficheroLog("Intento de login con email: " + loginDto.getEmail());
	    RespuestaLoginDto respuestaLogin = loginFuncionalidades.verificarCredenciales(loginDto);
	    if (respuestaLogin == null) {
	        Logs.ficheroLog("Login fallido para email: " + loginDto.getEmail());
	        return ResponseEntity.status(401).body(null);
	    }
	    Logs.ficheroLog("Login exitoso para email: " + loginDto.getEmail());
	    return ResponseEntity.ok(respuestaLogin);
	}

	/* METODOS CRUD DE LA TABLA CLUB */

	/**
	 * Método GET para obtener un usuario por su ID.
	 */
	@GetMapping("/club/{id_club}")
	public ResponseEntity<ClubDto> obtenerClubPorId(@PathVariable("id_club") Long idClub) {
	    Logs.ficheroLog("Buscando club con ID: " + idClub);
	    ClubDto clubDto = clubFuncionalidades.obtenerClubDtoPorId(idClub);
	    if (clubDto != null) {
	        Logs.ficheroLog("Club encontrado con ID: " + idClub);
	        return ResponseEntity.ok(clubDto);
	    } else {
	        Logs.ficheroLog("No se encontró club con ID: " + idClub);
	        return ResponseEntity.notFound().build();
	    }
	}

	/**
	 * Método GET para obtener todos los usuarios como una lista de DTOs.
	 */
	@GetMapping("/mostrarClubes")
	public List<ClubDto> mostrarClubes() {
	    Logs.ficheroLog("Mostrando todos los clubes");
	    return clubFuncionalidades.obtenerClubesDto();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla club
	 */
	@PostMapping("/guardarClub")
	public ResponseEntity<?> guardarClub(@RequestBody ClubDto clubDto) {
	    Logs.ficheroLog("Solicitud para guardar club con email: " + clubDto.getEmailClub());
	    try {
	        System.out.println("Datos recibidos: " + clubDto.getEmailClub() + " " + clubDto.getPasswordClub());
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

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla club
	 */
	@DeleteMapping("/eliminarClub/{id_club}")
	public boolean borrarClub(@PathVariable("id_club") String id_club) {
	    Logs.ficheroLog("Solicitud para eliminar club con ID: " + id_club);
	    boolean resultado = this.clubFuncionalidades.borrarClub(id_club);
	    Logs.ficheroLog("Resultado de eliminación del club con ID " + id_club + ": " + resultado);
	    return resultado;
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla club
	 */
	@PutMapping("/modificarClub/{id_club}")
	public boolean modificarClub(@PathVariable("id_club") String idClub, @RequestBody ClubDto clubDto) {
	    Logs.ficheroLog("Solicitud para modificar club con ID: " + idClub);
	    boolean resultado = this.clubFuncionalidades.modificarClub(idClub, clubDto);
	    Logs.ficheroLog("Resultado de modificación del club con ID " + idClub + ": " + resultado);
	    return resultado;
	}


	/* METODOS CRUD DE LA TABLA EQUIPO_TORNEO */

	@GetMapping("/equipoTorneo/{id_equipoTorneo}")
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
	public List<EquipoTorneoDto> mostrarEquipoTorneo() {
	    Logs.ficheroLog("Mostrando todos los equipos torneo");
	    return equipoTorneoFuncionalidades.obtenerEquiposTorneoDto();
	}

	@PostMapping("/guardarEquipoTorneo")
	public EquipoTorneoDto guardarEquipoTorneo(@RequestBody EquipoTorneoDto equipoTorneoDto) {
	    Logs.ficheroLog("Solicitud para guardar equipo torneo con datos: " + equipoTorneoDto.toString());
	    EquipoTorneoEntidad equipoTorneoEntidad = equipoTorneoFuncionalidades.guardarEquipoTorneo(equipoTorneoDto);
	    Logs.ficheroLog("Equipo torneo guardado exitosamente con ID: " + equipoTorneoEntidad.getIdEquipoTorneo());
	    return equipoTorneoFuncionalidades.mapearAEquipoTorneoDto(equipoTorneoEntidad);
	}

	@DeleteMapping("/eliminarEquipoTorneo/{id_equipoTorneo}")
	public boolean borrarEquipoTorneo(@PathVariable("id_equipoTorneo") String id_equipoTorneo) {
	    Logs.ficheroLog("Solicitud para eliminar equipo torneo con ID: " + id_equipoTorneo);
	    boolean resultado = this.equipoTorneoFuncionalidades.borrarEquipoTorneo(id_equipoTorneo);
	    Logs.ficheroLog("Resultado de eliminación del equipo torneo con ID " + id_equipoTorneo + ": " + resultado);
	    return resultado;
	}

	@PutMapping("/modificarEquipoTorneo/{id_equipoTorneo}")
	public boolean modificarEquipoTorneo(@PathVariable("id_equipoTorneo") String idEquipoTorneo,
	        @RequestBody EquipoTorneoDto equipoTorneoDto) {
	    Logs.ficheroLog("Solicitud para modificar equipo torneo con ID: " + idEquipoTorneo);
	    boolean resultado = this.equipoTorneoFuncionalidades.modificarEquipoTorneo(idEquipoTorneo, equipoTorneoDto);
	    Logs.ficheroLog("Resultado de modificación del equipo torneo con ID " + idEquipoTorneo + ": " + resultado);
	    return resultado;
	}

	/* METODOS CRUD DE LA TABLA INSTALACION */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla instalacion para obetener id
	 */
	@GetMapping("/instalacion/{id_instalacion}")
	public ResponseEntity<InstalacionDto> obtenerInstalacionPorId(@PathVariable("id_instalacion") Long idInstalacion) {
	    Logs.ficheroLog("Buscando instalación con ID: " + idInstalacion);
	    InstalacionDto instalacionDto = instalacionFuncionalidades.obtenerInstalacionDtoPorId(idInstalacion);
	    if (instalacionDto != null) {
	        Logs.ficheroLog("Instalación encontrada con ID: " + idInstalacion);
	        return ResponseEntity.ok(instalacionDto);
	    } else {
	        Logs.ficheroLog("No se encontró instalación con ID: " + idInstalacion);
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("mostrarInstalaciones")
	public List<InstalacionDto> mostrarInstalaciones() {
	    Logs.ficheroLog("Mostrando todas las instalaciones");
	    return instalacionFuncionalidades.obtenerInstalacionesDto();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla instalacion
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

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla instalacion
	 */
	@DeleteMapping("/eliminarInstalacion/{id_instalacion}")
	public boolean eliminarInstalacion(@PathVariable("id_instalacion") String id_instalacion) {
	    Logs.ficheroLog("Solicitud para eliminar instalación con ID: " + id_instalacion);
	    boolean resultado = this.instalacionFuncionalidades.borrarInstalacion(id_instalacion);
	    Logs.ficheroLog("Resultado de eliminación de instalación con ID " + id_instalacion + ": " + resultado);
	    return resultado;
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla instalacion
	 */
	@PutMapping("/modificarInstalacion/{id_instalacion}")
	public boolean modificarInstalacion(@PathVariable("id_instalacion") String id_instalacion,
	        @RequestBody InstalacionDto instalacionDto) {
	    Logs.ficheroLog("Solicitud para modificar instalación con ID: " + id_instalacion);
	    boolean resultado = this.instalacionFuncionalidades.modificarInstalacion(id_instalacion, instalacionDto);
	    Logs.ficheroLog("Resultado de modificación de instalación con ID " + id_instalacion + ": " + resultado);
	    return resultado;
	}


	/* METODOS CRUD DE LA TABLA MIEMBRO_CLUB */

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

	@GetMapping("mostrarMiembrosClub")
	public List<MiembroClubDto> mostrarMiembroClub() {
	    Logs.ficheroLog("Mostrando todos los miembros del club");
	    return miembroClubFuncionalidades.obtenerMiembrosClubDto();
	}

	@PostMapping("/guardarMiembroClub")
	public MiembroClubDto guardarMiembroClub(@RequestBody MiembroClubDto miembroClubDto) {
	    Logs.ficheroLog("Solicitud para guardar miembro del club con datos: " + miembroClubDto.toString());
	    MiembroClubEntidad miembroClubEntidad = miembroClubFuncionalidades.guardarMiembroClub(miembroClubDto);
	    Logs.ficheroLog("Miembro del club guardado exitosamente con ID: " + miembroClubEntidad.getIdMiembroClub());
	    return miembroClubFuncionalidades.mapearAMiembroClubDto(miembroClubEntidad);
	}

	@DeleteMapping("/eliminarMiembroClub/{id_miembroClub}")
	public boolean eliminarMiembroClub(@PathVariable("id_miembroClub") String id_miembroClub) {
	    Logs.ficheroLog("Solicitud para eliminar miembro del club con ID: " + id_miembroClub);
	    boolean resultado = this.miembroClubFuncionalidades.borrarMiembroClub(id_miembroClub);
	    Logs.ficheroLog("Resultado de eliminación del miembro del club con ID " + id_miembroClub + ": " + resultado);
	    return resultado;
	}

	@PutMapping("/modificarMiembroClub/{id_miembroClub}")
	public boolean modificarMiembroClub(@PathVariable("id_miembroClub") String id_miembroClub,
	        @RequestBody MiembroClubDto miembroClubDto) {
	    Logs.ficheroLog("Solicitud para modificar miembro del club con ID: " + id_miembroClub);
	    boolean resultado = this.miembroClubFuncionalidades.modificarMiembroClub(id_miembroClub, miembroClubDto);
	    Logs.ficheroLog("Resultado de modificación del miembro del club con ID " + id_miembroClub + ": " + resultado);
	    return resultado;
	}


	/* METODOS CRUD DE LA TABLA TORNEO */

	@GetMapping("/torneo/{id_torneo}")
	public ResponseEntity<TorneoDto> obtenerTorneoPorId(@PathVariable("id_torneo") Long idTorneo) {
	    Logs.ficheroLog("Buscando torneo con ID: " + idTorneo);
	    TorneoDto torneoDto = torneoFuncionalidades.obtenerTorneoPorId(idTorneo);
	    if (torneoDto != null) {
	        Logs.ficheroLog("Torneo encontrado con ID: " + idTorneo);
	        return ResponseEntity.ok(torneoDto);
	    } else {
	        Logs.ficheroLog("No se encontró torneo con ID: " + idTorneo);
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/mostrarTorneo")
	public List<TorneoDto> mostrarTorneos() {
	    Logs.ficheroLog("Mostrando todos los torneos");
	    return torneoFuncionalidades.obtenerTodosLosTorneos();
	}

	@PostMapping("/guardarTorneo")
	public TorneoDto guardarTorneo(@RequestBody TorneoDto torneoDto) {
	    Logs.ficheroLog("Solicitud para guardar torneo con datos: " + torneoDto.toString());
	    TorneoEntidad torneoEntidad = torneoFuncionalidades.guardarTorneo(torneoDto);
	    Logs.ficheroLog("Torneo guardado exitosamente con ID: " + torneoEntidad.getIdTorneo());
	    return torneoFuncionalidades.mapearATorneoDto(torneoEntidad);
	}

	@DeleteMapping("/eliminarTorneo/{id_torneo}")
	public boolean borrarTorneo(@PathVariable("id_torneo") String id_torneo) {
	    Logs.ficheroLog("Solicitud para eliminar torneo con ID: " + id_torneo);
	    boolean resultado = this.torneoFuncionalidades.borrarTorneo(id_torneo);
	    Logs.ficheroLog("Resultado de eliminación del torneo con ID " + id_torneo + ": " + resultado);
	    return resultado;
	}

	@PutMapping("/modificarTorneo/{id_torneo}")
	public boolean modificarTorneo(@PathVariable("id_torneo") String idTorneo, @RequestBody TorneoDto torneoDto) {
	    Logs.ficheroLog("Solicitud para modificar torneo con ID: " + idTorneo);
	    boolean resultado = this.torneoFuncionalidades.modificarTorneo(idTorneo, torneoDto);
	    Logs.ficheroLog("Resultado de modificación del torneo con ID " + idTorneo + ": " + resultado);
	    return resultado;
	}


	/* METODOS CRUD DE LA TABLA USUARIO */

	/**
	 * Método GET para obtener un usuario por su ID.
	 */
	@GetMapping("/usuarios/{id_usuario}")
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable("id_usuario") Long idUsuario) {
	    Logs.ficheroLog("Buscando usuario con ID: " + idUsuario);
	    UsuarioDto usuarioDto = usuarioFuncionalidades.obtenerUsuarioDtoPorId(idUsuario);
	    if (usuarioDto != null) {
	        Logs.ficheroLog("Usuario encontrado con ID: " + idUsuario);
	        return ResponseEntity.ok(usuarioDto);
	    } else {
	        Logs.ficheroLog("No se encontró usuario con ID: " + idUsuario);
	        return ResponseEntity.notFound().build();
	    }
	}

	/**
	 * Método GET para obtener todos los usuarios como una lista de DTOs.
	 */
	@GetMapping("/mostrarUsuarios")
	public ArrayList<UsuarioDto> mostrarUsuarios() {
	    Logs.ficheroLog("Mostrando todos los usuarios.");
	    return usuarioFuncionalidades.obtenerUsuariosDto();
	}

	/**
	 * Método POST para crear un nuevo usuario. Recibe un DTO de Usuario y lo guarda en la base de datos.
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
	 */
	@DeleteMapping("/eliminarUsuario/{id_usuario}")
	public boolean eliminarUsuario(@PathVariable("id_usuario") String idUsuario) {
	    Logs.ficheroLog("Solicitud para eliminar usuario con ID: " + idUsuario);
	    boolean resultado = usuarioFuncionalidades.borrarUsuario(idUsuario);
	    Logs.ficheroLog("Resultado de la eliminación del usuario con ID " + idUsuario + ": " + resultado);
	    return resultado;
	}

	/**
	 * Método PUT para actualizar un usuario por su ID. Recibe un DTO y lo actualiza.
	 */
	@PutMapping("/modificarUsuario/{id_Usuario}")
	public boolean modificarUsuario(@PathVariable("id_Usuario") String idUsuario, @RequestBody UsuarioDto usuarioDto) {
	    Logs.ficheroLog("Solicitud para modificar usuario con ID: " + idUsuario);
	    boolean resultado = usuarioFuncionalidades.modificarUsuario(idUsuario, usuarioDto);
	    Logs.ficheroLog("Resultado de la modificación del usuario con ID " + idUsuario + ": " + resultado);
	    return resultado;
	}

}