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


}
