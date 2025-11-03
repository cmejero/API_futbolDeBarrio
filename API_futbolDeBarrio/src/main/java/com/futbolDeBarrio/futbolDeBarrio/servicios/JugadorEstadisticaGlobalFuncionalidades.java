package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.JugadorEstadisticaGlobalDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Jugador estadística
 * global
 */
@Service
public class JugadorEstadisticaGlobalFuncionalidades {

	@Autowired
	private JugadorEstadisticaGlobalInterfaz jugadorEstadisticaGlobalInterfaz;

	/**
	 * Mapea una entidad JugadorEstadisticaGlobalEntidad a un DTO
	 * JugadorEstadisticaGlobalDto.
	 * 
	 * @param entidad Entidad de jugador estadística global.
	 * @return DTO correspondiente con los datos de la entidad.
	 */
	public JugadorEstadisticaGlobalDto mapearAJugadorEstadisticaGlobalDto(JugadorEstadisticaGlobalEntidad entidad) {
		JugadorEstadisticaGlobalDto dto = new JugadorEstadisticaGlobalDto();
		dto.setIdGlobal(entidad.getIdJugadorEstadisticaGlobal());
		dto.setJugadorGlobalId(entidad.getJugadorGlobalId().getIdUsuario());
		dto.setNombreJugador(entidad.getJugadorGlobalId().getNombreCompletoUsuario());
		dto.setAliasJugador(entidad.getJugadorGlobalId().getAliasUsuario());
		dto.setGolesGlobal(entidad.getGolesGlobal());
		dto.setAsistenciasGlobal(entidad.getAsistenciasGlobal());
		dto.setAmarillasGlobal(entidad.getAmarillasGlobal());
		dto.setRojasGlobal(entidad.getRojasGlobal());
		dto.setPartidosGanadosGlobal(entidad.getPartidosGanadosGlobal());
		dto.setPartidosPerdidosGlobal(entidad.getPartidosPerdidosGlobal());
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
		return jugadorEstadisticaGlobalInterfaz.findById(idEstadistica).map(this::mapearAJugadorEstadisticaGlobalDto)
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
	        .stream()
	        .filter(entidad -> entidad.getJugadorGlobalId().getRolUsuario() == RolUsuario.Jugador)
	        .map(this::mapearAJugadorEstadisticaGlobalDto)
	        .forEach(listaDto::add);

	    return listaDto;
	}

	

	public JugadorEstadisticaGlobalDto obtenerPorJugadorId(Long jugadorId) {
		return jugadorEstadisticaGlobalInterfaz.findByJugadorGlobalId_IdUsuario(jugadorId)
				.map(this::mapearAJugadorEstadisticaGlobalDto).orElse(null);
	}

}
