package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad JugadorEstadisticaGlobalEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface JugadorEstadisticaGlobalInterfaz extends JpaRepository<JugadorEstadisticaGlobalEntidad, Long>{

	  /**
     * Busca estadistica global del jugador por su identificador único.
     *
     * @param idJugadorEstadisticaGlobal ID de la estadistica.
     * @return Entidad {@link JugadorEstadisticaGlobalEntidad} correspondiente si existe.
     */
	JugadorEstadisticaGlobalEntidad findByIdJugadorEstadisticaGlobal(long idJugadorEstadisticaGlobal);
	
	/**
	 * Busca la estadística global de un jugador a partir del ID de usuario del jugador.
	 *
	 * @param idUsuario ID del jugador (usuario).
	 * @return {@link Optional} con la entidad {@link JugadorEstadisticaGlobalEntidad} si existe,
	 *         o {@link Optional#empty()} si no se encuentra.
	 */
	Optional<JugadorEstadisticaGlobalEntidad> findByJugadorGlobalId_IdUsuario(Long idUsuario);
	
	

}
