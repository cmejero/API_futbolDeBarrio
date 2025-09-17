package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.EquipoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.EquipoTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;

@Service
/**
 * Clase que se encarga de la lógica de los metodos CRUD de EquipoTorneo
 */
public class EquipoTorneoFuncionalidades {

    @Autowired
    EquipoTorneoInterfaz equipoTorneoInterfaz;
    
    @Autowired
    TorneoInterfaz torneoInterfaz;
    
    @Autowired
    ClubInterfaz clubInterfaz;


    /**
     * Mapea una entidad EquipoTorneo a su correspondiente DTO.
     *
     * @param entidad entidad EquipoTorneo
     * @return DTO correspondiente
     */
    public EquipoTorneoDto mapearAEquipoTorneoDto(EquipoTorneoEntidad equipoTorneoEntidad) {
        EquipoTorneoDto equipoTorneoDto = new EquipoTorneoDto();
        equipoTorneoDto.setIdEquipoTorneo(equipoTorneoEntidad.getIdEquipoTorneo());
        equipoTorneoDto.setFechaInicioParticipacion(equipoTorneoEntidad.getFechaInicioParticipacion());
        equipoTorneoDto.setFechaFinParticipacion(equipoTorneoEntidad.getFechaFinParticipacion());
        equipoTorneoDto.setEstadoParticipacion(equipoTorneoEntidad.getEstadoParticipacion());
        equipoTorneoDto.setTorneoId(equipoTorneoEntidad.getTorneo().getIdTorneo());
        equipoTorneoDto.setClubId(equipoTorneoEntidad.getClub().getIdClub());
        return equipoTorneoDto;
    }

    /**
     * Mapea un DTO de EquipoTorneo a su entidad correspondiente.
     *
     * @param dto DTO de EquipoTorneo
     * @return entidad correspondiente
     */
    private EquipoTorneoEntidad mapearADtoAEntidad(EquipoTorneoDto equipoTorneoDto) {
        EquipoTorneoEntidad equipoTorneoEntidad = new EquipoTorneoEntidad();
        equipoTorneoEntidad.setIdEquipoTorneo(equipoTorneoDto.getIdEquipoTorneo());
        equipoTorneoEntidad.setFechaInicioParticipacion(equipoTorneoDto.getFechaInicioParticipacion());
        equipoTorneoEntidad.setFechaFinParticipacion(equipoTorneoDto.getFechaFinParticipacion());
        equipoTorneoEntidad.setEstadoParticipacion(equipoTorneoDto.getEstadoParticipacion());
        
        Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(equipoTorneoDto.getTorneoId());
        torneoOpt.ifPresent(equipoTorneoEntidad::setTorneo);

        Optional<ClubEntidad> clubOpt = clubInterfaz.findById(equipoTorneoDto.getClubId());
        clubOpt.ifPresent(equipoTorneoEntidad::setClub);
             
        return equipoTorneoEntidad;
    }

    /**
     * Obtiene una lista de equipos torneo como DTOs.
     *
     * @return lista de EquipoTorneoDto
     */
    public List<EquipoTorneoDto> obtenerEquiposTorneoDto() {
        List<EquipoTorneoEntidad> equiposTorneoEntidad = (List<EquipoTorneoEntidad>) equipoTorneoInterfaz.findAll();
        List<EquipoTorneoDto> equiposTorneoDto = new ArrayList<>();
        for (EquipoTorneoEntidad equipoTorneo : equiposTorneoEntidad) {
            equiposTorneoDto.add(mapearAEquipoTorneoDto(equipoTorneo));
        }
        return equiposTorneoDto;
    }

    /**
     * Busca un equipo torneo por ID.
     *
     * @param id ID del equipo torneo
     * @return DTO si existe, null si no
     */
    public EquipoTorneoDto obtenerEquipoTorneoDtoPorId(Long idEquipoTorneo) {
        EquipoTorneoEntidad equipoTorneoEntidad = equipoTorneoInterfaz.findById(idEquipoTorneo).orElse(null);
        return equipoTorneoEntidad != null ? mapearAEquipoTorneoDto(equipoTorneoEntidad) : null;
    }

    /**
     * Guarda un equipo en un torneo.
     *
     * @param dto DTO con los datos del equipo torneo
     * @return entidad guardada
     */
    public EquipoTorneoEntidad guardarEquipoTorneo(EquipoTorneoDto equipoTorneoDto) {
        // Verificar si el club ya está inscrito en este torneo
        if (estaInscritoEnTorneo(equipoTorneoDto.getTorneoId(), equipoTorneoDto.getClubId())) {
            throw new RuntimeException("El club ya está inscrito en este torneo");
        }

        EquipoTorneoEntidad equipoTorneoEntidad = mapearADtoAEntidad(equipoTorneoDto);
        return equipoTorneoInterfaz.save(equipoTorneoEntidad);
    }

    
    public boolean estaInscritoEnTorneo(Long torneoId, Long clubId) {
        return equipoTorneoInterfaz.existsByTorneo_IdTorneoAndClub_IdClub(torneoId, clubId);
    }


    /**
     * Modifica un equipo torneo existente.
     *
     * @param id  ID en String
     * @param dto DTO con datos a modificar
     * @return true si fue modificado, false en otro caso
     */
    public boolean modificarEquipoTorneo(String id, EquipoTorneoDto equipoTorneoDto) {
        boolean esModificado = false;
        try {
            Long idEquipoTorneo = Long.parseLong(id);
            EquipoTorneoEntidad equipoTorneo = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);

            if (equipoTorneo == null) {
                // System.out.println("El ID proporcionado no existe");
            } else {
                equipoTorneo.setFechaInicioParticipacion(equipoTorneoDto.getFechaInicioParticipacion());
                equipoTorneo.setFechaFinParticipacion(equipoTorneoDto.getFechaFinParticipacion());
                equipoTorneo.setEstadoParticipacion(equipoTorneoDto.getEstadoParticipacion());              
                Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(equipoTorneoDto.getTorneoId());
                torneoOpt.ifPresent(equipoTorneo::setTorneo);
                Optional<ClubEntidad> clubOpt = clubInterfaz.findById(equipoTorneoDto.getClubId());
                clubOpt.ifPresent(equipoTorneo::setClub);
                equipoTorneoInterfaz.save(equipoTorneo);
                // System.out.println("El equipo en el torneo ha sido modificado.");
                esModificado = true;
            }
        } catch (NumberFormatException nfe) {
            // System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            // System.out.println("Se ha producido un error al modificar el equipo en el torneo. " + e.getMessage());
        }

        return esModificado;
    }

    /**
     * Borra un equipo torneo por ID.
     *
     * @param idEquipoTorneoString ID como string
     * @return true si fue borrado, false en otro caso
     */
    public boolean borrarEquipoTorneo(String idEquipoTorneoString) {
        boolean estaBorrado = false;
        try {
            Long idEquipoTorneo = Long.parseLong(idEquipoTorneoString);
            EquipoTorneoEntidad equipoTorneoDto = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);
            if (equipoTorneoDto == null) {
                estaBorrado = false;
                // System.out.println("El id del equipo en el torneo no existe");
            } else {
                equipoTorneoInterfaz.delete(equipoTorneoDto);
                estaBorrado = true;
                // System.out.println("El equipo en el torneo ha sido borrado con éxito");
            }

        } catch (NumberFormatException nfe) {
            // System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            // System.out.println("Se ha producido un error al borrar el equipo en el torneo. " + e.getMessage());
        }
        return estaBorrado;
    }
}
