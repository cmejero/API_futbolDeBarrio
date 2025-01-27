package com.futbolDeBarrio.futbolDeBarrio.controladores;

import java.security.PublicKey;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.TorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
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
	@GetMapping("/club")
	public ArrayList<ClubDto> mostrarClubes() {
		return this.clubFuncionalidades.listaClubes();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla club
	 */
	@PostMapping("/guardarClub")
	public ClubDto guardarClub(@RequestBody ClubDto club) {
		return this.clubFuncionalidades.guardarClub(club);
	}

	/**
	 * Metodo que se encarga de realizar un peticion delete a tabla club
	 */
	@DeleteMapping("/eliminarClub/{id_club}")
	public Boolean borrarClub(@PathVariable("id_club") String id_club) {
		return this.clubFuncionalidades.borrarClub(id_club);
	}

	/**
	 * Metodo que se encarga de realizar una peticion put a tabla club
	 */
	@PutMapping("/modificarClub/{id_club}")
	public Boolean modificarCluBoolean(@PathVariable("id_club") String id_club, @RequestBody ClubDto clubDto) {
		return this.clubFuncionalidades.modificarClub(id_club, clubDto);
	}

	/* METODOS CRUD DE LA TABLA EQUIPO_TORNEO */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla equipo_torneo
	 */
	@GetMapping("/equipoTorneo")
	public ArrayList<EquipoTorneoDto> mostrarEquiposTorneo() {
		return this.equipoTorneoFuncionalidades.listaEquipoTorneo();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla equipo_torneo
	 */
	@PostMapping("/guardarEquipoTorneo")
	public EquipoTorneoDto guardarEquipoTorneo(@RequestBody EquipoTorneoDto equipoTorneoDto) {
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
			@RequestBody EquipoTorneoDto equipoTorneoDto) {
		return equipoTorneoFuncionalidades.modificarEquipoTorneo(id_equipo_torneo, equipoTorneoDto);
	}

	/* METODOS CRUD DE LA TABLA INSTALACION */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla instalacion
	 */
	@GetMapping("/instalacion")
	public ArrayList<InstalacionDto> mostrarInstalaciones() {
		return this.instalacionFuncionalidades.listaInstalacion();

	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla instalacion
	 */
	@PostMapping("/guardarInstalacion")
	public InstalacionDto guardarInstalacion(@RequestBody InstalacionDto instalacionDto) {
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
			@RequestBody InstalacionDto instalacionDto) {
		return this.instalacionFuncionalidades.modificarInstalacion(id_instalacion, instalacionDto);
	}

	/* METODOS CRUD DE LA TABLA MIEMBRO_CLUB */

	/**
	 * Metodo que se encarga de realizar una peticion get a tabla miembro_club
	 */
	@GetMapping("/miembroClub")
	public ArrayList<MiembroClubDto> mostrarMiembrosClubes() {
		return this.miembroClubFuncionalidades.listaMiembroClub();
	}

	/**
	 * Metodo que se encarga de realizar una peticion post a tabla miembro_club
	 */
	@PostMapping("/guardarMiembroClub")
	public MiembroClubDto guardarMiembroClub(@RequestBody MiembroClubDto miembroClubDto) {
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
	public Boolean modificarMiembroClub(@PathVariable ("id_miembro_club") String id_miembro_club, @RequestBody MiembroClubDto miembroClubDto) {
		return this.miembroClubFuncionalidades.modificarMiembroClub(id_miembro_club, miembroClubDto);
	}

	/* METODOS CRUD DE LA TABLA TORNEO */
	
	 /**
     * Metodo que se encarga de realizar una peticion GET a la tabla torneo
     */
    @GetMapping("/torneo")
    public ArrayList<TorneoDto> mostrarTorneos() {
        return this.torneoFuncionalidades.listaTorneos();
    }

    /**
     * Metodo que se encarga de realizar una peticion POST a la tabla torneo
     */
    @PostMapping("/guardarTorneo")
    public TorneoDto guardarTorneo(@RequestBody TorneoDto torneoDto) {
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
    public Boolean modificarTorneo(@PathVariable("id_torneo") String idTorneo, @RequestBody TorneoDto torneoDto) {
        return this.torneoFuncionalidades.modificarTorneo(idTorneo, torneoDto);
    }
    
    
    /* METODOS CRUD DE LA TABLA USUARIO */
	
	 /**
    * Metodo que se encarga de realizar una peticion GET a la tabla usuario
    */
   @GetMapping("/usuario")
   public ArrayList<UsuarioDto> mostrarUsuario() {
       return this.usuarioFuncionalidades.listaUsuarios();
   }

   /**
    * Metodo que se encarga de realizar una peticion POST a la tabla usuario
    */
   @PostMapping("/guardarUsuario")
   public UsuarioDto guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
       return this.usuarioFuncionalidades.guardarUsuario(usuarioDto);
   }

   /**
    * Metodo que se encarga de realizar una peticion DELETE a la tabla usuario
    */
   @DeleteMapping("/eliminarUsuario/{id_usuario}")
   public Boolean eliminarUsuario(@PathVariable("id_usuario") String idUsuario) {
       return this.usuarioFuncionalidades.borrarUsuario(idUsuario);
   }

   /**
    * Metodo que se encarga de realizar una peticion PUT a la tabla usuario
    */
   @PutMapping("/modificarUsuario/{id_usuario}")
   public Boolean modificarUsuario(@PathVariable("id_usuario") String idUsuario, @RequestBody UsuarioDto usuarioDto) {
       return this.usuarioFuncionalidades.modificarUsuario(idUsuario, usuarioDto);
   }
}
