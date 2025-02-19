package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad EquipoTorneoDto.
 */
public interface EquipoTorneoInterfaz extends JpaRepository<EquipoTorneoEntidad, Long> {

	/**
	 * metodo que elimina un equipo de torneo dado su id.
	 * @param idClub
	 * @return
	 */
	void deleteByIdEquipoTorneo(Long idEquipoTorneo);
	/**
	 * metodo que busca un equipo de torneo por su id.
	 * @param idClub
	 * @return
	 */
	EquipoTorneoEntidad findByIdEquipoTorneo(Long idEquipoTorneo);
}
