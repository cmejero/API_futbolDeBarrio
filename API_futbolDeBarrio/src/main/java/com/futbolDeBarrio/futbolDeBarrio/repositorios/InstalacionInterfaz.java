package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad InstalacionDto.
 */
public interface InstalacionInterfaz extends JpaRepository<InstalacionDto, Long> {
	
	
	/**
	 * metodo que busca una instalacion dado su id.
	 * @param idInstalacion
	 * @return
	 */
	InstalacionDto findByIdInstalacion(Long idInstalacion);
	
	/**
	 * metodo que elimina una instalacion por su id.
	 * @param idInstalacion
	 * @return
	 */
	void deleteByIdInstalacion(Long idInstalacion);

}
