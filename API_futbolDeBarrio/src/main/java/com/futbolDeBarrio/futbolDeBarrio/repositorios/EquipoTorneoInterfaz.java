package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad EquipoTorneoEntidad.
 * Extiende JpaRepository para facilitar el acceso 
 */
@Repository
public interface EquipoTorneoInterfaz extends JpaRepository<EquipoTorneoEntidad, Long> {

    /**
     * Método que elimina un equipo de torneo dado su ID.
     * 
     * @param idEquipoTorneo El identificador único del equipo de torneo que se desea eliminar.
     */
    void deleteByIdEquipoTorneo(Long idEquipoTorneo);

    /**
     * Método que busca un equipo de torneo por su ID.
     * 
     * @param idEquipoTorneo El identificador único del equipo de torneo que se desea buscar.
     * @return El equipo de torneo correspondiente al ID proporcionado, si existe.
     */
    EquipoTorneoEntidad findByIdEquipoTorneo(Long idEquipoTorneo);
    
    /**
     * Verifica si existe un equipo inscrito en un torneo específico perteneciente a un club determinado.
     *
     * @param torneoId ID del torneo a verificar.
     * @param clubId ID del club a verificar.
     * @return true si el club ya tiene un equipo inscrito en el torneo, false en caso contrario.
     */
    boolean existsByTorneo_IdTorneoAndClub_IdClub(Long torneoId, Long clubId);

    Optional<EquipoTorneoEntidad> findByTorneo_IdTorneoAndClub_IdClub(Long torneoId, Long clubId);

    
    
    
}
