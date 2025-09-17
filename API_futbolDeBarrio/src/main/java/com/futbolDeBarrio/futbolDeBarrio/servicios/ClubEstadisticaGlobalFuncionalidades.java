package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisiticaGlobalDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Club estadística global
 */
@Service
public class ClubEstadisticaGlobalFuncionalidades {

    @Autowired
    private ClubInterfaz clubInterfaz;

    @Autowired
    private ClubEstadisticaGlobalInterfaz clubEstadisticaGlobalInterfaz;

    /**
     * Mapea una entidad ClubEstadisticaGlobalEntidad a un DTO ClubEstadisiticaGlobalDto.
     * 
     * @param entidad Entidad de club estadística global.
     * @return DTO correspondiente con los datos de la entidad.
     */
    public ClubEstadisiticaGlobalDto mapearAClubEstadisticaGlobalDto(ClubEstadisticaGlobalEntidad entidad) {
        return new ClubEstadisiticaGlobalDto(entidad);
    }

    /**
     * Obtiene la estadística global de un club por su ID.
     * 
     * @param idEstadistica ID de la estadística global del club.
     * @return DTO con los datos del club, o null si no se encuentra.
     */
    public ClubEstadisiticaGlobalDto obtenerClubEstadisticaGlobalPorId(Long idEstadistica) {
        return clubEstadisticaGlobalInterfaz.findById(idEstadistica)
                .map(this::mapearAClubEstadisticaGlobalDto)
                .orElse(null);
    }

    /**
     * Muestra todas las estadísticas globales de todos los clubes.
     * 
     * @return Lista de DTOs con los datos de cada club.
     */
    public ArrayList<ClubEstadisiticaGlobalDto> mostrarClubEstadisticaGlobal() {
        ArrayList<ClubEstadisiticaGlobalDto> listaDto = new ArrayList<>();
        clubEstadisticaGlobalInterfaz.findAll()
            .forEach(entidad -> listaDto.add(mapearAClubEstadisticaGlobalDto(entidad)));
        return listaDto;
    }

    /**
     * Actualiza las estadísticas globales de un club a partir de un evento de partido.
     * Si no existe la estadística global del club, se crea una nueva.
     * 
     * @param dto DTO con los datos a actualizar: partidos, victorias, empates, derrotas, goles a favor y en contra.
     */
    public void actualizarEstadisticasDesdeEvento(ClubEstadisiticaGlobalDto dto) {
        Optional<ClubEstadisticaGlobalEntidad> entidadOpt =
                clubEstadisticaGlobalInterfaz.findByClubGlobal_IdClub(dto.getClubGlobalId());

        ClubEstadisticaGlobalEntidad entidad;
        if (entidadOpt.isPresent()) {
            entidad = entidadOpt.get();
            entidad.setPartidosJugadosGlobal(entidad.getPartidosJugadosGlobal() + dto.getPartidosJugadosGlobal());
            entidad.setGanadosGlobal(entidad.getGanadosGlobal() + dto.getGanadosGlobal());
            entidad.setEmpatadosGlobal(entidad.getEmpatadosGlobal() + dto.getEmpatadosGlobal());
            entidad.setPerdidosGlobal(entidad.getPerdidosGlobal() + dto.getPerdidosGlobal());
            entidad.setGolesFavorGlobal(entidad.getGolesFavorGlobal() + dto.getGolesFavorGlobal());
            entidad.setGolesContraGlobal(entidad.getGolesContraGlobal() + dto.getGolesContraGlobal());
        } else {
            entidad = new ClubEstadisticaGlobalEntidad();
            entidad.setClubGlobal(clubInterfaz.findById(dto.getClubGlobalId())
                    .orElseThrow(() -> new IllegalArgumentException("Club no encontrado")));
            entidad.setPartidosJugadosGlobal(dto.getPartidosJugadosGlobal());
            entidad.setGanadosGlobal(dto.getGanadosGlobal());
            entidad.setEmpatadosGlobal(dto.getEmpatadosGlobal());
            entidad.setPerdidosGlobal(dto.getPerdidosGlobal());
            entidad.setGolesFavorGlobal(dto.getGolesFavorGlobal());
            entidad.setGolesContraGlobal(dto.getGolesContraGlobal());
        }
        clubEstadisticaGlobalInterfaz.save(entidad);
    }
}
