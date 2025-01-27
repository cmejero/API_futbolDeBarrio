package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.EquipoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.EquipoTorneoInterfaz;

/**
 * Clase que se encarga de la logica interna de los metodos CRUD
 */
@Service
public class EquipoTorneoFuncionalidades {

	@Autowired
	EquipoTorneoInterfaz equipoTorneoInterfaz;

	/**
	 * Metodo que se encarga de mostrar listado de los clubes de un torneo
	 */
	public ArrayList<EquipoTorneoDto> listaEquipoTorneo() {
		return (ArrayList<EquipoTorneoDto>) equipoTorneoInterfaz.findAll();
	}

	/**
	 * Metodo que se encarga de guardar un nuevo club en un torneo
	 */
	public EquipoTorneoDto guardarEquipoTorneo(EquipoTorneoDto equipoTorneoDto) {
		return equipoTorneoInterfaz.save(equipoTorneoDto);
	}

	
	/**
	 * Metodo que se encarga de eliminar un club en un torneo
	 */
	public Boolean borrarEquipoTorneo(String id) {
		try {
			Long idEquipoTorneo = Long.parseLong(id);
			boolean estaBorrado = false;
			boolean coincide = false;

			EquipoTorneoDto equipoTorneoDto = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);

			if (equipoTorneoDto == null) {
				System.out.println("El ID introducido es incorrecto");
				estaBorrado = false;
			}

			if (idEquipoTorneo == equipoTorneoDto.getIdEquipoTorneo()) {
				coincide = true;
			}

			if (coincide == true) {
				System.out.println("Ha sido eliminado con exito");
				equipoTorneoInterfaz.delete(equipoTorneoDto);
				estaBorrado = true;

			}
			return estaBorrado;

		} catch (NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es valido " + nfe.getMessage());
			return false;
		} catch (Exception e) {

			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
	}

	/**
	 * Metodo que se encarga de modificar los campos de un club en un torneo 
	 */
	public Boolean modificarEquipoTorneo(String id, EquipoTorneoDto equipoTorneoDto) {
	    boolean esModificado = false;

	    try {
	        Long idEquipoTorneo = Long.parseLong(id);
	        EquipoTorneoDto equipoTorneo = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);

	        if (equipoTorneo == null) {
	            System.out.println("El id introducido no existe");
	            esModificado = false;
	        } else {
	            equipoTorneo.setClub(equipoTorneoDto.getClub());
	            equipoTorneo.setEstadoParticipacion(equipoTorneoDto.getEstadoParticipacion());
	            equipoTorneo.setFechaInicioParticipacion(equipoTorneoDto.getFechaInicioParticipacion());
	            equipoTorneo.setFechaFinParticipacion(equipoTorneoDto.getFechaFinParticipacion());
	            equipoTorneo.setTorneo(equipoTorneoDto.getTorneo());

	            equipoTorneoInterfaz.save(equipoTorneo);
	            System.out.println("La modificación se ha realizado correctamente");
	            esModificado = true;
	        }
	    } catch (NumberFormatException nfe) {
	        System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
	    } catch (Exception e) {
	        System.out.println("Se ha producido un error al modificar el equipo del torneo. " + e.getMessage());
	    }

	    return esModificado;
	}
	
}


