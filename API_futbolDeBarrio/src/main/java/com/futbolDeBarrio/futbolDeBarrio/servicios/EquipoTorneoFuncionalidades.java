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
public class EquipoTorneoFuncionalidades {

    @Autowired
    EquipoTorneoInterfaz equipoTorneoInterfaz;
    
    @Autowired
    TorneoInterfaz torneoInterfaz;
    
    @Autowired
    ClubInterfaz clubInterfaz;

    /**
     * Método que mapea de entidad a DTO
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
     * Método que mapea de DTO a entidad
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
     * Método que mapea una lista de entidades a DTOs
     */
    public List<EquipoTorneoDto> obtenerEquiposTorneoDto() {
        List<EquipoTorneoEntidad> equiposTorneoEntidad = (List<EquipoTorneoEntidad>) equipoTorneoInterfaz.findAll();
        List<EquipoTorneoDto> equiposTorneoDto = new ArrayList<>();
        for (EquipoTorneoEntidad equipoTorneo : equiposTorneoEntidad) {
            equiposTorneoDto.add(mapearAEquipoTorneoDto(equipoTorneo));
        }
        return equiposTorneoDto;
    }

    public EquipoTorneoDto obtenerEquipoTorneoDtoPorId(Long idEquipoTorneo) {
        EquipoTorneoEntidad equipoTorneoEntidad = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);
        return equipoTorneoEntidad != null ? mapearAEquipoTorneoDto(equipoTorneoEntidad) : null;
    }

    /**
     * Método para guardar un equipo en un torneo en la base de datos, recibiendo un DTO
     */
    public EquipoTorneoEntidad guardarEquipoTorneo(EquipoTorneoDto equipoTorneoDto) {
        // Mapeamos el DTO a una entidad
        EquipoTorneoEntidad equipoTorneoEntidad = mapearADtoAEntidad(equipoTorneoDto);

        // Guardamos la entidad en la base de datos
        return equipoTorneoInterfaz.save(equipoTorneoEntidad);
    }

    /**
     * Método que se encarga de modificar un equipo en un torneo en la base de datos
     */
    public boolean modificarEquipoTorneo(String id, EquipoTorneoDto equipoTorneoDto) {
        boolean esModificado = false;
        try {
            Long idEquipoTorneo = Long.parseLong(id);
            EquipoTorneoEntidad equipoTorneo = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);

            if (equipoTorneo == null) {
                System.out.println("El ID proporcionado no existe");
            } else {
                // Mapeamos el DTO a entidad
                equipoTorneo.setFechaInicioParticipacion(equipoTorneoDto.getFechaInicioParticipacion());
                equipoTorneo.setFechaFinParticipacion(equipoTorneoDto.getFechaFinParticipacion());
                equipoTorneo.setEstadoParticipacion(equipoTorneoDto.getEstadoParticipacion());
                
                Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(equipoTorneoDto.getTorneoId());
                torneoOpt.ifPresent(equipoTorneo::setTorneo);

                Optional<ClubEntidad> clubOpt = clubInterfaz.findById(equipoTorneoDto.getClubId());
                clubOpt.ifPresent(equipoTorneo::setClub);

                // Guardamos la entidad modificada en la base de datos
                equipoTorneoInterfaz.save(equipoTorneo);
                System.out.println("El equipo en el torneo ha sido modificado.");
                esModificado = true;
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error al modificar el equipo en el torneo. " + e.getMessage());
        }

        return esModificado;
    }

    /**
     * Método que borra un equipo en un torneo por su ID
     */
    public boolean borrarEquipoTorneo(String idEquipoTorneoString) {
        boolean estaBorrado = false;
        try {
            Long idEquipoTorneo = Long.parseLong(idEquipoTorneoString);
            EquipoTorneoEntidad equipoTorneoDto = equipoTorneoInterfaz.findByIdEquipoTorneo(idEquipoTorneo);

            if (equipoTorneoDto == null) {
                estaBorrado = false;
                System.out.println("El id del equipo en el torneo no existe");
            } else {
                equipoTorneoInterfaz.delete(equipoTorneoDto);
                estaBorrado = true;
                System.out.println("El equipo en el torneo ha sido borrado con éxito");
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error al borrar el equipo en el torneo. " + e.getMessage());
        }

        return estaBorrado;
    }
}
