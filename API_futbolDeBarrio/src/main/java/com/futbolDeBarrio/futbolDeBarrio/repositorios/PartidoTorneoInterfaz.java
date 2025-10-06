package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.PartidoTorneoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la
 * entidad PartidoTorneoEntidad. Extiende JpaRepository para facilitar el
 * acceso.
 */
public interface PartidoTorneoInterfaz extends JpaRepository<PartidoTorneoEntidad, Long> {

	/**
	 * Busca un partido torneo por su identificador único.
	 *
	 * @param idPartidoTorneo ID del partido.
	 * @return Entidad {@link PartidoTorneoEntidad} correspondiente si existe.
	 */
	PartidoTorneoEntidad findByIdPartidoTorneo(long idPartidoTorneo);

	/**
	 * Busca todos los partidos de un torneo específico por su ID.
	 *
	 * @param idTorneo ID del torneo.
	 * @return Lista de partidos correspondientes al torneo.
	 */
	List<PartidoTorneoEntidad> findByTorneo_IdTorneo(Long idTorneo);

	/**
	 * Busca un partido específico dentro de un torneo por ronda y por los equipos que participan.
	 *
	 * @param idTorneo       ID del torneo al que pertenece el partido.
	 * @param ronda          Nombre de la ronda (por ejemplo: "octavos", "cuartos", "semifinal", "final").
	 * @param idEquipoLocal  ID del equipo que actúa como local.
	 * @param idEquipoVisitante ID del equipo que actúa como visitante.
	 * @return Un {@link Optional} que contiene la entidad del partido si existe, o vacío si no se encontró.
	 */
	Optional<PartidoTorneoEntidad> findByTorneo_IdTorneoAndRondaAndEquipoLocal_IdEquipoTorneoAndEquipoVisitante_IdEquipoTorneo(
			Long idTorneo, String ronda, Long idEquipoLocal, Long idEquipoVisitante);
}
