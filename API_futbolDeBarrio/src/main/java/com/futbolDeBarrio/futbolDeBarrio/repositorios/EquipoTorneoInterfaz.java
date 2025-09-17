package com.futbolDeBarrio.futbolDeBarrio.repositorios;

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
    
    boolean existsByTorneo_IdTorneoAndClub_IdClub(Long torneoId, Long clubId);


    
    
    
}
