package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisticaGlobalDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.MiembroClubInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Club estadística global
 */
@Service
public class ClubEstadisticaGlobalFuncionalidades {

 

    @Autowired
    private ClubEstadisticaGlobalInterfaz clubEstadisticaGlobalInterfaz;
    @Autowired
    private MiembroClubInterfaz miembroClubInterfaz;

    /**
     * Mapea una entidad ClubEstadisticaGlobalEntidad a un DTO ClubEstadisiticaGlobalDto.
     * 
     * @param entidad Entidad de club estadística global.
     * @return DTO correspondiente con los datos de la entidad.
     */
    public ClubEstadisticaGlobalDto mapearAClubEstadisticaGlobalDto(ClubEstadisticaGlobalEntidad entidad) {
        return new ClubEstadisticaGlobalDto(entidad);
    }

    /**
     * Obtiene la estadística global de un club por su ID.
     * 
     * @param idEstadistica ID de la estadística global del club.
     * @return DTO con los datos del club, o null si no se encuentra.
     */
    public ClubEstadisticaGlobalDto obtenerClubEstadisticaGlobalPorId(Long idEstadistica) {
        return clubEstadisticaGlobalInterfaz.findById(idEstadistica)
                .map(this::mapearAClubEstadisticaGlobalDto)
                .orElse(null);
    }

    /**
     * Muestra todas las estadísticas globales de todos los clubes.
     * 
     * @return Lista de DTOs con los datos de cada club.
     */
    public ArrayList<ClubEstadisticaGlobalDto> mostrarClubEstadisticaGlobal() {
        ArrayList<ClubEstadisticaGlobalDto> listaDto = new ArrayList<>();

        clubEstadisticaGlobalInterfaz.findAll().forEach(entidad -> {
            ClubEstadisticaGlobalDto dto = mapearAClubEstadisticaGlobalDto(entidad);

            int jugadoresActivos = (int) miembroClubInterfaz.findByClub_IdClub(entidad.getClubGlobal().getIdClub())
                .stream()
                .filter(m -> m.getFechaBajaUsuario() == null || "9999-01-01".equals(m.getFechaBajaUsuario()))
                .count();

            dto.setTotalJugadoresActivos(jugadoresActivos); 

            listaDto.add(dto);
        });

        return listaDto;
    }

    
    public ClubEstadisticaGlobalDto obtenerPorClubId(Long clubId) {
		return clubEstadisticaGlobalInterfaz.findByClubGlobal_IdClub(clubId)
				.map(this::mapearAClubEstadisticaGlobalDto).orElse(null);
	}


}
