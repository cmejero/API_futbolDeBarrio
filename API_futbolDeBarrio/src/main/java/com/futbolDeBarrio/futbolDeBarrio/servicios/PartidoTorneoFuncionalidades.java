package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.PartidoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
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
 * Clase que se encarga de la lógica de los métodos CRUD de PartidoTorneo
 */
public class PartidoTorneoFuncionalidades {

	@Autowired
	private PartidoTorneoInterfaz partidoTorneoInterfaz;
	@Autowired
	private EquipoTorneoInterfaz equipoTorneoInterfaz;
	@Autowired
	private TorneoInterfaz torneoInterfaz;
	@Autowired
	private ActaPartidoInterfaz actaPartidoInterfaz;
	@Autowired
	private ClubInterfaz clubInterfaz;
	@Autowired
	private InstalacionInterfaz instalacionInterfaz;

	/**
	 * Mapea una entidad PartidoTorneoEntidad a un DTO PartidoTorneoDto.
	 *
	 * @param entidad la entidad del partido del torneo.
	 * @return el DTO correspondiente al partido.
	 */
	public PartidoTorneoDto mapearAPartidoTorneoDto(PartidoTorneoEntidad entidad) {
		PartidoTorneoDto dto = new PartidoTorneoDto();

		dto.setIdPartidoTorneo(entidad.getIdPartidoTorneo());
		dto.setTorneoId(entidad.getTorneo().getIdTorneo());
		dto.setInstalacionId(entidad.getInstalacion().getIdInstalacion());
		dto.setClubLocalId(entidad.getClubLocal().getIdClub());
		dto.setClubVisitanteId(entidad.getClubVisitante().getIdClub());
		dto.setEquipoLocalId(entidad.getEquipoLocal().getIdEquipoTorneo());
		dto.setEquipoVisitanteId(entidad.getEquipoVisitante().getIdEquipoTorneo());
		dto.setGolesLocal(entidad.getGolesLocal());
		dto.setGolesVisitante(entidad.getGolesVisitante());
		dto.setFechaPartido(entidad.getFechaPartido());
		dto.setRonda(entidad.getRonda());
		dto.setEstado(entidad.getEstado());

		if (entidad.getActaPartido() != null) {
			dto.setActaPartidoId(entidad.getActaPartido().getIdActaPartido());
		}

		return dto;
	}

	/**
	 * Mapea un DTO PartidoTorneoDto a una entidad PartidoTorneoEntidad.
	 *
	 * @param dto el DTO del partido del torneo.
	 * @return la entidad correspondiente.
	 */
	public PartidoTorneoEntidad mapearAPartidoTorneoEntidad(PartidoTorneoDto dto) {
		PartidoTorneoEntidad entidad = new PartidoTorneoEntidad();

		entidad.setIdPartidoTorneo(dto.getIdPartidoTorneo());

		TorneoEntidad torneo = torneoInterfaz.findById(dto.getTorneoId()).orElseThrow(
				() -> new IllegalArgumentException("El torneo con ID " + dto.getTorneoId() + " no existe"));
		entidad.setTorneo(torneo);

		ClubEntidad clubLocal = clubInterfaz.findById(dto.getClubLocalId()).orElseThrow(
				() -> new IllegalArgumentException("El club local con ID " + dto.getClubLocalId() + " no existe"));
		entidad.setClubLocal(clubLocal);

		ClubEntidad clubVisitante = clubInterfaz.findById(dto.getClubVisitanteId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El club visitante con ID " + dto.getClubVisitanteId() + " no existe"));
		entidad.setClubVisitante(clubVisitante);

		EquipoTorneoEntidad equipoLocal = equipoTorneoInterfaz.findById(dto.getEquipoLocalId()).orElseThrow(
				() -> new IllegalArgumentException("El equipo local con ID " + dto.getEquipoLocalId() + " no existe"));
		entidad.setEquipoLocal(equipoLocal);

		EquipoTorneoEntidad equipoVisitante = equipoTorneoInterfaz.findById(dto.getEquipoVisitanteId())
				.orElseThrow(() -> new IllegalArgumentException(
						"El equipo visitante con ID " + dto.getEquipoVisitanteId() + " no existe"));
		entidad.setEquipoVisitante(equipoVisitante);

		if (dto.getActaPartidoId() != null) {
			ActaPartidoEntidad acta = actaPartidoInterfaz.findById(dto.getActaPartidoId()).orElseThrow(
					() -> new IllegalArgumentException("El acta con ID " + dto.getActaPartidoId() + " no existe"));
			entidad.setActaPartido(acta);
		}

		entidad.setGolesLocal(dto.getGolesLocal());
		entidad.setGolesVisitante(dto.getGolesVisitante());
		entidad.setFechaPartido(dto.getFechaPartido());
		entidad.setRonda(dto.getRonda());
		entidad.setEstado(dto.getEstado());
		entidad.setInstalacion(instalacionInterfaz.findById(dto.getInstalacionId()).orElseThrow(
				() -> new IllegalArgumentException("La instalación con ID " + dto.getInstalacionId() + " no existe")));

		return entidad;
	}

	/**
	 * Obtiene un partido del torneo por su ID.
	 *
	 * @param idPartidoTorneo el ID del partido.
	 * @return el DTO correspondiente, o null si no existe.
	 */
	public PartidoTorneoDto obtenerPartidoTorneoDtoPorId(Long idPartidoTorneo) {
		PartidoTorneoEntidad entidad = partidoTorneoInterfaz.findById(idPartidoTorneo).orElse(null);
		return entidad != null ? mapearAPartidoTorneoDto(entidad) : null;
	}

	/**
	 * Obtiene todos los partidos del torneo.
	 *
	 * @return lista de DTOs.
	 */
	public ArrayList<PartidoTorneoDto> obtenerPartidosTorneoDto() {
		List<PartidoTorneoEntidad> lista = partidoTorneoInterfaz.findAll();
		return lista.stream().map(this::mapearAPartidoTorneoDto).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Obtiene todos los partidos de un torneo específico.
	 *
	 * @param idTorneo ID del torneo.
	 * @return lista de DTOs de los partidos.
	 */
	public ArrayList<PartidoTorneoDto> obtenerPartidosPorTorneoDto(Long idTorneo) {
		List<PartidoTorneoEntidad> lista = partidoTorneoInterfaz.findByTorneo_IdTorneo(idTorneo);
		return lista.stream().map(this::mapearAPartidoTorneoDto).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Guarda un nuevo partido de torneo.
	 *
	 * @param dto el DTO a guardar.
	 * @return la entidad guardada.
	 */
	public PartidoTorneoEntidad guardarPartidoTorneo(PartidoTorneoDto dto) {
	    PartidoTorneoEntidad entidad = new PartidoTorneoEntidad();

	    // Setear torneo, clubes, equipos, instalación
	    TorneoEntidad torneo = torneoInterfaz.findById(dto.getTorneoId()).orElseThrow(
	            () -> new IllegalArgumentException("El torneo con ID " + dto.getTorneoId() + " no existe"));
	    entidad.setTorneo(torneo);

	    ClubEntidad clubLocal = clubInterfaz.findById(dto.getClubLocalId()).orElseThrow(
	            () -> new IllegalArgumentException("El club local con ID " + dto.getClubLocalId() + " no existe"));
	    entidad.setClubLocal(clubLocal);

	    ClubEntidad clubVisitante = clubInterfaz.findById(dto.getClubVisitanteId()).orElseThrow(
	            () -> new IllegalArgumentException("El club visitante con ID " + dto.getClubVisitanteId() + " no existe"));
	    entidad.setClubVisitante(clubVisitante);

	    EquipoTorneoEntidad equipoLocal = equipoTorneoInterfaz.findById(dto.getEquipoLocalId()).orElseThrow(
	            () -> new IllegalArgumentException("El equipo local con ID " + dto.getEquipoLocalId() + " no existe"));
	    entidad.setEquipoLocal(equipoLocal);

	    EquipoTorneoEntidad equipoVisitante = equipoTorneoInterfaz.findById(dto.getEquipoVisitanteId()).orElseThrow(
	            () -> new IllegalArgumentException("El equipo visitante con ID " + dto.getEquipoVisitanteId() + " no existe"));
	    entidad.setEquipoVisitante(equipoVisitante);

	    entidad.setInstalacion(instalacionInterfaz.findById(dto.getInstalacionId()).orElseThrow(
	            () -> new IllegalArgumentException("La instalación con ID " + dto.getInstalacionId() + " no existe")));

	   
	    if (dto.getActaPartidoId() != null) {
	        try {
	            ActaPartidoEntidad acta = actaPartidoInterfaz.findById(dto.getActaPartidoId())
	                .orElseThrow(() -> new IllegalArgumentException(
	                    "El acta con ID " + dto.getActaPartidoId() + " no existe"
	                ));
	            entidad.setActaPartido(acta);

	            // Loguear éxito
	            Logs.ficheroLog("Acta asignada correctamente al partido. actaId=" + dto.getActaPartidoId());
	        } catch (IllegalArgumentException e) {
	            // Loguear error y volver a lanzar excepción para que se capture en el controlador
	            Logs.ficheroLog("ERROR al asignar acta: " + e.getMessage());
	            throw e;
	        } catch (Exception e) {
	            // Cualquier otro error inesperado
	            Logs.ficheroLog("ERROR inesperado al asignar acta: " + e.getMessage());
	            throw new RuntimeException("Error asignando acta al partido", e);
	        }
	    } else {
	        // Loguear que no había acta (opcional)
	        Logs.ficheroLog("No se asigna acta al partido, actaPartidoId es null.");
	    }
	    

	    

	    // Setear goles, fecha, ronda, estado
	    entidad.setGolesLocal(dto.getGolesLocal());
	    entidad.setGolesVisitante(dto.getGolesVisitante());
	    entidad.setFechaPartido(dto.getFechaPartido());
	    entidad.setRonda(dto.getRonda());
	    entidad.setEstado(dto.getEstado());

	    return partidoTorneoInterfaz.save(entidad);
	}


	/**
	 * Modifica un partido de torneo existente.
	 *
	 * @param idPartidoTorneo el ID del partido a modificar.
	 * @param dto             el DTO actualizado.
	 * @return true si la modificación fue exitosa, false si el partido no existe.
	 */
	public boolean modificarPartidoTorneo(Long idPartidoTorneo, PartidoTorneoDto dto) {
		Optional<PartidoTorneoEntidad> opt = partidoTorneoInterfaz.findById(idPartidoTorneo);

		if (opt.isPresent()) {
			PartidoTorneoEntidad entidad = opt.get();

			if (entidad.getActaPartido() != null && Boolean.TRUE.equals(entidad.getActaPartido().isCerrado())) {
				throw new IllegalStateException("No se puede modificar el partido porque el acta ya está cerrada");
			}

			entidad.setFechaPartido(dto.getFechaPartido());
			entidad.setRonda(dto.getRonda());
			entidad.setEstado(dto.getEstado());

			entidad.setEquipoLocal(equipoTorneoInterfaz.findById(dto.getEquipoLocalId())
					.orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado")));

			entidad.setEquipoVisitante(equipoTorneoInterfaz.findById(dto.getEquipoVisitanteId())
					.orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado")));

			entidad.setClubLocal(clubInterfaz.findById(dto.getClubLocalId())
					.orElseThrow(() -> new IllegalArgumentException("Club local no encontrado")));

			entidad.setClubVisitante(clubInterfaz.findById(dto.getClubVisitanteId())
					.orElseThrow(() -> new IllegalArgumentException("Club visitante no encontrado")));

			entidad.setInstalacion(instalacionInterfaz.findById(dto.getInstalacionId())
					.orElseThrow(() -> new IllegalArgumentException("Instalación no encontrada")));

			partidoTorneoInterfaz.save(entidad);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina un partido de torneo por su ID.
	 *
	 * @param idPartidoTorneo el ID del partido.
	 * @return true si se eliminó, false si no existe.
	 */
	public boolean eliminarPartidoTorneo(Long idPartidoTorneo) {
		Optional<PartidoTorneoEntidad> entidadOpt = partidoTorneoInterfaz.findById(idPartidoTorneo);

		if (entidadOpt.isPresent()) {
			PartidoTorneoEntidad entidad = entidadOpt.get();

			if (entidad.getActaPartido() != null && Boolean.TRUE.equals(entidad.getActaPartido().isCerrado())) {
				throw new IllegalStateException("No se puede eliminar el partido porque el acta ya está cerrada");
			}

			partidoTorneoInterfaz.delete(entidad);
			return true;
		} else {
			return false;
		}
	}

	// =========================================
	// NUEVO MÉTODO: CERRAR ACTA DEL PARTIDO
	// =========================================
	/**
	 * Cierra el acta de un partido y marca el partido como FINALIZADO.
	 *
	 * @param idPartidoTorneo ID del partido a cerrar.
	 * @return true si se cerró correctamente, false si ya estaba cerrada o no
	 *         existe.
	 * @throws IllegalStateException si el partido no tiene acta asociada.
	 */
	public boolean cerrarActaPartido(Long idPartidoTorneo) {

		Optional<PartidoTorneoEntidad> entidadOpt = partidoTorneoInterfaz.findById(idPartidoTorneo);

		if (entidadOpt.isPresent()) {
			PartidoTorneoEntidad partido = entidadOpt.get();


			if (partido.getActaPartido() == null) {

				throw new IllegalStateException("El partido no tiene acta asociada");
			}

			ActaPartidoEntidad acta = partido.getActaPartido();

			if (acta.isCerrado()) {

				return false;
			}

			acta.setCerrado(true);
			actaPartidoInterfaz.save(acta);

			partido.setEstado("FINALIZADO");
			partidoTorneoInterfaz.save(partido);

			return true;
		}

		return false;
	}

}
