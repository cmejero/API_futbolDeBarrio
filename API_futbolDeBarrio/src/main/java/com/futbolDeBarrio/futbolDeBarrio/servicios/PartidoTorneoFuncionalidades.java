package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.PartidoTorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
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
	@Autowired
	private MiembroClubFuncionalidades miembroClubFuncionalidades;

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
	    dto.setClubLocalNombre(entidad.getClubLocal().getNombreClub());
	    dto.setClubVisitanteNombre(entidad.getClubVisitante().getNombreClub());
	    dto.setClubLocalAbreviatura(entidad.getClubLocal().getAbreviaturaClub());
	    dto.setClubVisitanteAbreviatura(entidad.getClubVisitante().getAbreviaturaClub());
	    dto.setGolesLocal(entidad.getGolesLocal());
	    dto.setGolesVisitante(entidad.getGolesVisitante());
	    dto.setFechaPartido(entidad.getFechaPartido());
	    dto.setRonda(entidad.getRonda());
	    dto.setEstado(entidad.getEstado());
	    dto.setUbicacionRonda(entidad.getUbicacionRonda());
	    dto.setNombreTorneo(entidad.getTorneo().getNombreTorneo());
	    dto.setNombreInstalacion(entidad.getInstalacion().getNombreInstalacion());

	    dto.setJugadoresLocal(
	        miembroClubFuncionalidades.obtenerMiembrosPorClub(entidad.getClubLocal().getIdClub())
	            .stream()
	            .map(miembro -> {
	                UsuarioDto jugador = new UsuarioDto();
	                jugador.setIdUsuario(miembro.getUsuarioId());
	                jugador.setNombreCompletoUsuario(miembro.getUsuario().getNombreCompletoUsuario());
	                return jugador;
	            })
	            .collect(Collectors.toList())
	    );

	    dto.setJugadoresVisitante(
	        miembroClubFuncionalidades.obtenerMiembrosPorClub(entidad.getClubVisitante().getIdClub())
	            .stream()
	            .map(miembro -> {
	                UsuarioDto jugador = new UsuarioDto();
	                jugador.setIdUsuario(miembro.getUsuarioId());
	                jugador.setNombreCompletoUsuario(miembro.getUsuario().getNombreCompletoUsuario());
	                return jugador;
	            })
	            .collect(Collectors.toList())
	    );
	    
	    if (entidad.getActaPartido() != null && entidad.getActaPartido().getClubGanador() != null) {
	        EquipoTorneoEntidad equipoGanador = equipoTorneoInterfaz
	                .findByTorneo_IdTorneoAndClub_IdClub(
	                        entidad.getTorneo().getIdTorneo(),
	                        entidad.getActaPartido().getClubGanador().getIdClub())
	                .orElse(null);

	        if (equipoGanador != null) {
	            dto.setEquipoGanadorId(equipoGanador.getIdEquipoTorneo());
	        }
	    }

	    if (entidad.getActaPartido() != null) {
	        dto.setActaPartidoId(entidad.getActaPartido().getIdActaPartido());
	        dto.setActaCerrada(entidad.getActaPartido().estaCerrado());
	    } else {
	        dto.setActaPartidoId(null);
	        dto.setActaCerrada(false);
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
		entidad.setUbicacionRonda(dto.getUbicacionRonda());
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
	public PartidoTorneoEntidad guardarPartidoTorneo(PartidoTorneoDto dto, String emailInstalacionLogueada) {

	    // 1️⃣ Obtener la instalación que está logueada
	    InstalacionEntidad instalacionLogueada = instalacionInterfaz.findByEmailInstalacion(emailInstalacionLogueada)
	        .orElseThrow(() -> new IllegalArgumentException("Instalación logueada no encontrada"));

	    // 2️⃣ Obtener el torneo
	    TorneoEntidad torneo = torneoInterfaz.findById(dto.getTorneoId())
	        .orElseThrow(() -> new IllegalArgumentException("El torneo con ID " + dto.getTorneoId() + " no existe"));

	    // 3️⃣ Verificar que la instalación logueada creó el torneo
	    if (torneo.getInstalacion().getIdInstalacion() != instalacionLogueada.getIdInstalacion()) {
	        Logs.ficheroLog("⚠️ Intento no autorizado de guardar partido en torneo " + torneo.getIdTorneo() +
	                        " por instalación " + emailInstalacionLogueada);
	        throw new IllegalArgumentException("No tienes permisos para agregar partidos a este torneo");
	    }

	    // 4️⃣ Validar duplicados (como ya haces)
	    Optional<PartidoTorneoEntidad> existente =
	        partidoTorneoInterfaz.findByTorneo_IdTorneoAndRondaAndEquipoLocal_IdEquipoTorneoAndEquipoVisitante_IdEquipoTorneo(
	            dto.getTorneoId(),
	            dto.getRonda(),
	            dto.getEquipoLocalId(),
	            dto.getEquipoVisitanteId()
	        );

	    if (existente.isPresent()) {
	        Logs.ficheroLog("⚠️ Partido duplicado detectado. No se creará uno nuevo. " +
	                "Torneo=" + dto.getTorneoId() + ", Ronda=" + dto.getRonda() +
	                ", Local=" + dto.getEquipoLocalId() + ", Visitante=" + dto.getEquipoVisitanteId());
	        return existente.get();
	    }

	    // 5️⃣ Crear el partido (igual que ahora)
	    PartidoTorneoEntidad entidad = new PartidoTorneoEntidad();
	    entidad.setTorneo(torneo);
	    entidad.setClubLocal(clubInterfaz.findById(dto.getClubLocalId())
	            .orElseThrow(() -> new IllegalArgumentException("El club local no existe")));
	    entidad.setClubVisitante(clubInterfaz.findById(dto.getClubVisitanteId())
	            .orElseThrow(() -> new IllegalArgumentException("El club visitante no existe")));
	    entidad.setEquipoLocal(equipoTorneoInterfaz.findById(dto.getEquipoLocalId())
	            .orElseThrow(() -> new IllegalArgumentException("El equipo local no existe")));
	    entidad.setEquipoVisitante(equipoTorneoInterfaz.findById(dto.getEquipoVisitanteId())
	            .orElseThrow(() -> new IllegalArgumentException("El equipo visitante no existe")));
	    entidad.setInstalacion(instalacionLogueada); // ⚡ Asignamos la instalación logueada
	    entidad.setGolesLocal(dto.getGolesLocal());
	    entidad.setGolesVisitante(dto.getGolesVisitante());
	    entidad.setFechaPartido(dto.getFechaPartido());
	    entidad.setRonda(dto.getRonda());
	    entidad.setEstado(dto.getEstado());
	    entidad.setUbicacionRonda(dto.getUbicacionRonda());

	    Logs.ficheroLog("✅ Partido nuevo guardado: " +
	            "Torneo=" + dto.getTorneoId() + ", Ronda=" + dto.getRonda() +
	            ", Local=" + dto.getEquipoLocalId() + ", Visitante=" + dto.getEquipoVisitanteId() +
	            ", por instalación=" + emailInstalacionLogueada);

	    return partidoTorneoInterfaz.save(entidad);
	}




	/**
	 * Modifica un partido de torneo existente.
	 *
	 * @param idPartidoTorneo el ID del partido a modificar.
	 * @param dto             el DTO actualizado.
	 * @return true si la modificación fue exitosa, false si el partido no existe.
	 */
	public boolean modificarPartidoTorneo(Long idPartidoTorneo, PartidoTorneoDto dto, String emailLogueado) {
	    Optional<PartidoTorneoEntidad> opt = partidoTorneoInterfaz.findById(idPartidoTorneo);

	    if (opt.isEmpty()) return false;

	    PartidoTorneoEntidad entidad = opt.get();

	    // ⚠️ Evitar modificar si el acta está cerrada
	    if (entidad.getActaPartido() != null && Boolean.TRUE.equals(entidad.getActaPartido().isCerrado())) {
	        throw new IllegalStateException("No se puede modificar el partido porque el acta ya está cerrada");
	    }

	    // ⚠️ Validar que la instalación creadora sea la misma que la logueada
	    InstalacionEntidad instalacionLogueada = instalacionInterfaz.findByEmailInstalacion(emailLogueado)
	            .orElseThrow(() -> new IllegalStateException("Instalación logueada no encontrada"));

	    if (entidad.getInstalacion().getIdInstalacion() != instalacionLogueada.getIdInstalacion()) {
	        throw new IllegalStateException("No tienes permisos para modificar este partido");
	    }

	    // Guardar los cambios
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

	    Logs.ficheroLog("Partido modificado por la instalación: " + instalacionLogueada.getNombreInstalacion());

	    return true;
	}


	
}
