package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;

/**
 * Interfaz encargado de gestionar las operaciones CRUD sobre la entidad TorneoEntidad.
 * Extiende {@link JpaRepository} para proporcionar acceso a la base de datos.
 */
@Repository
public interface TorneoInterfaz extends JpaRepository<TorneoEntidad, Long> {

    /**
     * Busca un torneo por su identificador único.
     *
     * @param idTorneo ID del torneo.
     * @return Entidad {@link TorneoEntidad} correspondiente si existe.
     */
    TorneoEntidad findByIdTorneo(Long idTorneo);

    /**
     * Elimina un torneo por su identificador.
     *
     * @param idTorneo ID del torneo a eliminar.
     */
    void deleteByIdTorneo(Long idTorneo);

    /**
     * Obtiene una lista de torneos asociados a una instalación específica.
     *
     * @param instalacionId ID de la instalación.
     * @return Lista de torneos relacionados con la instalación.
     */
    List<TorneoEntidad> findByInstalacion_IdInstalacion(Long instalacionId);
}
