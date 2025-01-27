package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.dtos.TorneoDto;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * TorneoDto.
 */
public interface TorneoInterfaz extends JpaRepository<TorneoDto, Long> {

	/**
	 * metodo que busca un torneo dado su id.
	 * 
	 * @param idTorneo
	 * @return
	 */
	TorneoDto findByIdTorneo(Long idTorneo);

	/**
	 * metodo que elimina un torneo dado su id.
	 * 
	 * @param idTorneo
	 * @return
	 */
	void deleteByIdTorneo(Long idTorneo);
}
