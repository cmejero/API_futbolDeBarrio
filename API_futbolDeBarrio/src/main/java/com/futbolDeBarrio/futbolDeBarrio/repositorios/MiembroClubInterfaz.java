package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad MiembroClubDto.
 */
public interface MiembroClubInterfaz extends JpaRepository<MiembroClubDto, Long> {

	/**
	 * metodo que busca un miembro de un club dado su id.
	 * @param idMiembroClub
	 * @return
	 */
	MiembroClubDto findByIdMiembroClub(Long idMiembroClub);
	
	/**
	 * metodo que elimina una miembro de un club por su id.
	 * @param idmiembroClub
	 * @return
	 */
	void deleteByIdMiembroClub(Long idmiembroClub);
}
