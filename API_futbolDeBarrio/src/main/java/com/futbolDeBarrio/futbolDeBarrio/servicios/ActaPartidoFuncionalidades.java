package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ActaPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.EventoPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EventoPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
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

		TorneoEntidad torneo = torneoInterfaz.findById(actaPartidoDto.getTorneoId()).orElseThrow(
				() -> new IllegalArgumentException("El torneo con ID " + actaPartidoDto.getTorneoId() + " no existe"));
		actaPartidoEntidad.setTorneo(torneo);

		InstalacionEntidad instalacion = instalacionInterfaz.findById(actaPartidoDto.getInstalacionId())
				.orElseThrow(() -> new IllegalArgumentException(
						"La instalacion con ID " + actaPartidoDto.getInstalacionId() + " no existe"));
		actaPartidoEntidad.setInstalacion(instalacion);

		ClubEntidad clubLocal = clubInterfaz.findById(actaPartidoDto.getClubLocalId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El club local con ID " + actaPartidoDto.getClubLocalId() + " no existe"));
		actaPartidoEntidad.setClubLocal(clubLocal);

		ClubEntidad clubVisitante = clubInterfaz.findById(actaPartidoDto.getClubVisitanteId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El club visitante con ID " + actaPartidoDto.getClubVisitanteId() + " no existe"));
		actaPartidoEntidad.setClubVisitante(clubVisitante);

		EquipoTorneoEntidad equipoLocal = equipoTorneoInterfaz.findById(actaPartidoDto.getEquipoLocalId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El equipo local con ID " + actaPartidoDto.getEquipoLocalId() + " no existe"));
		actaPartidoEntidad.setEquipoLocal(equipoLocal);

		EquipoTorneoEntidad equipoVisitante = equipoTorneoInterfaz.findById(actaPartidoDto.getEquipoVisitanteId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El equipo visitante con ID " + actaPartidoDto.getEquipoVisitanteId() + " no existe"));
		actaPartidoEntidad.setEquipoVisitante(equipoVisitante);

		PartidoTorneoEntidad partidoTorneo = partidoTorneoInterfaz.findById(actaPartidoDto.getPartidoTorneoId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El partido Torneo con ID " + actaPartidoDto.getPartidoTorneoId() + " no existe"));
		actaPartidoEntidad.setPartidoTorneo(partidoTorneo);

		actaPartidoEntidad.setGolesLocal(actaPartidoDto.getGolesLocal());
		actaPartidoEntidad.setGolesVisitante(actaPartidoDto.getGolesVisitante());
		actaPartidoEntidad.setGolesPenaltisLocal(actaPartidoDto.getGolesPenaltisLocal());
		actaPartidoEntidad.setGolesPenaltisVisitante(actaPartidoDto.getGolesPenaltisVisitante());
		actaPartidoEntidad.setFechaPartido(actaPartidoDto.getFechaPartido());
		actaPartidoEntidad.setObservaciones(actaPartidoDto.getObservaciones());
		actaPartidoEntidad.setCerrado(actaPartidoDto.isCerrado());

		if (actaPartidoDto.getEventos() != null) {
			List<EventoPartidoEntidad> eventosEntidad = actaPartidoDto.getEventos().stream().map(dto -> {
				EventoPartidoEntidad e = eventoPartidoFuncionalidades.mapearAEventoPartidoEntidad(dto);
				e.setActaPartido(actaPartidoEntidad);
				return e;
			}).collect(Collectors.toList());
			actaPartidoEntidad.setEventoPartido(eventosEntidad);
		}

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
		// 1️⃣ Mapear ActaPartidoDto a ActaPartidoEntidad
		ActaPartidoEntidad actaPartidoEntidad = mapearAActaPartidoEntidad(actaPartidoDto);

		// 2️⃣ Guardar primero el acta para generar el ID
		actaPartidoEntidad = actaPartidoInterfaz.save(actaPartidoEntidad);

		// 3️⃣ Guardar eventos usando DTOs y asignar el actaPartidoId
		if (actaPartidoDto.getEventos() != null) {
			for (EventoPartidoDto eventoDto : actaPartidoDto.getEventos()) {
				eventoDto.setActaPartidoId(actaPartidoEntidad.getIdActaPartido());
				eventoPartidoFuncionalidades.guardarEventoPartido(eventoDto);
			}
		}

		// 4️⃣ Retornar la entidad guardada
		return actaPartidoEntidad;
	}

	/**
	 * Modifica un acta de partido existente.
	 * 
	 * @param id             el ID del acta a modificar
	 * @param actaPartidoDto el DTO actualizado del actaPartido
	 * @return true si la modificación fue exitosa, false en caso contrario
	 */
	public boolean modificarActaPartido(String id, ActaPartidoDto actaPartidoDto) {
		Long idActaPartido = Long.parseLong(id);
		Optional<ActaPartidoEntidad> actaPartidoOpt = actaPartidoInterfaz.findById(idActaPartido);

		if (actaPartidoOpt.isPresent()) {
			ActaPartidoEntidad actaPartidoEntidad = actaPartidoOpt.get();

			Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(actaPartidoDto.getTorneoId());
			torneoOpt.ifPresent(actaPartidoEntidad::setTorneo);

			Optional<InstalacionEntidad> instalacionOpt = instalacionInterfaz
					.findById(actaPartidoDto.getInstalacionId());
			instalacionOpt.ifPresent(actaPartidoEntidad::setInstalacion);

			Optional<ClubEntidad> clubLocalOpt = clubInterfaz.findById(actaPartidoDto.getClubLocalId());
			clubLocalOpt.ifPresent(actaPartidoEntidad::setClubLocal);

			Optional<ClubEntidad> clubVisitanteOpt = clubInterfaz.findById(actaPartidoDto.getClubVisitanteId());
			clubVisitanteOpt.ifPresent(actaPartidoEntidad::setClubVisitante);

			Optional<EquipoTorneoEntidad> equipoLocalOpt = equipoTorneoInterfaz
					.findById(actaPartidoDto.getEquipoLocalId());
			equipoLocalOpt.ifPresent(actaPartidoEntidad::setEquipoLocal);

			Optional<EquipoTorneoEntidad> equipoVisitanteOpt = equipoTorneoInterfaz
					.findById(actaPartidoDto.getEquipoVisitanteId());
			equipoVisitanteOpt.ifPresent(actaPartidoEntidad::setEquipoVisitante);

			Optional<PartidoTorneoEntidad> partidoTorneoOpt = partidoTorneoInterfaz
					.findById(actaPartidoDto.getPartidoTorneoId());
			partidoTorneoOpt.ifPresent(actaPartidoEntidad::setPartidoTorneo);

			actaPartidoEntidad.setGolesLocal(actaPartidoDto.getGolesLocal());
			actaPartidoEntidad.setGolesVisitante(actaPartidoDto.getGolesVisitante());
			actaPartidoEntidad.setGolesPenaltisLocal(actaPartidoDto.getGolesPenaltisLocal());
			actaPartidoEntidad.setGolesPenaltisVisitante(actaPartidoDto.getGolesPenaltisVisitante());
			actaPartidoEntidad.setFechaPartido(actaPartidoDto.getFechaPartido());
			actaPartidoEntidad.setObservaciones(actaPartidoDto.getObservaciones());
			actaPartidoEntidad.setCerrado(actaPartidoDto.isCerrado());

			actaPartidoInterfaz.save(actaPartidoEntidad);
			return true;
		} else {
			return false;
		}
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
