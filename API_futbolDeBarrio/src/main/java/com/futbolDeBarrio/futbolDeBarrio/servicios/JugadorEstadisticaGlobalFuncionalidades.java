package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.JugadorEstadisticaGlobalDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Jugador estadística global
 */
@Service
public class JugadorEstadisticaGlobalFuncionalidades {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;

    @Autowired
    private JugadorEstadisticaGlobalInterfaz jugadorEstadisticaGlobalInterfaz;

    /**
     * Mapea una entidad JugadorEstadisticaGlobalEntidad a un DTO JugadorEstadisticaGlobalDto.
     * 
     * @param entidad Entidad de jugador estadística global.
     * @return DTO correspondiente con los datos de la entidad.
     */
    public JugadorEstadisticaGlobalDto mapearAJugadorEstadisticaGlobalDto(JugadorEstadisticaGlobalEntidad entidad) {
        JugadorEstadisticaGlobalDto dto = new JugadorEstadisticaGlobalDto();
        dto.setIdGlobal(entidad.getIdJugadorEstadisticaGlobal());
        dto.setJugadorGlobalId(entidad.getJugadorGlobalId().getIdUsuario());
        dto.setGolesGlobal(entidad.getGolesGlobal());
        dto.setAsistenciasGlobal(entidad.getAsistenciasGlobal());
        dto.setAmarillasGlobal(entidad.getAmarillasGlobal());
        dto.setRojasGlobal(entidad.getRojasGlobal());
        dto.setPartidosJugadosGlobal(entidad.getPartidosJugadosGlobal());
        dto.setMinutosJugadosGlobal(entidad.getMinutosJugadosGlobal());
        return dto;
    }

    /**
     * Obtiene las estadísticas globales de un jugador por su ID.
     * 
     * @param idEstadistica ID de la estadística global del jugador.
     * @return DTO con los datos del jugador, o null si no se encuentra.
     */
    public JugadorEstadisticaGlobalDto obtenerJugadorEstadisticaGlobalPorId(Long idEstadistica) {
        return jugadorEstadisticaGlobalInterfaz.findById(idEstadistica)
                .map(this::mapearAJugadorEstadisticaGlobalDto)
                .orElse(null);
    }

    /**
     * Muestra todas las estadísticas globales de todos los jugadores.
     * 
     * @return Lista de DTOs con los datos de cada jugador.
     */
    public ArrayList<JugadorEstadisticaGlobalDto> mostrarJugadorEstadisticaGlobal() {
        ArrayList<JugadorEstadisticaGlobalDto> listaDto = new ArrayList<>();
        jugadorEstadisticaGlobalInterfaz.findAll()
            .forEach(entidad -> listaDto.add(mapearAJugadorEstadisticaGlobalDto(entidad)));
        return listaDto;
    }

    /**
     * Actualiza las estadísticas globales de un jugador a partir de un evento de partido.
     * Si no existe la estadística global del jugador, se crea una nueva.
     * 
     * @param dto DTO con los datos a actualizar: goles, asistencias, tarjetas, minutos y partidos.
     */
    public void actualizarEstadisticasDesdeEvento(JugadorEstadisticaGlobalDto dto) {
    	Optional<JugadorEstadisticaGlobalEntidad> entidadOpt =
    		    jugadorEstadisticaGlobalInterfaz.findByJugadorGlobalId_IdUsuario(dto.getJugadorGlobalId());


        JugadorEstadisticaGlobalEntidad entidad;
        if (entidadOpt.isPresent()) {
            entidad = entidadOpt.get();
            entidad.setGolesGlobal(entidad.getGolesGlobal() + dto.getGolesGlobal());
            entidad.setAsistenciasGlobal(entidad.getAsistenciasGlobal() + dto.getAsistenciasGlobal());
            entidad.setAmarillasGlobal(entidad.getAmarillasGlobal() + dto.getAmarillasGlobal());
            entidad.setRojasGlobal(entidad.getRojasGlobal() + dto.getRojasGlobal());
            entidad.setPartidosJugadosGlobal(entidad.getPartidosJugadosGlobal() + dto.getPartidosJugadosGlobal());
            entidad.setMinutosJugadosGlobal(entidad.getMinutosJugadosGlobal() + dto.getMinutosJugadosGlobal());
        } else {
            entidad = new JugadorEstadisticaGlobalEntidad();
            entidad.setJugadorGlobalId(usuarioInterfaz.findById(dto.getJugadorGlobalId())
            	    .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado")));

            entidad.setGolesGlobal(dto.getGolesGlobal());
            entidad.setAsistenciasGlobal(dto.getAsistenciasGlobal());
            entidad.setAmarillasGlobal(dto.getAmarillasGlobal());
            entidad.setRojasGlobal(dto.getRojasGlobal());
            entidad.setPartidosJugadosGlobal(dto.getPartidosJugadosGlobal());
            entidad.setMinutosJugadosGlobal(dto.getMinutosJugadosGlobal());
        }
        jugadorEstadisticaGlobalInterfaz.save(entidad);
    }
}
