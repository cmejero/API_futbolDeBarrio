package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EventoPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.MiembroClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.PartidoTorneoInterfaz;

import jakarta.transaction.Transactional;

@Service
public class ActualizarEstadisticasFuncionalidades {

	@Autowired
	private ClubEstadisticaTorneoInterfaz clubEstadisticasTorneoInterfaz;
	@Autowired
	private ClubEstadisticaGlobalInterfaz clubEstadisticasGlobalInterfaz;
	@Autowired
	private JugadorEstadisticaTorneoInterfaz jugadorEstadisticasTorneoInterfaz;
	@Autowired
	private JugadorEstadisticaGlobalInterfaz jugadorEstadisticasGlobalInterfaz;
	@Autowired
	private MiembroClubInterfaz miembroClubInterfaz;
	@Autowired
	private PartidoTorneoInterfaz partidoTorneoInterfaz;

	/**
	 * Actualiza las estadísticas de clubes y jugadores después de cerrado un acta
	 * de partido.
	 *
	 * @param acta Acta del partido a procesar.
	 */
	@Transactional
	public void actualizarEstadisticas(ActaPartidoEntidad acta) {
		if (acta == null || !acta.estaCerrado())
			return;

		actualizarEstadisticasClub(acta.getEquipoLocal(), acta, true);
		actualizarEstadisticasClub(acta.getEquipoVisitante(), acta, false);

		actualizarEstadisticasJugadores(acta.getEquipoLocal(), acta, true);
		actualizarEstadisticasJugadores(acta.getEquipoVisitante(), acta, false);
	}

	/**
	 * Actualiza las estadísticas de un club para el torneo y de manera global.
	 *
	 * @param equipo  Equipo del club a actualizar.
	 * @param acta    Acta del partido.
	 * @param esLocal Indica si el equipo es local o visitante.
	 */
	private void actualizarEstadisticasClub(EquipoTorneoEntidad equipo, ActaPartidoEntidad acta, boolean esLocal) {
		int golesFavor = esLocal ? acta.getGolesLocal() : acta.getGolesVisitante();
		int golesContra = esLocal ? acta.getGolesVisitante() : acta.getGolesLocal();
		int penalesFavor = esLocal ? acta.getGolesPenaltisLocal() : acta.getGolesPenaltisVisitante();
		int penalesContra = esLocal ? acta.getGolesPenaltisVisitante() : acta.getGolesPenaltisLocal();

		int resultado;
		if (golesFavor > golesContra)
			resultado = 1;
		else if (golesFavor < golesContra)
			resultado = -1;
		else { // Empate en tiempo normal
			if (penalesFavor > penalesContra)
				resultado = 1;
			else if (penalesFavor < penalesContra)
				resultado = -1;
			else
				resultado = 0;
		}

		Long clubId = equipo.getClub().getIdClub();

		actualizarEstadisticasClubTorneo(equipo, acta, golesFavor, golesContra, resultado, clubId);
		actualizarEstadisticasClubGlobal(equipo, golesFavor, golesContra, resultado, clubId);
	}

	/**
	 * Actualiza las estadísticas de un club para un torneo específico.
	 *
	 * @param equipo      Equipo del club.
	 * @param acta        Acta del partido.
	 * @param golesFavor  Goles a favor del equipo.
	 * @param golesContra Goles en contra del equipo.
	 * @param clubId      ID del club.
	 */
	private void actualizarEstadisticasClubTorneo(EquipoTorneoEntidad equipo, ActaPartidoEntidad acta, int golesFavor,
			int golesContra, int resultado, Long clubId) {
		ClubEstadisticaTorneoEntidad torneoStats = clubEstadisticasTorneoInterfaz
				.findByClub_IdClubAndTorneo_IdTorneo(clubId, acta.getTorneo().getIdTorneo()).orElseGet(() -> {
					ClubEstadisticaTorneoEntidad nueva = new ClubEstadisticaTorneoEntidad();
					nueva.setClub(equipo.getClub());
					nueva.setTorneo(acta.getTorneo());
					nueva.setPartidosJugados(0);
					nueva.setGanados(0);
					nueva.setEmpatados(0);
					nueva.setPerdidos(0);
					nueva.setGolesFavor(0);
					nueva.setGolesContra(0);
					return nueva;
				});

		torneoStats.setPartidosJugados(torneoStats.getPartidosJugados() + 1);
		torneoStats.setGolesFavor(torneoStats.getGolesFavor() + golesFavor);
		torneoStats.setGolesContra(torneoStats.getGolesContra() + golesContra);

		if (resultado == 1)
			torneoStats.setGanados(torneoStats.getGanados() + 1);
		else if (resultado == -1)
			torneoStats.setPerdidos(torneoStats.getPerdidos() + 1);
		else
			torneoStats.setEmpatados(torneoStats.getEmpatados() + 1);

		clubEstadisticasTorneoInterfaz.save(torneoStats);
	}

	/**
	 * Actualiza las estadísticas globales de un club.
	 *
	 * @param equipo      Equipo del club.
	 * @param golesFavor  Goles a favor del equipo.
	 * @param golesContra Goles en contra del equipo.
	 * @param clubId      ID del club.
	 */
	private void actualizarEstadisticasClubGlobal(EquipoTorneoEntidad equipo, int golesFavor, int golesContra,
			int resultado, Long clubId) {
		ClubEstadisticaGlobalEntidad globalStats = clubEstadisticasGlobalInterfaz.findByClubGlobal_IdClub(clubId)
				.orElseGet(() -> {
					ClubEstadisticaGlobalEntidad nueva = new ClubEstadisticaGlobalEntidad();
					nueva.setClubGlobal(equipo.getClub());
					nueva.setPartidosJugadosGlobal(0);
					nueva.setGanadosGlobal(0);
					nueva.setEmpatadosGlobal(0);
					nueva.setPerdidosGlobal(0);
					nueva.setGolesFavorGlobal(0);
					nueva.setGolesContraGlobal(0);
					return nueva;
				});

		globalStats.setPartidosJugadosGlobal(globalStats.getPartidosJugadosGlobal() + 1);
		globalStats.setGolesFavorGlobal(globalStats.getGolesFavorGlobal() + golesFavor);
		globalStats.setGolesContraGlobal(globalStats.getGolesContraGlobal() + golesContra);

		if (resultado == 1)
			globalStats.setGanadosGlobal(globalStats.getGanadosGlobal() + 1);
		else if (resultado == -1)
			globalStats.setPerdidosGlobal(globalStats.getPerdidosGlobal() + 1);
		else
			globalStats.setEmpatadosGlobal(globalStats.getEmpatadosGlobal() + 1);

		clubEstadisticasGlobalInterfaz.save(globalStats);
	}

	/**
	 * Actualiza las estadísticas de todos los jugadores de un equipo.
	 *
	 * @param equipo  Equipo cuyos jugadores se actualizarán.
	 * @param acta    Acta del partido.
	 * @param esLocal Indica si el equipo es local o visitante.
	 */
	private void actualizarEstadisticasJugadores(EquipoTorneoEntidad equipo, ActaPartidoEntidad acta, boolean esLocal) {
		List<UsuarioEntidad> jugadores = miembroClubInterfaz.findByClub_IdClub(equipo.getClub().getIdClub()).stream()
				.map(MiembroClubEntidad::getUsuario).collect(Collectors.toList());

		int golesFavor = esLocal ? acta.getGolesLocal() : acta.getGolesVisitante();
		int golesContra = esLocal ? acta.getGolesVisitante() : acta.getGolesLocal();
		int penalesFavor = esLocal ? acta.getGolesPenaltisLocal() : acta.getGolesPenaltisVisitante();
		int penalesContra = esLocal ? acta.getGolesPenaltisVisitante() : acta.getGolesPenaltisLocal();

		int resultado;
		if (golesFavor > golesContra)
			resultado = 1;
		else if (golesFavor < golesContra)
			resultado = -1;
		else {
			if (penalesFavor > penalesContra)
				resultado = 1;
			else if (penalesFavor < penalesContra)
				resultado = -1;
			else
				resultado = 0;
		}

		Map<Long, List<EventoPartidoEntidad>> eventosPorJugador = acta.getEventoPartido().stream()
				.collect(Collectors.groupingBy(e -> e.getJugador().getIdUsuario()));

		for (UsuarioEntidad jugador : jugadores) {
			actualizarEstadisticasJugador(jugador, acta, eventosPorJugador, resultado);
		}
	}

	/**
	 * Actualiza las estadísticas individuales de un jugador para torneo y global.
	 *
	 * @param jugador           Jugador a actualizar.
	 * @param acta              Acta del partido.
	 * @param eventosPorJugador Mapa de eventos agrupados por jugador.
	 * @param resultado         Resultado del partido para el jugador (1=ganó,
	 *                          -1=perdió, 0=empate).
	 */
	private void actualizarEstadisticasJugador(UsuarioEntidad jugador, ActaPartidoEntidad acta,
			Map<Long, List<EventoPartidoEntidad>> eventosPorJugador, int resultado) {
		JugadorEstadisticaTorneoEntidad torneoStats = jugadorEstadisticasTorneoInterfaz
				.findByJugadorIdUsuarioAndTorneoIdTorneo(jugador.getIdUsuario(), acta.getTorneo().getIdTorneo())
				.orElseGet(JugadorEstadisticaTorneoEntidad::new);

		torneoStats.setJugador(jugador);
		torneoStats.setTorneo(acta.getTorneo());

		JugadorEstadisticaGlobalEntidad globalStats = jugadorEstadisticasGlobalInterfaz
				.findByJugadorGlobalId_IdUsuario(jugador.getIdUsuario())
				.orElseGet(JugadorEstadisticaGlobalEntidad::new);

		globalStats.setJugadorGlobalId(jugador);

		List<EventoPartidoEntidad> eventos = eventosPorJugador.getOrDefault(jugador.getIdUsuario(), List.of());
		procesarEventosJugador(torneoStats, globalStats, eventos);

		torneoStats.setPartidosJugadosTorneo(torneoStats.getPartidosJugadosTorneo() + 1);
		torneoStats.setMinutosJugadosTorneo(torneoStats.getMinutosJugadosTorneo() + 90);
		globalStats.setPartidosJugadosGlobal(globalStats.getPartidosJugadosGlobal() + 1);
		globalStats.setMinutosJugadosGlobal(globalStats.getMinutosJugadosGlobal() + 90);

		if (resultado == 1) {
			torneoStats.setPartidosGanadosTorneo(torneoStats.getPartidosGanadosTorneo() + 1);
			globalStats.setPartidosGanadosGlobal(globalStats.getPartidosGanadosGlobal() + 1);
		} else if (resultado == -1) {
			torneoStats.setPartidosPerdidosTorneo(torneoStats.getPartidosPerdidosTorneo() + 1);
			globalStats.setPartidosPerdidosGlobal(globalStats.getPartidosPerdidosGlobal() + 1);
		}

		jugadorEstadisticasTorneoInterfaz.save(torneoStats);
		jugadorEstadisticasGlobalInterfaz.save(globalStats);
	}

	/**
	 * Procesa los eventos del partido y actualiza goles y tarjetas de un jugador.
	 *
	 * @param torneoStats Estadísticas del torneo del jugador.
	 * @param globalStats Estadísticas globales del jugador.
	 * @param eventos     Lista de eventos ocurridos para el jugador.
	 */
	private void procesarEventosJugador(JugadorEstadisticaTorneoEntidad torneoStats,
			JugadorEstadisticaGlobalEntidad globalStats, List<EventoPartidoEntidad> eventos) {
		for (EventoPartidoEntidad evento : eventos) {
			switch (evento.getTipoEvento()) {
			case "Gol":
				torneoStats.setGolesTorneo(torneoStats.getGolesTorneo() + 1);
				globalStats.setGolesGlobal(globalStats.getGolesGlobal() + 1);
				break;
			case "Tarjeta Amarilla":
				torneoStats.setAmarillasTorneo(torneoStats.getAmarillasTorneo() + 1);
				globalStats.setAmarillasGlobal(globalStats.getAmarillasGlobal() + 1);
				break;
			case "Tarjeta Roja":
				torneoStats.setRojasTorneo(torneoStats.getRojasTorneo() + 1);
				globalStats.setRojasGlobal(globalStats.getRojasGlobal() + 1);
				break;
			}
		}
	}
	
	
	public void actualizarGolesPartidoTorneo(ActaPartidoEntidad acta) {
	    if (acta.getPartidoTorneo() != null) {
	        acta.getPartidoTorneo().setGolesLocal(acta.getGolesLocal());
	        acta.getPartidoTorneo().setGolesVisitante(acta.getGolesVisitante());
	        partidoTorneoInterfaz.save(acta.getPartidoTorneo());
	    }
	}

}
