package com.futbolDeBarrio.futbolDeBarrio.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.EquipoTorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.InstalacionFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.MiembroClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.TorneoFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.UsuarioFuncionalidades;

/*
 * Clase que se encarga de los metodos CRUD de la API
 */
@RestController
@RequestMapping("/api")
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

	/* METODOS CRUD DE LA TABLA CLUB */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla club
	 */
	
	    
	    
	
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
	    public ClubDto guardarClub(@RequestBody ClubDto club) {
	    	ClubEntidad clubEntidad = clubFuncionalidades.guardarClub(club);
	        return clubFuncionalidades.mapearAClubDto(clubEntidad);
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
	public boolean modificarClub(@PathVariable("id_club") String idClub, @RequestBody ClubEntidad clubDto) {
		return this.clubFuncionalidades.modificarClub(idClub, null);
	}
	

	

	/* METODOS CRUD DE LA TABLA EQUIPO_TORNEO */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla equipo_torneo
	 */
	@GetMapping("/equipoTorneo")
	public ArrayList<EquipoTorneoEntidad> mostrarEquiposTorneo() {
		return this.equipoTorneoFuncionalidades.listaEquipoTorneo();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla equipo_torneo
	 */
	@PostMapping("/guardarEquipoTorneo")
	public EquipoTorneoEntidad guardarEquipoTorneo(@RequestBody EquipoTorneoEntidad equipoTorneoDto) {
		return this.equipoTorneoFuncionalidades.guardarEquipoTorneo(equipoTorneoDto);
	}

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla equipo_torneo
	 */
	@DeleteMapping("/eliminarEquipoTorneo/{id_equipo_torneo}")
	public Boolean borrarEquipoTorneo(@PathVariable("id_equipo_torneo") String id_equipo_torneo) {
		return equipoTorneoFuncionalidades.borrarEquipoTorneo(id_equipo_torneo);
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla equipo_torneo
	 */
	@PutMapping("/modificarEquipoTorneo/{id_equipo_torneo}")
	public Boolean modificarEquipoTorneo(@PathVariable("id_equipo_torneo") String id_equipo_torneo,
			@RequestBody EquipoTorneoEntidad equipoTorneoDto) {
		return equipoTorneoFuncionalidades.modificarEquipoTorneo(id_equipo_torneo, equipoTorneoDto);
	}

	/* METODOS CRUD DE LA TABLA INSTALACION */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla instalacion
	 */
	@GetMapping("/instalacion")
	public ArrayList<InstalacionEntidad> mostrarInstalaciones() {
		return this.instalacionFuncionalidades.listaInstalacion();

	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla instalacion
	 */
	@PostMapping("/guardarInstalacion")
	public InstalacionEntidad guardarInstalacion(@RequestBody InstalacionEntidad instalacionDto) {
		return this.instalacionFuncionalidades.guardarInstalacion(instalacionDto);
	}

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla instalacion
	 */
	@DeleteMapping("/eliminarInstalacion/{id_instalacion}")
	public Boolean eliminarInstalacion(@PathVariable("id_instalacion") String id_instalacion) {
		return this.instalacionFuncionalidades.borrarInstalacion(id_instalacion);
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla instalacion
	 */
	@PutMapping("/modificarInstalacion/{id_instalacion}")
	public Boolean modificarInstalacion(@PathVariable("id_instalacion") String id_instalacion,
			@RequestBody InstalacionEntidad instalacionDto) {
		return this.instalacionFuncionalidades.modificarInstalacion(id_instalacion, instalacionDto);
	}

	/* METODOS CRUD DE LA TABLA MIEMBRO_CLUB */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla miembro_club
	 */
	@GetMapping("/miembroClub")
	public ArrayList<MiembroClubEntidad> mostrarMiembrosClubes() {
		return this.miembroClubFuncionalidades.listaMiembroClub();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla miembro_club
	 */
	@PostMapping("/guardarMiembroClub")
	public MiembroClubEntidad guardarMiembroClub(@RequestBody MiembroClubEntidad miembroClubDto) {
		return this.miembroClubFuncionalidades.guardarMiembroClub(miembroClubDto);
	}

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla miembro_club
	 */
	@DeleteMapping("eliminarMiembroClub/{id_miembro_club}")
	public Boolean eliminarMiembroClub(@PathVariable("id_miembro_club") String id_miembro_club) {
		return this.miembroClubFuncionalidades.eliminarMiembroClub(id_miembro_club);
	}
	
	/**
	 * Metodo que se encarga de realizar una peticion put a tabla miembro_club
	 */
	@PutMapping("modificarMiembroClub/{id_miembro_club}")
	public Boolean modificarMiembroClub(@PathVariable ("id_miembro_club") String id_miembro_club, @RequestBody MiembroClubEntidad miembroClubDto) {
		return this.miembroClubFuncionalidades.modificarMiembroClub(id_miembro_club, miembroClubDto);
	}

	/* METODOS CRUD DE LA TABLA TORNEO */
	
	 /**
     * Metodo que se encarga de realizar una peticion GET a la tabla torneo
     */
    @GetMapping("/torneo")
    public ArrayList<TorneoEntidad> mostrarTorneos() {
        return this.torneoFuncionalidades.listaTorneos();
    }

    /**
     * Metodo que se encarga de realizar una peticion POST a la tabla torneo
     */
    @PostMapping("/guardarTorneo")
    public TorneoEntidad guardarTorneo(@RequestBody TorneoEntidad torneoDto) {
        return this.torneoFuncionalidades.guardarTorneo(torneoDto);
    }

    /**
     * Metodo que se encarga de realizar una peticion DELETE a la tabla torneo
     */
    @DeleteMapping("/eliminarTorneo/{id_torneo}")
    public Boolean eliminarTorneo(@PathVariable("id_torneo") String idTorneo) {
        return this.torneoFuncionalidades.eliminarTorneo(idTorneo);
    }

    /**
     * Metodo que se encarga de realizar una peticion PUT a la tabla torneo
     */
    @PutMapping("/modificarTorneo/{id_torneo}")
    public Boolean modificarTorneo(@PathVariable("id_torneo") String idTorneo, @RequestBody TorneoEntidad torneoDto) {
        return this.torneoFuncionalidades.modificarTorneo(idTorneo, torneoDto);
    }
    
    
    /* METODOS CRUD DE LA TABLA USUARIO */
	
	 /**
    * Metodo que se encarga de realizar una peticion GET a la tabla usuario
    */
    @CrossOrigin(origins = "http://localhost:4200")
    
    
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
    public List<UsuarioDto> mostrarUsuarios() {
        // Devolver la lista de DTOs
        return usuarioFuncionalidades.obtenerUsuariosDto();
    }

    /**
     * Método POST para crear un nuevo usuario. Recibe un DTO de Usuario y lo guarda en la base de datos.
     */
    @PostMapping("/guardarUsuario")
    public UsuarioDto guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
        // Guardar el usuario y devolverlo como DTO
        UsuarioEntidad usuarioEntidad = usuarioFuncionalidades.guardarUsuario(usuarioDto);
        return usuarioFuncionalidades.mapearAUsuarioDto(usuarioEntidad);
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
     * Método PUT para actualizar un usuario por su ID. Recibe un DTO y lo actualiza.
     */
    @PutMapping("/modificarUsuario/{id_usuario}")
    public boolean modificarUsuario(@PathVariable("id_usuario") String idUsuario, @RequestBody UsuarioDto usuarioDto) {
        // Modificar el usuario y devolver si fue exitoso
        return usuarioFuncionalidades.modificarUsuario(idUsuario, usuarioDto);
    }
}
