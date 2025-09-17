package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;

/**
 * Interfaz que gestiona operaciones CRUD sobre la entidad MiembroClubEntidad.
 * Extiende {@link JpaRepository} para facilitar la interacción con la base de datos.
 */
@Repository
public interface MiembroClubInterfaz extends JpaRepository<MiembroClubEntidad, Long> {

    /**
     * Busca un miembro del club por su identificador único.
     *
     * @param idMiembroClub ID del miembro del club.
     * @return La entidad correspondiente si existe.
     */
    MiembroClubEntidad findByIdMiembroClub(Long idMiembroClub);

    /**
     * Elimina un miembro del club según su identificador.
     *
     * @param idMiembroClub ID del miembro del club a eliminar.
     */
    void deleteByIdMiembroClub(Long idMiembroClub);

    /**
     * Obtiene todos los miembros pertenecientes a un club específico.
     *
     * @param idClub ID del club.
     * @return Lista de miembros del club.
     */
    List<MiembroClubEntidad> findByClub_IdClub(Long idClub);

    /**
     * Busca un miembro del club por el ID de usuario y el ID del club.
     *
     * @param usuarioId ID del usuario.
     * @param clubId ID del club.
     * @return Optional que puede contener el miembro si existe.
     */
    Optional<MiembroClubEntidad> findByUsuario_IdUsuarioAndClub_IdClub(Long usuarioId, Long clubId);
}
