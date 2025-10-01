package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad ClubEstadisticaGlobalEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface ClubEstadisticaGlobalInterfaz extends JpaRepository<ClubEstadisticaGlobalEntidad, Long>{

	  /**
   * Busca estadistica global del club por su identificador único.
   *
   * @param idClubEstadisticaGlobal ID de la estadistica.
   * @return Entidad {@link ClubEstadisticaGlobalEntidad} correspondiente si existe.
   */
	ClubEstadisticaGlobalEntidad findByIdClubEstadisticaGlobal(long idClubEstadisticaGlobal);
	
	
	/**
	 * Busca la estadística global de un club a partir del ID de club 
	 *
	 * @param idClub ID del club.
	 * @return {@link Optional} con la entidad {@link ClubEstadisticaGlobalEntidad} si existe,
	 *         o {@link Optional#empty()} si no se encuentra.
	 */
	Optional<ClubEstadisticaGlobalEntidad> findByClubGlobal_IdClub(Long idClub);
	
	

}

