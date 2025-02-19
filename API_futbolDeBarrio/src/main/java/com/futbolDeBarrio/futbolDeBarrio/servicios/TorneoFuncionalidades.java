package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;

/**
 * Clase que se encarga de la lógica interna de los métodos CRUD para Torneos
 */
@Service
public class TorneoFuncionalidades {

	@Autowired
	TorneoInterfaz torneoInterfaz;
	
	/**
	 * Método que se encarga de mostrar listado de todos los torneos
	 */
	public ArrayList<TorneoEntidad> listaTorneos() {
		return (ArrayList<TorneoEntidad>) torneoInterfaz.findAll();
	}
	
	/**
	 * Método que se encarga de guardar un torneo
	 */
	public TorneoEntidad guardarTorneo(TorneoEntidad torneoDto) {
		return torneoInterfaz.save(torneoDto);
	}
	
	/**
	 * Método que se encarga de eliminar un torneo por su ID
	 */
	public boolean eliminarTorneo(String idTorneoString) {
		
		boolean estaBorrado = false;
		try {
			Long idTorneo = Long.parseLong(idTorneoString);
			boolean coincide = false;
			TorneoEntidad torneoDto = torneoInterfaz.findByIdTorneo(idTorneo);
			
			if(torneoDto == null) {
				System.out.println("El ID introducido no existe");
				estaBorrado = false;
			}
			
			if(idTorneo == torneoDto.getIdTorneo()) {
				coincide = true;
			}
			
			if(coincide) {
				System.out.println("El torneo se ha eliminado con éxito");
				torneoInterfaz.delete(torneoDto);
				estaBorrado = true;
			}
			
		} catch (NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es válido " + nfe.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
		return estaBorrado;
	}
	
	/**
	 * Método que se encarga de modificar los campos del torneo
	 */
	public boolean modificarTorneo(String idTorneoString, TorneoEntidad torneo) {
		
		boolean estaModificado = false;
		try {
			Long idTorneo = Long.parseLong(idTorneoString);
			TorneoEntidad torneoDto = torneoInterfaz.findByIdTorneo(idTorneo);
			
			if(torneo == null) {
				System.out.println("El ID introducido no existe");
				estaModificado = false;
			} else {
				torneoDto.setNombreTorneo(torneo.getNombreTorneo());
				torneoDto.setFechaInicioTorneo(torneo.getFechaInicioTorneo());
				torneoDto.setFechaFinTorneo(torneo.getFechaFinTorneo());
				torneoDto.setDescripcionTorneo(torneo.getDescripcionTorneo());
				torneoDto.setModalidad(torneo.getModalidad());
				torneoDto.setInstalcion(torneo.getInstalacion());
				
				torneoInterfaz.save(torneoDto);
				System.out.println("Se ha modificado el torneo con éxito");
				estaModificado = true;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es válido " + nfe.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
		return estaModificado;
	}
}

