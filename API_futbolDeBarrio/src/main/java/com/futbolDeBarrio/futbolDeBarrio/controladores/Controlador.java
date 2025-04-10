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
	

	@CrossOrigin(origins = "http://localhost:4200")

	@PostMapping("/login")
	 public ResponseEntity<RespuestaLoginDto> login(@RequestBody LoginDto loginDto) {
        // Llamamos al servicio para verificar las credenciales y obtener el token
        RespuestaLoginDto respuestaLogin = loginFuncionalidades.verificarCredenciales(loginDto);

        // Si las credenciales son incorrectas, devolvemos un error 401 (Unauthorized)
        if (respuestaLogin == null) {
            return ResponseEntity.status(401).body(null);
        }

        // Si las credenciales son correctas, devolvemos el token en la respuesta
        return ResponseEntity.ok(respuestaLogin);
    }

	/* METODOS CRUD DE LA TABLA CLUB */

	/**
	 * Método GET para obtener un usuario por su ID.
	 */
	@GetMapping("/club/{id_club}")
	public ResponseEntity<ClubDto> obtenerClubPorId(@PathVariable("id_club") Long idClub) {
		ClubDto clubDto = clubFuncionalidades.obtenerClubDtoPorId(idClub);

		if (clubDto != null) {
			return ResponseEntity.ok(clubDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Método GET para obtener todos los usuarios como una lista de DTOs.
	 */
	@GetMapping("/mostrarClubes")
	public List<ClubDto> mostrarClubes() {
		// Devolver la lista de DTOs
		return clubFuncionalidades.obtenerClubesDto();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla club
	 */
	@PostMapping("/guardarClub")
	public ResponseEntity<ClubDto> guardarClub(@RequestBody ClubDto clubDto) {
	    try {
	        System.out.println("Datos recibidos: " + clubDto.getEmailClub() + " " + clubDto.getPasswordClub());
	        ClubEntidad clubEntidad = clubFuncionalidades.guardarClub(clubDto);
	        return ResponseEntity.ok(clubFuncionalidades.mapearAClubDto(clubEntidad));
	    } catch (IllegalArgumentException e) {
	        // Si el email ya está en uso, capturamos la excepción y retornamos un error
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (Exception e) {
	        // Captura de otras excepciones
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla club
	 */
	@DeleteMapping("/eliminarClub/{id_club}")
	public boolean borrarClub(@PathVariable("id_club") String id_club) {
		return this.clubFuncionalidades.borrarClub(id_club);
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla club
	 */
	@PutMapping("/modificarClub/{id_club}")
	public boolean modificarClub(@PathVariable("id_club") String idClub, @RequestBody ClubDto clubDto) {
		return this.clubFuncionalidades.modificarClub(idClub, clubDto);
	}

	/* METODOS CRUD DE LA TABLA EQUIPO_TORNEO */

	@GetMapping("/equipoTorneo/{id_equipoTorneo}")
	public ResponseEntity<EquipoTorneoDto> obtenerEquipoTorneoPorId(
			@PathVariable("id_equipoTorneo") Long idEquipoTorneo) {
		EquipoTorneoDto equipoTorneoDto = equipoTorneoFuncionalidades.obtenerEquipoTorneoDtoPorId(idEquipoTorneo);

		if (equipoTorneoDto != null) {
			return ResponseEntity.ok(equipoTorneoDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/mostrarEquipoTorneo")
	public List<EquipoTorneoDto> mostrarEquipoTorneo() {

		return equipoTorneoFuncionalidades.obtenerEquiposTorneoDto();
	}

	@PostMapping("/guardarEquipoTorneo")
	public EquipoTorneoDto guardarEquipoTorneo(@RequestBody EquipoTorneoDto equipoTorneoDto) {
		EquipoTorneoEntidad equipoTorneoEntidad = equipoTorneoFuncionalidades.guardarEquipoTorneo(equipoTorneoDto);
		return equipoTorneoFuncionalidades.mapearAEquipoTorneoDto(equipoTorneoEntidad);
	}

	@DeleteMapping("/eliminarEquipoTorneo/{id_equipoTorneo}")
	public boolean borrarEquipoTorneo(@PathVariable("id_equipoTorneo") String id_equipoTorneo) {
		return this.equipoTorneoFuncionalidades.borrarEquipoTorneo(id_equipoTorneo);
	}

	@PutMapping("/modificarEquipoTorneo/{id_equipoTorneo}")
	public boolean modificarEquipoTorneo(@PathVariable("id_equipoTorneo") String idEquipoTorneo,
			@RequestBody EquipoTorneoDto equipoTorneoDto) {
		return this.equipoTorneoFuncionalidades.modificarEquipoTorneo(idEquipoTorneo, equipoTorneoDto);
	}

	/* METODOS CRUD DE LA TABLA INSTALACION */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla instalacion para
	 * obetener id
	 */
	@GetMapping("/instalacion/{id_instalacion}")
	public ResponseEntity<InstalacionDto> obtenerInstalacionPorId(@PathVariable("id_instalacion") Long idInstalacion) {
		InstalacionDto instalacionDto = instalacionFuncionalidades.obtenerInstalacionDtoPorId(idInstalacion);
		if (instalacionDto != null) {
			return ResponseEntity.ok(instalacionDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("mostrarInstalaciones")
	public List<InstalacionDto> mostrarInstalaciones() {
		return instalacionFuncionalidades.obtenerInstalacionesDto();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla instalacion
	 */
	@PostMapping("/guardarInstalacion")
	public ResponseEntity<InstalacionDto> guardarInstalacion(@RequestBody InstalacionDto instalacionDto) {
	    try {
	        System.out.println("Datos recibidos: " + instalacionDto.getEmailInstalacion() + " " + instalacionDto.getPasswordInstalacion());
	        InstalacionEntidad instalacionEntidad = instalacionFuncionalidades.guardarInstalacion(instalacionDto);
	        return ResponseEntity.ok(instalacionFuncionalidades.mapearAInstalacionDto(instalacionEntidad));
	    } catch (IllegalArgumentException e) {
	        // Si el email ya está en uso, capturamos la excepción y retornamos un error
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (Exception e) {
	        // Captura de otras excepciones
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}



	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla instalacion
	 */
	@DeleteMapping("/eliminarInstalacion/{id_instalacion}")
	public boolean eliminarInstalacion(@PathVariable("id_instalacion") String id_instalacion) {
		return this.instalacionFuncionalidades.borrarInstalacion(id_instalacion);
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla instalacion
	 */
	@PutMapping("/modificarInstalacion/{id_instalacion}")
	public boolean modificarInstalacion(@PathVariable("id_instalacion") String id_instalacion,
			@RequestBody InstalacionDto instalacionDto) {
		return this.instalacionFuncionalidades.modificarInstalacion(id_instalacion, instalacionDto);
	}

	/* METODOS CRUD DE LA TABLA MIEMBRO_CLUB */

	@GetMapping("/miembroClub/{id_miembroClub}")
	public ResponseEntity<MiembroClubDto> obtenerMiembroClubPorId(@PathVariable("id_miembroClub") Long idMiembroClub) {
		MiembroClubDto miembroClubDto = miembroClubFuncionalidades.obtenerMiembroClubDtoPorId(idMiembroClub);
		if (miembroClubDto != null) {
			return ResponseEntity.ok(miembroClubDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("mostrarMiembrosClub")
	public List<MiembroClubDto> mostrarMiembroClub() {
		return miembroClubFuncionalidades.obtenerMiembrosClubDto();
	}

	@PostMapping("/guardarMiembroClub")
	public MiembroClubDto guardarMiembroClub(@RequestBody MiembroClubDto miembroClubDto) {
		MiembroClubEntidad miembroClubEntidad = miembroClubFuncionalidades.guardarMiembroClub(miembroClubDto);
		return miembroClubFuncionalidades.mapearAMiembroClubDto(miembroClubEntidad);
	}

	@DeleteMapping("/eliminarMiembroClub/{id_miembroClub}")
	public boolean eliminarMiembroClub(@PathVariable("id_miembroClub") String id_miembroClub) {
		return this.miembroClubFuncionalidades.borrarMiembroClub(id_miembroClub);
	}

	@PutMapping("/modificarMiembroClub/{id_miembroClub}")
	public boolean modificarMiembroClub(@PathVariable("id_miembroClub") String id_miembroClub,
			@RequestBody MiembroClubDto miembroClubDto) {
		return this.miembroClubFuncionalidades.modificarMiembroClub(id_miembroClub, miembroClubDto);
	}

	/* METODOS CRUD DE LA TABLA TORNEO */

	@GetMapping("/torneo/{id_torneo}")
	public ResponseEntity<TorneoDto> obtenerTorneoPorId(@PathVariable("id_torneo") Long idTorneo) {
		TorneoDto torneoDto = torneoFuncionalidades.obtenerTorneoPorId(idTorneo);

		if (torneoDto != null) {
			return ResponseEntity.ok(torneoDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/mostrarTorneo")
	public List<TorneoDto> mostrarTorneos() {

		return torneoFuncionalidades.obtenerTodosLosTorneos();
	}

	@PostMapping("/guardarTorneo")
	public TorneoDto guardarTorneo(@RequestBody TorneoDto torneoDto) {
		TorneoEntidad torneoEntidad = torneoFuncionalidades.guardarTorneo(torneoDto);
		return torneoFuncionalidades.mapearATorneoDto(torneoEntidad);
	}

	@DeleteMapping("/eliminarTorneo/{id_torneo}")
	public boolean borrarTorneo(@PathVariable("id_torneo") String id_torneo) {
		return this.torneoFuncionalidades.borrarTorneo(id_torneo);
	}

	@PutMapping("/modificarTorneo/{id_torneo}")
	public boolean modificarTorneo(@PathVariable("id_torneo") String idTorneo, @RequestBody TorneoDto torneoDto) {
		return this.torneoFuncionalidades.modificarTorneo(idTorneo, torneoDto);
	}

	/* METODOS CRUD DE LA TABLA USUARIO */

	/**
	 * Método GET para obtener un usuario por su ID.
	 */
	@GetMapping("/usuarios/{id_usuario}")
	public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable("id_usuario") Long idUsuario) {
		UsuarioDto usuarioDto = usuarioFuncionalidades.obtenerUsuarioDtoPorId(idUsuario);

		if (usuarioDto != null) {
			return ResponseEntity.ok(usuarioDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Método GET para obtener todos los usuarios como una lista de DTOs.
	 */
	@GetMapping("/mostrarUsuarios")
	public ArrayList<UsuarioDto> mostrarUsuarios() {
		// Devolver la lista de DTOs
		return usuarioFuncionalidades.obtenerUsuariosDto();
	}

	/**
	 * Método POST para crear un nuevo usuario. Recibe un DTO de Usuario y lo guarda
	 * en la base de datos.
	 */
	@PostMapping("/guardarUsuario")
	public ResponseEntity<UsuarioDto> guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
	    try {
	        System.out.println("Datos recibidos: " + usuarioDto.getEmailUsuario() + " " + usuarioDto.getPasswordUsuario());
	        UsuarioEntidad usuarioEntidad = usuarioFuncionalidades.guardarUsuario(usuarioDto);
	        return ResponseEntity.ok(usuarioFuncionalidades.mapearAUsuarioDto(usuarioEntidad));
	    } catch (IllegalArgumentException e) {
	        // Si el email ya está en uso, capturamos la excepción y retornamos un error
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (Exception e) {
	        // Captura de otras excepciones
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	/**
	 * Método DELETE para eliminar un usuario por su ID.
	 */
	@DeleteMapping("/eliminarUsuario/{id_usuario}")
	public boolean eliminarUsuario(@PathVariable("id_usuario") String idUsuario) {
		// Llamada a la funcionalidad de eliminación
		return usuarioFuncionalidades.borrarUsuario(idUsuario);
	}

	/**
	 * Método PUT para actualizar un usuario por su ID. Recibe un DTO y lo
	 * actualiza.
	 */
	@PutMapping("/modificarUsuario/{id_Usuario}")
	public boolean modificarUsuario(@PathVariable("id_Usuario") String idUsuario, @RequestBody UsuarioDto usuarioDto) {
		// Modificar el usuario y devolver si fue exitoso
		return usuarioFuncionalidades.modificarUsuario(idUsuario, usuarioDto);
	}
}