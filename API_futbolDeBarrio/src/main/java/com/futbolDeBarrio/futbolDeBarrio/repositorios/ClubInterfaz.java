package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * ClubDto.
 */
@Repository
public interface ClubInterfaz extends JpaRepository<ClubEntidad, Long> {

	/**
	 * metodo que busca un club por su id.
	 * 
	 * @param idClub
	 * @return
	 */
	ClubEntidad findByIdClub(long idClub);

	/**
	 * metodo que elimina un club dado su id.
	 * 
	 * @param idClub
	 * @return
	 */
	void deleteByIdClub(long idClub);
}
