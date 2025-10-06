	package com.futbolDeBarrio.futbolDeBarrio.servicios;
	
	import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ActaPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.EventoPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EventoPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ActaPartidoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.EquipoTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.PartidoTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;
	
	@Service
	/**
	 * * Clase que se encarga de la lógica de los metodos CRUD de Acta de partido
	 */
	public class ActaPartidoFuncionalidades {
	
		@Autowired
		private EquipoTorneoInterfaz equipoTorneoInterfaz;
		@Autowired
		private TorneoInterfaz torneoInterfaz;
		@Autowired
		private ClubInterfaz clubInterfaz;
		@Autowired
		private InstalacionInterfaz instalacionInterfaz;
		@Autowired
		private ActaPartidoInterfaz actaPartidoInterfaz;
		@Autowired
		private PartidoTorneoInterfaz partidoTorneoInterfaz;
		@Autowired
		private EventoPartidoFuncionalidades eventoPartidoFuncionalidades;
		@Autowired
		ActualizarEstadisticasFuncionalidades actualizarEstadisticasFuncionalidades;
		
	
	
		/**
		 * Mapea una entidad ActaPartidoEntidad a un DTO ActaPartidoDto.
		 * 
		 * @param ActaPartidoEntidad la entidad del acta del partido
		 * @return el DTO correspondiente al acta del partido
		 */
		public ActaPartidoDto mapearAActaPartidoDto(ActaPartidoEntidad actaPartidoEntidad) {
	
			ActaPartidoDto actaPartidoDto = new ActaPartidoDto();
			actaPartidoDto.setIdActaPartido(actaPartidoEntidad.getIdActaPartido());
			actaPartidoDto.setTorneoId(actaPartidoEntidad.getTorneo().getIdTorneo());
			actaPartidoDto.setInstalacionId(actaPartidoEntidad.getInstalacion().getIdInstalacion());
			actaPartidoDto.setClubLocalId(actaPartidoEntidad.getClubLocal().getIdClub());
			actaPartidoDto.setClubVisitanteId(actaPartidoEntidad.getClubVisitante().getIdClub());
			actaPartidoDto.setEquipoLocalId(actaPartidoEntidad.getEquipoLocal().getIdEquipoTorneo());
			actaPartidoDto.setEquipoVisitanteId(actaPartidoEntidad.getEquipoVisitante().getIdEquipoTorneo());
			actaPartidoDto.setPartidoTorneoId(actaPartidoEntidad.getPartidoTorneo().getIdPartidoTorneo());
			actaPartidoDto.setGolesLocal(actaPartidoEntidad.getGolesLocal());
			actaPartidoDto.setGolesVisitante(actaPartidoEntidad.getGolesVisitante());
			actaPartidoDto.setGolesPenaltisLocal(actaPartidoEntidad.getGolesPenaltisLocal());
			actaPartidoDto.setGolesPenaltisVisitante(actaPartidoEntidad.getGolesPenaltisVisitante());
			actaPartidoDto.setClubGanadorId(actaPartidoEntidad.getClubGanador().getIdClub());
			actaPartidoDto.setFechaPartido(actaPartidoEntidad.getFechaPartido());
			actaPartidoDto.setObservaciones(actaPartidoEntidad.getObservaciones());
			actaPartidoDto.setCerrado(actaPartidoEntidad.estaCerrado());
			actaPartidoDto.setEventos(
					actaPartidoEntidad.getEventoPartido().stream().map(EventoPartidoDto::new).collect(Collectors.toList()));
			return actaPartidoDto;
		}
	
		/**
		 * Mapea un DTO ActaPartidoDto a una entidad ActaPartidoEntidad.
		 * 
		 * @param actaPartidoDto el DTO del actaPartido
		 * @return la entidad correspondiente al actaPartido
		 */
		public ActaPartidoEntidad mapearAActaPartidoEntidad(ActaPartidoDto actaPartidoDto) {
	
		    ActaPartidoEntidad actaPartidoEntidad = new ActaPartidoEntidad();
	
		    actaPartidoEntidad.setIdActaPartido(actaPartidoDto.getIdActaPartido());
	
		    // Mapear relaciones simples (torneo, instalacion, clubes, equipos, etc.)
		    actaPartidoEntidad.setTorneo(torneoInterfaz.findById(actaPartidoDto.getTorneoId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El torneo con ID " + actaPartidoDto.getTorneoId() + " no existe")));
		    actaPartidoEntidad.setInstalacion(instalacionInterfaz.findById(actaPartidoDto.getInstalacionId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "La instalacion con ID " + actaPartidoDto.getInstalacionId() + " no existe")));
		    actaPartidoEntidad.setClubLocal(clubInterfaz.findById(actaPartidoDto.getClubLocalId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El club local con ID " + actaPartidoDto.getClubLocalId() + " no existe")));
		    actaPartidoEntidad.setClubVisitante(clubInterfaz.findById(actaPartidoDto.getClubVisitanteId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El club visitante con ID " + actaPartidoDto.getClubVisitanteId() + " no existe")));
		    actaPartidoEntidad.setEquipoLocal(equipoTorneoInterfaz.findById(actaPartidoDto.getEquipoLocalId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El equipo local con ID " + actaPartidoDto.getEquipoLocalId() + " no existe")));
		    actaPartidoEntidad.setEquipoVisitante(equipoTorneoInterfaz.findById(actaPartidoDto.getEquipoVisitanteId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El equipo visitante con ID " + actaPartidoDto.getEquipoVisitanteId() + " no existe")));
		    actaPartidoEntidad.setPartidoTorneo(partidoTorneoInterfaz.findById(actaPartidoDto.getPartidoTorneoId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El partido Torneo con ID " + actaPartidoDto.getPartidoTorneoId() + " no existe")));
		    actaPartidoEntidad.setClubGanador(clubInterfaz.findById(actaPartidoDto.getClubGanadorId())
		        .orElseThrow(() -> new IllegalArgumentException(
		            "El equipo ganador con ID " + actaPartidoDto.getClubGanadorId() + " no existe")));
	
		    // Mapear campos simples
		    actaPartidoEntidad.setGolesLocal(actaPartidoDto.getGolesLocal());
		    actaPartidoEntidad.setGolesVisitante(actaPartidoDto.getGolesVisitante());
		    actaPartidoEntidad.setGolesPenaltisLocal(actaPartidoDto.getGolesPenaltisLocal());
		    actaPartidoEntidad.setGolesPenaltisVisitante(actaPartidoDto.getGolesPenaltisVisitante());
		    actaPartidoEntidad.setFechaPartido(actaPartidoDto.getFechaPartido());
		    actaPartidoEntidad.setObservaciones(actaPartidoDto.getObservaciones());
		    actaPartidoEntidad.setCerrado(actaPartidoDto.isCerrado());
	
		    actaPartidoEntidad.setEventoPartido(new ArrayList<>()); 
	
		    return actaPartidoEntidad;
		}
	
		/**
		 * Método que obtiene un acta de partido por su ID.
		 * 
		 * @param idActaPartido el ID del acta
		 * @return el DTO del acta null si no se encuentra
		 */
		public ActaPartidoDto obtenerActaPartidoDtoPorId(Long idActaPartido) {
			ActaPartidoEntidad actaPartidoEntidad = actaPartidoInterfaz.findById(idActaPartido).orElse(null);
			return actaPartidoEntidad != null ? mapearAActaPartidoDto(actaPartidoEntidad) : null;
		}
	
		/**
		 * Método que mapea una lista de entidades a DTOs.
		 * 
		 * @return la lista de DTOs de acta partido
		 */
		public ArrayList<ActaPartidoDto> obtenerActasPartidosDto() {
			ArrayList<ActaPartidoEntidad> actaPartidoEntidad = (ArrayList<ActaPartidoEntidad>) actaPartidoInterfaz
					.findAll();
			ArrayList<ActaPartidoDto> actaPartidoDto = new ArrayList<>();
			for (ActaPartidoEntidad acta : actaPartidoEntidad) {
				actaPartidoDto.add(mapearAActaPartidoDto(acta));
			}
			return actaPartidoDto;
		}
	
		/**
		 * Guarda un nuevo Acta de partido.
		 * 
		 * @param ActaPartidoDto el DTO del actaPartido a guardar
		 * @return la entidad del ActaPartido guardada
		 */
		public ActaPartidoEntidad guardarActaPartido(ActaPartidoDto actaPartidoDto) {
		    // 1️⃣ Mapear DTO a entidad
		    ActaPartidoEntidad actaPartidoEntidad = mapearAActaPartidoEntidad(actaPartidoDto);
		    actaPartidoEntidad.setEventoPartido(new ArrayList<>()); // temporalmente vacío

		    // 2️⃣ Asociar partido torneo si existe
		    if (actaPartidoDto.getPartidoTorneoId() != null) {
		        PartidoTorneoEntidad partidoTorneo = partidoTorneoInterfaz
		            .findById(actaPartidoDto.getPartidoTorneoId())
		            .orElseThrow(() -> new RuntimeException("PartidoTorneo no encontrado"));
		        actaPartidoEntidad.setPartidoTorneo(partidoTorneo);
		        partidoTorneo.setActaPartido(actaPartidoEntidad); // sincroniza objetos en memoria
		    }

		    // 3️⃣ Guardar acta para generar ID
		    actaPartidoEntidad = actaPartidoInterfaz.save(actaPartidoEntidad);

		    // 4️⃣ Mapear y agregar eventos
		    if (actaPartidoDto.getEventos() != null && !actaPartidoDto.getEventos().isEmpty()) {
		        for (EventoPartidoDto eventoDto : actaPartidoDto.getEventos()) {
		            eventoDto.setActaPartidoId(actaPartidoEntidad.getIdActaPartido());
		            EventoPartidoEntidad eventoEntidad = eventoPartidoFuncionalidades
		                .mapearAEventoPartidoEntidad(eventoDto);
		            actaPartidoEntidad.getEventoPartido().add(eventoEntidad);
		        }
		        actaPartidoEntidad = actaPartidoInterfaz.save(actaPartidoEntidad);
		        actualizarEstadisticasFuncionalidades.actualizarCamposPartidoTorneo(actaPartidoEntidad);
		    }

		    // 5️⃣ Actualizar estadísticas globales
		    actualizarEstadisticasFuncionalidades.actualizarEstadisticas(actaPartidoEntidad);

		    return actaPartidoEntidad;
		}


	
	
		/**
		 * Modifica un acta de partido existente.
		 * 
		 * @param id             el ID del acta a modificar
		 * @param actaPartidoDto el DTO actualizado del actaPartido
		 * @return true si la modificación fue exitosa, false en caso contrario
		 */
		public boolean modificarActaPartido(Long idActaPartido, ActaPartidoDto actaPartidoDto) {
		    Optional<ActaPartidoEntidad> actaPartidoOpt = actaPartidoInterfaz.findById(idActaPartido);
		    if (actaPartidoOpt.isEmpty()) return false;

		    ActaPartidoEntidad actaPartidoEntidad = actaPartidoOpt.get();

		    // Actualizar relaciones si existen
		    torneoInterfaz.findById(actaPartidoDto.getTorneoId()).ifPresent(actaPartidoEntidad::setTorneo);
		    instalacionInterfaz.findById(actaPartidoDto.getInstalacionId()).ifPresent(actaPartidoEntidad::setInstalacion);
		    clubInterfaz.findById(actaPartidoDto.getClubLocalId()).ifPresent(actaPartidoEntidad::setClubLocal);
		    clubInterfaz.findById(actaPartidoDto.getClubVisitanteId()).ifPresent(actaPartidoEntidad::setClubVisitante);
		    equipoTorneoInterfaz.findById(actaPartidoDto.getEquipoLocalId()).ifPresent(actaPartidoEntidad::setEquipoLocal);
		    equipoTorneoInterfaz.findById(actaPartidoDto.getEquipoVisitanteId()).ifPresent(actaPartidoEntidad::setEquipoVisitante);
		    partidoTorneoInterfaz.findById(actaPartidoDto.getPartidoTorneoId()).ifPresent(actaPartidoEntidad::setPartidoTorneo);
		    clubInterfaz.findById(actaPartidoDto.getClubGanadorId()).ifPresent(actaPartidoEntidad::setClubGanador);

		    // Actualizar campos simples
		    actaPartidoEntidad.setGolesLocal(actaPartidoDto.getGolesLocal());
		    actaPartidoEntidad.setGolesVisitante(actaPartidoDto.getGolesVisitante());
		    actaPartidoEntidad.setGolesPenaltisLocal(actaPartidoDto.getGolesPenaltisLocal());
		    actaPartidoEntidad.setGolesPenaltisVisitante(actaPartidoDto.getGolesPenaltisVisitante());
		    actaPartidoEntidad.setFechaPartido(actaPartidoDto.getFechaPartido());
		    actaPartidoEntidad.setObservaciones(actaPartidoDto.getObservaciones());
		    actaPartidoEntidad.setCerrado(actaPartidoDto.isCerrado());

		    // Manejar eventos
		    actaPartidoEntidad.getEventoPartido().clear(); // borrar eventos previos
		    if (actaPartidoDto.getEventos() != null && !actaPartidoDto.getEventos().isEmpty()) {
		        for (EventoPartidoDto eventoDto : actaPartidoDto.getEventos()) {
		            eventoDto.setActaPartidoId(actaPartidoEntidad.getIdActaPartido());
		            EventoPartidoEntidad eventoEntidad = eventoPartidoFuncionalidades.mapearAEventoPartidoEntidad(eventoDto);
		            actaPartidoEntidad.getEventoPartido().add(eventoEntidad);
		        }
		    }

		    actaPartidoInterfaz.save(actaPartidoEntidad);
		    actualizarEstadisticasFuncionalidades.actualizarEstadisticas(actaPartidoEntidad);

		    return true;
		}
	
		/**
		 * Método que borra un acta de partido por su ID.
		 * 
		 * @param idActaPartidoString el ID del acta como cadena
		 * @return true si el acta fue borrado correctamente, false en caso contrario
		 */
		public boolean borrarActaPartido(String idActaPartidoString) {
			boolean estaBorrado = false;
	
			Long idActaPartido = Long.parseLong(idActaPartidoString);
			ActaPartidoEntidad actaPartidoEntidad = actaPartidoInterfaz.findByIdActaPartido(idActaPartido);
	
			if (actaPartidoEntidad == null) {
				estaBorrado = false;
			} else {
				actaPartidoInterfaz.delete(actaPartidoEntidad);
				estaBorrado = true;
			}
	
			return estaBorrado;
		}
	
	}
