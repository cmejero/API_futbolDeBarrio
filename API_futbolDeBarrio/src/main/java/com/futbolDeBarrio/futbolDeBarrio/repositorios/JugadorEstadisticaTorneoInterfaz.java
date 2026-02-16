package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;
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
    
    
    /**
     * Recupera todas las estadísticas de torneos asociadas a un jugador específico.
     *
     * @param jugadorId Identificador del jugador.
     * @return Lista de entidades de estadísticas de torneos del jugador.
     */
    List<JugadorEstadisticaTorneoEntidad> findByJugador_IdUsuario(Long jugadorId);

    
    /**
     * Recupera todas las estadísticas de jugadores asociadas a un torneo específico.
     *
     * @param torneoId Identificador del torneo.
     * @return Lista de entidades de estadísticas de jugadores en el torneo.
     */
    List<JugadorEstadisticaTorneoEntidad> findByTorneo_IdTorneo(Long torneoId);

}

