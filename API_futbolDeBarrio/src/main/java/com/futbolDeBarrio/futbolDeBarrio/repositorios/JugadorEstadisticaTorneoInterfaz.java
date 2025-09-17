package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaTorneoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad JugadorEstadisticaTorneoEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface JugadorEstadisticaTorneoInterfaz extends JpaRepository<JugadorEstadisticaTorneoEntidad, Long> {

	  /**
   * Busca un jugadaor estadistica torneo por su identificador único.
   *
   * @param idJugadorEstadisticaTorneo ID del jugadaor estadistica torneo.
   * @return Entidad {@link JugadorEstadisticaTorneoEntidad} correspondiente si existe.
   */
	JugadorEstadisticaTorneoEntidad findByIdJugadorEstadisticaTorneo(long idJugadorEstadisticaTorneo);
	

	  /**
 *  Este método permite buscar un registro por jugador y torneo
 *
 * @param jugadorId ID del jugador y torneoId ID del torneo.
 * 
 * @return Entidad {@link JugadorEstadisticaTorneoEntidad} correspondiente si existe.
 */
    Optional<JugadorEstadisticaTorneoEntidad> findByJugadorIdUsuarioAndTorneoIdTorneo(Long jugadorId, Long torneoId);
}

