package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Club estadística en torneo.
 */
@Service
public class ClubEstadisticaTorneoFuncionalidades {

    @Autowired
    private ClubInterfaz clubInterfaz;

    @Autowired
    private TorneoInterfaz torneoInterfaz;

    @Autowired
    private ClubEstadisticaTorneoInterfaz clubEstadisticaTorneoInterfaz;

    /**
     * Mapea una entidad ClubEstadisticaTorneoEntidad a un DTO ClubEstadisticaTorneoDto.
     * 
     * @param entidad Entidad de club estadística torneo.
     * @return DTO correspondiente con los datos de la entidad.
     */
    public ClubEstadisticaTorneoDto mapearAClubEstadisticaTorneoDto(ClubEstadisticaTorneoEntidad entidad) {
        return new ClubEstadisticaTorneoDto(entidad);
    }

    /**
     * Obtiene las estadísticas de un club en un torneo por su ID.
     * 
     * @param idEstadistica ID de la estadística del club en el torneo.
     * @return DTO con los datos del club, o null si no se encuentra.
     */
    public ClubEstadisticaTorneoDto obtenerClubEstadisticaTorneoPorId(Long idEstadistica) {
        return clubEstadisticaTorneoInterfaz.findById(idEstadistica)
                .map(this::mapearAClubEstadisticaTorneoDto)
                .orElse(null);
    }

    /**
     * Muestra todas las estadísticas de todos los clubes en torneos.
     * 
     * @return Lista de DTOs con los datos de cada club en cada torneo.
     */
    public ArrayList<ClubEstadisticaTorneoDto> mostrarClubEstadisticaTorneo() {
        ArrayList<ClubEstadisticaTorneoDto> listaDto = new ArrayList<>();
        clubEstadisticaTorneoInterfaz.findAll()
            .forEach(entidad -> listaDto.add(mapearAClubEstadisticaTorneoDto(entidad)));
        return listaDto;
    }

    /**
     * Actualiza las estadísticas de un club en un torneo a partir de un evento de partido.
     * Si no existe la estadística del club en el torneo, se crea una nueva.
     * 
     * @param dto DTO con los datos a actualizar: goles, partidos, victorias, empates, derrotas.
     */
    public void actualizarEstadisticasDesdeEvento(ClubEstadisticaTorneoDto dto) {
        Optional<ClubEstadisticaTorneoEntidad> entidadOpt =
                clubEstadisticaTorneoInterfaz.findByClub_IdClubAndTorneo_IdTorneo(dto.getClubId(), dto.getTorneoId());

        ClubEstadisticaTorneoEntidad entidad;
        if (entidadOpt.isPresent()) {
            entidad = entidadOpt.get();
            entidad.setPartidosJugados(entidad.getPartidosJugados() + dto.getPartidosJugados());
            entidad.setGanados(entidad.getGanados() + dto.getGanados());
            entidad.setEmpatados(entidad.getEmpatados() + dto.getEmpatados());
            entidad.setPerdidos(entidad.getPerdidos() + dto.getPerdidos());
            entidad.setGolesFavor(entidad.getGolesFavor() + dto.getGolesFavor());
            entidad.setGolesContra(entidad.getGolesContra() + dto.getGolesContra());
        } else {
            entidad = new ClubEstadisticaTorneoEntidad();
            entidad.setClub(clubInterfaz.findById(dto.getClubId())
                    .orElseThrow(() -> new IllegalArgumentException("Club no encontrado")));
            entidad.setTorneo(torneoInterfaz.findById(dto.getTorneoId())
                    .orElseThrow(() -> new IllegalArgumentException("Torneo no encontrado")));
            entidad.setPartidosJugados(dto.getPartidosJugados());
            entidad.setGanados(dto.getGanados());
            entidad.setEmpatados(dto.getEmpatados());
            entidad.setPerdidos(dto.getPerdidos());
            entidad.setGolesFavor(dto.getGolesFavor());
            entidad.setGolesContra(dto.getGolesContra());
        }
        clubEstadisticaTorneoInterfaz.save(entidad);
    }
}
