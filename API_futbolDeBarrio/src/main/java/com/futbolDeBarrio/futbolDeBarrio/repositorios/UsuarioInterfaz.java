package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * TorneoDto.
 */
public interface UsuarioInterfaz extends JpaRepository<UsuarioDto, Long> {

	/**
	 * metodo que busca un usuario dado su id.
	 * 
	 * @param idUsuario
	 * @return
	 */
	UsuarioDto findByIdUsuario(Long idUsuario);
	
	/**
	 * metodo que elimina un usuario dado su id.
	 * 
	 * @param idTorneo
	 * @return
	 */
	void deleteByIdUsuario(Long isUsuario);
}
