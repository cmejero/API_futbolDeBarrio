package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * MiembroClubDto.
 */
public interface MiembroClubInterfaz extends JpaRepository<MiembroClubEntidad, Long> {

	/**
	 * metodo que busca un miembro de un club dado su id.
	 * 
	 * @param idMiembroClub
	 * @return
	 */
	MiembroClubEntidad findByIdMiembroClub(Long idMiembroClub);

	/**
	 * metodo que elimina una miembro de un club por su id.
	 * 
	 * @param idmiembroClub
	 * @return
	 */
	void deleteByIdMiembroClub(Long idmiembroClub);

	/**
	 * Metodo que busca el id de un club
	 * @param idClub
	 * @return
	 */
	List<MiembroClubEntidad> findByClub_IdClub(Long idClub);
	
	 Optional<MiembroClubEntidad> findByUsuario_IdUsuarioAndClub_IdClub(Long usuarioId, Long clubId);

}
