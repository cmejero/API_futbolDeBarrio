package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.TorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;

@Service
/**
 * Clase que se encarga de la lógica de los metodos CRUD de torneo
 */
public class TorneoFuncionalidades {

	@Autowired
	private TorneoInterfaz torneoInterfaz;

	@Autowired
	private InstalacionInterfaz instalacionInterfaz;

	/**
	 * Mapea una entidad TorneoEntidad a un DTO TorneoDto.
	 * 
	 * @param torneoEntidad la entidad del torneo
	 * @return el DTO correspondiente al torneo
	 */
	public TorneoDto mapearATorneoDto(TorneoEntidad torneoEntidad) {
		TorneoDto torneoDto = new TorneoDto();
		torneoDto.setIdTorneo(torneoEntidad.getIdTorneo());
		torneoDto.setNombreTorneo(torneoEntidad.getNombreTorneo());
		torneoDto.setFechaInicioTorneo(torneoEntidad.getFechaInicioTorneo());
		torneoDto.setFechaFinTorneo(torneoEntidad.getFechaFinTorneo());
		torneoDto.setDescripcionTorneo(torneoEntidad.getDescripcionTorneo());
		torneoDto.setClubesInscritos(torneoEntidad.getClubesInscritos());
		torneoDto.setModalidad(torneoEntidad.getModalidad());
		torneoDto.setEstaActivo(torneoEntidad.isEstaActivo());
		torneoDto.setInstalacionId(torneoEntidad.getInstalacion().getIdInstalacion());
		torneoDto.setDireccionInstalacion(torneoEntidad.getInstalacion().getDireccionInstalacion());
		torneoDto.setNombreInstalacion(torneoEntidad.getInstalacion().getNombreInstalacion());

		return torneoDto;
	}

	/**
	 * Mapea un DTO TorneoDto a una entidad TorneoEntidad.
	 * 
	 * @param torneoDto el DTO del torneo
	 * @return la entidad correspondiente al torneo
	 */
	public TorneoEntidad mapearADtoAEntidad(TorneoDto torneoDto) {
		TorneoEntidad torneoEntidad = new TorneoEntidad();
		torneoEntidad.setIdTorneo(torneoDto.getIdTorneo());
		torneoEntidad.setNombreTorneo(torneoDto.getNombreTorneo());
		torneoEntidad.setFechaInicioTorneo(torneoDto.getFechaInicioTorneo());
		torneoEntidad.setFechaFinTorneo(torneoDto.getFechaFinTorneo());
		torneoEntidad.setDescripcionTorneo(torneoDto.getDescripcionTorneo());
		torneoEntidad.setClubesInscritos(torneoDto.getClubesInscritos());

		torneoEntidad.setModalidad(torneoDto.getModalidad());
		torneoEntidad.setEstaActivo(torneoDto.isEstaActivo());

		Optional<InstalacionEntidad> instalacionOpt = instalacionInterfaz.findById(torneoDto.getInstalacionId());
		instalacionOpt.ifPresent(torneoEntidad::setInstalacion);

		return torneoEntidad;
	}

	/**
	 * Obtiene una lista de todos los torneos.
	 * 
	 * @return la lista de todos los DTOs de torneos
	 */
	public List<TorneoDto> obtenerTodosLosTorneos() {
		List<TorneoEntidad> torneosEntidad = torneoInterfaz.findAll();
		return torneosEntidad.stream().map(this::mapearATorneoDto).collect(Collectors.toList());
	}

	/**
	 * Obtiene una lista de torneos por el ID de la instalación.
	 * 
	 * @param instalacionId el ID de la instalación
	 * @return la lista de los DTOs de torneos asociados con la instalación
	 */
	public List<TorneoDto> obtenerTorneosPorInstalacion(Long instalacionId) {
		List<TorneoEntidad> torneosEntidad = torneoInterfaz.findByInstalacion_IdInstalacion(instalacionId);
		return torneosEntidad.stream().map(this::mapearATorneoDto).collect(Collectors.toList());
	}

	/**
	 * Guarda un nuevo torneo.
	 * 
	 * @param torneoDto el DTO del torneo a guardar
	 * @return la entidad del torneo guardada
	 */
	public TorneoEntidad guardarTorneo(TorneoDto torneoDto) {
		TorneoEntidad torneoEntidad = mapearADtoAEntidad(torneoDto);
		return torneoInterfaz.save(torneoEntidad);
	}

	/**
	 * Modifica un torneo existente.
	 * 
	 * @param id        el ID del torneo a modificar
	 * @param torneoDto el DTO actualizado del torneo
	 * @return true si la modificación fue exitosa, false en caso contrario
	 */
	public boolean modificarTorneo(String id, TorneoDto torneoDto) {
		Long idTorneo = Long.parseLong(id);
		Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(idTorneo);

		if (torneoOpt.isPresent()) {
			TorneoEntidad torneoEntidad = torneoOpt.get();
			torneoEntidad.setNombreTorneo(torneoDto.getNombreTorneo());
			torneoEntidad.setFechaInicioTorneo(torneoDto.getFechaInicioTorneo());
			torneoEntidad.setFechaFinTorneo(torneoDto.getFechaFinTorneo());
			torneoEntidad.setDescripcionTorneo(torneoDto.getDescripcionTorneo());
			torneoEntidad.setClubesInscritos(torneoDto.getClubesInscritos());
			torneoEntidad.setModalidad(torneoDto.getModalidad());
			torneoEntidad.setEstaActivo(torneoDto.isEstaActivo());

			// Actualizar la instalación si es necesario
			Optional<InstalacionEntidad> instalacionOpt = instalacionInterfaz.findById(torneoDto.getInstalacionId());
			instalacionOpt.ifPresent(torneoEntidad::setInstalacion);

			torneoInterfaz.save(torneoEntidad);
			return true;
		} else {
			// System.out.println("El ID proporcionado no existe");
			return false;
		}
	}

	/**
	 * Borra un torneo por su ID.
	 * 
	 * @param idTorneoString el ID del torneo como cadena
	 * @return true si el torneo fue borrado, false si el torneo no existe
	 */
	public boolean borrarTorneo(String idTorneoString) {
		Long idTorneo = Long.parseLong(idTorneoString);
		Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(idTorneo);

		if (torneoOpt.isPresent()) {
			torneoInterfaz.delete(torneoOpt.get());
			// System.out.println("El torneo ha sido borrado con éxito");
			return true;
		} else {
			// System.out.println("El id del Torneo no existe");
			return false;
		}
	}
}
