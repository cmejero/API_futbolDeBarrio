package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * ClubDto.
 */
@Repository
public interface ClubInterfaz extends JpaRepository<ClubDto, Long> {

	/**
	 * metodo que busca un club por su id.
	 * 
	 * @param idClub
	 * @return
	 */
	ClubDto findByIdClub(long idClub);

	/**
	 * metodo que elimina un club dado su id.
	 * 
	 * @param idClub
	 * @return
	 */
	void deleteByIdClub(long idClub);
}
