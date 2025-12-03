package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.JugadorEstadisticaTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaTorneoInterfaz;

/**
 * Clase que se encarga de la lógica de los métodos CRUD de Jugador estadística
 * torneo
 */
@Service
public class JugadorEstadisticaTorneoFuncionalidades {

	@Autowired
	private JugadorEstadisticaTorneoInterfaz jugadorEstadisticaTorneoInterfaz;

	/**
	 * Mapea una entidad JugadorEstadisticaTorneoEntidad a un DTO
	 * JugadorEstadisticaTorneoDto.
	 * 
	 * @param entidad Entidad de jugador estadística torneo.
	 * @return DTO correspondiente con los datos de la entidad.
	 */
	public JugadorEstadisticaTorneoDto mapearAJugadorEstadisticaTorneoDto(JugadorEstadisticaTorneoEntidad entidad) {
		JugadorEstadisticaTorneoDto dto = new JugadorEstadisticaTorneoDto();

		dto.setIdJugadorEstadisticaTorneo(entidad.getIdJugadorEstadisticaTorneo());
		dto.setJugadorId(entidad.getJugador().getIdUsuario());
		dto.setNombreJugador(entidad.getJugador().getNombreCompletoUsuario());
		dto.setTorneoId(entidad.getTorneo().getIdTorneo());
		dto.setNombreTorneo(entidad.getTorneo().getNombreTorneo());

		dto.setGolesTorneo(entidad.getGolesTorneo());
		dto.setAsistenciasTorneo(entidad.getAsistenciasTorneo());
		dto.setAmarillasTorneo(entidad.getAmarillasTorneo());
		dto.setRojasTorneo(entidad.getRojasTorneo());
		dto.setPartidosGanadosTorneo(entidad.getPartidosGanadosTorneo());
		dto.setPartidosPerdidosTorneo(entidad.getPartidosPerdidosTorneo());

		dto.setPartidosJugadosTorneo(entidad.getPartidosJugadosTorneo());
		dto.setMinutosJugadosTorneo(entidad.getMinutosJugadosTorneo());

		String nombreClub = "Sin club";

		if (entidad.getJugador().getMiembroClub() != null && !entidad.getJugador().getMiembroClub().isEmpty()) {
			nombreClub = entidad.getJugador().getMiembroClub().stream().map(MiembroClubEntidad::getClub) // obtenemos el
																											// club
					.filter(club -> club.getEquipoTorneo().stream()
							.anyMatch(et -> et.getTorneo().getIdTorneo() == entidad.getTorneo().getIdTorneo()))
					.map(ClubEntidad::getNombreClub).findFirst().orElse("Sin club");
		}

		dto.setNombreClub(nombreClub);

		return dto;
	}

	/**
	 * Obtiene las estadísticas de un jugador en un torneo por su ID.
	 * 
	 * @param idEstadistica ID de la estadística del jugador.
	 * @return DTO con los datos del jugador, o null si no se encuentra.
	 */
	public JugadorEstadisticaTorneoDto obtenerJugadorEstadisticaTorneoPorId(Long idEstadistica) {
		return jugadorEstadisticaTorneoInterfaz.findById(idEstadistica).map(this::mapearAJugadorEstadisticaTorneoDto)
				.orElse(null);
	}
	
	
	public ArrayList<JugadorEstadisticaTorneoDto> obtenerEstadisticasDeTodosLosTorneos(Long jugadorId) {
	    ArrayList<JugadorEstadisticaTorneoDto> listaDto = new ArrayList<>();
	    jugadorEstadisticaTorneoInterfaz.findByJugador_IdUsuario(jugadorId)
	        .forEach(entidad -> listaDto.add(mapearAJugadorEstadisticaTorneoDto(entidad)));
	    return listaDto;
	}
 

	/**
	 * Muestra todas las estadísticas de todos los jugadores en torneos.
	 * 
	 * @return Lista de DTOs con los datos de cada jugador en cada torneo.
	 */
	public ArrayList<JugadorEstadisticaTorneoDto> mostrarJugadorEstadisticaTorneo() {
	    ArrayList<JugadorEstadisticaTorneoDto> listaDto = new ArrayList<>();

	    // Filtramos solo jugadores
	    jugadorEstadisticaTorneoInterfaz.findAll()
	        .stream()
	        .filter(entidad -> entidad.getJugador().getRolUsuario() == RolUsuario.Jugador)
	        .map(this::mapearAJugadorEstadisticaTorneoDto)
	        .forEach(listaDto::add);

	    return listaDto;
	}

	


}
