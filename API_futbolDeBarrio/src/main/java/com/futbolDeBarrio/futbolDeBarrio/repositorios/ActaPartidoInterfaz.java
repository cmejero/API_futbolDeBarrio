package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad ActaPArtidoEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface ActaPartidoInterfaz extends JpaRepository<ActaPartidoEntidad, Long> {

	  /**
     * Busca un acta de partido por su identificador único.
     *
     * @param idActaPartido ID del acta.
     * @return Entidad {@link ActaPartidoEntidad} correspondiente si existe.
     */
    ActaPartidoEntidad findByIdActaPartido(long idActaPartido);
}
