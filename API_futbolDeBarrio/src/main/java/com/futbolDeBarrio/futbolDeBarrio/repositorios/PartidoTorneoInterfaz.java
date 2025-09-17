package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad PartidoTorneoEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
public interface PartidoTorneoInterfaz extends JpaRepository<PartidoTorneoEntidad, Long>{


	  /**
   * Busca un partido torneo por su identificador único.
   *
   * @param idPartidoTorneo ID del partido.
   * @return Entidad {@link PartidoTorneoEntidad} correspondiente si existe.
   */
	PartidoTorneoEntidad findByIdPartidoTorneo(long idPartidoTorneo);
	
	/**
     * Busca todos los partidos de un torneo específico por su ID.
     *
     * @param idTorneo ID del torneo.
     * @return Lista de partidos correspondientes al torneo.
     */
    List<PartidoTorneoEntidad> findByTorneo_IdTorneo(Long idTorneo);
}
