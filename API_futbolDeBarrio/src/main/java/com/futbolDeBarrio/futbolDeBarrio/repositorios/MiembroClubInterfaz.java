package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
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
    
    
    /**
     * Obtiene todos los miembros activos de un club específico.
     * 
     * @param clubId ID del club del cual se quieren obtener los miembros activos.
     * @return Lista de entidades {@link MiembroClubEntidad} que representan
     *         a los miembros activos del club.
     */
    List<MiembroClubEntidad> findByClubAndFechaBajaUsuarioIsNull(ClubEntidad  clubId);

    /**
     * Devuelve el miembro del club de un jugador que esté en un club
     * participando en un torneo específico.
     */
    default Optional<MiembroClubEntidad> findMiembroPorJugadorYTorneo(Long jugadorId, Long torneoId) {
        // Traemos todos los clubes del jugador
        List<MiembroClubEntidad> miembros = findByUsuario_IdUsuario(jugadorId);

        // Buscamos el club que tenga un equipo en el torneo indicado
        for (MiembroClubEntidad mc : miembros) {
            boolean participa = mc.getClub().getEquipoTorneo().stream()
                                  .anyMatch(et -> et.getTorneo().getIdTorneo() == torneoId);
            if (participa) {
                return Optional.of(mc);
            }
        }
        return Optional.empty();
    }
    
    /**
     *  Método estándar para traer todos los clubes de un jugador
     * @param usuarioId
     * @return
     */
    List<MiembroClubEntidad> findByUsuario_IdUsuario(Long usuarioId);

}
