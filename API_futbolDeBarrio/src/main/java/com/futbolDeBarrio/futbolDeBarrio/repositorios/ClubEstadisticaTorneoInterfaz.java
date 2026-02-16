package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaTorneoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad ClubEstadisticaTorneoEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface ClubEstadisticaTorneoInterfaz extends JpaRepository<ClubEstadisticaTorneoEntidad, Long>{

	  /**
 * Busca estadistica torneo del club por su identificador único.
 *
 * @param idClubEstadisticaTorneo ID de la estadistica.
 * @return Entidad {@link ClubEstadisticaTorneoEntidad} correspondiente si existe.
 */
	ClubEstadisticaTorneoEntidad findByIdClubEstadisticaTorneo(long idClubEstadisticaTorneo);
	
	 /**
     * Busca la estadística de un club en un torneo específico por el ID del club y el ID del torneo.
     *
     * @param clubId  ID del club.
     * @param torneoId ID del torneo.
     * @return Optional con la entidad ClubEstadisticaTorneoEntidad si existe, o vacío si no existe.
     */
    Optional<ClubEstadisticaTorneoEntidad> findByClub_IdClubAndTorneo_IdTorneo(Long clubId, Long torneoId);
    
    /**
     * Recupera todas las estadísticas de torneos asociadas a un club específico.
     *
     * @param clubId Identificador del club.
     * @return Lista de entidades de estadísticas de torneos del club.
     */
    List<ClubEstadisticaTorneoEntidad> findByClub_IdClub(Long clubId);
    
    
    /**
     * Recupera todas las estadísticas de clubes asociadas a un torneo específico.
     *
     * @param torneoId Identificador del torneo.
     * @return Lista de entidades de estadísticas de clubes en el torneo.
     */
    List<ClubEstadisticaTorneoEntidad> findByTorneo_IdTorneo(Long torneoId);


}