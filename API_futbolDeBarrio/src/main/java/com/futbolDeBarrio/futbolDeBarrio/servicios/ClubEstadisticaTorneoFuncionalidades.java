package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaTorneoInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Club estadística en torneo.
 */
@Service
public class ClubEstadisticaTorneoFuncionalidades {



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
    
    

	public ArrayList<ClubEstadisticaTorneoDto> obtenerEstadisticasDeTodosLosTorneos(Long clubId) {
	    ArrayList<ClubEstadisticaTorneoDto> listaDto = new ArrayList<>();
	    clubEstadisticaTorneoInterfaz.findByClub_IdClub(clubId)
	        .forEach(entidad -> listaDto.add(mapearAClubEstadisticaTorneoDto(entidad)));
	    return listaDto;
	}
}
