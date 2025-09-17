package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.JugadorEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;


/**
 * Clase que se encarga de la lógica de los métodos CRUD de Jugador estadística
 * torneo
 */
@Service
public class JugadorEstadisticaTorneoFuncionalidades {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;

    @Autowired
    private TorneoInterfaz torneoInterfaz;

    @Autowired
    private JugadorEstadisticaTorneoInterfaz jugadorEstadisticaTorneoInterfaz;

    /**
     * Mapea una entidad JugadorEstadisticaTorneoEntidad a un DTO JugadorEstadisticaTorneoDto.
     * 
     * @param entidad Entidad de jugador estadística torneo.
     * @return DTO correspondiente con los datos de la entidad.
     */
    public JugadorEstadisticaTorneoDto mapearAJugadorEstadisticaTorneoDto(JugadorEstadisticaTorneoEntidad entidad) {
        JugadorEstadisticaTorneoDto dto = new JugadorEstadisticaTorneoDto();
        dto.setIdJugadorEstadisticaTorneo(entidad.getIdJugadorEstadisticaTorneo());
        dto.setJugadorId(entidad.getJugador().getIdUsuario());
        dto.setTorneoId(entidad.getTorneo().getIdTorneo());
        dto.setGolesTorneo(entidad.getGolesTorneo());
        dto.setAsistenciasTorneo(entidad.getAsistenciasTorneo());
        dto.setAmarillasTorneo(entidad.getAmarillasTorneo());
        dto.setRojasTorneo(entidad.getRojasTorneo());
        dto.setPartidosJugadosTorneo(entidad.getPartidosJugadosTorneo());
        dto.setMinutosJugadosTorneo(entidad.getMinutosJugadosTorneo());
        return dto;
    }

    /**
     * Obtiene las estadísticas de un jugador en un torneo por su ID.
     * 
     * @param idEstadistica ID de la estadística del jugador.
     * @return DTO con los datos del jugador, o null si no se encuentra.
     */
    public JugadorEstadisticaTorneoDto obtenerJugadorEstadisticaTorneoPorId(Long idEstadistica) {
        return jugadorEstadisticaTorneoInterfaz.findById(idEstadistica)
                .map(this::mapearAJugadorEstadisticaTorneoDto)
                .orElse(null);
    }

    /**
     * Muestra todas las estadísticas de todos los jugadores en torneos.
     * 
     * @return Lista de DTOs con los datos de cada jugador en cada torneo.
     */
    public ArrayList<JugadorEstadisticaTorneoDto> mostrarJugadorEstadisticaTorneo() {
        ArrayList<JugadorEstadisticaTorneoDto> listaDto = new ArrayList<>();
        jugadorEstadisticaTorneoInterfaz.findAll()
            .forEach(entidad -> listaDto.add(mapearAJugadorEstadisticaTorneoDto(entidad)));
        return listaDto;
    }

    /**
     * Actualiza las estadísticas de un jugador en un torneo a partir de un evento de partido.
     * Si no existe la estadística del jugador en el torneo, se crea una nueva.
     * 
     * @param dto DTO con los datos a actualizar: goles, asistencias, tarjetas, minutos y partidos.
     */
    public void actualizarEstadisticasDesdeEvento(JugadorEstadisticaTorneoDto dto) {
        Optional<JugadorEstadisticaTorneoEntidad> entidadOpt =
                jugadorEstadisticaTorneoInterfaz.findByJugadorIdUsuarioAndTorneoIdTorneo(dto.getJugadorId(), dto.getTorneoId());

        JugadorEstadisticaTorneoEntidad entidad;
        if (entidadOpt.isPresent()) {
            entidad = entidadOpt.get();
            entidad.setGolesTorneo(entidad.getGolesTorneo() + dto.getGolesTorneo());
            entidad.setAsistenciasTorneo(entidad.getAsistenciasTorneo() + dto.getAsistenciasTorneo());
            entidad.setAmarillasTorneo(entidad.getAmarillasTorneo() + dto.getAmarillasTorneo());
            entidad.setRojasTorneo(entidad.getRojasTorneo() + dto.getRojasTorneo());
            entidad.setPartidosJugadosTorneo(entidad.getPartidosJugadosTorneo() + dto.getPartidosJugadosTorneo());
            entidad.setMinutosJugadosTorneo(entidad.getMinutosJugadosTorneo() + dto.getMinutosJugadosTorneo());
        } else {
            entidad = new JugadorEstadisticaTorneoEntidad();
            entidad.setJugador(usuarioInterfaz.findById(dto.getJugadorId())
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado")));
            entidad.setTorneo(torneoInterfaz.findById(dto.getTorneoId())
                .orElseThrow(() -> new IllegalArgumentException("Torneo no encontrado")));
            entidad.setGolesTorneo(dto.getGolesTorneo());
            entidad.setAsistenciasTorneo(dto.getAsistenciasTorneo());
            entidad.setAmarillasTorneo(dto.getAmarillasTorneo());
            entidad.setRojasTorneo(dto.getRojasTorneo());
            entidad.setPartidosJugadosTorneo(dto.getPartidosJugadosTorneo());
            entidad.setMinutosJugadosTorneo(dto.getMinutosJugadosTorneo());
        }
        jugadorEstadisticaTorneoInterfaz.save(entidad);
    }
}
