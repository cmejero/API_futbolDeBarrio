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
		ClubEstadisticaTorneoEntidad torneoEstadisticas = clubEstadisticasTorneoInterfaz
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

		torneoEstadisticas.setPartidosJugados(torneoEstadisticas.getPartidosJugados() + 1);
		torneoEstadisticas.setGolesFavor(torneoEstadisticas.getGolesFavor() + golesFavor);
		torneoEstadisticas.setGolesContra(torneoEstadisticas.getGolesContra() + golesContra);

		if (resultado == 1)
			torneoEstadisticas.setGanados(torneoEstadisticas.getGanados() + 1);
		else if (resultado == -1)
			torneoEstadisticas.setPerdidos(torneoEstadisticas.getPerdidos() + 1);
		else
			torneoEstadisticas.setEmpatados(torneoEstadisticas.getEmpatados() + 1);

		clubEstadisticasTorneoInterfaz.save(torneoEstadisticas);
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
		ClubEstadisticaGlobalEntidad globalEstadisticas = clubEstadisticasGlobalInterfaz.findByClubGlobal_IdClub(clubId)
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

		globalEstadisticas.setPartidosJugadosGlobal(globalEstadisticas.getPartidosJugadosGlobal() + 1);
		globalEstadisticas.setGolesFavorGlobal(globalEstadisticas.getGolesFavorGlobal() + golesFavor);
		globalEstadisticas.setGolesContraGlobal(globalEstadisticas.getGolesContraGlobal() + golesContra);

		if (resultado == 1)
			globalEstadisticas.setGanadosGlobal(globalEstadisticas.getGanadosGlobal() + 1);
		else if (resultado == -1)
			globalEstadisticas.setPerdidosGlobal(globalEstadisticas.getPerdidosGlobal() + 1);
		else
			globalEstadisticas.setEmpatadosGlobal(globalEstadisticas.getEmpatadosGlobal() + 1);

		clubEstadisticasGlobalInterfaz.save(globalEstadisticas);
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
		JugadorEstadisticaTorneoEntidad torneoEstadisticas = jugadorEstadisticasTorneoInterfaz
				.findByJugadorIdUsuarioAndTorneoIdTorneo(jugador.getIdUsuario(), acta.getTorneo().getIdTorneo())
				.orElseGet(JugadorEstadisticaTorneoEntidad::new);

		torneoEstadisticas.setJugador(jugador);
		torneoEstadisticas.setTorneo(acta.getTorneo());

		JugadorEstadisticaGlobalEntidad globalEstadisticas = jugadorEstadisticasGlobalInterfaz
				.findByJugadorGlobalId_IdUsuario(jugador.getIdUsuario())
				.orElseGet(JugadorEstadisticaGlobalEntidad::new);

		globalEstadisticas.setJugadorGlobalId(jugador);

		List<EventoPartidoEntidad> eventos = eventosPorJugador.getOrDefault(jugador.getIdUsuario(), List.of());
		procesarEventosJugador(torneoEstadisticas, globalEstadisticas, eventos);

		torneoEstadisticas.setPartidosJugadosTorneo(torneoEstadisticas.getPartidosJugadosTorneo() + 1);
		torneoEstadisticas.setMinutosJugadosTorneo(torneoEstadisticas.getMinutosJugadosTorneo() + 90);
		globalEstadisticas.setPartidosJugadosGlobal(globalEstadisticas.getPartidosJugadosGlobal() + 1);
		globalEstadisticas.setMinutosJugadosGlobal(globalEstadisticas.getMinutosJugadosGlobal() + 90);

		if (resultado == 1) {
			torneoEstadisticas.setPartidosGanadosTorneo(torneoEstadisticas.getPartidosGanadosTorneo() + 1);
			globalEstadisticas.setPartidosGanadosGlobal(globalEstadisticas.getPartidosGanadosGlobal() + 1);
		} else if (resultado == -1) {
			torneoEstadisticas.setPartidosPerdidosTorneo(torneoEstadisticas.getPartidosPerdidosTorneo() + 1);
			globalEstadisticas.setPartidosPerdidosGlobal(globalEstadisticas.getPartidosPerdidosGlobal() + 1);
		}

		jugadorEstadisticasTorneoInterfaz.save(torneoEstadisticas);
		jugadorEstadisticasGlobalInterfaz.save(globalEstadisticas);
	}

	/**
	 * Procesa los eventos del partido y actualiza goles y tarjetas de un jugador.
	 *
	 * @param torneoEstadisticas Estadísticas del torneo del jugador.
	 * @param globalEstadisticas Estadísticas globales del jugador.
	 * @param eventos     Lista de eventos ocurridos para el jugador.
	 */
	private void procesarEventosJugador(JugadorEstadisticaTorneoEntidad torneoEstadisticas,
			JugadorEstadisticaGlobalEntidad globalEstadisticas, List<EventoPartidoEntidad> eventos) {
		for (EventoPartidoEntidad evento : eventos) {
			switch (evento.getTipoEvento()) {
			case "Gol":
				torneoEstadisticas.setGolesTorneo(torneoEstadisticas.getGolesTorneo() + 1);
				globalEstadisticas.setGolesGlobal(globalEstadisticas.getGolesGlobal() + 1);
				break;
			case "Tarjeta Amarilla":
				torneoEstadisticas.setAmarillasTorneo(torneoEstadisticas.getAmarillasTorneo() + 1);
				globalEstadisticas.setAmarillasGlobal(globalEstadisticas.getAmarillasGlobal() + 1);
				break;
			case "Tarjeta Roja":
				torneoEstadisticas.setRojasTorneo(torneoEstadisticas.getRojasTorneo() + 1);
				globalEstadisticas.setRojasGlobal(globalEstadisticas.getRojasGlobal() + 1);
				break;
			}
		}
	}
	
	
	public void actualizarCamposPartidoTorneo(ActaPartidoEntidad acta) {
	    if (acta.getPartidoTorneo() != null) {
	        acta.getPartidoTorneo().setGolesLocal(acta.getGolesLocal());
	        acta.getPartidoTorneo().setGolesVisitante(acta.getGolesVisitante());
	        acta.getPartidoTorneo().setEstado("finalizado");
	        
	        partidoTorneoInterfaz.save(acta.getPartidoTorneo());
	    }
	}

	

}
