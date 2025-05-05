package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * TorneoDto.
 */
public interface TorneoInterfaz extends JpaRepository<TorneoEntidad, Long> {

	/**
	 * metodo que busca un torneo dado su id.
	 * 
	 * @param idTorneo
	 * @return
	 */
	TorneoEntidad findByIdTorneo(Long idTorneo);

	/**
	 * metodo que elimina un torneo dado su id.
	 * 
	 * @param idTorneo
	 * @return
	 */
	void deleteByIdTorneo(Long idTorneo);
	
	List<TorneoEntidad> findByInstalacion_IdInstalacion(Long instalacionId);

}
