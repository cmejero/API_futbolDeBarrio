package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;

/**
 * Interfaz que gestiona las operaciones CRUD básicas sobre la entidad  InstalacionEntidad.
 * Extiende JpaRepository para facilitar la interacción con la base de datos.
 */
@Repository
public interface InstalacionInterfaz extends JpaRepository<InstalacionEntidad, Long> {

    /**
     * Busca una instalación por su identificador único.
     *
     * @param idInstalacion El ID de la instalación a buscar.
     * @return La entidad InstalacionEntidad correspondiente, si existe.
     */
    InstalacionEntidad findByIdInstalacion(Long idInstalacion);

    /**
     * Elimina una instalación por su identificador único.
     *
     * @param idInstalacion El ID de la instalación que se desea eliminar.
     */
    void deleteByIdInstalacion(Long idInstalacion);

    /**
     * Busca una instalación por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico de la instalación.
     * @return Un Optional que puede contener la instalación encontrada.
     */
    Optional<InstalacionEntidad> findByEmailInstalacion(String email);
    boolean existsByEmailInstalacion(String email);
}
